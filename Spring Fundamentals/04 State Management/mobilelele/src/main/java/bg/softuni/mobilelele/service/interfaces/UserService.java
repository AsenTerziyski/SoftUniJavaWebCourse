package bg.softuni.mobilelele.service.interfaces;

import bg.softuni.mobilelele.model.binding.UserRegisterBindingModel;
import bg.softuni.mobilelele.model.service.UserLoginServiceModel;

public interface UserService {
    boolean login(UserLoginServiceModel userLoginServiceModel);
    void logout ();
    boolean registerUser(UserRegisterBindingModel userRegisterBindingModel);
}
