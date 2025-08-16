package com.example.project.repository;

import com.example.project.model.entity.FreelancerProfile;
import com.example.project.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface FreelancerProfileRepository extends JpaRepository<FreelancerProfile, UUID> {
    Optional<FreelancerProfile> findByUserId(UUID userId);

    Optional<FreelancerProfile> findByUser(User user);
}
