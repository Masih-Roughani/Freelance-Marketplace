package com.example.project.repository;

import com.example.project.model.entity.EmployerProfile;
import com.example.project.model.entity.FreelancerProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface FreelancerProfileRepository extends JpaRepository<FreelancerProfile, UUID> {
    Optional<FreelancerProfile> findByUserId(UUID userId);
}
