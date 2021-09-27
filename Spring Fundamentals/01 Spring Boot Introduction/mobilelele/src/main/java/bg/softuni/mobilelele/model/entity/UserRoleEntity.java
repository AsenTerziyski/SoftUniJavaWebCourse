package bg.softuni.mobilelele.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class UserRoleEntity {
    private Long id;
    private RoleEnum role;

    public UserRoleEntity() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public RoleEnum getRole() {
        return role;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setRole(RoleEnum role) {
        this.role = role;
    }
}
