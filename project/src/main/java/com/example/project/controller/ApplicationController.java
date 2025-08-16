package com.example.project.controller;

import com.example.project.model.dto.ApplicationRequest;
import com.example.project.service.ApplicationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("application/")
public class ApplicationController {
    private final ApplicationService applicationService;

    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @PostMapping("apply")
    @PreAuthorize("hasRole('FREELANCER')")
    public ResponseEntity<?> applyToProject(@RequestBody ApplicationRequest applicationRequest, Authentication authentication) {
        try {
            applicationService.applyToProject(applicationRequest, authentication);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.CREATED).body("Application is successfully sent");
    }

    @GetMapping("view-applications")
    @PreAuthorize("hasRole('EMPLOYER')")
    public ResponseEntity<?> viewApplications(@RequestParam UUID projectId, Authentication authentication) {
        try {
            return ResponseEntity.ok(applicationService.viewApplications(projectId, authentication));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
