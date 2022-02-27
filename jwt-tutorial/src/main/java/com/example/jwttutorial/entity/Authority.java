package com.example.jwttutorial.entity;


import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "authority")
@Entity
public class Authority {
    @Id
    @Column(name = "authority_name", length = 50)
    private String authorityName; // 권한명이라는 PK
}
