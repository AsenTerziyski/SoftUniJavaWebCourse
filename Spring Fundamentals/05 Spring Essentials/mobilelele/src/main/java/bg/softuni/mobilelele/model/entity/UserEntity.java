package bg.softuni.mobilelele.model.entity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity{

  @Column(nullable = false, unique = true)
  private String username;

  private String firstName;
  private String lastName;
  private String password;
  private boolean isActive;

  @ManyToMany(fetch = FetchType.EAGER)
  private Set<UserRoleEntity> roles = new HashSet<>();

  public String getPassword() {
    return password;
  }

  public UserEntity setPassword(String password) {
    this.password = password;
    return this;
  }

  public String getUsername() {
    return username;
  }

  public UserEntity setUsername(String username) {
    this.username = username;
    return this;
  }

  public String getFirstName() {
    return firstName;
  }

  public UserEntity setFirstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  public String getLastName() {
    return lastName;
  }

  public UserEntity setLastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  public boolean isActive() {
    return isActive;
  }

  public UserEntity setActive(boolean active) {
    isActive = active;
    return this;
  }

  public Set<UserRoleEntity> getRoles() {
    return roles;
  }

  public UserEntity setRoles(
      Set<UserRoleEntity> roles) {
    this.roles = roles;
    return this;
  }
}
