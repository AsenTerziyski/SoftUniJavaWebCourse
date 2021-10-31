package bg.softuini.abooks.model.entity;

import javax.persistence.*;
import java.time.Instant;

@MappedSuperclass
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "BaseEntity{" +
                "id=" + id +
                '}';
    }

    //  @Column(nullable = false)
//  private Instant created;
//
//  private Instant modified;

//  public Instant getCreated() {
//    return created;
//  }
//
//  public BaseEntity setCreated(Instant created) {
//    this.created = created;
//    return this;
//  }
//
//  public Instant getModified() {
//    return modified;
//  }
//
//  public BaseEntity setModified(Instant modified) {
//    this.modified = modified;
//    return this;
//  }
//
//  @PrePersist
//  public void beforeCreate() {
//    System.out.println("BEFORE CREATE!");
//    setCreated(Instant.now());
//  }
//
//  @PostPersist void onUpdate() {
//    setModified(Instant.now());
//  }
}
