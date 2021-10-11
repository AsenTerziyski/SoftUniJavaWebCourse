package bg.softuni.mobilelele.service.impl;

import bg.softuni.mobilelele.model.entity.UserEntity;
import bg.softuni.mobilelele.model.entity.UserRoleEntity;
import bg.softuni.mobilelele.model.entity.enums.UserRoleEnum;
import bg.softuni.mobilelele.model.service.UserLoginServiceModel;
import bg.softuni.mobilelele.model.service.UserRegistrationServiceModel;
import bg.softuni.mobilelele.repository.UserRepository;
import bg.softuni.mobilelele.repository.UserRoleRepository;
import bg.softuni.mobilelele.service.UserService;
import bg.softuni.mobilelele.user.CurrentUser;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final CurrentUser currentUser;

    public UserServiceImpl(PasswordEncoder passwordEncoder,
        UserRepository userRepository,
        UserRoleRepository userRoleRepository,
        CurrentUser currentUser) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.currentUser = currentUser;
    }

    @Override
    public void initializeUsersAndRoles() {
        initializeRoles();
        initializeUsers();
    }

    private void initializeUsers() {
        if (userRepository.count() == 0) {

            UserRoleEntity adminRole = userRoleRepository.findByRole(UserRoleEnum.ADMIN);
            UserRoleEntity userRole = userRoleRepository.findByRole(UserRoleEnum.USER);

            UserEntity admin = new UserEntity();

            admin
                .setUsername("admin")
                .setPassword(passwordEncoder.encode("test"))
                .setFirstName("Admin")
                .setLastName("Adminov")
                .setActive(true);
            admin.setRoles(Set.of(adminRole, userRole));
            userRepository.save(admin);

            UserEntity pesho = new UserEntity();

            pesho
                .setUsername("pesho")
                .setPassword(passwordEncoder.encode("test"))
                .setFirstName("Pesho")
                .setLastName("Petrov")
                .setActive(true);
            pesho.setRoles(Set.of(userRole));
            userRepository.save(pesho);
        }
    }

    private void initializeRoles() {

        if (userRoleRepository.count() == 0) {
            UserRoleEntity adminRole = new UserRoleEntity();
            adminRole.setRole(UserRoleEnum.ADMIN);
            UserRoleEntity userRole = new UserRoleEntity();
            userRole.setRole(UserRoleEnum.USER);
            userRoleRepository.saveAll(List.of(adminRole, userRole));
        }
    }

    @Override
    public boolean login(UserLoginServiceModel loginServiceModel) {

        Optional<UserEntity> userEntityOpt = userRepository.findByUsername(loginServiceModel.getUsername());

        if (userEntityOpt.isEmpty()) {
            logout();
            return false;
        } else {
            boolean success = passwordEncoder.matches(
                    loginServiceModel.getRawPassword(),
                    userEntityOpt.get().getPassword());

            if (success) {
                UserEntity loggedInUser = userEntityOpt.get();
                login(loggedInUser);

                loggedInUser.getRoles().forEach(r -> currentUser.addRole(r.getRole()));
            }

            return success;
        }
    }

    @Override
    public void logout() {
        currentUser.clean();
    }

    @Override
    public void registerAndLoginUser(UserRegistrationServiceModel userRegistrationServiceModel) {

        UserRoleEntity userRole = userRoleRepository.findByRole(UserRoleEnum.USER);
        UserEntity newUser = new UserEntity();

        newUser.
            setUsername(userRegistrationServiceModel.getUsername()).
            setFirstName(userRegistrationServiceModel.getFirstName()).
            setLastName(userRegistrationServiceModel.getLastName()).
            setActive(true).
            setPassword(passwordEncoder.encode(userRegistrationServiceModel.getPassword())).
            setRoles(Set.of(userRole));
//        UserEntity save = userRepository.save(newUser);
        newUser = userRepository.save(newUser);
        login(newUser);
    }

    public boolean isUserNameFree(String username) {
        return userRepository.findByUsernameIgnoreCase(username).isEmpty();
    }

    private void login(UserEntity user) {
        currentUser.
            setLoggedIn(true).
            setUserName(user.getUsername()).
            setFirstName(user.getFirstName()).
            setLastName(user.getLastName());
    }
}
