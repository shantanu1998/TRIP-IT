package com.example.tripit;

public class HomeStay {

    private int ID;
    private String name,location,rent;

    public HomeStay(int HomeStay_id, String name, String location, String rent) {
        this.ID = HomeStay_id;
        this.name = name;
        this.location = location;
        this.rent = rent;
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getRent() {
        return rent;
    }
}
