package com.tech.frist.service.impl;

import com.tech.frist.dto.request.*;
import com.tech.frist.dto.response.*;
import com.tech.frist.entity.*;
import com.tech.frist.repository.EmployeeRepository;
import com.tech.frist.security.JwtUtil;
import com.tech.frist.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final EmployeeRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Override
    public AuthResponse register(RegisterRequest request) {

        if (repository.findByEmailAndDeletedFalse(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        Employee employee = Employee.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.ROLE_USER)
                .build();

        repository.save(employee);

        String token = jwtUtil.generateToken(employee.getEmail());

        return AuthResponse.builder()
                .accessToken(token)
                .refreshToken(null)
                .build();
    }

    @Override
    public AuthResponse login(LoginRequest request) {

        Employee employee = repository
                .findByEmailAndDeletedFalse(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(
                request.getPassword(),
                employee.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        String token = jwtUtil.generateToken(employee.getEmail());

        return AuthResponse.builder()
                .accessToken(token)
                .refreshToken(null)
                .build();
    }
}
