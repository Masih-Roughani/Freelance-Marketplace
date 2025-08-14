package com.example.project.model.entity;

import com.example.project.model.enums.Skill;
import jakarta.persistence.*;
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
    private String companyName;

    @Column(nullable = false)
    private String contact;

    @OneToOne
    private User user;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<Skill> skills;
}