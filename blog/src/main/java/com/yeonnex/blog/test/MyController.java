package com.yeonnex.blog.test;

import com.yeonnex.blog.model.User;
import com.yeonnex.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

// html 파일이 아니라 data 를 리턴해주는 controller = RestController
@RestController
public class MyController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/temp/join")
    public User join(User user){
        return userRepository.save(user);
    }

    @GetMapping("/temp/user/{id}")
    public Optional<User> read(@PathVariable int id){
        return Optional.ofNullable(userRepository.findById(id).orElseThrow(()->{
            return new IllegalArgumentException("해당 사용자는 없음");
        }));
    }

    // 한 페이지당 2건의 데이터를 리턴받을 에정정
   @GetMapping("/temp/user")
    public Page<User> pageList(@PageableDefault(size=2, sort = "id", direction = Sort.Direction.DESC)Pageable pageable){
        return userRepository.findAll(pageable);
        // return userRepository.findAll(pageable).getContent();
    }

    @GetMapping("/temp/users")
    public List<User> allUser(){
        return userRepository.findAll();
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

