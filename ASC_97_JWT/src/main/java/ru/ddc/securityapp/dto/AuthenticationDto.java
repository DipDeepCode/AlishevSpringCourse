package ru.ddc.securityapp.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class AuthenticationDto {

    @NotEmpty(message = "*Please provide your name")
    @Size(min = 2, max = 100, message = "*Your name must have at least 2 characters")
    private String username;

    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
