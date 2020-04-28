package com.example.tripit;

public class Admin_User {

    private int id;
    private String username, email;

    public Admin_User(int Admin_ID, String Admin_Name, String Admin_Email) {
        this.id = Admin_ID;
        this.username = Admin_Name;
        this.email = Admin_Email;

    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }
}
