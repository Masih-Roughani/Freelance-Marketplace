package com.example.project.model.dto;

import com.example.project.model.enums.ProjectStatus;
import com.example.project.model.enums.Skill;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record ProjectCreationRequest(
        @NotBlank
        String title,
        @NotBlank
        String description,
        @NotNull
        List<Skill> skills,
        @NotNull
        ProjectStatus status
) {
}
