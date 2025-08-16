package com.example.project.model.dto;

import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record ApplicationRequest(
        @NotBlank UUID projectId,
        @NotBlank String cover,
        @NotBlank String letter
) {
}
