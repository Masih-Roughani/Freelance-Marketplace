package com.example.project.model.dto;

import com.example.project.model.enums.ProjectStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record ProjectCreationRequest(
        @NotBlank
        String title,
        @NotBlank
        String description,
        @NotNull
        List<SkillAddition> skills,
        @NotNull
        ProjectStatus status
) {
}
