package com.treatmentangel.utils;

import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v4.app.FragmentManager;

import com.treatmentangel.model.SearchModel;

import org.json.JSONArray;

import java.util.ArrayList;


public class Config {
    public static final String CUSTOMER = "CUSTOMER";
    public static final String PAYMENT = "PAYMENT";
    public static final String EXPENSE = "EXPENSE";
    public static final String SERVICE = "SERVICE";
    public static final String WORK = "WORK";
    public static final String REPORT = "REPORT";
    public static final String REMINDER = "REMINDER";



    public final static String CENTURY_GOTHIC_REGULAR = "CENTURY_GOTHIC_REGULAR";
    public final static String CENTURY_GOTHIC_BOLD = "CENTURY_GOTHIC_BOLD";
    public static final int LOCATION_PERMISSION = 1001;
    public static final int MEDIA_PERMISSION = 1002;
    public static final int REQUEST_CHECK_SETTINGS = 0x1;
    public static FragmentManager fragmentManager;
    public static Uri CAMERAFILEURI;
    public static String CURRENT_PAGE = "";
    public static final int CAMERAIMAGE = 123;
    public static final int GALLERYIMAGE = 124;
    public static final int CROPIMAGE = 127;
    public static final int ROTATEIMAGE = 128;
    public static final String IMAGE_DIRECTORY_NAME = "KNEEDLE";
    public static Bitmap postBitmap;
    public static Bitmap fullScreenFeedBitmap;
    public static Bitmap fullScreenUserBitmap;

    public static boolean updateProfile, updateFollower, updateFollowing;

    public static ArrayList<String> PROFILE_TYPE = new ArrayList<>();

    public static final String TWITTER_KEY = "TN7kGWPfeUHiIAU3v3HfSnUMp";
    public static final String TWITTER_SECRET = "tlQaaIxgtFtsgmRzZlQACLpA5lAmApp2m2EPKEKsCZhizMmdrJ";

    public final static int SPLASH_TIME = 2000;
    public final static String BASE_URL = "http://www.business.treatmentangel.com/";

    public static JSONArray searchResultArray;


}
