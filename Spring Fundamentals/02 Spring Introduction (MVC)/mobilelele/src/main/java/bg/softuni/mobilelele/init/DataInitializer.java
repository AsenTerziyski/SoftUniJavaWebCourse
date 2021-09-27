package bg.softuni.mobilelele.init;

import bg.softuni.mobilelele.model.entity.UserEntity;
import bg.softuni.mobilelele.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public void run(String... args) throws Exception {
        System.out.println("*********************************************************");
        System.out.println("TEST START");

        if(userRepository.count() == 0) {
            UserEntity admin = new UserEntity();
            admin.setActive(true);
            admin.setUsername("adminIvan");
            admin.setFirstName("Ivan");
            admin.setLastName("Ivanov");
            admin.setPassword(this.passwordEncoder.encode("1234"));
            this.userRepository.save(admin);
        }












        System.out.println("TEST END");
        System.out.println("*********************************************************");

    }
}
