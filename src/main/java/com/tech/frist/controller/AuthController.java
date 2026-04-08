package com.tech.frist.controller;

import com.tech.frist.dto.request.*;
import com.tech.frist.dto.response.*;
import com.tech.frist.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService service;

    @PostMapping("/register")
    public ApiResponse<AuthResponse> register(
            @Valid @RequestBody RegisterRequest request) {

        return ApiResponse.<AuthResponse>builder()
                .success(true)
                .message("Registered successfully")
                .data(service.register(request))
                .build();
    }

    @PostMapping("/login")
    public ApiResponse<AuthResponse> login(
            @RequestBody LoginRequest request) {

        return ApiResponse.<AuthResponse>builder()
                .success(true)
                .message("Login successful")
                .data(service.login(request))
                .build();
    }
}
