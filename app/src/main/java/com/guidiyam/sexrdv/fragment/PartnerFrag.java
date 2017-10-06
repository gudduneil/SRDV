package com.guidiyam.sexrdv.fragment;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

//import com.daimajia.swipe.util.Attributes;
import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.guidiyam.sexrdv.ActivityNewMeeting;
import com.guidiyam.sexrdv.ActivityPartner;
import com.guidiyam.sexrdv.Adapter.ContactAdapter;
import com.guidiyam.sexrdv.Adapter.RecyclerViewAdapter;
import com.guidiyam.sexrdv.HomePage;
import com.guidiyam.sexrdv.Login;
import com.guidiyam.sexrdv.R;
import com.guidiyam.sexrdv.helper.ConnectionDetector;
import com.guidiyam.sexrdv.service.ContactService;
import com.guidiyam.sexrdv.setget.ContactList_getset;
import com.guidiyam.sexrdv.setget.ContactSetGet;
import com.guidiyam.sexrdv.setget.PartnerSetGet;
import com.guidiyam.sexrdv.volley.AppData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by su on 19/7/17.
 */

public class PartnerFrag extends Fragment {
    RecyclerView recyclerview;
    ArrayList<PartnerSetGet> conList=null;
    RecyclerViewAdapter mAdapter=null;
    RelativeLayout plannewmeeting;
    TextView no_data;
    RelativeLayout pbar;
    ConnectionDetector cd;
    SharedPreferences sharedPreferences,sharedPreferences2,sharedPreferences3;
    TextView save;
    public int frag_count=0;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {

        View view = inflater.inflate(R.layout.frag_partner, container, false);
        ///////////////////////////////////////////////////////////////////
//        sharedPreferences3 = getActivity().getSharedPreferences("shareprefcontactlist", MODE_PRIVATE);
//        String json = sharedPreferences3.getString("contactlist", " ");
//        Gson gson = new Gson();
//        Type type = new TypeToken<ArrayList<ContactList_getset>>() {}.getType();
//        AppData.arrayList=new ArrayList<ContactList_getset>();
//        AppData.arrayList = gson.fromJson(json, type);
        //////////////////////////////////////////////////////////

        no_data= (TextView) view.findViewById(R.id.no_data);
        no_data.setVisibility(View.GONE);

        pbar= (RelativeLayout) view.findViewById(R.id.pbar);
        pbar.setVisibility(View.GONE);

        recyclerview=(RecyclerView)view.findViewById(R.id.recyclerview);

        recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerview.setHasFixedSize(true);

        AppData.page="PartnerFrag";
        Log.d("Pageforsevice:",AppData.page);
        sharedPreferences=getActivity().getSharedPreferences("logindetails", MODE_PRIVATE);
        cd=new ConnectionDetector(getActivity());
        save=(TextView)getActivity().findViewById(R.id.save) ;
       // pbar.setVisibility(View.VISIBLE);

        ///////////////////////////////////////////////////////////////////////////////////



////////////////////////////////////////////////////////////////////////////

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


               if(AppData.addextabuttonforpartner==false)

                {



                if (frag_count == 0) {


                    if (cd.isConnectingToInternet()) {
                        Intent i2 = new Intent(getActivity(), ActivityNewMeeting.class);
                        // i.putExtra("HomePage","Partner");
                        startActivity(i2);
                        getActivity().finish();


                    } else {

                        Toast.makeText(getActivity(), getResources().getString(R.string.internet_conn), Toast.LENGTH_SHORT).show();
                    }

                }

                else {

                    int p = 0;
                    //jsonArray = new JSONArray();

                    for (int i = 0; i < AppData.selectedcontact.size(); i++) {

                        if (AppData.selectedcontact.get(i).isSelect() == true) {

                            JSONObject jsonObject = new JSONObject();
                            try {
                                jsonObject.put("id", AppData.selectedcontact.get(i).getId());
                                jsonObject.put("name", AppData.selectedcontact.get(i).getName());
                                jsonObject.put("image", AppData.selectedcontact.get(i).getImage());
                                AppData.partnerjsonarray.put(p, jsonObject);

                                p++;


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    Log.d("Data", "::::::" + AppData.partnerjsonarray.toString());

                    pbar.setVisibility(View.VISIBLE);

                    if (cd.isConnectingToInternet()) {
                        Intent i = new Intent(getActivity(), ActivityNewMeeting.class);
                        // i.putExtra("HomePage","Partner");
                        startActivity(i);
                        getActivity().finish();


                    }

                    else {

                        Toast.makeText(getActivity(), getResources().getString(R.string.internet_conn), Toast.LENGTH_SHORT).show();
                    }

                }

            }

               /////////////////////////////////////////////////////////////////////////////////////


                else if(AppData.addextabuttonforpartner==true)

                {



                    if (frag_count == 0) {


                        if (cd.isConnectingToInternet()) {
                            Intent i2 = new Intent(getActivity(), ActivityNewMeeting.class);
                            // i.putExtra("HomePage","Partner");
                            startActivity(i2);
                            getActivity().finish();


                        } else {

                            Toast.makeText(getActivity(), getResources().getString(R.string.internet_conn), Toast.LENGTH_SHORT).show();
                        }

                    } else {

                        int p =      0;
                        //jsonArray = new JSONArray();

                        for (int i = 0; i < AppData.selectedcontact.size(); i++) {

                            if (AppData.selectedcontact.get(i).isSelect() == true) {

                                JSONObject jsonObject = new JSONObject();
                                try {
                                    jsonObject.put("id", AppData.selectedcontact.get(i).getId());
                                    jsonObject.put("name", AppData.selectedcontact.get(i).getName());
                                    jsonObject.put("image", AppData.selectedcontact.get(i).getImage());
                                    AppData.partnerjsonarray.put(p, jsonObject);

                                    p++;


                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }

                        Log.d("Data", "::::::" + AppData.partnerjsonarray.toString());

                        pbar.setVisibility(View.VISIBLE);

                        if (cd.isConnectingToInternet()) {
                            Intent i = new Intent(getActivity(), ActivityNewMeeting.class);
                            // i.putExtra("HomePage","Partner");
                            startActivity(i);
                            getActivity().finish();


                        } else {

                            Toast.makeText(getActivity(), getResources().getString(R.string.internet_conn), Toast.LENGTH_SHORT).show();
                        }

                    }

                }
            }
        });

        /////////////////////////

        plannewmeeting=(RelativeLayout)view.findViewById(R.id.plannewmeeting);


        plannewmeeting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(AppData.isServiceCompleted==true)
                {
                    //AppData.partnerlistingpage=true;

                   // AppData.isFirst_time=true;
                    if(AppData.buttonforseeingpartner==false)
                    {
                        //AppData.buttonforseeingpartner=true;
                        Intent i = new Intent(getActivity(), ActivityPartner.class);
                        startActivity(i);

                    }
                    else if(AppData.buttonforseeingpartner==true)
                    {
                        Intent i = new Intent(getActivity(), ActivityPartner.class);
                        startActivity(i);

                    }
                }
else {
                    Toast.makeText(getActivity(),"contacts are being synced",Toast.LENGTH_SHORT).show();
                }
            }
        });



