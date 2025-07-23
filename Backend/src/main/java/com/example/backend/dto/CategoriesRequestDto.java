package com.example.backend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "카테고리 생성 요청 DTO")
public class CategoriesRequestDto {

    @Schema(description = "카테고리 이름")
    private String name;

    @Schema(description = "상위 카테고리")
    private Integer parentId;
}
