package com.example.pathfinder.config;

import com.example.pathfinder.model.entities.UserEntity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

@Configuration
public class ApplicationConfig {

    @Bean
    public PasswordEncoder passwordEncoder () {
        Pbkdf2PasswordEncoder pbkdf2PasswordEncoder = new Pbkdf2PasswordEncoder();
        return  pbkdf2PasswordEncoder;
    }

    @Bean
    @Scope("prototype")
    public UserEntity user () {
        return new UserEntity();
    }
}
