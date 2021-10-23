package com.example.myexamprep.service;

import com.example.myexamprep.model.entities.CategoryEntity;
import com.example.myexamprep.model.entities.enums.CategoryEnum;


public interface CategoryService {
    void initializeCategories();

    CategoryEntity findCategoryByCategoryEnum(CategoryEnum category);

}
