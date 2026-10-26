package com.project.gatekeeper.management.service;

import com.project.gatekeeper.management.dto.RequestDto.OrganizationRequest;
import com.project.gatekeeper.management.dto.RequestDto.OrganizationStatusRequest;
import com.project.gatekeeper.management.dto.RequestDto.TenantAdminRequest;
import com.project.gatekeeper.management.dto.RequestDto.TenantOrganizationRequest;
import com.project.gatekeeper.management.dto.ResponseDto.OrganizationResponse;
import com.project.gatekeeper.management.dto.ResponseDto.TenantAdminResponse;
import com.project.gatekeeper.management.entity.ManagementUser;
import com.project.gatekeeper.management.entity.Organization;
import com.project.gatekeeper.management.entity.OrganizationStatus;
import com.project.gatekeeper.management.entity.Role;
import com.project.gatekeeper.management.exception.ApiException;
import com.project.gatekeeper.management.repository.ManagementUserRepository;
import com.project.gatekeeper.management.repository.OrganizationRepository;
import com.project.gatekeeper.management.repository.RoleRepository;
import com.sun.net.httpserver.HttpsConfigurator;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrganizationService {

    private final OrganizationRepository organizationRepository;
    private final ManagementUserRepository managementUserRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final TenantContext tenantContext;

    public OrganizationService(OrganizationRepository organizationRepository,
                               ManagementUserRepository managementUserRepository,
                               RoleRepository roleRepository,
                               PasswordEncoder passwordEncoder,
                               TenantContext tenantContext) {
        this.organizationRepository = organizationRepository;
        this.managementUserRepository = managementUserRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.tenantContext = tenantContext;
    }

    @Transactional
    public OrganizationResponse create(OrganizationRequest request) {
        long startedAt = System.nanoTime();

        checkUnique(request.getName(), request.getSlug(), null);

        Organization organization = new Organization();
        organization.setName(request.getName());
        organization.setSlug(request.getSlug());
        organization.setStatus(OrganizationStatus.PENDING);
        organization.setCreatedAt(LocalDateTime.now());
        organization.setUpdatedAt(LocalDateTime.now());

        organizationRepository.save(organization);

        return OrganizationResponse.from(organization);
    }

    @Transactional(readOnly = true)
    public List<OrganizationResponse> findAll() {

        List<OrganizationResponse> response = new ArrayList<>();

        for (Organization organization :
                  organizationRepository.findAllByStatusNot(OrganizationStatus.DELETE)) {
            response.add(OrganizationResponse.from(organization));
        }
        return response;
    }

    @Transactional(readOnly = true)
    public OrganizationResponse findById(Long id) {

        return OrganizationResponse.from(getOrganization(id));
    }

    @Transactional
    public OrganizationResponse update(Long id, OrganizationRequest request) {
        long startedAt = System.nanoTime();

        Organization organization = getOrganization(id);

        checkUnique(request.getName(), request.getSlug(), id);
        organization.setName(request.getName());
        organization.setSlug(request.getSlug());
        organization.setUpdatedAt(LocalDateTime.now());

        return OrganizationResponse.from(organization);
    }

    @Transactional
    public OrganizationResponse changeStatus(Long id, OrganizationStatusRequest request) {

        Long startedAt = System.nanoTime();
        Organization organization = getOrganization(id);

        if(request.getStatus() == OrganizationStatus.DELETE) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "Use DELETE to soft-delete an organization");
        }

        organization.setStatus(request.getStatus());
        organization.setUpdatedAt(LocalDateTime.now());

        return OrganizationResponse.from(organization);
    }

    @Transactional
    public void delete(Long id) {
        Long startedAt = System.nanoTime();

        Organization organization = getOrganization(id);
        organization.setStatus(OrganizationStatus.DELETE);
        organization.setDeletedAt((LocalDateTime.now()));
        organization.setUpdatedAt(LocalDateTime.now());
    }

    private Organization getOrganization(Long id) {
        return organizationRepository.findByIdAndStatusNot(id, OrganizationStatus.DELETE)
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "Organization not found"));
    }

    private void checkUnique(String name, String slug, Long currentId) {
        if(organizationRepository.existsByNameIgnoreCase(name)) {
            Organization current = currentId == null ?
                    null : organizationRepository.findById(currentId).orElse(null);

            if(current == null || !current.getName().equalsIgnoreCase(name)) {
                throw new ApiException(HttpStatus.CONFLICT, "Organization name already exists");
            }
        }

        if(organizationRepository.existsBySlugIgnoreCase(slug)) {
            Organization current = currentId == null ?
                    null : organizationRepository.findById(currentId).orElse(null);

            if(current == null || !current.getSlug().equalsIgnoreCase(slug)) {
                throw new ApiException(HttpStatus.CONFLICT, "Organization slug already exists");
            }
        }
    }

    // Tenant Admin CRUD

    @Transactional
    public TenantAdminResponse createTenantAdmin(Long organizationId, TenantAdminRequest request) {
        long startedAt = System.nanoTime();
        Organization organization = getOrganization(organizationId);

        if(managementUserRepository.findByOrganizationId(organizationId).isPresent()) {
            throw new ApiException(HttpStatus.CONFLICT,
                    "This organization already has a tenant administrator");
        }

        if(managementUserRepository.existsByUsername(request.getUsername())) {
            throw new ApiException(HttpStatus.CONFLICT, "Username already exists");
        }

        Role role = roleRepository.findByName("TENANT_ADMIN")
                .orElseThrow(() -> new ApiException(HttpStatus.INTERNAL_SERVER_ERROR,
                        "TENANT_ADMIN role is missing"));

        ManagementUser user = new ManagementUser();
        user.setOrganization(organization);
        user.setRole(role);
        user.setUsername(request.getUsername());
        user.setPasswordHash(passwordEncoder.encode(request.getPassword()));
        user.setEnabled(request.isEnabled());
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        managementUserRepository.save(user);

        return TenantAdminResponse.from(user);
    }

    @Transactional(readOnly = true)
    public TenantAdminResponse getTenantAdmin(Long organizationId) {
        return TenantAdminResponse.from(getTenantAdminEntity(organizationId));
    }

    @Transactional
    public TenantAdminResponse updateTenantAdmin(Long organizationId, TenantAdminRequest request) {
        long startedAt = System.nanoTime();
        ManagementUser user = getTenantAdminEntity(organizationId);

        if(!user.getUsername().equals(request.getUsername())
                && managementUserRepository.existsByUsername(request.getUsername())) {
            throw new ApiException(HttpStatus.CONFLICT, "Username already exists");
        }

        user.setUsername(request.getUsername());
        user.setPasswordHash(passwordEncoder.encode(request.getPassword()));
        user.setEnabled(request.isEnabled());
        user.setUpdatedAt(LocalDateTime.now());

        return TenantAdminResponse.from(user);
    }

    @Transactional
    public void deleteTenantAdmin(Long organizationId) {
        long startedAt = System.nanoTime();

        ManagementUser user = getTenantAdminEntity(organizationId);

        managementUserRepository.delete(user);
    }

    private ManagementUser getTenantAdminEntity(Long organizationId) {
        getOrganization(organizationId);
        return managementUserRepository.findByOrganizationId(organizationId)
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "Tenant administrator not found"));
    }

    //Methods for Tenant Admin
    @Transactional(readOnly = true)
    public OrganizationResponse getOwnOrganization() {
        return OrganizationResponse.from(tenantContext.getOrganization());
    }

    @Transactional
    public OrganizationResponse updateOwnOrganization(TenantOrganizationRequest request) {
        long startedAt = System.nanoTime();

        Organization organization = tenantContext.getOrganization();

        if(!organization.getName().equalsIgnoreCase(request.getName())
              && organizationRepository.existsByNameIgnoreCase(request.getName())) {
            throw new ApiException(HttpStatus.CONFLICT, "Organization name already exist");
        }

        organization.setName(request.getName());
        organization.setUpdatedAt(LocalDateTime.now());

        return OrganizationResponse.from(organization);
    }

}
