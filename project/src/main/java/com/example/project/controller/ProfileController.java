package com.example.project.controller;

import com.example.project.model.dto.EmployerProfileRequest;
import com.example.project.model.dto.FreelancerProfileRequest;
import com.example.project.service.ProfileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("profile/create/")
public class ProfileController {
    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @PostMapping("employer")
    @PreAuthorize("hasRole('EMPLOYER')")
    public ResponseEntity<?> createEmployerProfile(@RequestBody EmployerProfileRequest employerProfileRequest, Authentication authentication) {
        try {
            profileService.createEmployerProfile(employerProfileRequest, authentication);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body("Profile created successfully");
    }

    @PostMapping("freelancer")
    @PreAuthorize("hasRole('FREELANCER')")
    public ResponseEntity<?> createFreelancerProfile(@RequestBody FreelancerProfileRequest freelancerProfileRequest, Authentication authentication) {
        try {
            profileService.createFreelancerProfile(freelancerProfileRequest, authentication);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body("Profile created successfully");
    }
}
