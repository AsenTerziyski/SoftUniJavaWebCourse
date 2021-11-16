package com.example.cache;

public class Student {
    private final Long Id;
    private final String name;
    private final Integer age;

    public Student(Long id, String name, Integer age) {
        Id = id;
        this.name = name;
        this.age = age;
    }

    public Long getId() {
        return Id;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }
}
