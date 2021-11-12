package com.example.myproject.web;

import com.example.myproject.model.binding.UserDeleteBindingModel;
import com.example.myproject.model.binding.UserRegistrationBindingModel;
import com.example.myproject.model.entities.UserEntity;
import com.example.myproject.model.service.UserRegistrationServiceModel;
import com.example.myproject.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;


@Controller
public class UserController {
    private final ModelMapper modelMapper;
    private final UserService userService;

    public UserController(ModelMapper modelMapper, UserService userService) {
        this.modelMapper = modelMapper;
        this.userService = userService;
    }

    @GetMapping("/users/login")
    public String login() {

        return "login";
    }

    @PostMapping("/users/login-error")
    public String loginFailure(
            @ModelAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY) String userName,
            RedirectAttributes attributes) {
        System.out.println();
        attributes.addFlashAttribute("bad_credentials", true);
        attributes.addFlashAttribute("username", userName);
        return "redirect:/users/login";
    }

    @GetMapping("/user/register")
    public String getRegisterPage(Model model) {
        if (!model.containsAttribute("occupiedUsername")) {
            model.addAttribute("occupiedUsername", false);
        }
        return "user-add";
    }


    @ModelAttribute
    public UserRegistrationBindingModel userRegistrationBindingModel() {
        return new UserRegistrationBindingModel();
    }

    @PostMapping("/user/add-new-user")
    public String registerPost(@Valid UserRegistrationBindingModel userRegistrationBindingModel,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors() || !userRegistrationBindingModel.getPassword().equals(userRegistrationBindingModel.getConfirmPassword())) {
            redirectAttributes.addFlashAttribute("userRegistrationBindingModel", userRegistrationBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegistrationBindingModel", bindingResult);
            return "user-add";
        }

        UserRegistrationServiceModel userRegistrationServiceModel = this.modelMapper.map(userRegistrationBindingModel, UserRegistrationServiceModel.class);

        boolean usernameExists = this.userService.checkIfUsernameIsExists(userRegistrationBindingModel.getUsername());
        if (usernameExists) {
            redirectAttributes
                    .addFlashAttribute("userRegistrationBindingModel", userRegistrationBindingModel)
                    .addFlashAttribute("occupiedUsername", true);
            //за да излезе съобшението трябва да редиректна!
            return "redirect:/user/register";
        }

        this.userService.registerNewUser(userRegistrationServiceModel);

        return "index";
    }

    @GetMapping("/user/delete-user")
    private String getDeleteUserPage() {
        return "user-delete";
    }

    @ModelAttribute
    public UserDeleteBindingModel userDeleteBindingModel() {
        return new UserDeleteBindingModel();
    }

    @DeleteMapping("/user/delete-user")
    public String deleteUser(@Valid UserDeleteBindingModel userDeleteBindingModel,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userDeleteBindingModel", userDeleteBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userDeleteBindingModel",
                    bindingResult);
            return "user-delete";
        }

        String username = userDeleteBindingModel.getUsername();
        UserEntity userByUsername = this.userService.findUserByUsername(username);

        if (username.equalsIgnoreCase("admin")) {
            throw new UsernameNotFoundException(username);
        }

        if (userByUsername == null) {
            throw new UsernameNotFoundException(username);
        }

//        ModelAndView confirmModelAndView = new ModelAndView("userDeletedConfirmation");
//        confirmModelAndView.addObject("user", username);
//        this.userService.deleteUser(userByUsername);

        return "userDeletedConfirmation";
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ModelAndView handleDbExceptions(UsernameNotFoundException e) {
        ModelAndView modelAndView = new ModelAndView("userNotFound");
        modelAndView.addObject("userName", e.getUsername());
        modelAndView.setStatus(HttpStatus.NOT_FOUND);
        return modelAndView;
    }


}
