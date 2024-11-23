package com.techhive.api.dto.response.category;

import com.techhive.entity.CategoryEntity;
import java.util.List;

public record CategoryListResponse(List<CategoryResponse> categories) {

    public static CategoryListResponse from(List<CategoryEntity> categories) {
        return new CategoryListResponse(categories.stream().map(CategoryResponse::from).toList());
    }
}
