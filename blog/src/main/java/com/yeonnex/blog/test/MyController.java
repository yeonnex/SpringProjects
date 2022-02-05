package com.yeonnex.blog.test;

import com.yeonnex.blog.model.User;
import com.yeonnex.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

// html 파일이 아니라 data 를 리턴해주는 controller = RestController
@ControllerAdvice
@RestController
public class MyController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/temp/join")
    public User join(User user){
        return userRepository.save(user);
    }

    // save 함수는 id를 전달하지 않으면 insert 를 해주고,
    // save 함수는 id를 전달했을 때 해당 id 에 대한 데이터가 있으면 update 를 해주고
    // save 함수는 id를 전달했을 때 해당 id 에 대한 데이터가 없으면 insert 를 한다
    // 패스워드와 이메일만 수정가능하게
    @PutMapping("/temp/user/{id}")
    @Transactional // 함수 종료시에 자동으로 commit 이 됨
    public User update(@PathVariable int id, @RequestBody User req){ // json 데이터 받기 위해 @RequestBody
        System.out.println("PUT 메서드!!!");
       User user = userRepository.findById(id).orElseThrow(()-> {
           return new IllegalArgumentException("수정할 수 없어요...");
       });
       user.setPassword(req.getPassword());
       user.setEmail(req.getEmail());
       // return userRepository.save(user);
       // 변경감지 -> update 날림! 더티 체킹 (@Transactional)
       // 앞으로 값 업데이트 시 더티체킹 방식으로 값을 수정할 것이다
        return user;
    }

    @DeleteMapping("/temp/user/{id}")
    public Optional<User> delete(@PathVariable int id){
        Optional<User> user =  Optional.ofNullable(userRepository.findById(id).orElseThrow(() -> {
            return new IllegalArgumentException("해당 사용자 삭제할 수 없음");
        }));
        user.ifPresent(selectedUser ->{
            userRepository.delete(selectedUser);
        });
        return user;
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
        // return userRepository.findAll(pageable).getContent(); -> 반환값 List 로 바꿔주기
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

