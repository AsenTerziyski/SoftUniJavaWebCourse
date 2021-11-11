package com.example.myproject.init;

import com.example.myproject.service.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class InitData implements CommandLineRunner {
    private final UserService userService;
    private final UserRoleService userRoleService;
    private final RoomService roomService;
    private final BookingService bookingService;
    private final OffersService offersService;
    private final ReviewService reviewService;
    private final PictureService pictureService;

    public InitData(UserService userService, UserRoleService userRoleService, RoomService roomService, BookingService bookingService, OffersService offersService, ReviewService reviewService, PictureService pictureService) {
        this.userService = userService;
        this.userRoleService = userRoleService;
        this.roomService = roomService;
        this.bookingService = bookingService;
        this.offersService = offersService;
        this.reviewService = reviewService;
        this.pictureService = pictureService;
    }

    @Override
    public void run(String... args) throws Exception {
        this.userRoleService.initUserRoles();
        this.userService.initUsers();
        this.roomService.initRooms();
        this.offersService.initOffers();
        this.bookingService.initBookings();
        this.reviewService.initReviews();
        this.pictureService.initPictures();
    }
}
