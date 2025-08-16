package com.example.project.controller;

import com.example.project.model.dto.ProjectCreationRequest;
import com.example.project.service.ProjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("project/")
public class ProjectController {
    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping("create")
    @PreAuthorize("hasRole('EMPLOYER')")
    public ResponseEntity<?> createProject(@RequestBody ProjectCreationRequest projectCreationRequest, Authentication authentication) {
        projectService.createProject(projectCreationRequest, authentication);
        return ResponseEntity.status(HttpStatus.CREATED).body("Created project successfully");
    }

    @GetMapping("projects")
    @PreAuthorize("hasAnyRole('FREELANCER','EMPLOYER')")
    public ResponseEntity<?> getAllProjects(Authentication authentication) {
        return ResponseEntity.status(HttpStatus.OK).body(projectService.getAllProjects());
    }

    @GetMapping("details")
    @PreAuthorize("hasAnyRole('FREELANCER','EMPLOYER')")
    public ResponseEntity<?> getProject(@RequestParam UUID projectId, Authentication authentication) {

        return ResponseEntity.status(HttpStatus.OK).body(projectService.getProject(projectId));
    }
}
