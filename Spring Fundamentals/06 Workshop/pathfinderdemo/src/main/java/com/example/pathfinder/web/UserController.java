package com.example.pathfinder.web;

import com.example.pathfinder.model.binding.UserLoginBindingModel;
import com.example.pathfinder.model.binding.UserRegisterBindingModel;
import com.example.pathfinder.model.entities.UserEntity;
import com.example.pathfinder.model.service.UserServiceModel;
import com.example.pathfinder.model.view.UserViewModel;
import com.example.pathfinder.service.interfaces.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final ModelMapper modelMapper;

    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @ModelAttribute
    public UserRegisterBindingModel userRegisterBindingModel() {
        return new UserRegisterBindingModel();
    }

    @GetMapping("/register")
    public String register(Model model) {
        System.out.println("TEST REGISTER");
        System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@Valid UserRegisterBindingModel userRegisterBindingModel,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes) {

        boolean passwordConfirmed = userRegisterBindingModel
                .getPassword()
                .equals(userRegisterBindingModel.getConfirmPassword());

        if (bindingResult.hasErrors() || !passwordConfirmed) {
            System.out.println("CHECK PASSWORD OR FIELDS");
            redirectAttributes
                    .addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.userRegisterBindingModel",
                            bindingResult);
            return "redirect:register";
        }

        boolean usernameIsOccupied = this.userService.usernameIsExisting(userRegisterBindingModel.getUsername());

        if(usernameIsOccupied) {
            //todo redirect with message
            return "redirect:register";
        }

        UserServiceModel userServiceModel = this.modelMapper
                .map(userRegisterBindingModel, UserServiceModel.class);

        this.userService.registerUser(userServiceModel);
        System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
        System.out.println("SUCCESSFULLY REGISTERED USER WITH USERNAME: " + userRegisterBindingModel.getUsername());
        return "redirect:login";
    }

    //За да не гръмне нашият таймлийфтемплейт при първо повикване?
    @ModelAttribute
    public UserLoginBindingModel userLoginBindingModel() {
        return new UserLoginBindingModel();
    }

    @GetMapping("/login")
    public String login(Model model) {
        System.out.println("TEST LOGIN");
        System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
        model.addAttribute("isExisting", true);
        System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
        return "login";
    }

    @PostMapping("/login")
    public String login(@Valid UserLoginBindingModel userLoginBindingModel,
                        BindingResult bindingResult,
                        RedirectAttributes redirectAttributes) {

        boolean hasErrors = bindingResult.hasErrors();
        if (hasErrors) {
            redirectAttributes
                    .addFlashAttribute("userLoginBindingModel", userLoginBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.userLoginBindingModel", bindingResult);
            return "redirect:login";
        }

        String username = userLoginBindingModel.getUsername();
        String password = userLoginBindingModel.getPassword();
        UserServiceModel user = this.userService.findUserByUsernameAndPassword(username, password);
//        UserServiceLoginModel userServiceLoginModel = this.modelMapper.map(userLoginBindingModel, UserServiceLoginModel.class);
//
//        boolean foundUser = this.userService.findUserByUsernameAndPassword(userServiceLoginModel);
//        if(foundUser) {
//            System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
//            System.out.println("SUCCESSFULLY LOGGED USER WITH USERNAME: " + userLoginBindingModel.getUsername());
//            return "redirect:/";
//        }
        if (user == null) {
            redirectAttributes
                    .addFlashAttribute("isExisting", false)
                    .addFlashAttribute("userLoginBindingModel", userLoginBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.userLoginBindingModel", bindingResult);
            return "redirect:login";
        }
        this.userService.loginUser(user.getId(), user.getUsername());
        System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
        System.out.println("SUCCESSFULLY LOGGED USER WITH USERNAME: " + userLoginBindingModel.getUsername());
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout() {
        this.userService.logoutUser();
        return "redirect:/";
    }

    @GetMapping("/profile/{id}")
    public String profile(@PathVariable Long id, Model model) {
//        UserEntity userById = this.userService.findUserById(id);
//        UserViewModel userView = this.modelMapper.map(userById, UserViewModel.class);
//        model.addAttribute("user", userView);
        model.addAttribute("user", this.modelMapper.map(this.userService.findById(id), UserViewModel.class));
        return "profile";
    }

}
