package com.example.interceptor.controller;

import com.example.interceptor.annotation.Auth;
import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Auth
@RestController
@RequestMapping("/api/private")
public class PrivateController {
    @GetMapping("/hello")
    public String hello(){
        return "private hello";
    }
}
