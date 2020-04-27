package com.example.tripit;

public class User {

    private int id;
    private String username, email;

    public User(int USER_id, String USER_name, String USER_email) {
        this.id = USER_id;
        this.username = USER_name;
        this.email = USER_email;

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