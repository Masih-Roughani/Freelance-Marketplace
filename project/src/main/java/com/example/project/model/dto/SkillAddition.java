package com.example.project.model.dto;

import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record SkillAddition(
        @NotBlank UUID id
) {
}
