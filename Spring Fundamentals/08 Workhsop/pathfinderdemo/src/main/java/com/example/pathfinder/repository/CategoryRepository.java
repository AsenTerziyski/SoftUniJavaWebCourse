package com.example.pathfinder.repository;

import com.example.pathfinder.model.entities.CategoryEntity;
import com.example.pathfinder.model.entities.enums.CategoryNameEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
    Optional<CategoryEntity> findByName(CategoryNameEnum name);
}
