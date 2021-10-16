package bg.softuni.mobilelele.model.service;

import bg.softuni.mobilelele.model.entity.enums.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

public class OfferAddServiceModel {
    private Integer price;
    private Long id;
    private String description;
    private EngineEnum engine;
    private TransmissionEnum transmission;
    private CarModelsEnum model;
    private CarBrandsEnum brand;
    private CategoryEnum category;
    private String imageUrl;
    private Integer mileage;
    private Integer year;


    public OfferAddServiceModel() {
    }

    public Integer getPrice() {
        return price;
    }

    public OfferAddServiceModel setPrice(Integer price) {
        this.price = price;
        return this;
    }

    public Long getId() {
        return id;
    }

    public OfferAddServiceModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public OfferAddServiceModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public EngineEnum getEngine() {
        return engine;
    }

    public OfferAddServiceModel setEngine(EngineEnum engine) {
        this.engine = engine;
        return this;
    }

    public TransmissionEnum getTransmission() {
        return transmission;
    }

    public OfferAddServiceModel setTransmission(TransmissionEnum transmission) {
        this.transmission = transmission;
        return this;
    }

    public CarModelsEnum getModel() {
        return model;
    }

    public OfferAddServiceModel setModel(CarModelsEnum model) {
        this.model = model;
        return this;
    }

    public CategoryEnum getCategory() {
        return category;
    }

    public OfferAddServiceModel setCategory(CategoryEnum category) {
        this.category = category;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public OfferAddServiceModel setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public Integer getMileage() {
        return mileage;
    }

    public OfferAddServiceModel setMileage(Integer mileage) {
        this.mileage = mileage;
        return this;
    }

    public Integer getYear() {
        return year;
    }

    public OfferAddServiceModel setYear(Integer year) {
        this.year = year;
        return this;
    }

    public CarBrandsEnum getBrand() {
        return brand;
    }

    public OfferAddServiceModel setBrand(CarBrandsEnum brand) {
        this.brand = brand;
        return this;
    }
}
