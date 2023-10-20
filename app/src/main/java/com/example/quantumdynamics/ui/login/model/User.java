package com.example.quantumdynamics.ui.login.model;

public class User {

    private String email;
    private String password;
    private Long id;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}