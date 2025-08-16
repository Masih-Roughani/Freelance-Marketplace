package com.example.project.model.dto;

import jakarta.validation.constraints.NotBlank;


public record EmployerProfileRequest(
        @NotBlank
        String companyName,
        @NotBlank
        String contact
) {
}
