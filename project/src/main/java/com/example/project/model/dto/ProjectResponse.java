package com.example.project.model.dto;

import com.example.project.model.entity.Skill;
import com.example.project.model.enums.ProjectStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record ProjectResponse(
        @NotBlank
        String title,
        @NotBlank
        String description,
        @NotNull
        List<Skill> requiredSkills,
        @NotNull
        ProjectStatus status
) {
}
