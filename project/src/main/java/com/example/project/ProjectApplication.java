package com.example.project;

import com.example.project.model.entity.User;
import com.example.project.model.enums.Role;
import com.example.project.repository.UserRepository;
import com.example.project.security.JwtService;
import com.example.project.service.UserService;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class ProjectApplication {

    @Autowired
    UserRepository userRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(ProjectApplication.class, args);
    }

//    @Override
//    @Transactional
//    public void run(String... args) throws Exception {
//        User user = new User();
//        user.setPassword(passwordEncoder.encode("mmmMMM2005"));
//        user.setRole(Role.ADMIN);
//        user.setEmail("masihroughani@gmail.com");
//        userRepository.save(user);
//    }
}
