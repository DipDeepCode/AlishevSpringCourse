package ru.ddc.securityapp.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class PersonDto {

    @NotEmpty(message = "*Please provide your name")
    @Size(min = 2, max = 100, message = "*Your name must have at least 2 characters")
    private String username;

    @Min(value = 1900, message = "*Year of birth must be greater than 1900")
    private Integer yearOfBirth;

    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(Integer yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
