package bg.softuni.mobilelele.model.binding;

public class UserRegisterBindingModel {
    private String firstName;
    private String lastName;
    private String username;
    private String rawPassword;
//    private Set<UserRoleEntity> roles;

    public UserRegisterBindingModel() {
    }

    public String getFirstName() {
        return firstName;
    }

    public UserRegisterBindingModel setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserRegisterBindingModel setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserRegisterBindingModel setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getRawPassword() {
        return rawPassword;
    }

    public UserRegisterBindingModel setRawPassword(String rawPassword) {
        this.rawPassword = rawPassword;
        return this;
    }

//    public Set<UserRoleEntity> getRoles() {
//        return roles;
//    }
//
//    public UserRegisterBindingModel setRoles(Set<UserRoleEntity> roles) {
//        this.roles = roles;
//        return this;
//    }
}
