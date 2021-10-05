package bg.softuni.mobilelele.web;


import bg.softuni.mobilelele.model.binding.UserLoginBindingModel;
import bg.softuni.mobilelele.model.service.UserLoginServiceModel;
import bg.softuni.mobilelele.service.interfaces.UserService;
import bg.softuni.mobilelele.user.CurrentUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserLoginController {

    private static Logger LOGGER = LoggerFactory.getLogger(UserLoginController.class);
    private final UserService userService;
    private final CurrentUser currentUser;

    public UserLoginController(UserService userService, CurrentUser currentUser) {
        this.userService = userService;
        this.currentUser = currentUser;
    }

    //"users/login" е в auth-login.html =>
    @GetMapping("/users/login")
    public String login() {
        return "auth-login";
    }

    @PostMapping("/users/login")
    public String login(UserLoginBindingModel userLoginBindingModel) {

        UserLoginServiceModel userLoginServiceModel = new UserLoginServiceModel();

        String username = userLoginBindingModel.getUsername();
        String password = userLoginBindingModel.getPassword();

        userLoginServiceModel.setUsername(username);
        userLoginServiceModel.setRawPassword(password);

        //Делигирам логиката на service layer:
        boolean loginSuccessful = this.userService.login(userLoginServiceModel);

        String logInfoString = String.format("USER WITH USER NAME: " +
                "=> %s <= TRIED TO LOG IN. " +
                "Success %s!", username, loginSuccessful);
        LOGGER.info(logInfoString);
        if (loginSuccessful) {
            return "redirect:/";
        }
        return "redirect:login";
    }

}
