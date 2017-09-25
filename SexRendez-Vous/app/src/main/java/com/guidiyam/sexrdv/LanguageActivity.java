package com.guidiyam.sexrdv;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.guidiyam.sexrdv.helper.GPSTracker;
import com.guidiyam.sexrdv.volley.AppData;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;

/**
 * Created by su on 7/7/17.
 */

public class LanguageActivity extends Activity  {

    Geocoder geocoder;
    public static final String TAG = com.guidiyam.sexrdv.LanguageActivity.class.getSimpleName();
    List<Address> addresses;
    static TextView bt_next, txt_language, tv_language;
    RelativeLayout bt_language;
    String country;

    SharedPreferences sharedPreferences, sharedPreferences2;
    GPSTracker gps;

    static String selectLang = "", Lmode = "";
    private static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 1;

    // GPSTracker gps;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);
        bt_next = (TextView) findViewById(R.id.bt_next);
        txt_language = (TextView) findViewById(R.id.txt_language);
        tv_language = (TextView) findViewById(R.id.tv_language);
        bt_language = (RelativeLayout) findViewById(R.id.bt_language);
//        gps = new GPSTracker(LanguageActivity.this);
        String locale = LanguageActivity.this.getResources().getConfiguration().locale.getDisplayCountry();

        Log.d("Local",locale);
        sharedPreferences2 = getSharedPreferences("save_lang", Context.MODE_PRIVATE);


        if(locale.equals("France"))
        {
            txt_language.setText("French");
            tv_language.setText("La langue");
            bt_next.setText("Prochain");
            selectLang="fra";
            Lmode = "2";




        }
        else{

            txt_language.setText("English");
            tv_language.setText("Language");
            bt_next.setText("Next");
            selectLang="eng";
            Lmode = "1";

        }
//
        //accessLoc();
//
//        if (!gps.isGPSEnabled && !gps.isNetworkEnabled) {
//
//            gps.showSettingsAlert();
//            gps.getLocation();
//
//
//            Log.d("GPSENABLED", "GPS IS ENABLED");
//
//
//        }



        bt_language.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final TextView bt_eng, bt_ita, bt_done;
                final Dialog dialog = new Dialog(LanguageActivity.this);

                View view1 = getLayoutInflater().inflate(R.layout.language_dialog_view, null);
                bt_eng = (TextView) view1.findViewById(R.id.bt_eng);
                bt_ita = (TextView) view1.findViewById(R.id.bt_ita);
                bt_done = (TextView) view1.findViewById(R.id.bt_done);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

                dialog.setContentView(view1);

                dialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);


                if (selectLang.equals("eng")) {
                    bt_eng.setTextColor(Color.BLACK);
                    bt_eng.setTypeface(Typeface.DEFAULT_BOLD);
                    bt_done.setText("Done");

                } else if (selectLang.equals("fra")) {
                    bt_ita.setTextColor(Color.BLACK);
                    bt_ita.setTypeface(Typeface.DEFAULT_BOLD);
                    bt_done.setText("Terminé");

                }

                dialog.setCanceledOnTouchOutside(false);
                dialog.show();

                bt_eng.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        selectLang = "eng";
                        Lmode = "1";
                        bt_eng.setTextColor(Color.BLACK);
                        bt_eng.setTypeface(Typeface.DEFAULT_BOLD);
                        bt_ita.setTypeface(Typeface.DEFAULT);
                        bt_ita.setTextColor(Color.GRAY);

                    }
                });
                bt_ita.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        selectLang = "fra";
                        Lmode = "2";
                        bt_ita.setTextColor(Color.BLACK);
                        bt_ita.setTypeface(Typeface.DEFAULT_BOLD);

                        bt_eng.setTextColor(Color.GRAY);
                        bt_eng.setTypeface(Typeface.DEFAULT);


                    }
                });

                bt_done.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (selectLang.equals("")) {

                            Toast.makeText(LanguageActivity.this, "Select Language", Toast.LENGTH_SHORT).show();

                        } else {


                            if (selectLang.equals("eng")) {
                                txt_language.setText("English");
                                tv_language.setText("Language");
                                bt_next.setText("Next");

                            } else if (selectLang.equals("fra")) {
                                txt_language.setText("French");
                                tv_language.setText("La langue");
                                bt_next.setText("Prochain");


                            }

                            SharedPreferences.Editor editor = getSharedPreferences("save_lang", Context.MODE_PRIVATE).edit();
                            editor.putString("lang", selectLang);
                            editor.putString("mode", Lmode);
                            editor.commit();


                            AppData.language = sharedPreferences2.getString("lang", "");
                            AppData.mode = sharedPreferences2.getString("mode", "");
                            Log.d("Language Type::", "" + AppData.language);

                            dialog.cancel();
                        }


                    }
                });
            }
        });


        bt_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (selectLang.equals("")) {

                    Toast.makeText(LanguageActivity.this, "Select Application Language", Toast.LENGTH_SHORT).show();

                } else {

                    Intent i = new Intent(LanguageActivity.this, Login.class);
                    startActivity(i);
                    finish();
                }


            }
        });

