package com.example.myproject.service.impl;

import com.example.myproject.model.binding.BookingBindingModel;
import com.example.myproject.model.entities.BookingEntity;
import com.example.myproject.model.entities.GuestEntity;
import com.example.myproject.model.entities.RoomTypeEntity;
import com.example.myproject.model.entities.enums.RoomEnum;
import com.example.myproject.repository.BookingRepository;
import com.example.myproject.service.BookingService;
import com.example.myproject.service.GuestService;
import com.example.myproject.service.RoomService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;

@Service
public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepository;
    private final GuestService guestService;
    private final RoomService roomService;
    private final ModelMapper modelMapper;

    public BookingServiceImpl(BookingRepository bookingRepository, GuestService guestService, RoomService roomService, ModelMapper modelMapper) {
        this.bookingRepository = bookingRepository;
        this.guestService = guestService;
        this.roomService = roomService;
        this.modelMapper = modelMapper;
    }

    @Override
    public void saveBooking(BookingBindingModel bookingBindingModel) {

        LocalDate checkIn = bookingBindingModel.getCheckIn();
        LocalDate checkOut = bookingBindingModel.getCheckOut();
        long stay = DAYS.between(checkIn, checkOut);
        BookingEntity newBook = this.modelMapper.map(bookingBindingModel, BookingEntity.class);
        RoomEnum room = bookingBindingModel.getRoom();
        RoomTypeEntity roomType = this.roomService.findRoomBy(room);
        newBook.setRoom(roomType);
        BigDecimal price = roomType.getPrice();
        double doublePrice = price.doubleValue();
        BigDecimal totalPrice = BigDecimal.valueOf(stay * doublePrice);
        newBook.setTotalPrice(totalPrice);
        GuestEntity newGuestByEmailIfNotExists = this.guestService.createNewGuestByEmailIfNotExistsAndReturnsHimOrReturnsExistingGuestByEmail(bookingBindingModel.getEmail());
        System.out.println();
        newBook.setGuest(newGuestByEmailIfNotExists);
        newBook.setStay(stay);
        BookingEntity save = this.bookingRepository.save(newBook);
        newGuestByEmailIfNotExists.getBookings().add(save);
        System.out.println();


    }
}
