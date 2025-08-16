package com.example.project.model.dto;

import com.example.project.model.entity.User;
import com.example.project.model.enums.Skill;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;
import java.util.UUID;

public record FreelancerProfileRequest(
        @NotBlank
        String companyName,
        @NotBlank
        String contact,
        @NotBlank
        List<Skill> skills
) {
}
