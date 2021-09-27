package bg.softuni.mobilelele.service.impl;

import bg.softuni.mobilelele.model.entity.UserEntity;
import bg.softuni.mobilelele.model.serviceModel.UserLoginServiceModel;
import bg.softuni.mobilelele.repository.UserRepository;
import bg.softuni.mobilelele.service.interfaces.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public UserServiceImpl(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Override
    public boolean login(UserLoginServiceModel userLoginServiceModel) {
        String username = userLoginServiceModel.getUsername();
        Optional<UserEntity> byUsername = this.userRepository.findByUsername(username);
        if (byUsername.isEmpty()) {
            return false;
        } else {

            String rawPassword = userLoginServiceModel.getRawPassword();
            String password = byUsername.get().getPassword();

            return passwordEncoder.matches(rawPassword, password);
        }
    }
}
