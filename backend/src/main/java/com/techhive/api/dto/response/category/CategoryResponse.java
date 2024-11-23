package com.techhive.api.dto.response.category;

import com.techhive.entity.CategoryEntity;

public record CategoryResponse(Long id, String name) {
    public static CategoryResponse from(CategoryEntity categoryEntity) {
        return new CategoryResponse(categoryEntity.getId(), categoryEntity.getName());
    }
}
