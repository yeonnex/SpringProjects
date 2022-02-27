package com.example.jwttutorial.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HelloController {

    // 간단한 문자열 리턴하는 api
    @GetMapping("/hello")
    public ResponseEntity<String> hello(){
        return ResponseEntity.ok("hello");
    }

    @GetMapping("/hi") // 403 ERROR
    public ResponseEntity<String> hi(){
        System.out.println("hi 호출");
        return ResponseEntity.ok("hi");
    }
}
