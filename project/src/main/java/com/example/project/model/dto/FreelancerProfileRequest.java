package com.example.project.model.dto;

import com.example.project.model.entity.Skill;
import com.example.project.model.entity.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;
import java.util.UUID;

public record FreelancerProfileRequest(
        @NotBlank
        String name,
        @NotBlank
        String contact,
        @NotBlank
        String bio
) {
}
