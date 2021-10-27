package com.example.examsf.repository;

import com.example.examsf.model.entity.CategoryEntity;
import com.example.examsf.model.entity.enums.CategoryEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
    Optional<CategoryEntity> findCategoryEntityByName(CategoryEnum name);
}
