package com.example.myproject.init;

import com.example.myproject.service.RoomService;
import com.example.myproject.service.UserRoleService;
import com.example.myproject.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class InitData implements CommandLineRunner {
    private final UserService userService;
    private final UserRoleService userRoleService;
    private final RoomService roomService;

    public InitData(UserService userService, UserRoleService userRoleService, RoomService roomService) {
        this.userService = userService;
        this.userRoleService = userRoleService;
        this.roomService = roomService;
    }

    @Override
    public void run(String... args) throws Exception {
        this.userRoleService.initUserRoles();
        this.userService.initUsers();
        System.out.println();
        this.roomService.initRooms();

//        LocalDateTime startDate = LocalDateTime.now();
//        LocalDateTime endDate = LocalDateTime.of(2000,12,12,1,5);
//        long noOfDaysBetween = DAYS.between(startDate, endDate);
//        System.out.println(noOfDaysBetween);

//        //24-May-2017, change this to your desired Start Date
//        LocalDate dateBefore = LocalDate.of(2017, Month.MAY, 24);
//        //29-July-2017, change this to your desired End Date
//        LocalDate dateAfter = LocalDate.of(2017, Month.JULY, 29);
//        long noOfDaysBetween = DAYS.between(dateBefore, dateAfter);
//        System.out.println(noOfDaysBetween);


    }
}
