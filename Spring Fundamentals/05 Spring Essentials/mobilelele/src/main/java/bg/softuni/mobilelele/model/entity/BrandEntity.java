package bg.softuni.mobilelele.model.entity;

import java.time.Instant;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "brands")
public class BrandEntity extends BaseEntity {

  private String name;

  @OneToMany(mappedBy = "brand", cascade = CascadeType.ALL)
  private List<ModelEntity> models;

  public String getName() {
    return name;
  }

  public BrandEntity setName(String name) {
    this.name = name;
    return this;
  }

  public List<ModelEntity> getModels() {
    return models;
  }

  public BrandEntity setModels(
      List<ModelEntity> models) {
    this.models = models;
    return this;
  }
}
