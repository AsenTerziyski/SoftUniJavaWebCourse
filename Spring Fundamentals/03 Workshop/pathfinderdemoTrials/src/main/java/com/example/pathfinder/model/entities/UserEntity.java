package com.example.pathfinder.model.entities;

import com.example.pathfinder.model.entities.enums.LevelEnum;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity {
    private String username;
    private String fullName;
    private String password;
    private String email;
    private Integer age;
    private Set<RoleEntity> roles;
    private LevelEnum level;


    public UserEntity() {
    }

    @Column(name = "username", nullable = false, unique = true)
    @Size(min = 2)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "full_name")
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Column(name = "password", nullable = false)
    @Size(min = 2)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Email()
    @Column(name = "email", unique = true)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "age")
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @ManyToMany
    public Set<RoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleEntity> roles) {
        this.roles = roles;
    }

    @Enumerated(EnumType.STRING)
    public LevelEnum getLevel() {
        return level;
    }

    public void setLevel(LevelEnum level) {
        this.level = level;
    }
}
