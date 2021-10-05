package bg.softuni.mobilelele.user;


import bg.softuni.mobilelele.model.entity.enums.UserRoleEnum;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.HashSet;
import java.util.Set;

@Component
@SessionScope
public class CurrentUser {

    private boolean isLoggedIn;
    private String userName;
    private String firstName;
    private String lastName;
    private Set<UserRoleEnum> roles = new HashSet<>();

    public CurrentUser() {
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public CurrentUser setLoggedIn(boolean loggedIn) {
        isLoggedIn = loggedIn;
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public CurrentUser setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public CurrentUser setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public CurrentUser setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public CurrentUser clearRoles () {
        roles.clear();
        return this;
    }

    public void clean() {
        setLoggedIn(false).setUserName(null).setLastName(null).clearRoles();
    }

    public CurrentUser addRole(UserRoleEnum role) {
        this.roles.add(role);
        //return currentUser
        return this;
    }

    public boolean isAdmin() {
        return roles.contains(UserRoleEnum.ADMIN);
    }


}
