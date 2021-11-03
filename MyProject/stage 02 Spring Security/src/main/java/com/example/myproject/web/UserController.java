package com.example.myproject.web;

import com.example.myproject.model.binding.UserLoginBindingModel;
import com.example.myproject.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {

//    private final UserService userService;
//    private final PasswordEncoder passwordEncoder;

//    public UserController(UserService userService, PasswordEncoder passwordEncoder) {
//        this.userService = userService;
//        this.passwordEncoder = passwordEncoder;
//    }

    @GetMapping("/login")
    public String login() {
        System.out.println();
        return "login";
    }

//    @GetMapping("/login")
//    public String getLogin(Model model) {
//        if (!model.containsAttribute("isExisting")) {
//            model.addAttribute("isExisting", true);
//        }
//        return "login";
//    }

//    @ModelAttribute
//    public UserLoginBindingModel userLoginBindingModel() {
//        return new UserLoginBindingModel();
//    }
//
//    @PostMapping("/login")
//    public String postLogin(@Valid UserLoginBindingModel userLoginBindingModel,
//                            BindingResult bindingResult,
//                            RedirectAttributes redirectAttributes) {
//        System.out.println();
//
//        boolean hasErrors = bindingResult.hasErrors();
//
//        if (hasErrors) {
//            redirectAttributes
//                    .addFlashAttribute("userLoginBindingModel", userLoginBindingModel)
//                    .addFlashAttribute("org.springframework.validation.BindingResult.userLoginBindingModel", bindingResult);
//            return "redirect:login";
//        }
//
//        String username = userLoginBindingModel.getUsername();
//        String password = userLoginBindingModel.getPassword();
//        boolean userIsFound = this.userService.findUserByUsernameAndPassword(username, password);
//
//        if (!userIsFound) {
//            redirectAttributes.addFlashAttribute("userLoginBindingModel", userLoginBindingModel);
//            redirectAttributes.addFlashAttribute("isExisting", false);
//            return "redirect:login";
//        }
//
//        return "redirect:/";
//    }

}
