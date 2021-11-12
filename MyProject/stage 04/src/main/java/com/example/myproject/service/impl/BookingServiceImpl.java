package com.example.myproject.service.impl;

import com.example.myproject.model.binding.BookingBindingModel;
import com.example.myproject.model.entities.BookingEntity;
import com.example.myproject.model.entities.GuestEntity;
import com.example.myproject.model.entities.OffersEntity;
import com.example.myproject.model.entities.RoomTypeEntity;
import com.example.myproject.model.entities.enums.RoomEnum;
import com.example.myproject.model.view.BookingSummaryView;
import com.example.myproject.repository.BookingRepository;
import com.example.myproject.service.BookingService;
import com.example.myproject.service.GuestService;
import com.example.myproject.service.OffersService;
import com.example.myproject.service.RoomService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static java.time.temporal.ChronoUnit.DAYS;

@Service
public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepository;
    private final OffersService offersService;
    private final GuestService guestService;
    private final RoomService roomService;
    private final ModelMapper modelMapper;

    public BookingServiceImpl(BookingRepository bookingRepository, OffersService offersService, GuestService guestService, RoomService roomService, ModelMapper modelMapper) {
        this.bookingRepository = bookingRepository;
        this.offersService = offersService;
        this.guestService = guestService;
        this.roomService = roomService;
        this.modelMapper = modelMapper;
    }

    @Override
    public void initBookings() {
        BookingEntity expiredBooking = new BookingEntity();
        LocalDate checkIn = LocalDate.of(2020, 2, 1);
        LocalDate checkOut = LocalDate.of(2020, 2, 11);
        createSampleBookings(expiredBooking, checkIn, checkOut);

        BookingEntity notExpiredBooking = new BookingEntity();
        LocalDate checkInNotExpired = LocalDate.of(2022, 2, 1);
        LocalDate checkOutNotExpired = LocalDate.of(2022, 2, 11);
        createSampleBookings(notExpiredBooking, checkInNotExpired, checkOutNotExpired);
    }

    private void createSampleBookings(BookingEntity booking, LocalDate checkIn, LocalDate checkOut) {
        long st = DAYS.between(checkIn, checkOut);
        booking.setCheckIn(checkIn);
        booking.setCheckOut(checkOut);
        booking.setStay(st);
        RoomEnum room = RoomEnum.DOUBLE_ROOM;
        RoomTypeEntity roomType = this.roomService.findRoomBy(room);
        booking.setRoom(roomType);
        BigDecimal price = roomType.getPrice();
        double doublePrice = price.doubleValue();
        OffersEntity offerByRoomType = this.offersService.findOfferByRoomType(roomType);
        BigDecimal totalPrice = BigDecimal.valueOf(st * doublePrice);
        System.out.println();
        double discount = 0.0;
        if (offerByRoomType != null) {
            long offerStay = offerByRoomType.getStay();
            if (st > offerStay) {
                discount = offerByRoomType.getDiscount();
                totalPrice = BigDecimal.valueOf((st * doublePrice - st * doublePrice * discount / 100));
            }
        }
        booking.setTotalPrice(totalPrice);
        GuestEntity newGuestByEmailIfNotExists = this.guestService.createNewGuestByEmailIfNotExistsAndReturnsHimOrReturnsExistingGuestByEmail("test@test.bg");
        booking.setGuest(newGuestByEmailIfNotExists);
        booking.setStay(st);
        booking.setText("Lorem ipsum dolor sit amet, " +
                "consectetur adipiscing elit");
        booking.setFullName("Axl Rose");
        booking.setEmail("axl@gunsnroses.com");
        booking.setPhoneNumber("+359000000000");
        BookingEntity savedBooking = this.bookingRepository.save(booking);
        newGuestByEmailIfNotExists.getBookings().add(savedBooking);
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
        OffersEntity offerByRoomType = this.offersService.findOfferByRoomType(roomType);
        BigDecimal totalPrice = BigDecimal.valueOf(stay * doublePrice);
        double discount = 0.0;
        if (offerByRoomType != null) {

            long offerStay = offerByRoomType.getStay();
            if (stay > offerStay) {
                discount = offerByRoomType.getDiscount();
                totalPrice = BigDecimal.valueOf((stay * doublePrice - stay * doublePrice * discount / 100));
            }
        }

        newBook.setTotalPrice(totalPrice);
        GuestEntity newGuestByEmailIfNotExists = this.guestService.createNewGuestByEmailIfNotExistsAndReturnsHimOrReturnsExistingGuestByEmail(bookingBindingModel.getEmail());
        newBook.setGuest(newGuestByEmailIfNotExists);
        newBook.setStay(stay);
        newBook.setText(bookingBindingModel.getNotes());
        BookingEntity save = this.bookingRepository.save(newBook);
        newGuestByEmailIfNotExists.getBookings().add(save);
    }

    @Override
    public List<BookingSummaryView> getAllExpiredBookings(LocalDate now) {
        return this.bookingRepository.getAllByCheckOutBefore(now)
                .stream().map(bookingEntity -> this.modelMapper.map(bookingEntity, BookingSummaryView.class))
                .collect(Collectors.toList());

    }

    @Override
    public List<BookingSummaryView> getAllNotExpiredBookings(LocalDate now) {
        List<BookingEntity> allByCheckOutAfter = this.bookingRepository.getAllByCheckOutAfter(now);
        return allByCheckOutAfter.stream().map(bookingEntity -> {
            BookingSummaryView map = this.modelMapper.map(bookingEntity, BookingSummaryView.class);
            String text = bookingEntity.getText();
            System.out.println();
            if (text == null || text.trim().isBlank()) {
                map.setText("N/A");
            }
            return map;
        }).collect(Collectors.toList());
    }


    @Override
    public void removeBooking(Long id) {
        this.bookingRepository.deleteById(id);
    }


}
