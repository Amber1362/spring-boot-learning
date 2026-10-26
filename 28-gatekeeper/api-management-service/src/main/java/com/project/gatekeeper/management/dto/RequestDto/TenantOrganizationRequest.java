package com.project.gatekeeper.management.dto.RequestDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TenantOrganizationRequest {

    @NotBlank
    @Size(max = 100)
    private String name;
}
