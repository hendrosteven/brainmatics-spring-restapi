package com.brainmatics.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class UserData {
    
    @NotEmpty(message = "Email is required")
    @Email(message ="Email format is not valid")
    private String email;

    @NotEmpty(message = "Password is required")
    private String password;

    @NotEmpty(message = "Full Name is required")
    private String fullName;

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    
}
