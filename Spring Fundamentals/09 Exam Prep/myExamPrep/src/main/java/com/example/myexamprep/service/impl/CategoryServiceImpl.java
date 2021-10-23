package com.example.myexamprep.service.impl;

import com.example.myexamprep.model.entities.CategoryEntity;
import com.example.myexamprep.model.entities.enums.CategoryEnum;
import com.example.myexamprep.repository.CategoryRepository;
import com.example.myexamprep.service.CategoryService;
import jdk.jfr.Category;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void initializeCategories() {

        if (this.categoryRepository.count() > 0) {
            System.out.println("CATEGORY DATABASE ALREADY INITIALIZED");
            return;
        }

        CategoryEnum[] values = CategoryEnum.values();
        for (CategoryEnum value : values) {
            String name = value.name();
            CategoryEntity category = new CategoryEntity();
            category.setName(value);
            switch (name) {
                case "COFFEE":
                    category.setNeededTime(2);
                    break;
                case "CAKE":
                    category.setNeededTime(10);
                    break;
                case "DRINK":
                    category.setNeededTime(1);
                    break;
                case "OTHER":
                    category.setNeededTime(5);
                    break;
            }

            this.categoryRepository.save(category);
        }
    }

    @Override
    public CategoryEntity findCategoryByCategoryEnum(CategoryEnum category) {
        return this.categoryRepository
                .findCategoryEntitiesByName(category)
                .orElse(null);
    }
}
