package com.example.project.service;

import com.example.project.model.dto.AuthUser;
import com.example.project.model.dto.SkillAddition;
import com.example.project.model.entity.FreelancerProfile;
import com.example.project.model.entity.Skill;
import com.example.project.model.entity.User;
import com.example.project.repository.SkillRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotBlank;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class SkillService {
    private final SkillRepository skillRepository;
    private final ProfileService profileService;
    private final UserService userService;

    public SkillService(SkillRepository skillRepository, ProfileService profileService, UserService userService) {
        this.profileService = profileService;
        this.skillRepository = skillRepository;
        this.userService = userService;
    }

    public void add(String skill) {
        Skill skillEntity = new Skill();
        skillEntity.setName(skill);
        skillRepository.save(skillEntity);
    }

    @Transactional
    public void addSkillToProfile(SkillAddition skillAddition, Authentication authentication) {
        AuthUser authUser = (AuthUser) authentication.getPrincipal();
        User user = userService.findUserById(authUser.id());
        if (user == null) {
            throw new IllegalArgumentException("User not found");
        }

        FreelancerProfile freelancerProfile = profileService.findProfileByUser(user)
                .orElseThrow(() -> new IllegalArgumentException("Freelancer profile not found for user"));

        Skill skill = skillRepository.findById(skillAddition.id())
                .orElseThrow(() -> new IllegalArgumentException("Skill not found"));

        if (!freelancerProfile.getRequiredSkills().contains(skill)) {
            freelancerProfile.getRequiredSkills().add(skill);
            profileService.saveProfile(freelancerProfile);
        }
    }

    public Optional<Skill> findSkillById(UUID id) {
        return skillRepository.findById(id);
    }
}
