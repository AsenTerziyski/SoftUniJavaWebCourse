package bg.softuni.mobilelele.model.entity;

import bg.softuni.mobilelele.model.entity.enums.UserRoleEnum;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class UserRoleEntity {
    private Long id;
    private UserRoleEnum role;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    public UserRoleEnum getRole() {
        return role;
    }

    public void setRole(UserRoleEnum role) {
        this.role = role;
    }
}
