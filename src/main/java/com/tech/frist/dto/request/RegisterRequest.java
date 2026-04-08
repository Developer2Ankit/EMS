package com.tech.frist.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class RegisterRequest {

    @NotBlank(message = "Name is required")
    private String name;

    @Email
    private String email;

    @Size(min = 6)
    private String password;
}
