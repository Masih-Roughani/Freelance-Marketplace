package com.example.project.controller;

import com.example.project.model.dto.LoginRequest;
import com.example.project.model.dto.RegisterRequest;
import com.example.project.model.entity.User;
import com.example.project.model.enums.Role;
import com.example.project.security.JwtService;
import com.example.project.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user/")
public class UserController {
    UserService userService;
    JwtService jwtService;

    public UserController(UserService userService, JwtService jwtService) {
        this.userService = userService;
        this.jwtService = jwtService;
    }

    @PostMapping("register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest request) {
        if (request.role() == Role.ADMIN) {
            return ResponseEntity.badRequest()
                    .body("Invalid role. Only FREELANCER and EMPLOYER are allowed for registration.");
        }

        if (userService.existsByEmail(request.email())) {
            return ResponseEntity.badRequest()
                    .body("User with this email already exists.");
        }

        userService.register(request);
        return ResponseEntity.accepted().body("User is created successfully.");
    }

    @PostMapping("login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request) {
        User user = userService.authenticate(request.email(), request.password());
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
//        this must be fixed
        String token = jwtService.generateToken(request);

        return ResponseEntity.ok(token);
    }
}
