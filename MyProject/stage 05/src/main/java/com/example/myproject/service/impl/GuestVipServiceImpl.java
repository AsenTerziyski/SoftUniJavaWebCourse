package com.example.myproject.service.impl;

import com.example.myproject.model.entities.BookingEntity;
import com.example.myproject.model.entities.GuestEntity;
import com.example.myproject.model.entities.GuestVipEntity;
import com.example.myproject.repository.GuestVipRepository;
import com.example.myproject.service.GuestService;
import com.example.myproject.service.GuestVipService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class GuestVipServiceImpl implements GuestVipService {
    private final GuestService guestService;
    private final GuestVipRepository guestVipRepository;

    public GuestVipServiceImpl(GuestService guestService, GuestVipRepository guestVipRepository) {
        this.guestService = guestService;
        this.guestVipRepository = guestVipRepository;
    }

    @Override
    @Scheduled(cron = "${schedulers.cronAddVipGuest}")
    public void extractAllVipGuests() {
        List<GuestEntity> allVipGuestsWithBookingsHigherThan3 = this.guestService.findAllVipGuestsWithBookingsHigherThan3();
        for (GuestEntity guestEntity : allVipGuestsWithBookingsHigherThan3) {

            Long id = guestEntity.getId();
            Set<BookingEntity> bookings = guestEntity.getBookings();
            int size = bookings.size();
            String username = guestEntity.getUsername();
            String email = guestEntity.getEmail();

            GuestVipEntity byOriginalId = this.guestVipRepository.findByOriginalId(id);

            if (byOriginalId != null) {
                byOriginalId.setNumberOfBookings(size);
                this.guestVipRepository.save(byOriginalId);
            } else {
                GuestVipEntity guestVipEntity = new GuestVipEntity();
                guestVipEntity.setEmail(email).setUsername(username).setOriginalId(id).setNumberOfBookings(size);
                this.guestVipRepository.save(guestVipEntity);
            }
        }
    }

    @Override
    public boolean findIfGuestIsVip(Long id) {
        GuestVipEntity byOriginalId = this.guestVipRepository.findByOriginalId(id);
        if (byOriginalId == null) {
            return false;
        }
        return true;
    }
}
