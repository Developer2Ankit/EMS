package com.tech.frist.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @GetMapping("/profile")
    public String userProfile() {
        return "User Profile Accessed";
    }
}
