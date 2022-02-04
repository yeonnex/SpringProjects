package com.yeonnex.blog.repository;

import com.yeonnex.blog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


// DAO(Data Access Object)
// 자동으로 bean 등록이 된다
// @Repository 생략가능
public interface UserRepository extends JpaRepository<User, Integer> {
}
// Bean 으로 등록되어 스프링에 메모리에 띄워줄 것이기에 어디서나 UserRepository 사용가능
