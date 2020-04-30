package com.example.tripit;

public class HomeStay_Photos {

    private String HS_Image;
    private String HS_Name;
    private String HSID;
    private String Admin_ID;
    private String HS_Category;
    private String HS_location;
    private String HS_Rent ;
    private String HS_Description ;




    public HomeStay_Photos(String HSID, String HS_Image, String HS_Name,String Admin_ID,
                           String HS_Category,String HS_location,String HS_Rent,String HS_Description) {
        this.HS_Image = HS_Image;
        this.HS_Name = HS_Name;
        this.HSID = HSID;
        this.Admin_ID = Admin_ID;
        this.HS_Category = HS_Category;
        this.HS_location = HS_location;
        this.HS_Rent = HS_Rent;
        this.HS_Description = HS_Description;
    }

    public String getImage() {
        return HS_Image;
    }

    public String getAdmin_ID() {
        return Admin_ID;
    }

    public void setAdmin_ID(String admin_ID) {
        Admin_ID = admin_ID;
    }

    public String getHS_Category() {
        return HS_Category;
    }

    public void setHS_Category(String HS_Category) {
        this.HS_Category = HS_Category;
    }

    public String getHS_location() {
        return HS_location;
    }

    public void setHS_location(String HS_location) {
        this.HS_location = HS_location;
    }

    public String getHS_Rent() {
        return HS_Rent;
    }

    public void setHS_Rent(String HS_Rent) {
        this.HS_Rent = HS_Rent;
    }

    public String getHS_Description() {
        return HS_Description;
    }

    public void setHS_Description(String HS_Description) {
        this.HS_Description = HS_Description;
    }

    public void setImage(String HS_Image) {
        this.HS_Image = HS_Image;
    }

    public String getName() {
        return HS_Name;
    }

    public void setName(String HS_Name) {
        this.HS_Name = HS_Name;
    }

    public String getHSID() {
        return HSID;
    }

    public void setHSID(String HSID) {
        this.HSID = HSID;
    }
}
