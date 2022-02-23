package com.example.securitybasic.repository;

import com.example.securitybasic.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// 기본적인 CRUD 함수를 JpaRepository 가 들고 있음
// @Repository 라는 어노테이션이 없어도 IoC돼요. 이유는 JpaRepository 상속했기 때문에...
public interface UserRepository extends JpaRepository<User, Integer> {

    // findBy 규칙 -> Username 문법
    // select * from user where username = 1? (Jpa 쿼리 메서드 공부하자)
    Optional<User> findByUsername(String username);
}
