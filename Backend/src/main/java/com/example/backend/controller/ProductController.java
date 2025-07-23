package com.example.backend.controller;

import com.example.backend.domain.Product;
import com.example.backend.dto.ProductCreateRequestDto;
import com.example.backend.dto.ProductResponseDto;
import com.example.backend.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
@Tag(name = "Products", description = "상품(게시물) 관리 API")
public class ProductController {

    private final ProductService productService;

    @Operation(summary = "상품 등록", description = "새로운 상품(게시물)을 등록합니다.")
    @PostMapping
    public ResponseEntity<ProductResponseDto> createProduct(@RequestBody ProductCreateRequestDto requestDto) {
        // 1. Service를 호출하여 비즈니스 로직을 수행하고, 결과로 Product 엔티티를 받습니다.
        Product createdProduct = productService.createProduct(requestDto);

        // 2. 반환받은 Entity를 클라이언트에게 보여줄 ResponseDTO로 변환합니다.
        ProductResponseDto responseDto = new ProductResponseDto(createdProduct);

        // 3. 201 Created 상태 코드와 함께 DTO를 응답으로 보냅니다.
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @Operation(summary = "상품 상세 조회", description = "특정 ID의 상품 상세 정보를 조회합니다.")
    @GetMapping("/{productId}")
    public ResponseEntity<ProductResponseDto> getProduct(@PathVariable Long productId) {
        // 1. Service를 호출하여 Product 엔티티를 조회합니다.
        Product product = productService.findProductById(productId);

        // 2. 조회한 Entity를 ResponseDTO로 변환합니다.
        ProductResponseDto responseDto = new ProductResponseDto(product);

        // 3. 200 OK 상태 코드와 함께 DTO를 응답으로 보냅니다.
        return ResponseEntity.ok(responseDto);
    }
}