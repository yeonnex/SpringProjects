package com.example.restaurant.wishlist.dto;


import com.example.restaurant.wishlist.entity.WishListEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class WishListDto{
    private Integer index;
    private String title;                   // 음식명, 장소명
    private String category;                // 카테고리
    private String address;                 // 주소
    private String readAddress;             // 도로명
    private String homePageLink;            // 홈페이지 주소
    private String imageLink;               // 음식, 가게 이미지 주소
    private boolean isVisit;                // 방문 여부
    private int visitCount;                 // 방문 횟수
    private LocalDateTime lastVisitDate;    // 방문 일자


    public WishListDto add(WishListDto wishListDto) {
        var entity = dtoToEntity(wishListDto);
    }

    private WishListDto dtoToEntity(WishListDto wishListDto){
        var dto = new WishListDto();
        dto.setIndex(wishListDto.getIndex());
        dto.setTitle(wishListDto.getTitle());
        dto.setCategory(wishListDto.getCategory());
        dto.setAddress(wishListDto.getAddress());
        dto.setReadAddress(wishListDto.getReadAddress());
        dto.setImageLink(wishListDto.getImageLink());
        dto.setVisit(wishListDto.isVisit());
        dto.setVisitCount(wishListDto.getVisitCount());
        dto.setLastVisitDate(wishListDto.getLastVisitDate());
        return dto;
    }
    private WishListEntity entityToDto(WishListEntity wishListEntity){
        var entity = new WishListEntity();
        entity.setIndex(wishListEntity.getIndex());
        entity.setTitle(wishListEntity.getTitle());
        entity.setCategory(wishListEntity.getCategory());
        entity.setAddress(wishListEntity.getAddress());
        entity.setReadAddress(wishListEntity.getReadAddress());
        entity.setImageLink(wishListEntity.getImageLink());
        entity.setVisit(wishListEntity.isVisit());
        entity.setVisitCount(wishListEntity.getVisitCount());
        entity.setLastVisitDate(wishListEntity.getLastVisitDate());
        return entity;
    }
}
