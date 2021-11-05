package com.example.myproject.service;

import com.example.myproject.model.entities.GuestEntity;

public interface GuestService {

    boolean receiveEmailAndCreateNewGuestByEmailIfNotExistsOrAddsMessageToExistingGuest(String email, String text);
    GuestEntity createNewGuestByEmailIfNotExistsAndReturnsHimOrReturnsExistingGuestByEmail(String email);
}
