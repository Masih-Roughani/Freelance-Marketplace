package com.example.project.model.dto;

import jakarta.validation.constraints.NotBlank;


public record ApplicationResponse(
        @NotBlank
        String cover,
        @NotBlank
        String letter,
        @NotBlank
        String userEmail
) {
}