//        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
//        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 30000, 0, LanguageActivity.this);
//
//
//        Criteria criteria = new Criteria();
//        String bestProvider = locationManager.getBestProvider(criteria, true);
//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            // TODO: Consider calling
//            //    ActivityCompat#requestPermissions
//            // here to request the missing permissions, and then overriding
//            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//            //                                          int[] grantResults)
//            // to handle the case where the user grants the permission. See the documentation
//            // for ActivityCompat#requestPermissions for more details.
//            return;
//        }
//        Location location = locationManager.getLastKnownLocation(bestProvider);
//
//        if (location == null) {
//            Toast.makeText(getApplicationContext(), "GPS signal not found",
//                    Toast.LENGTH_SHORT).show();
//        }
//        if (location != null) {
//            Log.e("locatin", "location--" + location);
//
//            Log.e("latitude at beginning",
//                    "@@@@@@@@@@@@@@@" + location.getLatitude());
//            onLocationChanged(location);
//        }
    }

//
//    public void onLocationChanged(Location location) {
//
//        Geocoder geocoder;
//        List<Address> addresses;
//        geocoder = new Geocoder(this, Locale.getDefault());
//
//        AppData.lat = location.getLatitude();
//        AppData.lon = location.getLongitude();
//
//        Log.e("latitude", "latitude--" + AppData.lat);
//
//        try {
//            Log.e("latitude", "inside latitude--" + AppData.lat);
//            addresses = geocoder.getFromLocation(AppData.lat, AppData.lon, 1);
//
//
//
//
//
//            if (addresses != null && addresses.size() > 0) {
//                String address = addresses.get(0).getAddressLine(0);
//                String city = addresses.get(0).getLocality();
//                String state = addresses.get(0).getAdminArea();
//                 country = addresses.get(0).getCountryName();
//                String postalCode = addresses.get(0).getPostalCode();
//                String knownName = addresses.get(0).getFeatureName();
//                Toast.makeText(LanguageActivity.this,"COUNTRY"+country,Toast.LENGTH_SHORT).show();
//
//                //locationTxt.setText(address + " " + city + " " + country);
//            }
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//    }









    @Override
    protected void onResume() {
        super.onResume();


//
//        gps = new GPSTracker(LanguageActivity.this);
//
//
//        if (gps.canGetLocation())
//
//        {
//
//
////
//            AppData.lat = gps.getLatitude();
//            AppData.lon = gps.getLongitude();
//            Log.d("LAT_LONG", AppData.lat + " " + AppData.lon);
//
//
//
//            // latitude=22.584578;
//            //longitude=88.489680;
//
//            if (AppData.lat == 0.0 && AppData.lat == 0.0) {
//                AlertDialog alertDialog = new AlertDialog.Builder(
//                        LanguageActivity.this).create();
//                alertDialog.setCanceledOnTouchOutside(false);
//
//                // Setting Dialog Title
////                     alertDialog.setTitle("Alert Dialog");
//
//                // Setting Dialog Message
////                    if (sharedPreferences2.getString("lang", " ").equals("eng"))
//                alertDialog.setMessage("GPS is not working properly, make sure your GPS is working properly");
////                    else
////                        alertDialog.setMessage("Il GPS non funziona correttamente, assicurati che il GPS funzioni correttamente");
//
//                // Setting Icon to Dialog
//                // alertDialog.setIcon(R.drawable.tick);
//
//                // Setting OK Button
//                alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.dismiss();
//
//
//                        // Write your code here to execute after dialog closed
//                        //Toast.makeText(getApplicationContext(), "You clicked on OK", Toast.LENGTH_SHORT).show();
//
//                    }
//                });
//
//                // Showing Alert Message
//                alertDialog.show();
//
//            }
//
//
//            else {
//                //Toast.makeText(getApplicationContext(), "Go to else block", Toast.LENGTH_SHORT).show();
//              //  getAddress(LanguageActivity.this,47,2);
//                getAddress(LanguageActivity.this,AppData.lat,AppData.lon);
//
//
//
////                geocoder = new Geocoder(this, Locale.getDefault());
////
////
////                try {
////                    addresses = geocoder.getFromLocation(AppData.lat, AppData.lon, 1);
////                    country= addresses.get(0).getCountryName();
////                    Log.d("COUNTRY",country);
////                } catch (IOException e) {
////                    e.printStackTrace();
////                }
////            }
//                // }
//            }
//
//        }
//
//
//        else {
//            gps.showSettingsAlert();
//
//
//        }

    }


    public static void getAddress(Context context, double LATITUDE, double LONGITUDE) {

        //Set Address
        try {
            Geocoder geocoder = new Geocoder(context, Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(LATITUDE, LONGITUDE, 1);
            if (addresses != null && addresses.size() > 0) {



                String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
                String city = addresses.get(0).getLocality();
                String state = addresses.get(0).getAdminArea();
                String country = addresses.get(0).getCountryCode();
                String postalCode = addresses.get(0).getPostalCode();
                String knownName = addresses.get(0).getFeatureName(); // Only if available else return NULL

                Log.d(TAG, "getAddress:  address" + country);
                Log.d(TAG, "getAddress:  city" + city);
                Log.d(TAG, "getAddress:  state" + state);
                Log.d(TAG, "getAddress:  postalCode" + postalCode);
                Log.d(TAG, "getAddress:  knownName" + knownName);

                if(country.equals("FR"))
                {
                    txt_language.setText("French");
                    tv_language.setText("La langue");
                    bt_next.setText("Prochain");
                    selectLang="fra";
                    Lmode = "2";




                }
                else{

                    txt_language.setText("English");
                    tv_language.setText("Language");
                    bt_next.setText("Next");
                    selectLang="eng";
                    Lmode = "1";

                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return;
    }

//    @Override
//    public void onLocationChanged(Location location) {
//        Geocoder geocoder;
//        List<Address> addresses;
//        geocoder = new Geocoder(this, Locale.getDefault());
//
//        AppData.lat = location.getLatitude();
//        AppData.lon = location.getLongitude();
//
//        Log.e("latitude", "latitude--" + AppData.lat);
//
//        try {
//            Log.e("latitude", "inside latitude--" + AppData.lat);
//            addresses = geocoder.getFromLocation(AppData.lat, AppData.lon, 1);
//
//
//
//
//
//            if (addresses != null && addresses.size() > 0) {
//                String address = addresses.get(0).getAddressLine(0);
//                String city = addresses.get(0).getLocality();
//                String state = addresses.get(0).getAdminArea();
//                 country = addresses.get(0).getCountryName();
//                String postalCode = addresses.get(0).getPostalCode();
//                String knownName = addresses.get(0).getFeatureName();
//                Toast.makeText(LanguageActivity.this,"COUNTRY"+country,Toast.LENGTH_SHORT).show();
//
//                //locationTxt.setText(address + " " + city + " " + country);
//            }
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public void onStatusChanged(String s, int i, Bundle bundle) {
//
//    }
//
//    @Override
//    public void onProviderEnabled(String s) {
//
//    }
//
//    @Override
//    public void onProviderDisabled(String s) {
//
//    }
}

