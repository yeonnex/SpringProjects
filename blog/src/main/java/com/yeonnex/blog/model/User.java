package com.yeonnex.blog.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity // MySQL 에 자동으로 User 클래스 테이블이 생성된다
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 넘버링 전략. 프로젝트에서 연결된 DB 넘버링 전략을 따라가겠다
    private int id; // 오라클: 시퀀스, MySQL: auto_increment 전략

    @Column(nullable = false, length = 30)
    private String userName; // 아이디

    @Column(nullable = false, length = 100) // 123456 -> 해쉬(비밀번호 암호화 예정)
    private String password;

    @Column(nullable = false, length = 50)
    private String email;

    @ColumnDefault("'user'") // 작은따옴표로 한번더 감싸주어 문자열이라는 것을 알려주자자
   private String role; // Enum 으로 나중에 수정하자 // admin, user, manager

    @CreationTimestamp // 시간이 자동 입력. 나중에 데이터 생성할 때 id 값처럼 굳이 넣지 않아도 자동생성 예정
    private Timestamp createDate;
}
