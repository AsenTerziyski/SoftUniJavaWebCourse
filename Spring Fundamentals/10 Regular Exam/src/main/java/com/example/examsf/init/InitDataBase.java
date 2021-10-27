package com.example.examsf.init;

import com.example.examsf.service.CategoryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class InitDataBase implements CommandLineRunner {
    private final CategoryService categoryService;

    public InitDataBase(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("TEST INIT DATABASE");
        this.categoryService.initCategories();
    }
}
