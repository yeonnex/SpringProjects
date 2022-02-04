package com.yeonnex.blog.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Reply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 200)
    private String content;

    // 답변은 (1) 누가 User 가 달았는지 (2) 어떤 Board 에
    @ManyToOne
    @JoinColumn(nullable = false, name = "userId")
    private User user;

    @ManyToOne
    @JoinColumn(nullable = false, name = "boardId")
    private Board board;

    @CreationTimestamp
    private Timestamp createDate;
}
