package bg.softuni.mobilelele.web;

import bg.softuni.mobilelele.model.binding.UserRegisterBindingModel;
import bg.softuni.mobilelele.service.interfaces.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserRegisterController {
    private final UserService userService;

    public UserRegisterController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/register")
    public String login() {
        return "auth-register";
    }

    @PostMapping("/users/register")
    public String registerUser(
            @RequestParam(name = "firstName") String firstName,
            @RequestParam(name = "lastName") String lastName,
            @RequestParam(name = "username") String username,
            @RequestParam(name = "password") String password) {

        UserRegisterBindingModel userRegister = new UserRegisterBindingModel();

        userRegister
                .setUsername(username)
                .setFirstName(firstName)
                .setLastName(lastName)
                .setRawPassword(password);

        if (this.userService.registerUser(userRegister)) {
            System.out.println("REGISTERED USER: " + firstName + " " + lastName + " " + "with username: " + username + ".");
            return "auth-login";
        }
        return "auth-register";
    }
}
