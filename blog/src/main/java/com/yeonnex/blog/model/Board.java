package com.yeonnex.blog.model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.yeonnex.blog.model.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.minidev.json.annotate.JsonIgnore;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
    @ManyToOne(fetch = FetchType.EAGER) // 한명의 유저만 존재하기 때문에, 데이터 가지고 올 때, 패치타입을 EAGER 로 설정
    private User user; // DB는 오브젝트를 저장할 수 없다. FK로 저장해야함. 자바는 오브젝트를 저장할 수 있다

    @OneToMany(mappedBy = "board", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE) // mappedBy 가 있다는 건, "난 FK가 아니예요!" 라는 뜻. 그러니 "DB에 reply 컬럼을 만들지 마세요!"
    @JsonIgnoreProperties({"board"})
    @OrderBy("id desc")
    private List<Reply> replys;                                 // 난 그냥 Board 를 select 할 때 join 문을 통해 데이터를 들고만 와주기 위해 필요한 겁니다.


    @CreationTimestamp // insert 될때 자동으로
    private Timestamp createDate;

}
