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
        this.roomService.initRooms();




    }
}
