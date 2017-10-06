package com.guidiyam.sexrdv;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import android.util.Log;
import android.view.WindowManager;

import com.guidiyam.sexrdv.helper.GPSTracker;

public class MainActivity extends Activity {
    final int SPLASH_TIMEOUT = 2500;
    SharedPreferences loginPreferences;
    SharedPreferences.Editor loginPrefsEditor;
    String saveLogin;
    GPSTracker gps;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences = getSharedPreferences("logindetails", MODE_PRIVATE);
        loginPreferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
        loginPrefsEditor = loginPreferences.edit();
        saveLogin = loginPreferences.getString("saveLogin", "");
        Log.d("savelogin", saveLogin);


        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);



    }

    @Override
    protected void onStart() {
        super.onStart();



    }
    @Override
    protected void onResume() {
        super.onResume();

//        gps = new GPSTracker(MainActivity.this);
//
//        if (!gps.isGPSEnabled && !gps.isNetworkEnabled)
//        {
//
//            gps.showSettingsAlert();
//            gps.getLocation();
//
//
//            Log.d("GPSENABLED", "GPS IS ENABLED");
//
//
//        }

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {

//                Intent i = new Intent(MainActivity.this, LanguageActivity.class);
//                startActivity(i);
//                finish();
                if(saveLogin.equals("A"))
                {

if(sharedPreferences.getString("app_password_status", " ").equals("false"))
{
    Intent i = new Intent(MainActivity.this, HomePage.class);
    startActivity(i);
    finish();
}
else{

    Intent i = new Intent(MainActivity.this, ActivityPIN.class);
    startActivity(i);
    finish();

}

                }
                else {
                    Intent i = new Intent(MainActivity.this, Login.class);
                    startActivity(i);
                    finish();
                }

//                if(saveLogin.equals("A")){
//
//                    Intent i = new Intent(SplashScreen.this, Login.class);
//                    startActivity(i);
//                    finish();
//
//                }else {
//                    Intent i = new Intent(SplashScreen.this, LanguageActivity.class);
//                    startActivity(i);
//                    finish();
//                }


            }

        }, SPLASH_TIMEOUT);

    }
    }

