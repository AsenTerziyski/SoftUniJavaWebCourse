package bg.softuni.mobilelele.model.entity;

import java.time.Instant;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PostPersist;
import javax.persistence.PrePersist;

@MappedSuperclass
public abstract class BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private Instant created;

  private Instant modified;

  public Long getId() {
    return id;
  }

  public BaseEntity setId(Long id) {
    this.id = id;
    return this;
  }

  public Instant getCreated() {
    return created;
  }

  public BaseEntity setCreated(Instant created) {
    this.created = created;
    return this;
  }

  public Instant getModified() {
    return modified;
  }

  public BaseEntity setModified(Instant modified) {
    this.modified = modified;
    return this;
  }

  @PrePersist
  public void beforeCreate() {
    this.created = Instant.now();
  }

  @PostPersist void onUpdate() {
    this.modified = Instant.now();
  }
}
