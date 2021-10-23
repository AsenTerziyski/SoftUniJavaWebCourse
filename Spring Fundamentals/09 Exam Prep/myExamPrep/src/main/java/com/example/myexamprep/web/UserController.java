package com.example.myexamprep.web;

import com.example.myexamprep.model.binding.UserLoginBindingModel;
import com.example.myexamprep.model.binding.UserRegisterBindingModel;
import com.example.myexamprep.model.serviceModel.UserRegisterServiceModel;
import com.example.myexamprep.service.UserService;
import com.example.myexamprep.util.CurrentUser;
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
    private final ModelMapper modelMapper;
    private final UserService userService;
    private final CurrentUser currentUser;

    public UserController(ModelMapper modelMapper, UserService userService, CurrentUser currentUser) {
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.currentUser = currentUser;
    }

    @GetMapping("/register")
    public String registerUser() {
        System.out.println("REGISTER USER");
        return "register";
    }

    @ModelAttribute
    public UserRegisterBindingModel userRegisterBindingModel() {
        return new UserRegisterBindingModel();
    }

    @PostMapping("/register")
    public String registerUserPost(@Valid UserRegisterBindingModel userRegisterBindingModel,
                                   BindingResult bindingResult,
                                   RedirectAttributes redirectAttributes) {

        String password = userRegisterBindingModel.getPassword();
        String confirmPassword = userRegisterBindingModel.getConfirmPassword();
        boolean passwordConfirmed = password.equals(confirmPassword);

        if (bindingResult.hasErrors() || !passwordConfirmed) {
            redirectAttributes.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegisterBindingModel", bindingResult);
            System.out.println("bindingResult.hasErrors() or !passwordConfirmed");
            return "/register";
        }

        UserRegisterServiceModel userRegisterServiceModel = this.modelMapper.map(userRegisterBindingModel, UserRegisterServiceModel.class);
        boolean userSaved = this.userService.saveUserInDataBase(userRegisterServiceModel);

        if (userSaved) {
            return "redirect:/";
        } else {
            System.out.println("USERNAME/EMAIL OCCUPIED");
            return "redirect:register";
        }

    }

    @ModelAttribute
    public UserLoginBindingModel userLoginBindingModel() {
        return new UserLoginBindingModel();
    }

    @GetMapping("/login")
    public String loginUser(Model model) {
        if (!model.containsAttribute("userIsFound")) {
            model.addAttribute("userIsFound", true);
        }
        return "login";
    }

    @PostMapping("/login")
    public String loginUserPost(@Valid UserLoginBindingModel userLoginBindingModel,
                                BindingResult bindingResult,
                                RedirectAttributes redirectAttributes) {
        System.out.println();
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userLoginBindingModel", userLoginBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userLoginBindingModel", bindingResult);
            return "redirect:login";
        }
        String username = userLoginBindingModel.getUsername();
        String password = userLoginBindingModel.getPassword();
        boolean userSuccessfullyLogged = this.userService.loginUser(username, password);

        if (!userSuccessfullyLogged) {
            redirectAttributes.addFlashAttribute("userLoginBindingModel", userLoginBindingModel);
            redirectAttributes.addFlashAttribute("userIsFound", false);
            return "redirect:login";
        }
        Long loggedId = this.currentUser.getId();
        String loggedUsername = currentUser.getUsername();
        System.out.println(loggedUsername + " with ID" + loggedId + " just logged in!");
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String userLogout(HttpSession httpSession) {
        System.out.println();
        Long loggedId = this.currentUser.getId();
        String loggedUsername = currentUser.getUsername();
        System.out.println(loggedUsername + " with ID" + loggedId + " just logged out!");
        httpSession.invalidate();
        CurrentUser currentUser = this.currentUser;

        Long loggedOutId = this.currentUser.getId();
        String loggedOutUsername = this.currentUser.getUsername();
        System.out.println(loggedOutUsername + " with ID" + loggedOutId + " just logged out!");
        return "redirect:/";
    }


}
