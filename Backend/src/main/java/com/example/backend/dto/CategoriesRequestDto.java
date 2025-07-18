package com.example.backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoriesRequestDto {
    private String name;
    private Integer parentId;
}
