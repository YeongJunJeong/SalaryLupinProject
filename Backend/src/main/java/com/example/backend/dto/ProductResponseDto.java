package com.example.backend.dto;

import com.example.backend.domain.Product;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.sql.Timestamp;

@Getter
@Schema(description = "상품 정보 응답 DTO")
public class ProductResponseDto {

    private final Long id;
    private final String title;
    private final String content;
    private final int price;
    private final String status;
    private final String locationText;
    private final int viewCount;
    private final Timestamp createdAt;

    // 연관된 정보 (ID 대신 이름 등)
    private final Long sellerId;
    private final String sellerName;
    private final Integer categoryId;
    private final String categoryName;

    // Product 엔티티를 이 DTO로 변환하는 생성자
    public ProductResponseDto(Product product) {
        this.id = product.getId();
        this.title = product.getTitle();
        this.content = product.getContent();
        this.price = product.getPrice();
        this.status = product.getStatus().name();
        this.locationText = product.getLocationText();
        this.viewCount = product.getViewCount();
        this.createdAt = product.getCreatedAt();

        // 연관된 엔티티에서 필요한 정보만 추출
        this.sellerId = product.getSeller().getId();
        this.sellerName = product.getSeller().getName();
        this.categoryId = product.getCategory().getId();
        this.categoryName = product.getCategory().getName();
    }

}
