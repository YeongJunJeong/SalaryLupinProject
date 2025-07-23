package com.example.backend.controller;

import com.example.backend.domain.Categories;
import com.example.backend.dto.CategoriesRequestDto;
import com.example.backend.dto.CategoriesResponseDto;
import com.example.backend.service.CategoriesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import  java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/categories")

@Tag(name = "Categories", description = "카테고리 관리") //Swagger
public class CategoriesController {

    //Service 객체를 걍 가져와버림 이 한 줄로
    private final CategoriesService categoriesService;

    /**
     * 새로운 카테고리를 생성하는 API
     */
    @Operation(summary = "카테고리 생성", description = "새로운 카테고리 생성 parentId가 Null이면 최상위 카테고리가 됨")
    @PostMapping //Post 요청을 이쪽으로 받는다는 말
    //@RequestBody 하면 들어오는 JSON을 CategoriesRequestDto 입맛에 맞춰서 변경해줌
    public ResponseEntity<Categories> createCategories(@RequestBody CategoriesRequestDto requestDto) {

        //JSON 들어온거 그대로 Service가 처리하도록 던져줌
        Categories createdCategoryDto = categoriesService.createCategories(requestDto);

        return new ResponseEntity<>(createdCategoryDto, HttpStatus.CREATED);

    }

    /**
     * 최상위 카테고리 목록을 조회하는 API
     */
    @Operation(summary = "최상위 카테고리 조회", description = "최상위 카테고리들을 조회 함")
    @GetMapping //get 요청을 이쪽으로 받는다는 말
    public ResponseEntity<List<CategoriesResponseDto>> getTopLevelCategories() {

        //DB 값 전부 다 Service로 넘김
        List<CategoriesResponseDto> categoryDtos  = categoriesService.findTopLevelCategories();

        return new ResponseEntity(categoryDtos, HttpStatus.OK);

    }

}
