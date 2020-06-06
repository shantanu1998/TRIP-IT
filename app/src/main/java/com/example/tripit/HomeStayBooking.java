package com.example.tripit;

public class HomeStayBooking {
    private  String Booking_ID;
    private String HSID;
    private String User_ID;
    private String Image;
    private String HS_location;
    private String Total ;

    public HomeStayBooking(String booking_id, String HSID, String user_ID, String image, String HS_Category, String HS_location, String total) {
        this.Booking_ID = booking_id;
        this.HSID = HSID;
        this.User_ID = user_ID;
        this.Image = image;
       // this.HS_Category = HS_Category;
        this.HS_location = HS_location;
        this.Total = total;
    }

    public String getBooking_ID() {
        return Booking_ID;
    }

    public void setBooking_ID(String booking_ID) {
        Booking_ID = booking_ID;
    }

    public String getHSID() {
        return HSID;
    }

    public void setHSID(String HSID) {
        this.HSID = HSID;
    }

    public String getUser_ID() {
        return User_ID;
    }

    public void setUser_ID(String user_ID) {
        User_ID = user_ID;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

   
    public String getHS_location() {
        return HS_location;
    }

    public void setHS_location(String HS_location) {
        this.HS_location = HS_location;
    }

    public String getTotal() {
        return Total;
    }

    public void setTotal(String total) {
        Total = total;
    }
}
