package com.codecool.shop.model;

import java.util.Date;

public class User {

    public User(String username, String password, String email, Date registration_date) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.registration_date = registration_date;
    }

    private int id;
    private String username;
    private String password;
    private String email;
    private Date registration_date;

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public Date getRegistration_date() {
        return registration_date;
    }

    public void setId(int id) {
        this.id = id;
    }
}
