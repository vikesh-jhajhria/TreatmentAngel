package com.treatmentangel.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.io.ByteArrayOutputStream;

public class AppPreferences {

    public static final String API_KEY = "APIKEY";
    public static final String USER_ID = "USERID";
    public static final String LOGIN_TIME = "LOGIN_TIME";
    public static final String USER_NAME = "USERNAME";
    public static final String USER_PHONE = "USERPHONE";
    public static final String USER_IMAGE = "USERIMAGE";
    public static final String USER_IMAGE_URL = "USERIMAGEURL";
    public static final String LATITUDE = "LATITUDE";
    public static final String LONGITUDE = "LONGITUDE";
    public static final String FIREBASE_ID = "FIREBASE_ID";
    private static final String SHARED_PREFERENCE_NAME = "BUSINESS_MANAGER";
    private SharedPreferences mPrefs;

    private AppPreferences(Context context) {
        mPrefs = context.getSharedPreferences(SHARED_PREFERENCE_NAME,
                Context.MODE_PRIVATE);
    }

    public static AppPreferences getAppPreferences(Context context) {
        return new AppPreferences(context);

    }

    public long getLoginTime() {
        return mPrefs.getLong(LOGIN_TIME,0);
    }

    public void setLoginTime(long value) {
        SharedPreferences.Editor editor = mPrefs.edit();
        editor.putLong(LOGIN_TIME, value);
        editor.commit();
    }
    public String getFirebaseId() {
        return mPrefs.getString(FIREBASE_ID, "");
    }

    public void setFirebaseId(String firebaseId) {
        SharedPreferences.Editor editor = mPrefs.edit();
        editor.putString(FIREBASE_ID, firebaseId);
        editor.commit();
    }

    public String getUserId() {
        return mPrefs.getString(USER_ID, "");
    }

    public void setUserId(String value) {
        SharedPreferences.Editor editor = mPrefs.edit();
        editor.putString(USER_ID, value);
        editor.commit();
    }

    public String getUserName() {
        return mPrefs.getString(USER_NAME, "");
    }

    public void setUserName(String name) {

        SharedPreferences.Editor editor = mPrefs.edit();
        editor.putString(USER_NAME, name);
        editor.commit();
    }

    public String getLatitude() {
        return mPrefs.getString(LATITUDE, "0");
    }

    public void setLatitude(String value) {
        SharedPreferences.Editor editor = mPrefs.edit();
        editor.putString(LATITUDE, value);
        editor.commit();
    }

    public String getLongitude() {
        return mPrefs.getString(LONGITUDE, "0");
    }

    public void setLongitude(String value) {
        SharedPreferences.Editor editor = mPrefs.edit();
        editor.putString(LONGITUDE, value);
        editor.commit();
    }

    public String getStringValue(String Key) {
        return mPrefs.getString(Key, "");
    }

    public void putStringValue(String Key, String value) {
        SharedPreferences.Editor editor = mPrefs.edit();
        editor.putString(Key, value);
        editor.commit();
    }

    public void putImage(String key, Bitmap image) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] b = baos.toByteArray();
        String encodedImage = Base64.encodeToString(b, Base64.DEFAULT);
        SharedPreferences.Editor editor = mPrefs.edit();
        editor.putString(key, encodedImage);
        editor.commit();
    }

    public Bitmap getImage(String key) {
        Bitmap bitmap = null;
        String previouslyEncodedImage = mPrefs.getString(key, "");

        if (!previouslyEncodedImage.equalsIgnoreCase("")) {
            byte[] b = Base64.decode(previouslyEncodedImage, Base64.DEFAULT);
            bitmap = BitmapFactory.decodeByteArray(b, 0, b.length);
        }
        return bitmap;
    }

    public int getIntValue(String key) {
        return mPrefs.getInt(key, 0);
    }

    public void putIntValue(String key, int value) {
        SharedPreferences.Editor editor = mPrefs.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    public boolean getBooleanValue(String key) {

        return mPrefs.getBoolean(key, false);
    }

    public void putBooleanValue(String key, boolean value) {
        SharedPreferences.Editor editor = mPrefs.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    public void removeFromPreferences(String key) {
        SharedPreferences.Editor editor = mPrefs.edit();
        editor.remove(key);
        editor.commit();
    }
}
