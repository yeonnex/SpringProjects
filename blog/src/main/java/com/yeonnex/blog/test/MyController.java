package com.yeonnex.blog.test;

import com.yeonnex.blog.model.User;
import com.yeonnex.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/temp/join")
    public User join(User user){
        return userRepository.save(user);
    }

    @GetMapping("/temp/home")
    public String hello(){
        // 스프링 파일 기본 경로: src/main/resources/static
        // 리턴명: /home.html
        // 풀경로: src/main/resources/static/home.html
        return "/home.html";
    }

    @GetMapping("/temp/home2")
    public String hello2(){
        // prefix: /WEB-INF/views/
        // suffix: .jsp
        return "test";
    }
}

