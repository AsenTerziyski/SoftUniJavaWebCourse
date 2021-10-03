package com.example.pathfinder.service;

import com.example.pathfinder.model.bindingModels.UserRegisterBindingModel;

public interface UserService {
    public boolean logUser(String username, String password);

    boolean saveUserInDB(UserRegisterBindingModel userRegisterBindingModel);
}
