package bg.softuni.mobilelele.model.view;

import bg.softuni.mobilelele.model.entity.ModelEntity;
import bg.softuni.mobilelele.model.entity.UserEntity;
import bg.softuni.mobilelele.model.entity.enums.EnumEngine;
import bg.softuni.mobilelele.model.entity.enums.EnumTransmission;

import java.math.BigDecimal;

public class OfferSummeryView {
    private String description;
    private EnumEngine engine;
    private String imageUrl;
    private Integer mileage;
    private BigDecimal price;
    private EnumTransmission transmission;
    private Integer year;
    private String model;
    private String brand;

    public OfferSummeryView() {
    }

    public String getDescription() {
        return description;
    }

    public OfferSummeryView setDescription(String description) {
        this.description = description;
        return this;
    }

    public EnumEngine getEngine() {
        return engine;
    }

    public OfferSummeryView setEngine(EnumEngine engine) {
        this.engine = engine;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public OfferSummeryView setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public Integer getMileage() {
        return mileage;
    }

    public OfferSummeryView setMileage(Integer mileage) {
        this.mileage = mileage;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public OfferSummeryView setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public EnumTransmission getTransmission() {
        return transmission;
    }

    public OfferSummeryView setTransmission(EnumTransmission transmission) {
        this.transmission = transmission;
        return this;
    }

    public Integer getYear() {
        return year;
    }

    public OfferSummeryView setYear(Integer year) {
        this.year = year;
        return this;
    }

    public String getBrand() {
        return brand;
    }

    public OfferSummeryView setBrand(String brand) {
        this.brand = brand;
        return this;
    }

    public String getModel() {
        return model;
    }

    public OfferSummeryView setModel(String model) {
        this.model = model;
        return this;
    }
}
