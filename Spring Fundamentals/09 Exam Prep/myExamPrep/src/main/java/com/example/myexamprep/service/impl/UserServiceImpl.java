package com.example.myexamprep.service.impl;

import com.example.myexamprep.model.entities.UserEntity;
import com.example.myexamprep.model.serviceModel.UserRegisterServiceModel;
import com.example.myexamprep.model.view.UserViewModel;
import com.example.myexamprep.repository.UserRepository;
import com.example.myexamprep.service.UserService;
import com.example.myexamprep.util.CurrentUser;
import org.apache.catalina.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
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
    public boolean saveUserInDataBase(UserRegisterServiceModel userRegister) {
        String username = userRegister.getUsername();
        String email = userRegister.getEmail();

        Optional<UserEntity> byUsername = this.userRepository.findByUsername(username);
        Optional<UserEntity> byEmail = this.userRepository.findByEmail(email);

        if (byUsername.isEmpty() || byEmail.isEmpty()) {
            UserEntity user = this.modelMapper.map(userRegister, UserEntity.class);
            this.userRepository.save(user);
            return true;
        }
        return false;
    }

    @Override
    public boolean loginUser(String username, String password) {
        Optional<UserEntity> byUsernameAndPassword = this.userRepository.findByUsernameAndPassword(username, password);
        if (byUsernameAndPassword.isPresent()) {
            String usersUsername = byUsernameAndPassword.get().getUsername();
            Long id = byUsernameAndPassword.get().getId();
            this.currentUser.setId(id);
            this.currentUser.setUsername(usersUsername);
            return true;
        }
        return false;
    }

    @Override
    public UserEntity findUserById(Long id) {
        UserEntity userEntityById = this.userRepository.findById(id).orElse(null);
        if (userEntityById != null) {
            return userEntityById;
        }
        return null;
    }

    @Override
    public List<UserViewModel> findEmployeesAndTheirOrdersCount() {

        List<UserViewModel> userViewModelsList = new LinkedList<>();
        List<UserEntity> allUsersAndTheirOrdersNumber = this.userRepository.findAllUsersAndTheirOrdersNumber();

        for (UserEntity userEntity : allUsersAndTheirOrdersNumber) {
            String username = userEntity.getUsername();
            int size = userEntity.getOrders().size();
            UserViewModel userViewModel = new UserViewModel();
            userViewModel.setName(username);
            userViewModel.setCountOfOrders(size);
            userViewModelsList.add(userViewModel);
        }

        return userViewModelsList;
    }
}
