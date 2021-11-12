package com.example.myproject.service.impl;


import com.example.myproject.model.entities.UserEntity;
import com.example.myproject.model.entities.UserRoleEntity;
import com.example.myproject.model.entities.enums.UserRoleEnum;
import com.example.myproject.model.service.UserRegistrationServiceModel;
import com.example.myproject.repository.UserRepository;
import com.example.myproject.repository.UserRoleRepository;
import com.example.myproject.service.UserService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRoleRepository userRoleRepository;
    private final AndoriaUserServiceImpl andoriaUserService;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, UserRoleRepository userRoleRepository, AndoriaUserServiceImpl andoriaUserService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userRoleRepository = userRoleRepository;
        this.andoriaUserService = andoriaUserService;
    }

    @Override
    public void initUsers() {
        if (this.userRepository.count() == 0) {
            UserEntity adminUser = new UserEntity();
            adminUser.setUsername("admin")
                    .setPassword(this.passwordEncoder.encode("12345"))
                    .setFullName("Admin Adminov")
                    .setAdmin(true);

            UserRoleEnum adminEnum = UserRoleEnum.valueOf("ADMIN");
            UserRoleEnum userEnum = UserRoleEnum.valueOf("USER");

            UserRoleEntity adminRole = this.userRoleRepository.findUserRoleEntityByRole(adminEnum);
            UserRoleEntity userRole = this.userRoleRepository.findUserRoleEntityByRole(userEnum);

            adminUser.setRoles(Set.of(adminRole, userRole));
            this.userRepository.save(adminUser);

            UserEntity testUser = new UserEntity();
            testUser.setUsername("tuser").setPassword(this.passwordEncoder.encode("12345"))
                    .setFullName("Test User").setAdmin(false);
            testUser.setRoles(Set.of(userRole));
            this.userRepository.save(testUser);
        }
    }

    @Override
    public boolean findUserByUsernameAndPassword(String username, String password) {
        UserEntity userEntity = this.userRepository.findByUsernameAndPassword(username, password).orElse(null);
        if (userEntity == null) {
            return false;
        }
        return true;
    }

    @Override
    public UserEntity findUserByUsername(String username) {
        UserEntity userEntity = this.userRepository.findByUsername(username).orElse(null);

        return userEntity;
    }

    @Override
    public void registerNewUser(UserRegistrationServiceModel userRegistrationServiceModel) {
        UserRoleEntity userRole = userRoleRepository
                .findUserRoleEntityByRole(UserRoleEnum.USER);

        UserEntity newUser = new UserEntity();

        String username = userRegistrationServiceModel.getUsername();
        String password = userRegistrationServiceModel.getPassword();
        String fullName = userRegistrationServiceModel.getFullName();

        newUser.setUsername(username)
                .setPassword(passwordEncoder.encode(password))
                .setFullName(fullName)
                .setAdmin(false)
                .setRoles(Set.of(userRole));

        this.userRepository.save(newUser);

        UserDetails principal = andoriaUserService.loadUserByUsername(newUser.getUsername());

        Authentication authentication = new UsernamePasswordAuthenticationToken
                (
                        principal,
                        newUser.getPassword(),
                        principal.getAuthorities()
                );

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    @Override
    public boolean checkIfUsernameIsExists(String username) {
        UserEntity userEntity = this.userRepository.findByUsername(username).orElse(null);
        if (userEntity == null) {
            return false;
        }
        return true;
    }

    @Override
    public void deleteUser(UserEntity userByUsername) {
        this.userRepository.delete(userByUsername);
    }
}
