package com.example.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    private static final Logger LOGGER = LoggerFactory.getLogger(StudentService.class);

    @Cacheable("students")
    public List<Student> findAllStudents(){

        LOGGER.info("Here something complicated happens........");

        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            Thread.interrupted();
        }

        LOGGER.info("Complicated operation finished........");

        return List.of(
                new Student(1L, "TestSt1", 15),
                new Student(2L, "TestSt2", 17));

    }
}
