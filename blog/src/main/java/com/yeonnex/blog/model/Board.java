package com.yeonnex.blog.model;
import com.yeonnex.blog.model.User;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 100)
    private String title;

    @Lob // 대용량 데이터
    private String content; // 섬머노트 라이브러리쓸 것임.<html> 태그가 섞여서 디자인이 됨

    @ColumnDefault("0")
    private int count; // 조회수

    // private int userId; // 이 보드를 누가 적었는지
    @JoinColumn(name = "userId") // 실제 DB에는 userId로 FK가 들어갈 것이다
    @ManyToOne // 한명의 유저는 여러개의 글을 쓸 수 있다
    private User user; // DB는 오브젝트를 저장할 수 없다. FK로 저장해야함. 자바는 오브젝트를 저장할 수 있다

   @CreationTimestamp // insert 될때 자동으로
    private Timestamp createDate;

}
