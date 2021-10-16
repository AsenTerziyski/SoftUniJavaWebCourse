package bg.softuni.mobilelele.web;

import bg.softuni.mobilelele.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserLogoutController {

  private final UserService userService;

  public UserLogoutController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/users/logout")
  public String logout() {
    userService.logout();
    return "redirect:/";
  }

}
