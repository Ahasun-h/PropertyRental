package com.example.myapplication.SessionClass;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.myapplication.Activity.Login;
import com.example.myapplication.Model.SessionData;

public class SessionClass {
    private static final String SHARED_PREF_NAME = "simplifiedcodingsharedpref";
    private static final String KEY_ID = "keyid";
    private static final String KEY_TOKEN = "keytoken";
    private static final String KEY_NAME = "keyname";
    private static final String KEY_EMAIL = "keyeemail";
    private static final String KEY_ADDRESS = "keyeaddress";
    private static final String KEY_MOBILE = "keyemobile";
    private static final String KEY_PROFILE_IMAGE = "keyeprofileimage";


    private static SessionClass mInstance;
    private static Context mCtx;

    private SessionClass(Context context) {
        mCtx = context;
    }

    public static synchronized SessionClass getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SessionClass(context);
        }
        return mInstance;
    }

    //method to let the user login
    //this method will store the user data in shared preferences
    public void userLogin(SessionData sessionData) {

        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(KEY_ID, sessionData.getId());
        editor.putString(KEY_TOKEN, sessionData.getToken());
        editor.putString( KEY_NAME, sessionData.getName());
        editor.putString( KEY_EMAIL, sessionData.getEmail());
        editor.putString( KEY_ADDRESS, sessionData.getAddress());
        editor.putString( KEY_MOBILE, sessionData.getMobile());
        editor.putString( KEY_PROFILE_IMAGE,sessionData.getProfile_image() );
        editor.apply();
    }


    public boolean isLoggedIn() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_TOKEN, null) != null;
    }

    //this method will give the logged in user
    public SessionData getUser() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new SessionData(
                sharedPreferences.getString(KEY_ID, null)
        );
    }

    //this method will logout the user
    public void logout() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        mCtx.startActivity(new Intent(mCtx, Login.class));

    }
}
