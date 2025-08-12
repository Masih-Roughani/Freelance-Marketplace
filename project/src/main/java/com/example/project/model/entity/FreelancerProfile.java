package com.example.project.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FreelancerProfile {
    @Id
    @UuidGenerator
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String contact;

    @Column(length = 1000)
    private String bio;

    @OneToOne
    private Freelancer user;
}