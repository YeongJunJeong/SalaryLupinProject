package com.example.backend.dto;

import com.example.backend.domain.Categories;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class CategoriesResponseDto {

    private final Integer id;
    private final String name;
    private final Integer parentId;
    private final List<CategoriesResponseDto> children;

    // Entity를 DTO로 변환하는 생성자
    public CategoriesResponseDto(Categories category) {
        this.id = category.getId();
        this.name = category.getName();
        // 부모가 있으면 부모의 id를, 없으면 null을 저장
        this.parentId = category.getParent() != null ? category.getParent().getId() : null;
        // 자식들도 재귀적으로 DTO로 변환
        this.children = category.getChildren().stream()
                .map(CategoriesResponseDto::new) // .map(child -> new CategoryResponseDto(child))와 동일
                .collect(Collectors.toList());
    }
}