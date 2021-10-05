package bg.softuni.mobilelele.init;

import bg.softuni.mobilelele.repository.UserRepository;
import bg.softuni.mobilelele.service.interfaces.OfferService;
import bg.softuni.mobilelele.service.interfaces.UserRoleService;
import bg.softuni.mobilelele.service.interfaces.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final OfferService offerService;
    private final UserRoleService userRoleService;

    public DataInitializer(UserRepository userRepository, PasswordEncoder passwordEncoder, UserService userService, OfferService offerService, UserRoleService userRoleService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
        this.offerService = offerService;
        this.userRoleService = userRoleService;
    }


    @Override
    public void run(String... args) throws Exception {
        System.out.println("*********************************************************");
        System.out.println("TEST START");

        this.userRoleService.initializeRoles();
        this.offerService.initializeOffers();

        System.out.println("TEST END");
        System.out.println("*********************************************************");

    }
}
