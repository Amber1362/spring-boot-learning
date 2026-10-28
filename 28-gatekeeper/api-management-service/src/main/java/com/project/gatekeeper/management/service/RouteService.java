package com.project.gatekeeper.management.service;

import com.project.gatekeeper.management.dto.RequestDto.HeaderRuleRequest;
import com.project.gatekeeper.management.dto.RequestDto.RouteRequest;
import com.project.gatekeeper.management.dto.RequestDto.RouteStatusRequest;
import com.project.gatekeeper.management.dto.ResponseDto.RouteResponse;
import com.project.gatekeeper.management.entity.ApiRoute;
import com.project.gatekeeper.management.entity.Organization;
import com.project.gatekeeper.management.entity.OrganizationStatus;
import com.project.gatekeeper.management.entity.RouteHeaderRule;
import com.project.gatekeeper.management.exception.ApiException;
import com.project.gatekeeper.management.repository.ApiRouteRepository;
import org.springframework.cglib.core.Local;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tools.jackson.databind.ObjectMapper;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class RouteService {

    private final ApiRouteRepository apiRouteRepository;
    private final TenantContext tenantContext;
    private final ObjectMapper objectMapper;

    public RouteService(ApiRouteRepository apiRouteRepository,
                        TenantContext tenantContext,
                        ObjectMapper objectMapper) {
        this.apiRouteRepository = apiRouteRepository;
        this.tenantContext = tenantContext;
        this.objectMapper = objectMapper;
    }

    @Transactional
    public RouteResponse create(RouteRequest request) {

        Organization organization = tenantContext.getOrganization();
        validate(request, organization);

        if(apiRouteRepository.existsByOrganizationIdAndRouteId(
                organization.getId(), request.getRouteId()
        )) {
            throw new ApiException(HttpStatus.CONFLICT,
                    "Route Id is already exists for this organization");
        }

        ApiRoute route = new ApiRoute();
        route.setOrganization(organization);
        route.setConfigurationVersion(1L);
        route.setCreatedAt(LocalDateTime.now());
        apply(route, request);
        apiRouteRepository.save(route);

        return RouteResponse.from(route);
    }

    @Transactional(readOnly = true)
    public List<RouteResponse> findAllForTenant() {
        Organization organization = tenantContext.getOrganization();
        return toResponses(apiRouteRepository.findAllByOrganizationId(organization.getId()));
    }

    @Transactional(readOnly = true)
    public RouteResponse findById(Long id) {
        Organization organization = tenantContext.getOrganization();
        return RouteResponse.from(getOwnedRoute(id, organization.getId()));
    }

    @Transactional
    public RouteResponse update(Long id, RouteRequest request) {
        long startedAt = System.nanoTime();

        Organization organization = tenantContext.getOrganization();
        ApiRoute route = getOwnedRoute(id, organization.getId());

        if(!route.getRouteId().equals(request.getRouteId())) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "routeId cannot be changed");
        }

        validate(request, organization);

        route.setConfigurationVersion(route.getConfigurationVersion() + 1);

        apply(route, request);

        return RouteResponse.from(route);
    }

    @Transactional
    public RouteResponse changeStatus(Long id, RouteStatusRequest request) {
        long startedAt = System.nanoTime();

        Organization organization = tenantContext.getOrganization();
        ApiRoute route = getOwnedRoute(id, organization.getId());
        route.setActive(request.getActive());
        route.setConfigurationVersion(route.getConfigurationVersion() + 1);
        route.setUpdatedAt(LocalDateTime.now());

        return RouteResponse.from(route);
    }

    @Transactional
    public void delete(Long id) {
        long startedAt = System.nanoTime();

        Organization organization = tenantContext.getOrganization();
        ApiRoute route = getOwnedRoute(id, organization.getId());
        route.setConfigurationVersion(route.getConfigurationVersion() + 1);
        route.setUpdatedAt(LocalDateTime.now());

        apiRouteRepository.delete(route);
    }

    private void validate(RouteRequest request, Organization organization) {
        String tenantPrefix = "/" + organization.getSlug() + "/";

        if(!request.getPathPattern().startsWith(tenantPrefix)) {
            throw new ApiException(HttpStatus.BAD_REQUEST,
                    "pathPattern must start with the tenant prefix " + tenantPrefix);
        }

        if(!request.getTargetPathPrefix().startsWith("/")) {
            throw new ApiException(HttpStatus.BAD_REQUEST,
                    "targetPathPrefix must start with /");
        }

        try {
            URI uri = URI.create(request.getTargetBaseUrl());
            if(!"http".equalsIgnoreCase(uri.getScheme()) && !"https".equalsIgnoreCase(uri.getScheme())) {
                throw new IllegalArgumentException();
            }
        }
        catch (IllegalArgumentException exception) {
            throw new ApiException(HttpStatus.BAD_REQUEST,
                    "targetBaseUrl must be a valid HTTP or HTTPS URL");
        }

        normalizeMethods(request.getAllowedMethods());

        for(HeaderRuleRequest rule : request.getHeaderRules()) {
            String direction = rule.getDirection().toUpperCase(Locale.ENGLISH);
            String action = rule.getAction().toUpperCase(Locale.ENGLISH);
            if(!"REQUEST".equals(direction) && !"RESPONSE".equals(direction)) {
                throw new ApiException(HttpStatus.BAD_REQUEST,
                        "Header direction must be REQUEST or RESPONSE");
            }
            if(!"ADD".equals(action) && !"REMOVE".equals(action)) {
                throw new ApiException(HttpStatus.BAD_REQUEST,
                        "header action must be ADD or REMOVE");
            }
            if("ADD".equals(action) &&
                    (rule.getHeaderValue() == null || rule.getHeaderValue().trim().isEmpty())) {
                throw new ApiException(HttpStatus.BAD_REQUEST,
                        "ADD header rules require headerValue");
            }
        }
    }

    private ApiRoute getOwnedRoute(Long id, Long organizationId) {
        return apiRouteRepository.findByIdAndOrganizationId(id, organizationId)
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "Route not found"));
    }

    private Set<String> normalizeMethods(Set<String> methods) {
        Set<String> normalized = new HashSet<>();
        for(String method : methods) {
            String value = method.toUpperCase(Locale.ENGLISH);
            try {
                HttpMethod.valueOf(value);
            }
            catch (IllegalArgumentException exception) {
                throw new ApiException(HttpStatus.BAD_REQUEST, "Unsupported HTTP method: " + method);
            }
            normalized.add(value);
        }
        return normalized;
    }

    private void apply(ApiRoute route, RouteRequest request) {
        route.setRouteId(request.getRouteId());
        route.setName(request.getName());
        route.setPathPattern(request.getPathPattern());
        route.setTargetBaseUrl(request.getTargetBaseUrl());
        route.setTargetPathPrefix(request.getTargetPathPrefix());
        route.setRequestsPerMinute(request.getRequestsPerMinute());
        route.setResponseTimeoutMs(request.getResponseTimeoutMs());
        route.setIdempotencyEnabled(request.isIdempotencyEnabled());
        route.setActive(request.isActive());
        route.setUpdatedAt(LocalDateTime.now());
        route.setAllowedMethods(normalizeMethods(request.getAllowedMethods()));

        route.getHeaderRules().clear();
        for(HeaderRuleRequest ruleRequest : request.getHeaderRules()) {
            RouteHeaderRule rule = new RouteHeaderRule();
            rule.setRoute(route);
            rule.setDirection(ruleRequest.getDirection().toUpperCase(Locale.ENGLISH));
            rule.setAction(ruleRequest.getAction().toUpperCase(Locale.ENGLISH));
            rule.setHeaderName(ruleRequest.getHeaderName());
            rule.setHeaderValue(ruleRequest.getHeaderValue());
            rule.setCreatedAt(LocalDateTime.now());
            rule.setUpdatedAt(LocalDateTime.now());
            route.getHeaderRules().add(rule);
        }
    }

    private List<RouteResponse> toResponses(List<ApiRoute> routes) {
        List<RouteResponse> response = new ArrayList<RouteResponse>();
        for(ApiRoute route : routes) {
            response.add(RouteResponse.from(route));
        }
        return response;
    }

    //GATEWAY METHODS
    @Transactional(readOnly = true)
    public List<RouteResponse> findAllActiveForGateway() {
        return toResponses(apiRouteRepository
                .findAllByActiveTrueAndOrganizationStatus(
                        OrganizationStatus.ACTIVE));
    }
}
