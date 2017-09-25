package com.guidiyam.sexrdv.volley;

import android.content.Context;
import android.location.Location;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;
import android.text.TextUtils;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.guidiyam.sexrdv.setget.ContactList_getset;
import com.guidiyam.sexrdv.setget.ContactList_getset2;
import com.guidiyam.sexrdv.setget.PartnerListing;
import com.guidiyam.sexrdv.setget.PartnerSetGet;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by su on 28/6/17.
 */

public class AppData extends MultiDexApplication
{
    public static  boolean logout=false;
    public static  boolean partnerclick=false;
    public static  String fbUserID;
    public static  String fbFullName;
    public static  String fbEmail;
    public static  String gender;
    public static  String fbToken;
    public static  String country;
    public static  String ph;
    public static  String image;
    public static  boolean isServiceCompleted=false;
    public static  boolean allcontactsfetch=false;
      public  static ArrayList<PartnerListing> partnerListings=null;
    public static ArrayList<ContactList_getset> contactarraylist;
    public  static  boolean addextabuttonforpartner=false;

    public static ArrayList<PartnerSetGet> selectedcontact;
    public static ArrayList<PartnerSetGet> selectedcontact2=new ArrayList<PartnerSetGet>();
    public static ArrayList<ContactList_getset2> contactarraylistforfilter = new ArrayList<ContactList_getset2>();;

    public static JSONArray partnerjsonarray =new JSONArray();

    public static ArrayList<ContactList_getset> contactarraylistforfilter2;

    public static  ArrayList<ContactList_getset> contactList =null;
    public static  ArrayList<ContactList_getset> selectedContact =null;

    public static String page="page";
    public  static boolean isFirst_time=false;


    ///////////////////////////////////////

    public static  String app_password_status="";
    public static  String app_password="";
    /////////////////////////////////////////////////

    public static  String devicetoken;
    public static  String user_id;
    public static  String name;
    public static String email;
    public static  String language;
    public static Location location;
    public static  String mode;
    public static  String pin="";
    public static  boolean first_time=false;

    public static  String url="https://esolz.co.in/lab3/srdv";
    public static boolean okbuttonclicked=false;
    public static final String TAG = com.guidiyam.sexrdv.volley.AppData.class.getSimpleName();

    public RequestQueue mRequestQueue;

    private static com.guidiyam.sexrdv.volley.AppData mInstance;
    @Override
    public void onCreate() {
        super.onCreate();
       // Fabric.with(this, new Crashlytics());
        mInstance = this;
    }
    public static  boolean isEmailValid(String emailuser) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(emailuser);
        return matcher.matches();
    }


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    public static synchronized AppData getInstance() {
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }

}
