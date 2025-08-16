package com.example.project.model.entity;

import com.example.project.model.enums.ApplicationStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Application {
    @Id
    @UuidGenerator
    @Column(updatable = false)
    private UUID id;

    @Column(length = 300, nullable = false)
    private String cover;

    @Column(length = 1000, nullable = false)
    private String letter;

    @ManyToOne
    private Project project;

    @ManyToOne
    private User freelancer;

    @Enumerated(EnumType.STRING)
    private ApplicationStatus status = ApplicationStatus.PENDING;
}