        return view;
    }





    @Override
    public void onResume() {
        super.onResume();

        cd=new ConnectionDetector(getActivity());

        sharedPreferences2=getActivity().getSharedPreferences("shareprefselectedcontactlist", MODE_PRIVATE);
        String json = sharedPreferences2.getString("selectedcontactlist", " ");
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<PartnerSetGet>>() {}.getType();



        if(cd.isConnectingToInternet())
        {
            if(AppData.addextabuttonforpartner==false)
            {
                Toast.makeText(getActivity(),"go to if block",Toast.LENGTH_SHORT).show();
//                AppData.selectedcontact=new ArrayList<PartnerSetGet>();
//                AppData.selectedcontact = gson.fromJson(json, type);
//                if(AppData.selectedcontact.size()>0) {
//                    mAdapter = new RecyclerViewAdapter(getActivity(), AppData.selectedcontact, PartnerFrag.this);
//                    recyclerview.setAdapter(mAdapter);
//                }
                getPartners();
            }
            else{
                mAdapter = new RecyclerViewAdapter(getActivity(), AppData.selectedcontact, PartnerFrag.this);
                recyclerview.setAdapter(mAdapter);

            }

        } else {

            Toast.makeText(getActivity(), getResources().getString(R.string.internet_conn), Toast.LENGTH_SHORT).show();
        }

        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener()
        {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK)
                {
                    // handle back button's click listener
                    //Toast.makeText(getActivity(), "Back press", Toast.LENGTH_SHORT).show();


                    if(AppData.addextabuttonforpartner==false)

                    {



                        if (frag_count == 0) {


                            if (cd.isConnectingToInternet()) {
                                Intent i2 = new Intent(getActivity(), ActivityNewMeeting.class);
                                // i.putExtra("HomePage","Partner");
                                startActivity(i2);
                                getActivity().finish();


                            } else {

                                Toast.makeText(getActivity(), getResources().getString(R.string.internet_conn), Toast.LENGTH_SHORT).show();
                            }

                        }

                        else {

                            int p = 0;
                            //jsonArray = new JSONArray();

                            for (int i = 0; i < AppData.selectedcontact.size(); i++) {

                                if (AppData.selectedcontact.get(i).isSelect() == true) {

                                    JSONObject jsonObject = new JSONObject();
                                    try {
                                        jsonObject.put("id", AppData.selectedcontact.get(i).getId());
                                        jsonObject.put("name", AppData.selectedcontact.get(i).getName());
                                        jsonObject.put("image", AppData.selectedcontact.get(i).getImage());
                                        AppData.partnerjsonarray.put(p, jsonObject);

                                        p++;


                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }

                            Log.d("Data", "::::::" + AppData.partnerjsonarray.toString());

                            pbar.setVisibility(View.VISIBLE);

                            if (cd.isConnectingToInternet()) {
                                Intent i = new Intent(getActivity(), ActivityNewMeeting.class);
                                // i.putExtra("HomePage","Partner");
                                startActivity(i);
                                getActivity().finish();


                            }

                            else {

                                Toast.makeText(getActivity(), getResources().getString(R.string.internet_conn), Toast.LENGTH_SHORT).show();
                            }

                        }

                    }

                    /////////////////////////////////////////////////////////////////////////////////////


                    else if(AppData.addextabuttonforpartner==true)

                    {



                        if (frag_count == 0) {


                            if (cd.isConnectingToInternet()) {
                                Intent i2 = new Intent(getActivity(), ActivityNewMeeting.class);
                                // i.putExtra("HomePage","Partner");
                                startActivity(i2);
                                getActivity().finish();


                            } else {

                                Toast.makeText(getActivity(), getResources().getString(R.string.internet_conn), Toast.LENGTH_SHORT).show();
                            }

                        } else {

                            int p =      0;
                            //jsonArray = new JSONArray();

                            for (int i = 0; i < AppData.selectedcontact.size(); i++) {

                                if (AppData.selectedcontact.get(i).isSelect() == true) {

                                    JSONObject jsonObject = new JSONObject();
                                    try {
                                        jsonObject.put("id", AppData.selectedcontact.get(i).getId());
                                        jsonObject.put("name", AppData.selectedcontact.get(i).getName());
                                        jsonObject.put("image", AppData.selectedcontact.get(i).getImage());
                                        AppData.partnerjsonarray.put(p, jsonObject);

                                        p++;


                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }

                            Log.d("Data", "::::::" + AppData.partnerjsonarray.toString());

                            pbar.setVisibility(View.VISIBLE);

                            if (cd.isConnectingToInternet()) {
                                Intent i = new Intent(getActivity(), ActivityNewMeeting.class);
                                // i.putExtra("HomePage","Partner");
                                startActivity(i);
                                getActivity().finish();


                            } else {

                                Toast.makeText(getActivity(), getResources().getString(R.string.internet_conn), Toast.LENGTH_SHORT).show();
                            }

                        }

                    }
                    return true;
                }
                return false;
            }
        });


    }

    public void getPartners()
    {
        pbar.setVisibility(View.VISIBLE);

        String url = AppData.url+"/index.php/useraction/partnersget";

        Log.d("url_getParts","::::"+url);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s)
            {

                Log.d("url_getpartners_res","::::"+s.toString());

                try {
                    JSONObject jsonObject=new JSONObject(s);

                    if(jsonObject.getBoolean("status")==true)
                    {

                        AppData.selectedcontact  =new ArrayList<PartnerSetGet>();

                        if(jsonObject.getJSONArray("details").length()>0)


                        {





                            for (int i = 0; i < jsonObject.getJSONArray("details").length(); i++)
                            {

                                JSONObject object=jsonObject.getJSONArray("details").getJSONObject(i);
                                String id=object.getString("id");
                                String image=object.getString("image");
                                String number=object.getString("phone");
                                String name=object.getString("name");
                                String email=object.getString("email");


                                    PartnerSetGet contactSetGet = new PartnerSetGet(id, image, name, email, number, false);
                                    AppData.selectedcontact.add(contactSetGet);






                            }
                           // if(AppData.addextabuttonforpartner==false)
                            // {
                                mAdapter = new RecyclerViewAdapter(getActivity(), AppData.selectedcontact, PartnerFrag.this);
                                recyclerview.setAdapter(mAdapter);
                            //}
//                            else{
//                                mAdapter = new RecyclerViewAdapter(getActivity(), AppData.selectedcontact2, PartnerFrag.this);
//                                recyclerview.setAdapter(mAdapter);
//
//                            }
                        }
                        else{
                            no_data.setVisibility(View.VISIBLE);

                        }



                        pbar.setVisibility(View.GONE);

                    }else{
                        no_data.setVisibility(View.VISIBLE);
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
                Log.d("ERROR::::::::::",volleyError.toString());

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<String, String>();

                params.put("userid",sharedPreferences.getString("user_id",""));

                return params;

            }
        };

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                5000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        AppData.getInstance().addToRequestQueue(stringRequest);



    }
//    @Override
//    public void onDetach() {
//        super.onDetach();
//     //   Toast.makeText(getActivity(),"On Detach is called",Toast.LENGTH_SHORT).show();
//
//
//    }
}
