package com.example.restaurant.wishlist.entity;


import com.example.restaurant.db.MemoryDbEntity;
import jdk.jfr.DataAmount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class WishListEntity extends MemoryDbEntity {
    // 나머지 필요한 것들!
    private String title;                   // 음식명, 장소명
    private String category;                // 카테고리
    private String address;                 // 주소
    private String readAddress;             // 도로명
    private String homePageLink;            // 홈페이지 주소
    private String imageLink;               // 음식, 가게 이미지 주소
    private boolean isVisit;                // 방문 여부
    private int visitCount;                 // 방문 횟수
    private LocalDateTime lastVisitDate;    // 방문 일자


}
