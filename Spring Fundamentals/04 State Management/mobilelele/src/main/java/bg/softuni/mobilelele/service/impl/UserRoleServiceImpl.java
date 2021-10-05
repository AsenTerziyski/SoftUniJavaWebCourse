package bg.softuni.mobilelele.service.impl;

import bg.softuni.mobilelele.model.entity.UserRoleEntity;
import bg.softuni.mobilelele.model.entity.enums.UserRoleEnum;
import bg.softuni.mobilelele.repository.UserRoleRepository;
import bg.softuni.mobilelele.service.interfaces.UserRoleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRoleServiceImpl implements UserRoleService {
    private final UserRoleRepository userRoleRepository;

    public UserRoleServiceImpl(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public void initializeRoles() {
        if (this.userRoleRepository.count() == 0) {
            UserRoleEntity adminRole = new UserRoleEntity();
            adminRole.setRole(UserRoleEnum.ADMIN);
            UserRoleEntity userRole = new UserRoleEntity();
            userRole.setRole(UserRoleEnum.USER);
            this.userRoleRepository.saveAll(List.of(adminRole, userRole));
        }
    }
}
