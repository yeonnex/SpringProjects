package com.example.study.model.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity // order_detail 이라는 테이블에 자동적으로 연결됨! 자바는 camelCase, DB는 snake_case
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"user", "item"})
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime orderAt;

    // N : 1
    @ManyToOne
    private User user;


    // N : 1
    @ManyToOne
    private Item item;
}
