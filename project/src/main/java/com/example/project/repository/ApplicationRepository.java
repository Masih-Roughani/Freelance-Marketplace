package com.example.project.repository;

import com.example.project.model.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, UUID> {
    List<Application> findByProjectId(UUID projectId);

    Optional<Application> findByProjectIdAndFreelancerId(UUID projectId, UUID id);
}
