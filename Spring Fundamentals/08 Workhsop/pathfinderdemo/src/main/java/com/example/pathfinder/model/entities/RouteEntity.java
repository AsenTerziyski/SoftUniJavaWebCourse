package com.example.pathfinder.model.entities;


import com.example.pathfinder.model.entities.enums.LevelEnum;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "routes")
public class RouteEntity extends BaseEntity {

    private String gpxCoordinates;
    private LevelEnum level;
    private String name;
    private UserEntity author;
    private String videoUrl;
    private String description;
    private Set<PictureEntity> pictures;
    private Set<CategoryEntity> categories;

    public RouteEntity() {
    }

    @Column(columnDefinition = "LONGTEXT")
    public String getGpxCoordinates() {
        return gpxCoordinates;
    }


    public void setGpxCoordinates(String gpxCoordinates) {
        this.gpxCoordinates = gpxCoordinates;
    }

    @Enumerated(EnumType.STRING)
    public LevelEnum getLevel() {
        return level;
    }

    public void setLevel(LevelEnum level) {
        this.level = level;
    }

    @Column(nullable = false, unique = true)
    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    @ManyToOne
    public UserEntity getAuthor() {
        return author;
    }

    public void setAuthor(UserEntity author) {
        this.author = author;
    }

    @Column
    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    @Column(columnDefinition = "TEXT")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    public Set<CategoryEntity> getCategories() {
        return categories;
    }

    public void setCategories(Set<CategoryEntity> categories) {
        this.categories = categories;
    }

    @OneToMany(mappedBy = "route", fetch = FetchType.EAGER)
    public Set<PictureEntity> getPictures() {
        return pictures;
    }

    public RouteEntity setPictures(Set<PictureEntity> pictures) {
        this.pictures = pictures;
        return this;
    }
}
