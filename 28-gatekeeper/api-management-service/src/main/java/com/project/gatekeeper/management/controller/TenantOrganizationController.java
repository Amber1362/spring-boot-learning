package com.project.gatekeeper.management.controller;

import com.project.gatekeeper.management.dto.RequestDto.TenantOrganizationRequest;
import com.project.gatekeeper.management.dto.ResponseDto.OrganizationResponse;
import com.project.gatekeeper.management.service.OrganizationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tenant/organization")
public class TenantOrganizationController {

    private final OrganizationService organizationService;

    public TenantOrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    //1. Get
    @GetMapping
    public ResponseEntity<OrganizationResponse> getOwnOrganization() {
        return ResponseEntity.ok(organizationService.getOwnOrganization());
    }

    //2. Update( name )
    @PutMapping
    public ResponseEntity<OrganizationResponse> updateOwnOrganization(
            @Valid @RequestBody TenantOrganizationRequest request
            ) {
        return ResponseEntity.ok(organizationService.updateOwnOrganization(request));
    }


}
