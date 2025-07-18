package com.example.backend.repository;

import com.example.backend.domain.Categories;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoriesRepository extends  JpaRepository<Categories, Integer> {

    List<Categories> findByParentId(Integer id);

    List<Categories> findByParentIdIsNull();
}
