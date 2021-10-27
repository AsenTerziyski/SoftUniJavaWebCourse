package com.example.examsf.web;

import com.example.examsf.model.entity.UserEntity;
import com.example.examsf.service.UserService;
import com.example.examsf.util.CurrentUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {
    private final CurrentUser currentUser;
    private final UserService userService;

    public HomeController(CurrentUser currentUser, UserService userService) {
        this.currentUser = currentUser;
        this.userService = userService;
    }

    @GetMapping()
    public String index(Model model) {

        System.out.println();
        if(currentUser.getId() == null) {
            return "index";
        }

        List<UserEntity> allLoggedInUsersWhoOwnShip = this.userService.findAllLoggedInUsersWhoOwnShip();

        model.addAttribute("allUsersWithShip", allLoggedInUsersWhoOwnShip);


        return "home";
    }
}
