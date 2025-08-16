package com.example.project.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
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
    String cover;

    @Column(length = 1000, nullable = false)
    String letter;

    @OneToOne
    private Project project;

    @OneToOne
    private User freelancer;
}
