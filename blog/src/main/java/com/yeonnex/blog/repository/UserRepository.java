package com.yeonnex.blog.repository;

import com.yeonnex.blog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;


// DAO(Data Access Object)
// 자동으로 bean 등록이 된다
// @Repository 생략가능
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUserName(String username);
}
// Bean 으로 등록되어 스프링에 메모리에 띄워줄 것이기에 어디서나 UserRepository 사용가능
    // JPA 네이밍 쿼리 전략
    // SELECT * FROM user WHERE username = ? AND password = ?;
    // User findByUserNameAndPassword(String username, String password);

    // 네이티브 쿼리를 쓸 수도 있음. JPA 네이밍 쿼리와 똑같은 동작을 수행함
//    @Query(value = "SELECT * FROM user WHERE username = ?1 AND password = ?2", nativeQuery=-true)
//    User login(String username, String password);
