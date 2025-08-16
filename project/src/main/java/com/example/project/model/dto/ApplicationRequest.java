package com.example.project.model.dto;

import com.example.project.model.entity.Project;
import com.example.project.model.entity.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record ApplicationRequest(
        @NotBlank UUID projectId,
        @NotBlank String cover,
        @NotBlank String letter
) {
}
