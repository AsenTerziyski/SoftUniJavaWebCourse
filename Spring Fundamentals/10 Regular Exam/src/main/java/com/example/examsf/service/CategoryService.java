package com.example.examsf.service;

import com.example.examsf.model.entity.CategoryEntity;
import com.example.examsf.model.entity.enums.CategoryEnum;

public interface CategoryService {
    void initCategories();

    CategoryEntity findCategoryByCategoryEnum(CategoryEnum category);


}
