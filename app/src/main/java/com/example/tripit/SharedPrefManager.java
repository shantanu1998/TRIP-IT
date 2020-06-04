package com.example.tripit;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefManager {


    //the constants
    private static final String SHARED_PREF_NAME = "pref";
    //User Constants
    private static final String KEY_USERNAME = "keyusername";
    private static final String KEY_EMAIL = "keyemail";
    private static final String KEY_ID = "keyid";
    //Admin Constants
    private static final String KEY_Admin_USERNAME = "keyusername";
    private static final String KEY_Admin_EMAIL = "keyemail";
    private static final String KEY_Admin_ID = "keyid";
    private static final String IS_LOGIN = "IsLoggedIn";


    //the homestay constants
    private static final String KEY_HomeStayname = "keyHomeStayname";
    private static final String KEY_location = "keylocation";
    private static final String KEY_homstayID = "keyhomestayid";
    private static final String KEY_rent = "keyrent";
    private static final String KEY_Image = "keyrent";
    private static final String KEY_category = "keyrent";
    private static final String KEY_description = "keyrent";
    private static final String KEY_AdminID1 = "keyrent";

    private static SharedPrefManager mInstance;
    private static Context mCtx;
    private User user;

    private HomeStay homeStay;

    private Admin_User admin_User;

    private HomeStay_Photos homeStay_photos;
    private SharedPrefManager(Context context) {
        mCtx = context;
    }

    public static synchronized SharedPrefManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharedPrefManager(context);
        }
        return mInstance;
    }

    //method to let the user login
    //this method will store the user data in shared preferences
    public void userLogin(User user) {
        this.user = user;
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(KEY_ID, user.getId());
        editor.putString(KEY_USERNAME, user.getUsername());
        editor.putString(KEY_Admin_EMAIL, user.getEmail());

        editor.apply();

    }
    public void AdminLogin(Admin_User admin_User) {
        this.admin_User = admin_User;
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(KEY_Admin_ID, admin_User.getId());
        editor.putString(KEY_Admin_USERNAME, admin_User.getUsername());
        editor.putString(KEY_Admin_EMAIL, admin_User.getEmail());

        editor.apply();

    }





        public void HomeStaySelected(HomeStay_Photos homeStayPhotos) {
        this.homeStay_photos = homeStayPhotos;
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_homstayID, homeStayPhotos.getHSID());
        editor.putString(KEY_HomeStayname, homeStayPhotos.getName());
        editor.putString(KEY_location, homeStayPhotos.getHS_location());
        editor.putString(KEY_rent, homeStayPhotos.getHS_Rent());

        editor.apply();

    }

    //this method will checker whether user is already logged in or not
    public boolean isLoggedIn() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);

        return sharedPreferences.getString(KEY_USERNAME, null) != null;
    }

    //this method will give the logged in user
    public User getUser() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new User(
                sharedPreferences.getInt(KEY_ID, -1),
                sharedPreferences.getString(KEY_USERNAME, null),
                sharedPreferences.getString(KEY_EMAIL, null)

        );
    }

    public Admin_User getAdmin_User() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new Admin_User(
                sharedPreferences.getInt(KEY_Admin_ID, -1),
                sharedPreferences.getString(KEY_Admin_USERNAME, null),
                sharedPreferences.getString(KEY_Admin_EMAIL, null)

        );
    }
//(String HSID, String HS_Image, String HS_Name,String Admin_ID,
//                           String HS_Category,String HS_location,String HS_Rent,String HS_Description)

    public HomeStay_Photos getHomeStay() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new HomeStay_Photos(
                sharedPreferences.getString(KEY_homstayID, null),
                sharedPreferences.getString(KEY_Image, null),
                sharedPreferences.getString(KEY_HomeStayname, null),
                sharedPreferences.getString(KEY_AdminID1, null),
                sharedPreferences.getString(KEY_category, null),
                sharedPreferences.getString(KEY_location, null),
                sharedPreferences.getString(KEY_rent, null),
                sharedPreferences.getString(KEY_description, null)

        );
    }


    //this method will logout the user
    public void logout() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        //mCtx.startActivity(new Intent(mCtx, Login.class));
    }

}
