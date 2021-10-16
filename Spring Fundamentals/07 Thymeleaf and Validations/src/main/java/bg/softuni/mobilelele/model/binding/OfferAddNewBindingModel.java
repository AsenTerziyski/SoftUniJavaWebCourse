package bg.softuni.mobilelele.model.binding;

import bg.softuni.mobilelele.model.entity.ModelEntity;
import bg.softuni.mobilelele.model.entity.UserEntity;
import bg.softuni.mobilelele.model.entity.enums.*;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

public class OfferAddNewBindingModel {

    @NotNull
    @Min(100)
    private Integer price;

    private Long id;

    @NotBlank
    private String description;

    @NotNull
    private EngineEnum engine;

    @NotNull
    private TransmissionEnum transmission;

    @NotNull
    private CarBrandsEnum brand;

    @NotNull
    private CarModelsEnum model;

    @NotBlank
    private String imageUrl;

    @NotNull
    @PositiveOrZero
    private Integer mileage;

    @NotNull
    @Min(1930)
    private Integer year;

    @NotNull
    private CategoryEnum category;


    public OfferAddNewBindingModel() {
    }

    public Integer getPrice() {
        return price;
    }

    public OfferAddNewBindingModel setPrice(Integer price) {
        this.price = price;
        return this;
    }

    public Long getId() {
        return id;
    }

    public OfferAddNewBindingModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public OfferAddNewBindingModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public EngineEnum getEngine() {
        return engine;
    }

    public OfferAddNewBindingModel setEngine(EngineEnum engine) {
        this.engine = engine;
        return this;
    }

    public TransmissionEnum getTransmission() {
        return transmission;
    }

    public OfferAddNewBindingModel setTransmission(TransmissionEnum transmission) {
        this.transmission = transmission;
        return this;
    }

    public CarBrandsEnum getBrand() {
        return brand;
    }

    public OfferAddNewBindingModel setBrand(CarBrandsEnum brand) {
        this.brand = brand;
        return this;
    }

    public CarModelsEnum getModel() {
        return model;
    }

    public OfferAddNewBindingModel setModel(CarModelsEnum model) {
        this.model = model;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public OfferAddNewBindingModel setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public Integer getMileage() {
        return mileage;
    }

    public OfferAddNewBindingModel setMileage(Integer mileage) {
        this.mileage = mileage;
        return this;
    }

    public Integer getYear() {
        return year;
    }

    public OfferAddNewBindingModel setYear(Integer year) {
        this.year = year;
        return this;
    }

    public CategoryEnum getCategory() {
        return category;
    }

    public OfferAddNewBindingModel setCategory(CategoryEnum category) {
        this.category = category;
        return this;
    }
}
