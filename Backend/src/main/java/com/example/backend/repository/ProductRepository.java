package com.example.backend.repository;

import com.example.backend.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    // 상품 목록 조회, 검색 등 복잡한 기능이 필요할 때 여기에 쿼리 메소드를 추가합니다.
    // 예: List<Product> findByCategoryIdAndStatus(Integer categoryId, ProductStatus status);
}
