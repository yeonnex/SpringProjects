package com.example.securitybasic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller // View 를 리턴하겠다!!
public class MainController {
    // localhost:8080/
    // localhost:8080
    @GetMapping({"", "/"})
    public String index(){
        // mustache 기본 폴더 src/main/resources/
        // 뷰리졸버 설정: templates (prefix), .mustache (suffix) 생략 가능
        return "index";
    }
}
