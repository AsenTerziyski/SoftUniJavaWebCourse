package com.example.myproject.model.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity {
    private String username;
    private String password;
    private String fullName;
    private Boolean isAdmin = false;
    private Set<OffersEntity> offers;

    private Set<UserRoleEntity> roles;

    public UserEntity() {
        this.roles = new HashSet<>();
        this.offers = new HashSet<>();
    }

    public String getUsername() {
        return username;
    }

    public UserEntity setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserEntity setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getFullName() {
        return fullName;
    }

    public UserEntity setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public Boolean getAdmin() {
        return isAdmin;
    }

    public UserEntity setAdmin(Boolean admin) {
        isAdmin = admin;
        return this;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    public Set<UserRoleEntity> getRoles() {
        return roles;
    }

    public UserEntity setRoles(Set<UserRoleEntity> roles) {
        this.roles = roles;
        return this;
    }

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    public Set<OffersEntity> getOffers() {
        return offers;
    }

    public UserEntity setOffers(Set<OffersEntity> offers) {
        this.offers = offers;
        return this;
    }
}
