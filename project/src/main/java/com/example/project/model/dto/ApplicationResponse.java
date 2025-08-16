package com.example.project.model.dto;

import com.example.project.model.entity.Project;
import com.example.project.model.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

public record ApplicationResponse(
        @NotBlank
        String cover,
        @NotBlank
        String letter,
        @NotBlank
        String userEmail
) {
}
