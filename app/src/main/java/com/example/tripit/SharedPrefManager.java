package com.example.tripit;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

public class SharedPrefManager {


    //the constants
    private static final String SHARED_PREF_NAME = "pref";
    private static final String KEY_USERNAME = "keyusername";
    private static final String KEY_EMAIL = "keyemail";

    private static final String KEY_ID = "keyid";
    private static final String IS_LOGIN = "IsLoggedIn";


    //the homestay constants
    private static final String KEY_HomeStayname = "keyHomeStayname";
    private static final String KEY_location = "keylocation";

    private static final String KEY_homstayID = "keyhomestayid";
    private static final String KEY_rent = "keyrent";

    private static SharedPrefManager mInstance;
    private static Context mCtx;
    private User user;

    private HomeStay homeStay;

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
        editor.putString(KEY_EMAIL, user.getEmail());

        editor.apply();

    }





        public void HomeStaySelected(HomeStay homeStay) {
        this.homeStay = homeStay;
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(KEY_homstayID, homeStay.getID());
        editor.putString(KEY_HomeStayname, homeStay.getName());
        editor.putString(KEY_location, homeStay.getLocation());
        editor.putString(KEY_rent, homeStay.getRent());

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

    public HomeStay getHomeStay() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new HomeStay(
                sharedPreferences.getInt(KEY_homstayID, -1),
                sharedPreferences.getString(KEY_HomeStayname, null),
                sharedPreferences.getString(KEY_location, null),
                sharedPreferences.getString(KEY_rent, null)

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
