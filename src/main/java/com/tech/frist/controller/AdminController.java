package com.tech.frist.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @GetMapping("/dashboard")
    public String adminDashboard() {
        return "Welcome Admin";
    }
}
