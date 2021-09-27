package bg.softuni.mobilelele.service.interfaces;

import bg.softuni.mobilelele.model.serviceModel.UserLoginServiceModel;

public interface UserService {
    boolean login(UserLoginServiceModel userLoginServiceModel);
}
