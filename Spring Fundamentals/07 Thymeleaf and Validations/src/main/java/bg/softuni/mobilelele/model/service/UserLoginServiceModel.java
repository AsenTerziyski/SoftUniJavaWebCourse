package bg.softuni.mobilelele.model.service;

public class UserLoginServiceModel {

  private String username;
  private String rawPassword;

  public String getUsername() {
    return username;
  }

  public UserLoginServiceModel setUsername(String username) {
    this.username = username;
    return this;
  }

  public String getRawPassword() {
    return rawPassword;
  }

  public UserLoginServiceModel setRawPassword(String rawPassword) {
    this.rawPassword = rawPassword;
    return this;
  }
}
