package com.example.pathfinder.service.impl;

import com.example.pathfinder.model.entities.UserEntity;
import com.example.pathfinder.model.entities.enums.LevelEnum;
import com.example.pathfinder.model.service.UserServiceModel;
import com.example.pathfinder.repository.UserRepository;
import com.example.pathfinder.service.interfaces.UserService;
import com.example.pathfinder.util.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final CurrentUser currentUser;


    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, CurrentUser currentUser) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.currentUser = currentUser;
    }

    @Override
    public void registerUser(UserServiceModel userServiceModel) {
        UserEntity user = modelMapper.map(userServiceModel, UserEntity.class);
        user.setLevel(LevelEnum.BEGINNER);
        this.userRepository.save(user);

    }

//    @Override
//    public boolean findUserByUsernameAndPassword(UserServiceLoginModel userServiceLoginModel) {
//        String username = userServiceLoginModel.getUsername();
//        String password = userServiceLoginModel.getPassword();
//        Optional<UserEntity> byUsernameAndPassword = this.userRepository.findByUsernameAndPassword(username, password);
//        boolean empty = byUsernameAndPassword.isEmpty();
//        if(empty) {
//            return false;
//        }
//        return true;
//    }

    @Override
    public UserServiceModel findUserByUsernameAndPassword(String username, String password) {
        UserServiceModel userServiceModel = this.userRepository.findByUsernameAndPassword(username, password)
                .map(userEntity -> this.modelMapper.map(userEntity, UserServiceModel.class))
                .orElse(null);
        return userServiceModel;
    }

    @Override
    public void loginUser(Long id, String username) {
        this.currentUser.setId(id);
        this.currentUser.setUsername(username);
    }

    @Override
    public void logoutUser() {
        String username = this.currentUser.getUsername();
        Long id = this.currentUser.getId();
        this.currentUser.cleanCurrentUser();
        System.out.println("SUCCESSFULLY LOGOUT OUT USER WITH ID:" + id + " AND USERNAME:" + username + " => " +
                "USERNAME SET TO: " + this.currentUser.getUsername() + " ID SET TO " + this.currentUser.getId() + "!");
    }

//    @Override
//    public UserEntity findUserById(Long id) {
//        Optional<UserEntity> byId = this.userRepository.findById(id);
//        return byId.orElse(null);
//    }

    @Override
    public UserServiceModel findById(Long id) {
        UserServiceModel userServiceModel =
                this.userRepository
                        .findById(id)
                        .map(userEntity -> this.modelMapper.map(userEntity, UserServiceModel.class))
                        .orElse(null);
        return userServiceModel;
    }

    @Override
    public boolean usernameIsExisting(String username) {
        Optional<UserEntity> byUsername = this.userRepository.findByUsername(username);
        return byUsername.isPresent();
    }

    @Override
    public UserEntity findUserById(Long id) {
        return this.userRepository.findById(id).orElse(null);
    }

}
