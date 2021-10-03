package com.example.pathfinder.web;

import com.example.pathfinder.model.bindingModels.UserLoginBindingModel;
import com.example.pathfinder.model.bindingModels.UserRegisterBindingModel;
import com.example.pathfinder.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String register(){
        System.out.println("TEST REGISTER");
        System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
        return "register";
    }



    @PostMapping("/register")
    public String registerUser(@Valid UserRegisterBindingModel userRegisterBindingModel, BindingResult bindingResult) {
        System.out.println("TEST registerUser");
        System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");


        String password = userRegisterBindingModel.getPassword();
        String confirmPassword = userRegisterBindingModel.getConfirmPassword();
        System.out.println();
        if (!confirmPassword.equals(password)) {
            System.out.println("PLEASE CONFIRM PASSWORD!");
            return "register";
        }

        boolean savedInDB = this.userService.saveUserInDB(userRegisterBindingModel);
        if (savedInDB) {
            return "index";
        }

        System.out.println("User EXISTS!");
        return "register";
    }

    @GetMapping("/login")
    public String login(){
        System.out.println("TEST LOGIN");
        System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
        return "login";
    }

    @PostMapping("/login")
    public String logUser(UserLoginBindingModel userLoginBindingModel) {
        System.out.println();
        String username = userLoginBindingModel.getUsername();
        String password = userLoginBindingModel.getPassword();
        boolean b = this.userService.logUser(username, password);
        System.out.println("TEST logUser");
        System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");

        if (b) {
            System.out.println("User " + username + "logged in!" );
            return "index";
        }

        System.out.println("User " + username + " does no exist in DB!" );
        return "login";
    }
    
}
