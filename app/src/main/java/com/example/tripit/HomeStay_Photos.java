package com.example.tripit;

public class HomeStay_Photos {

    private String HS_Image;
    private String HS_Name;
    private String HSID;

    public HomeStay_Photos(String HSID, String HS_Image, String HS_Name) {
        this.HS_Image = HS_Image;
        this.HS_Name = HS_Name;
        this.HSID = HSID;
    }

    public String getImage() {
        return HS_Image;
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
