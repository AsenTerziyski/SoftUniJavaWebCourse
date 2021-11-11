package com.example.myproject.web;

import com.example.myproject.model.binding.UserRegistrationBindingModel;
import com.example.myproject.model.service.UserRegistrationServiceModel;
import com.example.myproject.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;


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
    public String getRegisterPage (Model model) {
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
                               RedirectAttributes redirectAttributes ) {
        System.out.println();
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




}
