package com.example.examsf.service.impl;

import com.example.examsf.model.entity.UserEntity;
import com.example.examsf.model.service.UserServiceModel;
import com.example.examsf.repository.UserRepository;
import com.example.examsf.service.UserService;
import com.example.examsf.util.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final CurrentUser currentUser;

    public UserServiceImpl(ModelMapper modelMapper, UserRepository userRepository, CurrentUser currentUser) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.currentUser = currentUser;
    }

    @Override
    public boolean registerNewUser(UserServiceModel newUser) {
        Optional<UserEntity> byUsernameAndEmail =
                this.userRepository.findByUsernameAndEmail(newUser.getUsername(), newUser.getEmail());
        if (byUsernameAndEmail.isEmpty()) {
            UserEntity savedUser = this.modelMapper.map(newUser, UserEntity.class);
            this.userRepository.save(savedUser);
            return true;
        }
        return false;
    }

    @Override
    public UserServiceModel findUserByUsernameAndPassword(String username, String password) {
        Optional<UserEntity> byUsernameAndEmail = this.userRepository.findByUsernameAndPassword(username,password);
        if(byUsernameAndEmail.isPresent()) {
            UserServiceModel userService = new UserServiceModel();
            String username1 = byUsernameAndEmail.get().getUsername();
            Long id = byUsernameAndEmail.get().getId();
            userService.setUsername(username1);
            userService.setId(id);
            return userService;
        }
        return null;
    }

    @Override
    public void loginUser(Long id, String username) {
        this.currentUser.setId(id);
        this.currentUser.setUsername(username);
    }

    @Override
    public UserEntity findUserById(Long id) {
        return this.userRepository.findById(id).orElse(null);
    }

    @Override
    public List<UserEntity> findAllLoggedInUsersWhoOwnShip() {
        List<UserEntity> allUsersWhoOwnShips = this.userRepository.findAllUsersWhoOwnShips();
        return allUsersWhoOwnShips;
    }
}
