package bg.softuni.mobilelele.model.entity;

import bg.softuni.mobilelele.model.entity.enums.EngineEnum;
import bg.softuni.mobilelele.model.entity.enums.TransmissionEnum;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "offers")
public class OfferEntity extends BaseEntity {

  @Column(nullable = false)
  private String description;
  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private EngineEnum engine;
  @Column(nullable = false)
  private String imageUrl;
  private int mileage;
  private int price;
  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private TransmissionEnum transmission;
  private int year;
  @ManyToOne
  private ModelEntity model;
  @ManyToOne
  private UserEntity seller;

  public String getDescription() {
    return description;
  }

  public OfferEntity setDescription(String description) {
    this.description = description;
    return this;
  }

  public EngineEnum getEngine() {
    return engine;
  }

  public OfferEntity setEngine(EngineEnum engine) {
    this.engine = engine;
    return this;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public OfferEntity setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
    return this;
  }

  public int getMileage() {
    return mileage;
  }

  public OfferEntity setMileage(int mileage) {
    this.mileage = mileage;
    return this;
  }

  public int getPrice() {
    return price;
  }

  public OfferEntity setPrice(int price) {
    this.price = price;
    return this;
  }

  public TransmissionEnum getTransmission() {
    return transmission;
  }

  public OfferEntity setTransmission(
      TransmissionEnum transmissionEnum) {
    this.transmission = transmissionEnum;
    return this;
  }

  public int getYear() {
    return year;
  }

  public OfferEntity setYear(int year) {
    this.year = year;
    return this;
  }

  public ModelEntity getModel() {
    return model;
  }

  public OfferEntity setModel(ModelEntity model) {
    this.model = model;
    return this;
  }

  public UserEntity getSeller() {
    return seller;
  }

  public OfferEntity setSeller(UserEntity seller) {
    this.seller = seller;
    return this;
  }
}
