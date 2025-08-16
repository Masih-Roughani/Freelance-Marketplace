package com.example.project.model.dto;

import jakarta.validation.constraints.NotBlank;

public record FreelancerProfileRequest(
        @NotBlank
        String name,
        @NotBlank
        String contact,
        @NotBlank
        String bio
) {
}
