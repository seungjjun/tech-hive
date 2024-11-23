package com.techhive.api.controller;

import com.techhive.api.dto.response.category.CategoryListResponse;
import com.techhive.entity.CategoryEntity;
import com.techhive.service.CategoryService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public CategoryListResponse getCategories() {
        List<CategoryEntity> categoryEntityList = categoryService.getCategoryList();
        return CategoryListResponse.from(categoryEntityList);
    }
}
