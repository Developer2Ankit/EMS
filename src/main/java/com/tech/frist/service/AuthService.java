package com.tech.frist.service;

import com.tech.frist.dto.request.LoginRequest;
import com.tech.frist.dto.request.RegisterRequest;
import com.tech.frist.dto.response.AuthResponse;

public interface AuthService {

    AuthResponse register(RegisterRequest request);

    AuthResponse login(LoginRequest request);
}
