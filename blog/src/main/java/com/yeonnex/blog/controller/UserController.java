package com.yeonnex.blog.controller;


import org.hibernate.annotations.JoinFormula;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class UserController {
    @GetMapping("/user/joinForm")
    public String joinForm(){
        System.out.println("?????");
        return "user/joinForm";
    }

    @GetMapping("/user/loginForm")
    public String loginForm(){
        return "user/loginForm";
    }
}
