package com.example.myexamprep.service;

import com.example.myexamprep.model.entities.UserEntity;
import com.example.myexamprep.model.serviceModel.UserRegisterServiceModel;
import com.example.myexamprep.model.view.UserViewModel;

import java.util.List;


public interface UserService {
    boolean saveUserInDataBase(UserRegisterServiceModel userRegisterServiceModel);

    boolean loginUser(String username, String password);

    UserEntity findUserById(Long id);

    List<UserViewModel> findEmployeesAndTheirOrdersCount();
}

