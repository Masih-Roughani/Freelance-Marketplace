package com.example.project.service;

import com.example.project.model.dto.AuthUser;
import com.example.project.model.dto.EmployerProfileRequest;
import com.example.project.model.dto.FreelancerProfileRequest;
import com.example.project.model.entity.EmployerProfile;
import com.example.project.model.entity.FreelancerProfile;
import com.example.project.model.entity.Skill;
import com.example.project.model.entity.User;
import com.example.project.repository.EmployerProfileRepository;
import com.example.project.repository.FreelancerProfileRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfileService {
    private final FreelancerProfileRepository freelancerProfileRepository;
    private final EmployerProfileRepository employerProfileRepository;
    private final UserService userService;


    public ProfileService(EmployerProfileRepository employerProfileRepository, UserService userService, FreelancerProfileRepository freelancerProfileRepository) {
        this.employerProfileRepository = employerProfileRepository;
        this.freelancerProfileRepository = freelancerProfileRepository;
        this.userService = userService;
    }

    @Transactional
    public void createEmployerProfile(EmployerProfileRequest employerProfileRequest, Authentication authentication) {
        AuthUser authUser = (AuthUser) authentication.getPrincipal();
        Optional<EmployerProfile> profile = employerProfileRepository.findByUserId(authUser.id());
        if (profile.isPresent()) {
            throw new IllegalStateException("Profile already exists for this user");

        }

        User user = userService.findUserById(authUser.id());
        EmployerProfile newProfile = new EmployerProfile();
        newProfile.setContact(employerProfileRequest.contact());
        newProfile.setCompanyName(employerProfileRequest.companyName());
        newProfile.setUser(user);

        employerProfileRepository.save(newProfile);
    }

    public void createFreelancerProfile(FreelancerProfileRequest freelancerProfileRequest, Authentication authentication) {
        AuthUser authUser = (AuthUser) authentication.getPrincipal();
        Optional<FreelancerProfile> profile = freelancerProfileRepository.findByUserId(authUser.id());
        if (profile.isPresent()) {
            throw new IllegalStateException("Profile already exists for this user");
        }

        User user = userService.findUserById(authUser.id());
        FreelancerProfile newProfile = new FreelancerProfile();
        newProfile.setContact(freelancerProfileRequest.contact());
        newProfile.setBio(freelancerProfileRequest.bio());
        newProfile.setName(freelancerProfileRequest.name());
        newProfile.setUser(user);

        freelancerProfileRepository.save(newProfile);
    }

    public Optional<FreelancerProfile> findProfileByUser(User user) {
        return freelancerProfileRepository.findByUser(user);
    }

    public FreelancerProfile saveProfile(FreelancerProfile profile) {
        return freelancerProfileRepository.save(profile);
    }
}

