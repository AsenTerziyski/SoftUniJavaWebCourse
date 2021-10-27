package com.example.examsf.service;

import com.example.examsf.model.entity.UserEntity;
import com.example.examsf.model.service.UserServiceModel;

import java.util.List;

public interface UserService {
    boolean registerNewUser(UserServiceModel newUser);

    UserServiceModel findUserByUsernameAndPassword(String username, String password);

    void loginUser(Long id, String username);

    UserEntity findUserById(Long id);

    List<UserEntity> findAllLoggedInUsersWhoOwnShip();
}
