package com.guidiyam.sexrdv;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;


import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.guidiyam.sexrdv.Adapter.SelectUserAdapter;
//import com.guidiyam.sexrdv.Adapter.SelectUserAdapter2;
import com.guidiyam.sexrdv.helper.ConnectionDetector;
import com.guidiyam.sexrdv.helper.Utils;
//import com.guidiyam.sexrdv.setget.SelectUser;
import com.guidiyam.sexrdv.setget.ContactList_getset;
import com.guidiyam.sexrdv.setget.NewContactList_getset;
import com.guidiyam.sexrdv.volley.AppData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class ActivityPartner extends AppCompatActivity {


    ImageView bck;

    String number = "",id="",image_thumb="";
    String numberIndex="",EmailAddress="";

//    ArrayList<SelectUser> selectUsers=null;
//    List<SelectUser> temp;
    RecyclerView rv_contact;

    SelectUserAdapter adapter;

    //SelectUserAdapter2 adapter2;
    EditText SearchText;
    RelativeLayout pbar;

    SharedPreferences sharedPreferences;
    JSONArray jsonArray;
    public int partner_count=0;

    ConnectionDetector cd;
    ArrayList<NewContactList_getset> newContactList_getsets=null;



    @SuppressLint("InlinedApi")
    private final static String SELECTION =(Utils.hasHoneycomb() ? ContactsContract.Contacts.DISPLAY_NAME_PRIMARY : ContactsContract.Contacts.DISPLAY_NAME) +
            "<>''" + " AND " + ContactsContract.Contacts.IN_VISIBLE_GROUP + "=1";

    @SuppressLint("InlinedApi")
    private final static String SORT_ORDER =Utils.hasHoneycomb() ? ContactsContract.Contacts.SORT_KEY_PRIMARY : ContactsContract.Contacts.DISPLAY_NAME;

    @SuppressLint("InlinedApi")
    private final static String[] PROJECTION = {
            ContactsContract.Contacts._ID,ContactsContract.Contacts.LOOKUP_KEY,
            Utils.hasHoneycomb() ? ContactsContract.Contacts.DISPLAY_NAME_PRIMARY : ContactsContract.Contacts.DISPLAY_NAME,
            ContactsContract.Contacts.HAS_PHONE_NUMBER,
            Utils.hasHoneycomb() ? ContactsContract.Contacts.PHOTO_THUMBNAIL_URI : ContactsContract.Contacts._ID,
            SORT_ORDER,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.partner_list_activity);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        sharedPreferences=getSharedPreferences("logindetails", Context.MODE_PRIVATE);
        cd=new ConnectionDetector(this);

        SearchText = (EditText)findViewById(R.id.inputSearch);
        SearchText.requestFocus();
        bck=(ImageView)findViewById(R.id.bck);
        pbar= (RelativeLayout) findViewById(R.id.pbar);
        pbar.setVisibility(View.GONE);
        bck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(partner_count==0)
                {


                    Log.d("1","Activity");
                   // AppData.isFirst_time=false;
//                    AppData.contactarraylistforfilter = new ArrayList<ContactList_getset>();
//                    AppData.contactarraylistforfilter.clear();
//                    AppData.contactarraylistforfilter.addAll(AppData.contactarraylistforfilter2);
                    AppData.partnerclick=true;
                    Intent i=new Intent(ActivityPartner.this,HomePage.class);
                    i.putExtra("HomePage","Partner");
                    startActivity(i);
                    finishAffinity();
                   // finish();
                }
                else{

                    int p = 0;
                    jsonArray = new JSONArray();

                    for (int i = 0; i < AppData.contactarraylist.size(); i++) {

                        if (AppData.contactarraylist.get(i).isTouch() == true) {

                            JSONObject jsonObject = new JSONObject();
                            try {
                                jsonObject.put("name", AppData.contactarraylist.get(i).getName());
                                jsonObject.put("phone", AppData.contactarraylist.get(i).getNumber().toString().trim());
                                jsonObject.put("email", AppData.contactarraylist.get(i).getEmail().toString().trim());

                                if (AppData.contactarraylist.get(i).getImage() != null) {

                                    try {
                                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(ActivityPartner.this.getContentResolver(), Uri.parse(AppData.contactarraylist.get(i).getImage()));

                                        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                                        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                                        byte[] byteArray = byteArrayOutputStream.toByteArray();

                                        String encoded = Base64.encodeToString(byteArray, Base64.DEFAULT);

                                        jsonObject.put("image", encoded.toString());

                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }

                                jsonArray.put(p, jsonObject);

                                p++;


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    Log.d("Data", "::::::" + jsonArray.toString());

                    pbar.setVisibility(View.VISIBLE);

                    if(cd.isConnectingToInternet()){

                    sendContacts();

                    } else {

                    Toast.makeText(ActivityPartner.this, getResources().getString(R.string.internet_conn), Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });


        rv_contact=(RecyclerView)findViewById(R.id.rv_contact);
        rv_contact.setLayoutManager(new LinearLayoutManager(ActivityPartner.this));
        rv_contact.setHasFixedSize(true);


        SearchText.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)

            {
                String text = SearchText.getText().toString().toLowerCase(Locale.getDefault());
                Log.d("TEXT",text);
              //  if(text.length()>0) {

                    //SelectUserAdapter.getFilter().filter(text);


                    adapter.filter(text);

//                adapter = new SelectUserAdapter(AppData.contactarraylist, ActivityPartner.this);
//                rv_contact.setAdapter(adapter);
               // adapter.notifyDataSetChanged();

                   // adapter.notifyDataSetChanged();
              //  }
//                else {
//                    //adapter.notifyDataSetChanged();
//                    //Toast.makeText(ActivityPartner.this,"txt length is zero",Toast.LENGTH_SHORT).show();
////                        adapter = new SelectUserAdapter(AppData.contactarraylist, ActivityPartner.this);
////                        rv_contact.setAdapter(adapter);
//                    //notifyDataSetChanged();
//                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

    }


    public void sendContacts() {


        String url = AppData.url+"/index.php/useraction/partnersadd";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {

                Log.d("url_response","::::"+s.toString());

                try {
                    JSONObject jsonObject=new JSONObject(s);

                    if(jsonObject.getBoolean("status")==true){

                        Toast.makeText(ActivityPartner.this,""+jsonObject.getString("message"),Toast.LENGTH_SHORT).show();

                        AppData.partnerclick=true;
                        Intent i=new Intent(ActivityPartner.this,HomePage.class);
                        i.putExtra("HomePage","Partner");
                        startActivity(i);
                        finishAffinity();
                        //finish();

                        pbar.setVisibility(View.GONE);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    pbar.setVisibility(View.GONE);

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

                pbar.setVisibility(View.GONE);

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<String, String>();

                params.put("userid",sharedPreferences.getString("user_id",""));
                params.put("username",sharedPreferences.getString("name",""));
                params.put("partners",jsonArray.toString());
                params.put("mode",sharedPreferences.getString("mode",""));

                return params;

            }
        };

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                5000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        AppData.getInstance().addToRequestQueue(stringRequest);



    }

    @Override
    protected void onResume() {
        super.onResume();
       // AppData.contactarraylistforfilter=new ArrayList<ContactList_getset>();
//        AppData.contactarraylistforfilter.clear();


        //AppData.contactarraylistforfilter.addAll(AppData.contactarraylist);
        //Log.d("Contactlistsize", String.valueOf(AppData.contactarraylist.size()));
        if(AppData.contactarraylist.size()>0)
        {
            newContactList_getsets=new ArrayList<NewContactList_getset>();
            for(int i=0;i<AppData.contactarraylist.size();i++)


           {

               String name=AppData.contactarraylist.get(i).getName();
               String number=AppData.contactarraylist.get(i).getNumber();
               String image=AppData.contactarraylist.get(i).getImage();
               String email=AppData.contactarraylist.get(i).getEmail();
               boolean touch=AppData.contactarraylist.get(i).isTouch();

               NewContactList_getset a=new NewContactList_getset(name,number,image,email,touch);
               newContactList_getsets.add(a);


                Log.d("Contactlistsize", "1 "+String.valueOf(AppData.contactarraylist.size()));


           }

            adapter = new SelectUserAdapter(newContactList_getsets, ActivityPartner.this);
            rv_contact.setAdapter(adapter);

//            else
//            {
//                Log.d("Contactlistsize", "2 "+String.valueOf(AppData.contactarraylistforfilter.size()));
//                                adapter = new SelectUserAdapter(AppData.contactarraylistforfilter, ActivityPartner.this);
//                rv_contact.setAdapter(adapter);
////
//            }

        }





    }
}
