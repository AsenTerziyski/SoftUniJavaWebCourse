package com.example.myproject.service;

import com.example.myproject.model.entities.UserEntity;

import java.util.Optional;

public interface UserService {
    void initUsers();

    boolean findUserByUsernameAndPassword(String username, String password);
    UserEntity findUserByUsername(String username);
}
