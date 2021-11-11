package com.example.myproject.service;

import com.example.myproject.model.binding.ReviewSendBindingModel;
import com.example.myproject.model.entities.GuestEntity;

public interface GuestService {

    boolean receiveEmailAndCreateNewGuestByEmailIfNotExistsOrAddsMessageToExistingGuest(String email, String text);
    GuestEntity createNewGuestByEmailIfNotExistsAndReturnsHimOrReturnsExistingGuestByEmail(String email);

    boolean checkIfPersonHasBeenHotelGuest(String email);

    void addReview(ReviewSendBindingModel reviewSendBindingModel);
}
