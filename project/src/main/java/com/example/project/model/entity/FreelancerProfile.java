package com.example.project.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FreelancerProfile {
    @Id
    @UuidGenerator
    @Column(updatable = false)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String contact;

    @Column(length = 100, nullable = false)
    private String bio;

    @OneToOne
    private User user;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Skill> requiredSkills;
}