package com.example.backend.service;

import com.example.backend.domain.Categories;
import com.example.backend.domain.Product;
import com.example.backend.domain.User;
import com.example.backend.dto.ProductCreateRequestDto;
import com.example.backend.repository.CategoriesRepository;
import com.example.backend.repository.ProductRepository;
import com.example.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductService {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final CategoriesRepository categoryRepository;

    /**
     * 상품 등록 로직
     */
    @Transactional
    public Product createProduct(ProductCreateRequestDto requestDto) {
        // 1. 요청 DTO에서 받은 ID를 사용하여 판매자와 카테고리 엔티티를 DB에서 찾아옵니다.
        //    - findById 대신 getReferenceById를 사용하면 실제 쿼리 없이 프록시 객체만 가져와 성능에 유리할 수 있습니다.
        User seller = userRepository.findById(requestDto.getSellerId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 판매자입니다. ID: " + requestDto.getSellerId()));

        Categories category = categoryRepository.findById(requestDto.getCategoryId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 카테고리입니다. ID: " + requestDto.getCategoryId()));

        // 2. 찾아온 엔티티들과 DTO의 다른 정보들을 사용하여 Product 엔티티를 생성합니다.
        Product product = Product.builder()
                .title(requestDto.getTitle())
                .content(requestDto.getContent())
                .price(requestDto.getPrice())
                .seller(seller)
                .category(category)
                .locationText(requestDto.getLocationText())
                .build();

        // 3. 생성된 Product 엔티티를 저장하고 반환합니다.
        return productRepository.save(product);
    }

    /**
     * 상품 상세 조회 로직
     */
    public Product findProductById(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("상품을 찾을 수 없습니다. ID: " + productId));
    }
}