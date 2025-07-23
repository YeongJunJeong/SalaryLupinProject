package com.example.backend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "상품 생성 요청 DTO")
public class ProductCreateRequestDto {

    @Schema(description = "상품 제목")
    private String title;

    @Schema(description = "상품 상세 설명")
    private String content;

    @Schema(description = "가격")
    private int price; // ★★★ String -> int 로 수정 ★★★

    @Schema(description = "판매자 ID")
    private Long sellerId; // ★★★ String -> Long 으로 수정 ★★★

    @Schema(description = "카테고리 ID")
    private Integer categoryId; // ★★★ String -> Integer 로 수정 ★★★

    @Schema(description = "거래 희망 지역")
    private String locationText;
}