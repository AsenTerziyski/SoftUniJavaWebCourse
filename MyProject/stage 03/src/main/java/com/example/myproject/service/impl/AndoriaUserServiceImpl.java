package com.example.myproject.service.impl;

import com.example.myproject.model.entities.UserEntity;
import com.example.myproject.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AndoriaUserServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    public AndoriaUserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//
//        // The purpose of this method is to map our user representation (UserEntity)
//        // to the user representation in the spring sercurity world (UserDetails).
//        // The only thing that spring will provide to us is the user name.
//        // The user name will come from the HTML login form.
//
//        UserEntity userEntity =
//                userRepository.findByUsername(username).
//                        orElseThrow(() -> new UsernameNotFoundException("User with name " + username + " not found!"));
//
//        return mapToUserDetails(userEntity);
//    }


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<UserEntity> byUsername = this.userRepository.findByUsername(s);
        UserEntity userEntity = byUsername.orElseThrow(() -> new UsernameNotFoundException("User with name " + s + " not found!"));
        return mapToUserDetails(userEntity);
    }

    private static UserDetails mapToUserDetails(UserEntity userEntity) {

        // GrantedAuthority is the representation of a user role in the
        // spring world. SimpleGrantedAuthority is an implementation of GrantedAuthority
        // which spring provides for our convenience.
        // Our representation of role is UserRoleEntity
        List<GrantedAuthority> auhtorities =
                userEntity.
                        getRoles().
                        stream().
                        map(r -> new SimpleGrantedAuthority("ROLE_" + r.getRole().name())).
                        collect(Collectors.toList());

        // User is the spring implementation of UserDetails interface.
        return new User(
                userEntity.getUsername(),
                userEntity.getPassword(),
                auhtorities
        );
    }

}
