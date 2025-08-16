package com.example.project.service;

import com.example.project.model.dto.AuthUser;
import com.example.project.model.dto.ProjectCreationRequest;
import com.example.project.model.dto.ProjectResponse;
import com.example.project.model.entity.Project;
import com.example.project.model.entity.Skill;
import com.example.project.model.enums.ProjectStatus;
import com.example.project.repository.ProjectRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProjectService {
    private final UserService userService;
    private final ProjectRepository projectRepository;
    private final SkillService skillService;

    public ProjectService(ProjectRepository projectRepository, UserService userService, SkillService skillService) {
        this.projectRepository = projectRepository;
        this.userService = userService;
        this.skillService = skillService;
    }

    @Transactional
    public void createProject(ProjectCreationRequest request, Authentication authentication) {
        AuthUser authUser = (AuthUser) authentication.getPrincipal();
        UUID userId = authUser.id();

        Project project = new Project();
        project.setTitle(request.title());
        project.setDescription(request.description());

        List<Skill> requiredSkills = request.skills().stream()
                .map(skillAddition -> skillService.findSkillById(skillAddition.id())
                        .orElseThrow(() -> new IllegalArgumentException("Skill not found: " + skillAddition.id())))
                .toList();
        project.setRequiredSkills(requiredSkills);

        project.setEmployer(userService.findUserById(userId));
        project.setStatus(request.status());
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

    //    must be dto in controller
    public Project getProject(UUID projectId) {
        return projectRepository.findById(projectId).orElse(null);
    }
}
