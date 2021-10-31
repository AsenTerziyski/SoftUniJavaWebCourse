package com.example.pathfinder.service.impl;

import com.example.pathfinder.model.entities.CategoryEntity;
import com.example.pathfinder.model.entities.enums.CategoryNameEnum;
import com.example.pathfinder.repository.CategoryRepository;
import com.example.pathfinder.service.interfaces.CategoryService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryEntity findCategoryByName(CategoryNameEnum categoryNameEnum) {
        return this.categoryRepository.findByName(categoryNameEnum).orElse(null);
    }
}
