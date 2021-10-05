package bg.softuni.mobilelele.model.entity;

import bg.softuni.mobilelele.model.entity.enums.EnumCategory;

import javax.persistence.*;

@Entity
@Table(name = "models")
public class ModelEntity extends BaseEntity {

    private String name;
    private EnumCategory category;
    private String imageUrl;
    private Integer startYear;
    private Integer endYear;
    private BrandEntity brand;

    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "category", nullable = false)
    public EnumCategory getCategory() {
        return category;
    }

    public void setCategory(EnumCategory category) {
        this.category = category;
    }

    @Column(name = "image_url", length = 512)
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Column(nullable = false)
    public Integer getStartYear() {
        return startYear;
    }

    public void setStartYear(Integer startYear) {
        this.startYear = startYear;
    }

    public Integer getEndYear() {
        return endYear;
    }

    public void setEndYear(Integer endYear) {
        this.endYear = endYear;
    }

    @ManyToOne
    public BrandEntity getBrand() {
        return brand;
    }

    public void setBrand(BrandEntity brand) {
        this.brand = brand;
    }
}
