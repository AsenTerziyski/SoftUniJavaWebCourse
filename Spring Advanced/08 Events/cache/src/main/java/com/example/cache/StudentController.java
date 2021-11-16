package com.example.cache;

import com.example.cache.Student;
import com.example.cache.StudentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/students")
    public List<Student> findAll() {
        List<Student> allStudents = this.studentService.findAllStudents();
        return allStudents;

    }
}
