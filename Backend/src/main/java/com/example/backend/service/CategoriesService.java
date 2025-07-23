package com.example.backend.service;

import com.example.backend.domain.Categories;
import com.example.backend.domain.User;
import com.example.backend.dto.CategoriesRequestDto;
import com.example.backend.dto.CategoriesResponseDto;
import com.example.backend.repository.CategoriesRepository;
import com.example.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder; // Spring Security의 암호화 기능
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoriesService {

    private final CategoriesRepository categoriesRepository;

    /**
     * 새로운 카테고리를 생성하는 비즈니스 로직
     * @param requestDto 컨트롤러로부터 받은 "카테고리 생성 주문서"
     * @return 데이터베이스에 저장된 Category 엔티티
     */
    @Transactional//DB에서 쓰기작업할때 필요함
    public Categories createCategories(CategoriesRequestDto requestDto) {

        Categories parent = null;
        if(requestDto.getParentId() != null) {
            parent = categoriesRepository.findById(requestDto.getParentId())
                    .orElseThrow(() -> new IllegalArgumentException("부모 카테고리를 찾을 수 없습니다. ID: " + requestDto.getParentId()));
        }

        Categories newCategory = Categories.builder()
                .name(requestDto.getName())
                .parent(parent)
                .build();

        return categoriesRepository.save(newCategory);

    }

    /**
     * 최상위 카테고리 목록만 조회하는 비즈니스 로직
     * @return 최상위 카테고리들의 리스트
     */
    @Transactional(readOnly = true)
    public List<CategoriesResponseDto> findTopLevelCategories() {
        return categoriesRepository.findByParentIdIsNull().stream()
                .map(CategoriesResponseDto::new) // .map(category -> new CategoryResponseDto(category))
                .collect(Collectors.toList());
    }
}
