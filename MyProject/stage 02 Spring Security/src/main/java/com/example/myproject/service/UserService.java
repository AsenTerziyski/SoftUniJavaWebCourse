package com.example.myproject.service;

public interface UserService {
    void initUsers();

    boolean findUserByUsernameAndPassword(String username, String password);
}
