package com.example.project.model.dto;

import com.example.project.model.enums.Role;
import jakarta.validation.constraints.*;
import org.springframework.security.access.annotation.Secured;

public record RegisterRequest(
        @NotBlank @Email String email,
        @NotBlank @Size(min = 8)
        @Pattern(
                regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$",
                message = "Password is not valid!"
        ) String password,
        @Secured({"ROLE_FREELANCER", "ROLE_EMPLOYER"}) Role role
) {
}