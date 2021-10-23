package com.example.myexamprep.repository;

import com.example.myexamprep.model.entities.CategoryEntity;
import com.example.myexamprep.model.entities.enums.CategoryEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {


    Optional<CategoryEntity> findCategoryEntitiesByName(CategoryEnum name);
}
