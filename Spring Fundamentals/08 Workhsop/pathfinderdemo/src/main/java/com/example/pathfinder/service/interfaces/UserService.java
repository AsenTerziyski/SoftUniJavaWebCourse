package com.example.pathfinder.service.interfaces;

import com.example.pathfinder.model.entities.UserEntity;
import com.example.pathfinder.model.service.UserServiceLoginModel;
import com.example.pathfinder.model.service.UserServiceModel;

public interface UserService {
    void registerUser(UserServiceModel userServiceModel);
    UserServiceModel findUserByUsernameAndPassword(String username, String password);
    void loginUser(Long id, String username);
    void logoutUser();

//    UserEntity findUserById(Long id);

    UserServiceModel findById(Long id);

    boolean usernameIsExisting(String username);
    UserEntity findUserById(Long id);
}
