package com.example.study.model.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity // order_detail 이라는 테이블에 자동적으로 연결됨! 자바는 camelCase, DB는 snake_case
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"orderGroup"})
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String status;

    private LocalDateTime arrivalDate;

    private Integer quantity;

    private BigDecimal totalPrice;

    private LocalDateTime createdAt;

    private String createdBy;

    private LocalDateTime updatedAt;

    private String updatedBy;

    private Long itemId;

    // OrderDetail N : 1 OrderGroup
    @ManyToOne
    private OrderGroup orderGroup;
}
