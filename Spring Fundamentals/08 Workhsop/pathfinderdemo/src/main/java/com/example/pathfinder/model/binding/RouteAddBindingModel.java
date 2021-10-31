package com.example.pathfinder.model.binding;

import com.example.pathfinder.model.entities.CategoryEntity;
import com.example.pathfinder.model.entities.enums.CategoryNameEnum;
import com.example.pathfinder.model.entities.enums.LevelEnum;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

public class RouteAddBindingModel {

    private String name;
    private String description;
    private MultipartFile gpxCoordinates;
    private LevelEnum level;
    private String videoUrl;

    private Set<CategoryNameEnum> categories;

    public RouteAddBindingModel() {

    }

    @Size(min = 3, max = 20, message = "Warning: Route name should be between 3 and 20 symbols!")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Size(min = 3)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public MultipartFile getGpxCoordinates() {
        return gpxCoordinates;
    }

    public void setGpxCoordinates(MultipartFile gpxCoordinates) {
        this.gpxCoordinates = gpxCoordinates;
    }

    @NotNull
    public LevelEnum getLevel() {
        return level;
    }

    public void setLevel(LevelEnum level) {
        this.level = level;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public Set<CategoryNameEnum> getCategories() {
        return categories;
    }

    public void setCategories(Set<CategoryNameEnum> categories) {
        this.categories = categories;
    }
}
