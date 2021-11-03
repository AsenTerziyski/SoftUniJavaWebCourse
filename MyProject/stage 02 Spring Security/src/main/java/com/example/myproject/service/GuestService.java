package com.example.myproject.service;

public interface GuestService {

    boolean receiveEmailAndCreateNewGuestByEmailIfNotExists(String email, String text);
    boolean createNewGuestByEmailIfNotExists(String email);
}
