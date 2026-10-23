package com.project.gatekeeper.management.dto.ResponseDto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public class LoginResponse {

    private String token;
    private String tokenType;
    private Long expiresInSeconds;
    private String role;
    private Long organizationId;
}
