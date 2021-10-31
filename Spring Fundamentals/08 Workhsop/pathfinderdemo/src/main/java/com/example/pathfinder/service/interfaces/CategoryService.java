package com.example.pathfinder.service.interfaces;

import com.example.pathfinder.model.entities.CategoryEntity;
import com.example.pathfinder.model.entities.enums.CategoryNameEnum;

import java.util.Optional;

public interface CategoryService {

    CategoryEntity findCategoryByName(CategoryNameEnum categoryNameEnum);
}
