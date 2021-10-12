package com.example.pathfinder.model.service;

public class UserServiceLoginModel {
    private String username;
    private String password;

    public UserServiceLoginModel() {
    }

    public String getUsername() {
        return username;
    }

    public UserServiceLoginModel setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserServiceLoginModel setPassword(String password) {
        this.password = password;
        return this;
    }
}
