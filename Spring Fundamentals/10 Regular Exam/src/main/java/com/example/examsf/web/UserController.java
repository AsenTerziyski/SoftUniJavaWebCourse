package com.example.examsf.web;

import com.example.examsf.model.binding.UserLoginBindingModel;
import com.example.examsf.model.binding.UserRegisterBindingModel;
import com.example.examsf.model.service.UserServiceModel;
import com.example.examsf.service.UserService;
import com.example.examsf.util.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;
    private final CurrentUser currentUser;

    public UserController(UserService userService, ModelMapper modelMapper, CurrentUser currentUser) {
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.currentUser = currentUser;
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @ModelAttribute
    public UserRegisterBindingModel userRegisterBindingModel() {
        return new UserRegisterBindingModel();
    }

    @PostMapping("/register")
    public String registerPost(@Valid UserRegisterBindingModel userRegisterBindingModel,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes) {

        String password = userRegisterBindingModel.getPassword();
        String confirmPassword = userRegisterBindingModel.getConfirmPassword();
        boolean passwordConfirmed = password.equals(confirmPassword);

        if (bindingResult.hasErrors() || !passwordConfirmed) {
            redirectAttributes.
                    addFlashAttribute("userRegisterBindingModel",
                            userRegisterBindingModel);
            redirectAttributes.
                    addFlashAttribute(
                            "org.springframework.validation.BindingResult.userRegisterBindingModel",
                            bindingResult);
            return "redirect:register";
        }

        UserServiceModel newUser = this.modelMapper
                .map(userRegisterBindingModel, UserServiceModel.class);

        boolean savedUser = this.userService.registerNewUser(newUser);
        if (savedUser) {
            return "redirect:login";
        }
        return "redirect:register";
    }

    @ModelAttribute
    public UserLoginBindingModel userLoginBindingModel() {
        return new UserLoginBindingModel();
    }

    @GetMapping("/login")
    private String login(Model model) {
        if (!model.containsAttribute("isFound")) {
            model.addAttribute("isFound", true);
        }
        return "login";
    }

    @PostMapping("/login")
    private String loginPost(@Valid UserLoginBindingModel userLoginBindingModel,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {

        System.out.println();
        if (bindingResult.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("userLoginBindingModel", userLoginBindingModel);
            redirectAttributes
                    .addFlashAttribute("org.springframework.validation.BindingResult.userLoginBindingModel",
                            bindingResult);
            return "redirect:login";
        }

        String username = userLoginBindingModel.getUsername();
        String password = userLoginBindingModel.getPassword();

        UserServiceModel userServiceModel = this.userService
                .findUserByUsernameAndPassword(username, password);

        if (userServiceModel == null) {
            redirectAttributes
                    .addFlashAttribute("userLoginBindingModel", userLoginBindingModel);
            redirectAttributes
                    .addFlashAttribute("isFound", false);
            return "redirect:login";
        }

        System.out.println();
        this.userService.loginUser(userServiceModel.getId(), userServiceModel.getUsername());
        Long id = this.currentUser.getId();
        String username1 = this.currentUser.getUsername();
        System.out.println();

        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpSession httpSession) {
        httpSession.invalidate();
        return "redirect:/";
    }





}
