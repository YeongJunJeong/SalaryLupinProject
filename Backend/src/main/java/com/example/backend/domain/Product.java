package com.example.backend.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import com.example.backend.domain.ProductStatus;

import java.sql.Timestamp;
import java.time.Instant;

@Entity
@Table(name = "products")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicInsert
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Lob
    @Column(nullable = false)
    private String content;

    @Column(nullable = false, columnDefinition = "INT UNSIGNED")
    @ColumnDefault("0")
    private Integer price;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "ENUM('SELLING', 'RESERVED', 'SOLD')")
    @ColumnDefault("'SELLING'") // 문자열이므로 작은따옴표로 감싸야 함
    private ProductStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seller_id", nullable = false)
    private User seller;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Categories category;

    @Column(name = "location_text")
    private String locationText;

    @Column(name = "view_count", nullable = false, columnDefinition = "INT UNSIGNED")
    @ColumnDefault("0")
    private Integer viewCount;

    @Column(name = "refreshed_at", nullable = false, updatable = false, columnDefinition = "TIMESTAMP")
    @ColumnDefault("CURRENT_TIMESTAMP")
    private Timestamp refreshedAt;

    @Column(name = "created_at", nullable = false, updatable = false, columnDefinition = "TIMESTAMP")
    @ColumnDefault("CURRENT_TIMESTAMP")
    private Timestamp createdAt;

    @Column(name = "updated_at", nullable = false, columnDefinition = "TIMESTAMP")
    @ColumnDefault("CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Timestamp updatedAt;

    @Column(name = "deleted_at")
    private Timestamp deletedAt;

    @Builder
    public Product(String title, String content, int price, User seller, Categories category, String locationText) {
        this.title = title;
        this.content = content;
        this.price = price;
        this.seller = seller;
        this.category = category;
        this.locationText = locationText;
        this.status = ProductStatus.SELLING;
        this.viewCount = 0;
        Timestamp now = Timestamp.from(Instant.now());
        this.createdAt = now;
        this.refreshedAt = now;
        this.updatedAt = now;

    }

}

