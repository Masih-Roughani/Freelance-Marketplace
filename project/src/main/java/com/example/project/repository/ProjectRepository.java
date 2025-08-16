package com.example.project.repository;

import com.example.project.model.entity.Project;
import com.example.project.model.enums.ProjectStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProjectRepository extends JpaRepository<Project, UUID> {
    List<Project> findByStatus(ProjectStatus status);

    Optional<Project> findById(UUID uuid);
}
