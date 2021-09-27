package bg.softuni.mobilelele.model.entity;


import bg.softuni.mobilelele.model.entity.enums.EnumEngine;
import bg.softuni.mobilelele.model.entity.enums.EnumTransmission;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "offers")
public class OfferEntity extends BaseEntity {
    private String description;
    private EnumEngine engine;
    private String imageUrl;
    private Integer mileage;
    private BigDecimal price;
    private EnumTransmission transmission;
    private Integer year;
    private ModelEntity model;
    private UserEntity seller;

    public OfferEntity() {
    }

    @Column(name = "description", columnDefinition = "TEXT")
    public String getDescription() {
        return description;
    }

    @Enumerated(EnumType.ORDINAL)
    public EnumEngine getEngine() {
        return engine;
    }

    @Column(name = "image_url", length = 255)
    public String getImageUrl() {
        return imageUrl;
    }

    @Column(name = "mileage")
    public Integer getMileage() {
        return mileage;
    }

    @Column(name = "price", nullable = false)
    public BigDecimal getPrice() {
        return price;
    }

    @Enumerated(EnumType.ORDINAL)
    public EnumTransmission getTransmission() {
        return transmission;
    }

    @Column(name = "year")
    public Integer getYear() {
        return year;
    }

    @OneToOne
    public ModelEntity getModel() {
        return model;
    }

    @ManyToOne
    public UserEntity getSeller() {
        return seller;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setEngine(EnumEngine engine) {
        this.engine = engine;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setMileage(Integer mileage) {
        this.mileage = mileage;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setTransmission(EnumTransmission transmission) {
        this.transmission = transmission;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public void setModel(ModelEntity model) {
        this.model = model;
    }

    public void setSeller(UserEntity seller) {
        this.seller = seller;
    }
}
