package com.example.myproject.service.impl;

import com.example.myproject.model.entities.GuestEntity;
import com.example.myproject.model.entities.MessageEntity;
import com.example.myproject.repository.GuestRepository;
import com.example.myproject.repository.MessageRepository;
import com.example.myproject.service.GuestService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.Set;

@Service
public class GuestServiceImpl implements GuestService {
    private final GuestRepository guestRepository;
    private final MessageRepository messageRepository;

    public GuestServiceImpl(GuestRepository guestRepository, MessageRepository messageRepository) {
        this.guestRepository = guestRepository;
        this.messageRepository = messageRepository;
    }

    @Override
    public boolean receiveEmailAndCreateNewGuestByEmailIfNotExists(String email, String text) {

        GuestEntity guestEntity = this.guestRepository.findByEmail(email).orElse(null);
        if (guestEntity == null) {
            Random rand = new Random();
            int upperbound = 101;
            int random = rand.nextInt(upperbound);
            String[] split = email.split("@");
            String firstPartOfEmail = split[0];
            String username = firstPartOfEmail + random;
            GuestEntity newGuest = new GuestEntity();
            newGuest.setUsername(username).setEmail(email);
            MessageEntity messageEntity = new MessageEntity();
            messageEntity.setMessageText(text);
            GuestEntity savedNewGuest = this.guestRepository.save(newGuest);
            messageEntity.setGuest(savedNewGuest);
            MessageEntity savedMessage = this.messageRepository.save(messageEntity);
            return true;
        }

        MessageEntity messageEntity = new MessageEntity();
        messageEntity.setMessageText(text);
        messageEntity.setGuest(guestEntity);
        this.messageRepository.save(messageEntity);
//        List<GuestEntity> allGuestWithMessages = this.guestRepository.findAllGuestWithMessages();
        return false;
    }

    @Override
    public boolean createNewGuestByEmailIfNotExists(String email) {
        GuestEntity guestEntity = this.guestRepository.findByEmail(email).orElse(null);
        if (guestEntity == null) {
            Random rand = new Random();
            int upperbound = 101;
            int random = rand.nextInt(upperbound);
            String[] split = email.split("@");
            String firstPartOfEmail = split[0];
            String username = firstPartOfEmail + random;
            GuestEntity newGuest = new GuestEntity();
            newGuest.setUsername(username).setEmail(email);
            GuestEntity savedNewGuest = this.guestRepository.save(newGuest);
            return true;
        }
        return false;
    }
}
