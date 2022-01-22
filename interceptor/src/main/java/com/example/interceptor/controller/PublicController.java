package com.example.interceptor.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/public")
@RestController
public class PublicController {

    @GetMapping(value = "/hello")
    public String hello(){
        return "public hello";
    }
}

