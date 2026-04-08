package com.tech.frist.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FirstContoller {

    @GetMapping("/")
    public String hello() {
        return "Hello Spring Boot Running!";
    }
}
