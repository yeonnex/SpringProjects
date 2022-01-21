package com.example.exception.controller;


import com.example.exception.dto.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/api/user")
public class ApiController {
    @GetMapping("") // ?name=1234
    public User get(@RequestParam(required = false) String name, @RequestParam(required = false) Integer age){
        System.out.println("get!!!");
        User user = new User();
        user.setName(name);
        user.setAge(age);

        int a = 10 + age;
        return user;
    }

    @PostMapping("")
    public User post(@Valid @RequestBody User user){
        System.out.println(user);
        return user;
    }
}
