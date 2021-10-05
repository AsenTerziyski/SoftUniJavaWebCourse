package bg.softuni.mobilelele.service.impl;

import bg.softuni.mobilelele.model.binding.UserRegisterBindingModel;
import bg.softuni.mobilelele.model.entity.UserEntity;
import bg.softuni.mobilelele.model.entity.UserRoleEntity;
import bg.softuni.mobilelele.model.entity.enums.UserRoleEnum;
import bg.softuni.mobilelele.model.service.UserLoginServiceModel;
import bg.softuni.mobilelele.repository.OfferRepository;
import bg.softuni.mobilelele.repository.UserRepository;
import bg.softuni.mobilelele.repository.UserRoleRepository;
import bg.softuni.mobilelele.service.interfaces.UserService;
import bg.softuni.mobilelele.user.CurrentUser;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final CurrentUser currentUser;

    public UserServiceImpl(PasswordEncoder passwordEncoder, UserRepository userRepository, UserRoleRepository userRoleRepository, CurrentUser currentUser, OfferRepository offerRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.currentUser = currentUser;
    }

    @Override
    public boolean registerUser(UserRegisterBindingModel userRegisterBindingModel) {
        String username = userRegisterBindingModel.getUsername();
        String firstName = userRegisterBindingModel.getFirstName();
        String lastName = userRegisterBindingModel.getLastName();
        String rawPassword = userRegisterBindingModel.getRawPassword();

        Optional<UserEntity> byUsername = this.userRepository.findByUsername(username);

        UserEntity userEntity = new UserEntity();
        Set<UserRoleEntity> roles = new HashSet<>();

        if(byUsername.isEmpty()) {
            long count = this.userRepository.count();
            userEntity.setUsername(username);
            userEntity.setFirstName(firstName);
            userEntity.setLastName(lastName);
            String encodedPassowrd = passwordEncoder.encode(rawPassword);
            userEntity.setPassword(encodedPassowrd);

            if (count == 0) {
                List<UserRoleEntity> findAllRoles = this.userRoleRepository.findAll();
                roles.addAll(findAllRoles);
                userEntity.setRoles(roles);
                this.userRepository.save(userEntity);
                return true;
            }

            UserRoleEntity user = this.userRoleRepository.findByRole(UserRoleEnum.USER);
            userEntity.setRoles(Set.of(user));
            this.userRepository.save(userEntity);
            return true;
        }
        System.out.println("USER WITH THIS USERNAME ALREADY EXISTS!");
        return false;
    }


    @Override
    public boolean login(UserLoginServiceModel userLoginServiceModel) {
        String username = userLoginServiceModel.getUsername();
        Optional<UserEntity> byUsername = this.userRepository.findByUsername(username);
        if (byUsername.isEmpty()) {
            logout();
            return false;
        } else {
            // Проверка на паролите:
            String rawPassword = userLoginServiceModel.getRawPassword();
            //Optional => ....get().......?:
            String password = byUsername.get().getPassword();
            boolean success = passwordEncoder.matches(rawPassword, password);

            if (success) {
                //Optional => ....get().......?:
                UserEntity loggedInUser = byUsername.get();

                currentUser
                        .setUserName(loggedInUser.getUsername())
                        .setFirstName(loggedInUser.getFirstName())
                        .setLastName(loggedInUser.getLastName())
                        .setLoggedIn(true);

                loggedInUser.getRoles().forEach(r->currentUser.addRole(r.getRole()));
            }
            return success;
        }
    }

    @Override
    public void logout() {
        currentUser.clean();
    }

}
