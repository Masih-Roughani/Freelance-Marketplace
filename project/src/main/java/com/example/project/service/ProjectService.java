package com.example.project.service;

import com.example.project.model.dto.AuthUser;
import com.example.project.model.dto.ProjectCreationRequest;
import com.example.project.model.dto.ProjectResponse;
import com.example.project.model.entity.Project;
import com.example.project.model.enums.ProjectStatus;
import com.example.project.repository.ProjectRepository;
import com.example.project.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProjectService {
    private final UserRepository userRepository;
    private final UserService userService;
    ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository, UserRepository userRepository, UserService userService) {
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @Transactional
    public void createProject(ProjectCreationRequest request, Authentication authentication) {
        AuthUser authUser = (AuthUser) authentication.getPrincipal();
        UUID userId = authUser.id();

        Project project = new Project();
        project.setTitle(request.title());
        project.setDescription(request.description());
        project.setRequiredSkills(request.skills());
        project.setEmployer(userService.findUserById(userId));
        project.setFreelancer(null);

        projectRepository.save(project);
    }

    @Transactional
    public void assignProject(UUID projectId, Authentication authentication) {
        AuthUser authUser = (AuthUser) authentication.getPrincipal();
        Project project = projectRepository.findById(projectId).orElse(null);

        project.setFreelancer(userService.findUserById(authUser.id()));
        projectRepository.save(project);
    }

    @Transactional
    public List<ProjectResponse> getAllProjects() {
        return projectRepository.findByStatus(ProjectStatus.OPEN)
                .stream()
                .map(project -> new ProjectResponse(
                        project.getTitle(),
                        project.getDescription(),
                        project.getRequiredSkills(),
                        project.getStatus()))
                .collect(Collectors.toList());
    }

    public Object getProject(UUID projectId) {
        return projectRepository.findById(projectId).orElse(null);
    }
}
