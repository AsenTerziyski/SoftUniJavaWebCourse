package com.example.pathfinder.service;

import com.example.pathfinder.model.bindingModels.UserRegisterBindingModel;
import com.example.pathfinder.model.entities.UserEntity;
import com.example.pathfinder.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
//    private UserEntity userEntity;

    public UserServiceImpl(UserRepository userRepository, UserEntity userEntity) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean logUser(String username, String password) {
        Optional<UserEntity> byUsernameAndPassword = this.userRepository.findByUsernameAndPassword(username, password);
        if (byUsernameAndPassword.isPresent()) {
            return true;
        }
        return false;
    }

    @Override
    public boolean saveUserInDB(UserRegisterBindingModel userRegisterBindingModel) {

        String fullName = userRegisterBindingModel.getFullname();
        String username = userRegisterBindingModel.getUsername();
        Integer age = userRegisterBindingModel.getAge();
        String email = userRegisterBindingModel.getEmail();
        String password = userRegisterBindingModel.getPassword();

        UserEntity userEntity = new UserEntity();

        userEntity.setFullName(fullName);
        userEntity.setAge(age);
        userEntity.setEmail(email);
        userEntity.setUsername(username);
        userEntity.setPassword(password);

        Optional<UserEntity> byUsernameAndPassword = this.userRepository.findByUsernameAndPassword(username, password);
        Optional<UserEntity> byEmail = this.userRepository.findByEmail(email);

        if (byUsernameAndPassword.isEmpty() && byEmail.isEmpty()) {
                this.userRepository.save(userEntity);
                return true;
        }

        return false;
    }
}
