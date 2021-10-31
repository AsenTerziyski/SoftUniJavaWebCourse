package com.example.pathfinder.model.view;

import com.example.pathfinder.model.entities.CategoryEntity;
import com.example.pathfinder.model.entities.PictureEntity;
import com.example.pathfinder.model.entities.UserEntity;
import com.example.pathfinder.model.entities.enums.LevelEnum;

import java.util.Set;

public class RouteDetailsViewModel {

    private String gpxCoordinates;
    private LevelEnum level;
    private String name;
    private UserEntity author;
    private String videoUrl;
    private String description;
    private Set<PictureEntity> pictures;
    private Set<CategoryEntity> categories;

    public RouteDetailsViewModel() {
    }

    public String getGpxCoordinates() {
        return gpxCoordinates;
    }

    public void setGpxCoordinates(String gpxCoordinates) {
        this.gpxCoordinates = gpxCoordinates;
    }

    public LevelEnum getLevel() {
        return level;
    }

    public void setLevel(LevelEnum level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserEntity getAuthor() {
        return author;
    }

    public void setAuthor(UserEntity author) {
        this.author = author;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<PictureEntity> getPictures() {
        return pictures;
    }

    public void setPictures(Set<PictureEntity> pictures) {
        this.pictures = pictures;
    }

    public Set<CategoryEntity> getCategories() {
        return categories;
    }

    public void setCategories(Set<CategoryEntity> categories) {
        this.categories = categories;
    }
}
