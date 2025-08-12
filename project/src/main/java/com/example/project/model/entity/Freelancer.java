package com.example.project.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Freelancer {
    @Id
    @UuidGenerator
    @Column(updatable = false)
    private Long id;

    private String email;

    private String password;
}
