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
public class EmployerProfile {
    @Id
    @UuidGenerator
    @Column(updatable = false)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String contact;

    @Column(length = 1000)
    private String bio;

    @OneToOne
    private User user;
}
