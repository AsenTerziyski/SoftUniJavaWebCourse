package bg.softuni.mobilelele.service;

import bg.softuni.mobilelele.model.service.UserLoginServiceModel;
import bg.softuni.mobilelele.model.service.UserRegistrationServiceModel;

public interface UserService {

  void initializeUsersAndRoles();

  boolean login(UserLoginServiceModel loginServiceModel);

  void logout();

  void registerAndLoginUser(UserRegistrationServiceModel userRegistrationServiceModel);

  boolean isUserNameFree(String username);
}
