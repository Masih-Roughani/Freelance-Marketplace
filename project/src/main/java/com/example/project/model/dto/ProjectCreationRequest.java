package com.example.project.model.dto;

import com.example.project.model.enums.Skill;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record ProjectCreationRequest(
        @NotBlank
        String title,
        @NotBlank
        String description,
        List<Skill> skills
) {
}
