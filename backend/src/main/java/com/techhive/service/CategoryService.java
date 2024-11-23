package com.techhive.service;

import com.techhive.entity.CategoryEntity;
import com.techhive.repository.CategoryRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<CategoryEntity> getCategoryList() {
        return categoryRepository.findAll();
    }
}
