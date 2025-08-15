package com.example.project.service;

import com.example.project.model.dto.AuthUser;
import com.example.project.model.dto.ProjectCreationRequest;
import com.example.project.model.entity.Project;
import com.example.project.repository.ProjectRepository;
import com.example.project.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProjectService {
    private final UserRepository userRepository;
    ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository, UserRepository userRepository) {
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public void createProject(ProjectCreationRequest request, Authentication authentication) {
        AuthUser authUser = (AuthUser) authentication.getPrincipal();
        UUID userId = authUser.id();

        Project project = new Project();
        project.setTitle(request.title());
        project.setDescription(request.description());
        project.setRequiredSkills(request.skills());
        project.setEmployer(userRepository.findById(userId).orElse(null));
        project.setFreelancer(null);

        projectRepository.save(project);
    }
}
