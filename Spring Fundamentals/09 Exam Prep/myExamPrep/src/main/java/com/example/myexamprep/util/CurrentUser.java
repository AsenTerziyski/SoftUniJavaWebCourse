package com.example.myexamprep.util;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class CurrentUser {
    private Long Id;
    private String username;
    private boolean isLogged = false;

    public CurrentUser() {
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    private void setIsLoggedTrue() {
        if (this.Id != null) {
            this.isLogged = true;
        }
    }


}
