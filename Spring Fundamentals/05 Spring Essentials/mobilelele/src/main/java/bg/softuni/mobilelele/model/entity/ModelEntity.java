package bg.softuni.mobilelele.model.entity;

import static javax.persistence.EnumType.STRING;

import bg.softuni.mobilelele.model.entity.enums.CategoryEnum;
import java.time.Instant;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

@Entity
@Table(name = "models")
public class ModelEntity extends BaseEntity{

  @Column(nullable = false)
  private String name;
  @Enumerated(STRING)
  @Column(nullable = false)
  private CategoryEnum category;
  @Column(nullable = false)
  private String imageUrl;
  @Column(nullable = false)
  private Integer startYear;
  private Integer endYear;
  @ManyToOne
  private BrandEntity brand;

  public String getName() {
    return name;
  }

  public ModelEntity setName(String name) {
    this.name = name;
    return this;
  }

  public CategoryEnum getCategory() {
    return category;
  }

  public ModelEntity setCategory(CategoryEnum category) {
    this.category = category;
    return this;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public ModelEntity setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
    return this;
  }

  public Integer getStartYear() {
    return startYear;
  }

  public ModelEntity setStartYear(Integer startYear) {
    this.startYear = startYear;
    return this;
  }

  public Integer getEndYear() {
    return endYear;
  }

  public ModelEntity setEndYear(Integer endYear) {
    this.endYear = endYear;
    return this;
  }

  public BrandEntity getBrand() {
    return brand;
  }

  public ModelEntity setBrand(BrandEntity brand) {
    this.brand = brand;
    return this;
  }

  @PrePersist
  public void beforeCreate() {
    setCreated(Instant.now());
  }
}
