package com.example.myproject.web;

import com.example.myproject.model.binding.UserLoginBindingModel;
import com.example.myproject.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class UserController {

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

//    @GetMapping("/users/login")
//    public String login() {
//        return "login";
//    }
//
//    @PostMapping("/users/login-error")
//    public String failedLogin(
//            @ModelAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY) String userName,
//            RedirectAttributes attributes) {
//        attributes.addFlashAttribute("bad_credentials", true);
//        attributes.addFlashAttribute("username", userName);
//        return "redirect:/users/login";
//    }
}
