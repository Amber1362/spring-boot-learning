package com.project.gatekeeper.management.controller;

import com.project.gatekeeper.management.dto.RequestDto.OrganizationRequest;
import com.project.gatekeeper.management.dto.RequestDto.OrganizationStatusRequest;
import com.project.gatekeeper.management.dto.ResponseDto.OrganizationResponse;
import com.project.gatekeeper.management.entity.Organization;
import com.project.gatekeeper.management.service.OrganizationService;
import jakarta.validation.Valid;
import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityReturnValueHandler;

import java.util.List;

@RestController
@RequestMapping("/api/platform/organizations")
public class PlatformOrganizationController {

    private final OrganizationService organizationService;

    public PlatformOrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    //CRUD --> Organization and tenant admin

    //1. CREATE
    @PostMapping
    public ResponseEntity<OrganizationResponse> create(
            @Valid @RequestBody OrganizationRequest request
    ) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(organizationService.create(request));
    }

    //2. READ ALL
    @GetMapping
    public ResponseEntity<List<OrganizationResponse>> findAll() {
        return ResponseEntity.ok(organizationService.findAll());
    }

    //3. READ ONE
    @GetMapping("/{id}")
    public ResponseEntity<OrganizationResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(organizationService.findById(id));
    }

    //4. UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<OrganizationResponse> update(@PathVariable Long id,
                                                       @Valid @RequestBody
                                                       OrganizationRequest request) {
        return ResponseEntity.ok(organizationService.update(id, request));
    }

    //5. PATCH
    @PatchMapping("/{id}/status")
    public ResponseEntity<OrganizationResponse> changeStatus(@PathVariable Long id,
                                                             @Valid @RequestBody
                                                             OrganizationStatusRequest request) {

        return ResponseEntity.ok(organizationService.changeStatus(id, request));
    }

    //DELETE ( SOFT DELETE )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        organizationService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
