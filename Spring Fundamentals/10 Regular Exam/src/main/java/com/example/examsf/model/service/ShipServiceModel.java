package com.example.examsf.model.service;

import com.example.examsf.model.entity.CategoryEntity;
import com.example.examsf.model.entity.enums.CategoryEnum;

import java.util.Date;

public class ShipServiceModel {

    private Long id;
    private String name;
    private Long health;
    private Long power;
    private Date created;
    private CategoryEnum category;

    public ShipServiceModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getHealth() {
        return health;
    }

    public void setHealth(Long health) {
        this.health = health;
    }

    public Long getPower() {
        return power;
    }

    public void setPower(Long power) {
        this.power = power;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public CategoryEnum getCategory() {
        return category;
    }

    public void setCategory(CategoryEnum category) {
        this.category = category;
    }
}
