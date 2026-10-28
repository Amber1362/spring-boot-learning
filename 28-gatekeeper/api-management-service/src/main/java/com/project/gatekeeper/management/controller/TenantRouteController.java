package com.project.gatekeeper.management.controller;

import com.project.gatekeeper.management.dto.RequestDto.RouteRequest;
import com.project.gatekeeper.management.dto.RequestDto.RouteStatusRequest;
import com.project.gatekeeper.management.dto.ResponseDto.RouteResponse;
import com.project.gatekeeper.management.dto.ResponseDto.RouteVersionResponse;
import com.project.gatekeeper.management.service.RouteService;
import jakarta.validation.Valid;
import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.service.annotation.DeleteExchange;

import java.util.List;

@RestController
@RequestMapping("/api/tenant")
public class TenantRouteController {

    private final RouteService routeService;

    public TenantRouteController(RouteService routeService) {
        this.routeService = routeService;
    }

    //CRUD for Routes

    //1.Create
    @PostMapping("/routes")
    public ResponseEntity<RouteResponse> create(@Valid @RequestBody RouteRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(routeService.create(request));
    }

    //2. Get, Get All
    @GetMapping("/routes")
    public ResponseEntity<List<RouteResponse>> findAll() {
        return ResponseEntity.ok(routeService.findAllForTenant());
    }

    @GetMapping("/routes/{id}")
    public ResponseEntity<RouteResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(routeService.findById(id));
    }

    //3. Update
    @PutMapping("/routes/{id}")
    public ResponseEntity<RouteResponse> update(@PathVariable Long id,
                                                @Valid @RequestBody RouteRequest request) {
        return ResponseEntity.ok(routeService.update(id, request));
    }

    //4. Patch Request
    @PatchMapping("/routes/{id}/status")
    public ResponseEntity<RouteResponse> changeStatus(@PathVariable Long id,
                                                      @Valid @RequestBody RouteStatusRequest request) {
        return ResponseEntity.ok(routeService.changeStatus(id, request));
    }

    //5. Delete
    @DeleteMapping("routes/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        routeService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/route-version/{routeId}")
    public ResponseEntity<List<RouteVersionResponse>> getVersions(
            @PathVariable String routeId
    ) {
        return ResponseEntity.ok(routeService.getVersions(routeId));
    }
}
