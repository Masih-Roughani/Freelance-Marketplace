package com.example.project.model.dto;

import com.example.project.model.enums.Role;
import jakarta.validation.constraints.*;

public record RegisterRequest(
        @NotBlank @Email String email,
        @NotBlank @Size(min = 8)
        @Pattern(
                regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$",
                message = "Password is not strong enough!"
        ) String password,
        @NotNull Role role
) {
}