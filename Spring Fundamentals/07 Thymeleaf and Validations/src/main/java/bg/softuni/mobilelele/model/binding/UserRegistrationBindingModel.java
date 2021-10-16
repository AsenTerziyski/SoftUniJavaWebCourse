package bg.softuni.mobilelele.model.binding;

import bg.softuni.mobilelele.model.validator.UniqueUserName;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class UserRegistrationBindingModel {

  @NotNull
  @Size(min=4, max=20)
  private String firstName;
  @NotNull
  @Size(min=4, max=20)
  private String lastName;
  @NotNull
  @Size(min=4, max=20)
  private String password;
  @NotNull
  @Size(min=4, max=20)
  private String confirmPassword;
  @NotNull
  @Size(min=4, max=20)
  @UniqueUserName
  private String username;

  public String getUsername() {
    return username;
  }

  public UserRegistrationBindingModel setUsername(String username) {
    this.username = username;
    return this;
  }

  public String getFirstName() {
    return firstName;
  }

  public UserRegistrationBindingModel setFirstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  public String getLastName() {
    return lastName;
  }

  public UserRegistrationBindingModel setLastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  public String getPassword() {
    return password;
  }

  public UserRegistrationBindingModel setPassword(String password) {
    this.password = password;
    return this;
  }

  public String getConfirmPassword() {
    return confirmPassword;
  }

  public UserRegistrationBindingModel setConfirmPassword(String confirmPassword) {
    this.confirmPassword = confirmPassword;
    return this;
  }
}
