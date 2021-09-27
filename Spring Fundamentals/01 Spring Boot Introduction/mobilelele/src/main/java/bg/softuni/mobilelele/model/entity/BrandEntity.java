package bg.softuni.mobilelele.model.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "brands")
public class BrandEntity extends BaseEntity {
    private String name;
    private Set<ModelEntity> models;

    public BrandEntity() {
        this.models = new HashSet<>();
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "brand", cascade = CascadeType.ALL)
    public Set<ModelEntity> getModels() {
        return models;
    }

    public void setModels(Set<ModelEntity> models) {
        this.models = models;
    }
}
