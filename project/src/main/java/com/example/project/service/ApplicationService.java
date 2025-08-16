package com.example.project.service;

import com.example.project.model.dto.ApplicationRequest;
import com.example.project.model.dto.ApplicationResponse;
import com.example.project.model.dto.AuthUser;
import com.example.project.model.entity.Application;
import com.example.project.model.entity.Project;
import com.example.project.model.enums.ProjectStatus;
import com.example.project.repository.ApplicationRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ApplicationService {
    private final ApplicationRepository applicationRepository;
    private final ProjectService projectService;
    private final UserService userService;

    public ApplicationService(ApplicationRepository applicationRepository, ProjectService projectService, UserService userService) {
        this.applicationRepository = applicationRepository;
        this.projectService = projectService;
        this.userService = userService;
    }

    @Transactional
    public void applyToProject(ApplicationRequest applicationRequest, Authentication authentication) {
        AuthUser authUser = (AuthUser) authentication.getPrincipal();
        Project project = projectService.getProject(applicationRequest.projectId());

        if (project == null) {
            throw new IllegalArgumentException("Project not found");
        }

        if (project.getStatus() != ProjectStatus.OPEN) {
            throw new IllegalArgumentException("Applications can only be submitted to open projects");
        }

        Optional<Application> existingApplication = applicationRepository
                .findByProjectIdAndFreelancerId(applicationRequest.projectId(), authUser.id());

        if (existingApplication.isPresent()) {
            throw new IllegalArgumentException("You have already applied to this project");
        }

        Application application = new Application();
        application.setProject(project);
        application.setFreelancer(userService.findUserById(authUser.id()));
        application.setLetter(applicationRequest.letter());
        application.setCover(applicationRequest.cover());

        applicationRepository.save(application);
    }

    @Transactional
    public List<ApplicationResponse> viewApplications(UUID projectId, Authentication authentication) {
        AuthUser authUser = (AuthUser) authentication.getPrincipal();
        Project project = projectService.getProject(projectId);
        if (project == null) {
            throw new IllegalArgumentException("Project not found");
        }

        if (!project.getEmployer().getId().equals(authUser.id())) {
            throw new IllegalArgumentException("You do not have access to this project");
        }
        return applicationRepository.findByProjectId(projectId)
                .stream()
                .map(application -> new ApplicationResponse(
                        application.getCover(),
                        application.getLetter(),
                        application.getFreelancer().getEmail()))
                .collect(Collectors.toList());
    }
}
