package com.example.project.controller;

import com.example.project.model.dto.SkillAddition;
import com.example.project.service.SkillService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("skill/")
public class SkillController {
    SkillService skillService;

    public SkillController(SkillService skillService) {
        this.skillService = skillService;
    }

    @PostMapping("add")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> addSkill(String skill, Authentication authentication) {
        try {
            skillService.add(skill);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body("Skill added successfully");
    }

    @PostMapping("add-to-profile")
    @PreAuthorize("hasRole('FREELANCER')")
    public ResponseEntity<?> addSkillToProfile(SkillAddition skillAddition, Authentication authentication) {
        try {
            skillService.addSkillToProfile(skillAddition, authentication);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.CREATED).body("Skill added successfully");
    }
}
