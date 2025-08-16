package com.example.project.model.dto;

import jakarta.validation.constraints.NotBlank;


public record EmployerProfileRequest(
        @NotBlank
        String name,
        @NotBlank
        String contact,
        @NotBlank
        String bio
) {
}
