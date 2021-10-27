package com.example.examsf.service.impl;

import com.example.examsf.model.entity.CategoryEntity;
import com.example.examsf.model.entity.enums.CategoryEnum;
import com.example.examsf.repository.CategoryRepository;
import com.example.examsf.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void initCategories() {
        if (this.categoryRepository.count() > 0) {
            return;
        }

        CategoryEnum[] values = CategoryEnum.values();
        for (CategoryEnum value : values) {
            CategoryEntity category = new CategoryEntity();
            category.setName(value);
            this.categoryRepository.save(category);
        }

    }

    @Override
    public CategoryEntity findCategoryByCategoryEnum(CategoryEnum category) {
        return this.categoryRepository.findCategoryEntityByName(category).orElse(null);
    }
}
