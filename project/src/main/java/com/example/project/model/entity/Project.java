package com.example.project.model.entity;

import com.example.project.model.enums.Skill;
import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;
import java.util.UUID;

public class Project {
    @Id
    @UuidGenerator
    @Column(updatable = false)
    private UUID id;

    @Column(nullable = false)
    private String Title;

    @Column(nullable = false)
    private String Description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private List<Skill> requiredSkills;

    @ManyToOne
    private User employer;

    @OneToOne
    private User freelancer;
}
