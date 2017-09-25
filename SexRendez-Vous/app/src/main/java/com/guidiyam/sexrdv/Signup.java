package com.guidiyam.sexrdv;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.guidiyam.sexrdv.helper.ConnectionDetector;
import com.guidiyam.sexrdv.setget.AllCountryDetails;
import com.guidiyam.sexrdv.volley.AppData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.LinkedList;


public class Signup extends Activity {
    boolean status;
    String message,locale,Lmode;
    LinearLayout ll_close;
    String urlJsonObj,urlJsonObjsignup;
    String Country_id,Dial_code;
    private static String TAG = Signup.class.getSimpleName();
    RelativeLayout rl_signup,rl_name,rl_email,rl_pass,rl_repass,rl_gender,rl_country,rl_ph;
    EditText name,email,password,repassword,phnumber;
    ImageView nameicon,emailicon,pass,repass,loveicon,arrow_icon,flagicon,arrow_icon2,phnumberimg;
    ConnectionDetector cd;
    TextView errortxt,errortxt1,errortxt2,errortxt3,errortxt4,errortxt5,errortxt6;
    String Name,Email,Password,Re_pASS,Ph_number;
    String first;
    Spinner gender,edt_country;
    ArrayList<String> arrraygender;
    String Gender;
    public String prefName = "spinner_value";
    public String prefName2 = "spinner_value2";
    public SharedPreferences prefsGender,prefsCountry;
    String Country;
    ArrayAdapter<CharSequence> countyadpter;
    ArrayAdapter<String> adp;
    String[] countrystring,countrycode;
    LinkedList<AllCountryDetails> allcoutrydetails = new LinkedList<AllCountryDetails>();
    ArrayList<String> countryidlist,countrynamelist,countrydialcodelist;
    String Gender_code;
    SharedPreferences.Editor editorspedometer,editorlisensebool,editorsupervisorid,selectedcountryid,colorchangeaccoringtotime;
    SharedPreferences langsharedpref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        cd=new ConnectionDetector(Signup.this);
        langsharedpref = getSharedPreferences("save_lang", Context.MODE_PRIVATE);
        if (cd.isConnectingToInternet())
        {

            countryfetch();

//            supervisorlisting();
//            drive_total();

        }
        else
        {
            Toast.makeText(getApplicationContext(),  getResources().getString(R.string.internet_conn), Toast.LENGTH_SHORT).show();
        }

        errortxt=(TextView)findViewById(R.id.errortxt);

        errortxt1=(TextView)findViewById(R.id.errortxt1);
        errortxt2=(TextView)findViewById(R.id.errortxt2);
        errortxt3=(TextView)findViewById(R.id.errortxt3);
        errortxt4=(TextView)findViewById(R.id.errortxt4);
        errortxt5=(TextView)findViewById(R.id.errortxt5);
        errortxt6=(TextView)findViewById(R.id.errortxt6);
        errortxt.setVisibility(View.GONE);
        errortxt1.setVisibility(View.GONE);
        errortxt2.setVisibility(View.GONE);
        errortxt3.setVisibility(View.GONE);
        errortxt4.setVisibility(View.GONE);
        errortxt5.setVisibility(View.GONE);
        errortxt6.setVisibility(View.GONE);
        //////////////////////////////////
        rl_signup=(RelativeLayout)findViewById(R.id.rl_signup);
        rl_name=(RelativeLayout)findViewById(R.id.rl_name);
        rl_email=(RelativeLayout)findViewById(R.id.rl_email);
        rl_pass=(RelativeLayout)findViewById(R.id.rl_pass);
        rl_repass=(RelativeLayout)findViewById(R.id.rl_repass);
        rl_gender=(RelativeLayout)findViewById(R.id.rl_gender);
        rl_country=(RelativeLayout)findViewById(R.id.rl_country);
        rl_ph=(RelativeLayout)findViewById(R.id.rl_number) ;
        //////////////////////



        gender = (Spinner) findViewById(R.id.gender);
        edt_country=(Spinner)findViewById(R.id.edt_country);
        arrraygender=new ArrayList<String>();
        arrraygender.add(getResources().getString(R.string.Your_gender));
        arrraygender.add(getResources().getString(R.string.Male));
        arrraygender.add(getResources().getString(R.string.Female));

      adp = new ArrayAdapter<String>(this, R.layout.spinner_item, arrraygender);
        adp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gender.setAdapter(adp);


        countryidlist=new ArrayList<String>();
        countrynamelist=new ArrayList<String>();
        countrydialcodelist=new ArrayList<String>();
        countrynamelist.add(getResources().getString(R.string.Country));

        ArrayAdapter<String> adp2 = new ArrayAdapter<String>(this,
                R.layout.spinner_item, countrynamelist);
        adp2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        edt_country.setAdapter(adp2);

        edt_country.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Country = adapterView.getItemAtPosition(i).toString();
                prefsCountry = getSharedPreferences(prefName2, MODE_PRIVATE);
                SharedPreferences.Editor editor = prefsCountry.edit();
                //---save the values in the EditText view to preferences---
                editor.putInt("second_val", i);


                editor.commit();


                if (i > 0) {
                    Country_id=countryidlist.get(i-1).toString();
                    selectedcountryid=getSharedPreferences("sharepreferencecountryid", MODE_PRIVATE).edit();
                    selectedcountryid.putString("countryid",Country_id);
                    selectedcountryid.commit();


                    Dial_code=countrydialcodelist.get(i-1).toString();

                    Log.d("Countryid", countryidlist.get(i - 1).toString());
                    //checkingodomerter();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        gender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0,
                                       View arg1, int pos, long arg3) {
                Gender = arg0.getItemAtPosition(pos).toString();
                prefsGender = getSharedPreferences(prefName, MODE_PRIVATE);
                SharedPreferences.Editor editor = prefsGender.edit();
                //---save the values in the EditText view to preferences---
                editor.putInt("first_val", pos);


                editor.commit();


                if (pos > 0) {
                    if(Gender.equals("Male"))
                    {
                       Gender_code="1";

                    }
                    else{

                        Gender_code="2";
                    }
//                    Country_id=countryidlist.get(i-1).toString();
//                    selectedcountryid=getSharedPreferences("sharepreferencecountryid", MODE_PRIVATE).edit();
//                    selectedcountryid.putString("countryid",Country_id);
//                    selectedcountryid.commit();
//
//                    Log.d("Countryid", countryidlist.get(i - 1).toString());
//                    //checkingodomerter();
                }

                //Toast.makeText(getBaseContext(),
                //	sp.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });
        /////////////////////////////////////////////////
        name=(EditText)findViewById(R.id.name);
        email=(EditText)findViewById(R.id.email);
        password=(EditText)findViewById(R.id.password);
        repassword=(EditText)findViewById(R.id.repassword);
        phnumber=(EditText)findViewById(R.id.phnumber);

        nameicon=(ImageView)findViewById(R.id.nameicon);
        emailicon=(ImageView)findViewById(R.id.emailicon);
        pass=(ImageView)findViewById(R.id.pass);
        repass=(ImageView)findViewById(R.id.repass);
        loveicon=(ImageView)findViewById(R.id.loveicon);
        arrow_icon=(ImageView)findViewById(R.id.arrow_icon);
        arrow_icon2=(ImageView)findViewById(R.id.arrow_icon2);
        phnumberimg=(ImageView)findViewById(R.id.phnumberimg) ;
        flagicon=(ImageView)findViewById(R.id.flagicon);

        rl_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Name=name.getText().toString().trim();
                Email=email.getText().toString().trim();
                Password=password.getText().toString().trim();
                Re_pASS=repassword.getText().toString().trim();
                Ph_number=phnumber.getText().toString().trim();



                if(cd.isConnectingToInternet()) {

                    if (Name.length() > 0) {

                        ///////////////////////////////
                        name.setTextColor(Color.parseColor("#FFFFFF"));
                        errortxt1.setVisibility(View.GONE);
                        rl_name.setBackgroundResource(R.drawable.edit_background);
                        nameicon.setBackgroundResource(R.drawable.user);

                        //////////////////////////////////////

                        if (Email.length() > 0)
                        {
                            ///////////////////////////////////////////
                            email.setTextColor(Color.parseColor("#FFFFFF"));
                            errortxt2.setVisibility(View.GONE);
                            rl_email.setBackgroundResource(R.drawable.edit_background);
                            emailicon.setBackgroundResource(R.drawable.email_icon);
                            ///////////////////////////////////////////
                            first = Email.substring(0, 1);
                            Log.d("FIRST_LETTER", first);

                            if (first.equals(".") || first.equals(" ") || first.equals(",")
                                    || first.equals("/") ||
                                    first.equals("?") ||
                                    first.equals(";") ||
                                    first.equals(":") || first.equals("!") ||
                                    first.equals("@") || first.equals("#") ||
                                    first.equals("$") || first.equals("%") ||
                                    first.equals("^") || first.equals("&") ||
                                    first.equals("*") || first.equals("(") ||
                                    first.equals(")") || first.equals("`") ||
                                    first.equals("~"))

                            {


                                //Toast.makeText(Signup.this, "Invaild emailid", Toast.LENGTH_SHORT).show();

                                email.setTextColor(Color.parseColor("#000000"));
                                errortxt2.setVisibility(View.VISIBLE);
                                errortxt2.setText(getResources().getString(R.string.invalidemail));
                                emailicon.setBackgroundResource(R.drawable.black_email);
                                email.requestFocus();
                                email.setText("");
                                email.setHint(getResources().getString(R.string.email));
                                email.setHintTextColor(Color.BLACK);
                                rl_email.setBackgroundResource(R.drawable.edit_black_background);
                            }


                            else if (android.util.Patterns.EMAIL_ADDRESS.matcher(email.getText().toString().trim()).matches())

                            {

                                if (Email.contains("@"))

                                {
                                    String parts[] = Email.split("\\@");
                                    String a = parts[0];
                                    Log.d("EMAIL", a);
                                    String last = a.substring(a.length() - 1);
                                    Log.d("LAST", last);


                                    if (last.equals(".") || last.equals(" ") || last.equals(",")
                                            || last.equals("/") ||
                                            last.equals("?") ||
                                            last.equals(";") ||
                                            last.equals(":") || last.equals("!") ||
                                            last.equals("@") || last.equals("#") ||
                                            last.equals("$") || last.equals("%") ||
                                            last.equals("^") || last.equals("&") ||
                                            last.equals("*") || last.equals("(") ||
                                            last.equals(")") || last.equals("`") ||
                                            last.equals("~"))
                                    {

                                        //Toast.makeText(Signup.this, "Invaild emailid", Toast.LENGTH_SHORT).show();
                                        email.setTextColor(Color.parseColor("#000000"));
                                        errortxt2.setVisibility(View.VISIBLE);
                                        errortxt2.setText(getResources().getString(R.string.invalidemail));
                                        emailicon.setBackgroundResource(R.drawable.black_email);
                                        email.requestFocus();
                                        email.setText("");
                                        email.setHint(getResources().getString(R.string.email));
                                        email.setHintTextColor(Color.BLACK);
                                        rl_email.setBackgroundResource(R.drawable.edit_black_background);

                                    } else
                                    {
                                        if (Password.length() > 0)

                                        {
                                            /////////////////////////////////////
                                            password.setTextColor(Color.parseColor("#FFFFFF"));
                                            errortxt3.setVisibility(View.GONE);
                                            rl_pass.setBackgroundResource(R.drawable.edit_background);
                                            pass.setBackgroundResource(R.drawable.password);


                                            /////////////////////////////


                                            if (Re_pASS.length() > 0)
                                            {
                                                ///////////////////////////////////////////////

                                                repassword.setTextColor(Color.parseColor("#FFFFFF"));
                                                errortxt.setVisibility(View.GONE);
                                                rl_repass.setBackgroundResource(R.drawable.edit_background);
                                                repass.setBackgroundResource(R.drawable.password);

                                                ////////////////////////////////////////////////////


                                                if (!isValidPassword(Password))
                                                {
                                                    password.setTextColor(Color.parseColor("#000000"));
                                                    errortxt3.setVisibility(View.VISIBLE);
                                                    errortxt3.setText(getResources().getString(R.string.paschk));
                                                    pass.setBackgroundResource(R.drawable.black_pass);
                                                    password.requestFocus();
                                                    password.setText("");
                                                    password.setHint(getResources().getString(R.string.password));
                                                    password.setHintTextColor(Color.BLACK);
                                                    rl_pass.setBackgroundResource(R.drawable.edit_black_background);


                                                }

                                                else
                                                {

                                                    if (Password.equals(Re_pASS))
                                                    {

                                                        errortxt.setVisibility(View.GONE);

                                                        if(Ph_number.length()>0)
                                                        {



                                                            phnumber.setTextColor(Color.parseColor("#FFFFFF"));
                                                            errortxt6.setVisibility(View.GONE);
                                                            rl_ph.setBackgroundResource(R.drawable.edit_background);
                                                            phnumberimg.setBackgroundResource(R.drawable.ic_action_name_ph);






                                                            if (Gender.equals(getResources().getString(R.string.Your_gender)))
                                                            {
//                                                            adp = new ArrayAdapter<String>(Signup.this, R.layout.spinner_item2, arrraygender);
//                                                            adp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//                                                            gender.setAdapter(adp);

                                                                errortxt4.setVisibility(View.VISIBLE);
                                                                errortxt4.setText(getResources().getString(R.string.entergender));
                                                                loveicon.setBackgroundResource(R.drawable.love_black);
                                                                arrow_icon.setBackgroundResource(R.drawable.dropdown_black);
                                                                rl_gender.setBackgroundResource(R.drawable.edit_black_background);


                                                            } else {

//                                                            adp = new ArrayAdapter<String>(Signup.this, R.layout.spinner_item, arrraygender);
//                                                            adp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//                                                            gender.setAdapter(adp);


                                                                errortxt4.setVisibility(View.GONE);
                                                                loveicon.setBackgroundResource(R.drawable.love);
                                                                arrow_icon.setBackgroundResource(R.drawable.dropdown);
                                                                rl_gender.setBackgroundResource(R.drawable.edit_background);


                                                                if (Country.equals(getResources().getString(R.string.Country))) {

//                                                      countyadpter=ArrayAdapter.createFromResource(Signup.this,R.array.country_arrays,R.layout.spinner_item2);
//                                                      countyadpter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//                                                      edt_country.setAdapter(countyadpter);


                                                                    errortxt5.setVisibility(View.VISIBLE);
                                                                    errortxt5.setText(getResources().getString(R.string.entercountry));
                                                                    flagicon.setBackgroundResource(R.drawable.flag_black);
                                                                    arrow_icon2.setBackgroundResource(R.drawable.dropdown_black);
                                                                    rl_country.setBackgroundResource(R.drawable.edit_black_background);



                                                                } else {

//                                                      countyadpter=ArrayAdapter.createFromResource(Signup.this,R.array.country_arrays,R.layout.spinner_item);
//                                                      countyadpter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//                                                      edt_country.setAdapter(countyadpter);

                                                                    errortxt5.setVisibility(View.GONE);
                                                                    flagicon.setBackgroundResource(R.drawable.flag);
                                                                    arrow_icon2.setBackgroundResource(R.drawable.dropdown);
                                                                    rl_country.setBackgroundResource(R.drawable.edit_background);


                                                                    try {
                                                                        Name = URLEncoder.encode(Name, "utf-8");
                                                                        Password = URLEncoder.encode(Password, "utf-8");
                                                                    } catch (UnsupportedEncodingException e) {
                                                                        e.printStackTrace();
                                                                    }


                                                                    srdv_signup_volly();


                                                                    // Toast.makeText(Signup.this, "Sign up successfully", Toast.LENGTH_SHORT).show();


                                                                }

                                                            }
                                                        }

                                                        else
                                                        {

                                                            phnumber.setTextColor(Color.parseColor("#000000"));
                                                            errortxt6.setVisibility(View.VISIBLE);
                                                            errortxt6.setText(getResources().getString(R.string.enter_ph_number));
                                                            phnumberimg.setBackgroundResource(R.drawable.ic_action_name_ph_deselect);
                                                            phnumber.requestFocus();
                                                            phnumber.setText("");
                                                            phnumber.setHint(getResources().getString(R.string.ph_number));
                                                            phnumber.setHintTextColor(Color.BLACK);
                                                            rl_ph.setBackgroundResource(R.drawable.edit_black_background);

                                                        }

                                                    }

                                                    else {

                                                        errortxt.setVisibility(View.VISIBLE);
                                                        errortxt.setText(getResources().getString(R.string.pasdiff));
                                                        repassword.setTextColor(Color.parseColor("#000000"));
                                                        repass.setBackgroundResource(R.drawable.black_pass);
                                                        repassword.requestFocus();
                                                        repassword.setText("");
                                                        repassword.setHint(getResources().getString(R.string.con_pass));
                                                        repassword.setHintTextColor(Color.BLACK);
                                                        rl_repass.setBackgroundResource(R.drawable.edit_black_background);
                                                    }


                                                }


                                            }
                                            else {

                                                errortxt.setVisibility(View.VISIBLE);
                                                errortxt.setText(getResources().getString(R.string.con_pass));
                                                repassword.setTextColor(Color.parseColor("#000000"));
                                                repass.setBackgroundResource(R.drawable.black_pass);
                                                repassword.requestFocus();
                                                repassword.setText("");
                                                repassword.setHint(getResources().getString(R.string.con_pass));
                                                repassword.setHintTextColor(Color.BLACK);
                                                rl_repass.setBackgroundResource(R.drawable.edit_black_background);

                                            }

                                        } else {
//                                            pass.setBackgroundResource(R.drawable.black_pass);
//                                            password.requestFocus();
//
//                                            password.setHint("Password");
//                                            password.setHintTextColor(Color.BLACK);
//                                            rl_pass.setBackgroundResource(R.drawable.edit_black_background);
                                            password.setTextColor(Color.parseColor("#000000"));
                                            errortxt3.setVisibility(View.VISIBLE);
                                            errortxt3.setText(getResources().getString(R.string.Enter_password));
                                            pass.setBackgroundResource(R.drawable.black_pass);
                                            password.requestFocus();
                                            password.setText("");
                                            password.setHint(getResources().getString(R.string.password));
                                            password.setHintTextColor(Color.BLACK);
                                            rl_pass.setBackgroundResource(R.drawable.edit_black_background);

                                        }


                                    }
                                }

                            }



                            else

                            {


                                email.setTextColor(Color.parseColor("#000000"));
                                errortxt2.setVisibility(View.VISIBLE);
                                errortxt2.setText(getResources().getString(R.string.invalidemail));
                                emailicon.setBackgroundResource(R.drawable.black_email);
                                email.requestFocus();
                                email.setText("");
                                email.setHint(getResources().getString(R.string.email));
                                email.setHintTextColor(Color.BLACK);
                                rl_email.setBackgroundResource(R.drawable.edit_black_background);

                            }
                        } else {


                            email.setTextColor(Color.parseColor("#000000"));
                            errortxt2.setVisibility(View.VISIBLE);
                            errortxt2.setText(getResources().getString(R.string.Enter_email));
                            emailicon.setBackgroundResource(R.drawable.black_email);
                            email.requestFocus();
                            email.setText("");
                            email.setHint(getResources().getString(R.string.email));
                            email.setHintTextColor(Color.BLACK);
                            rl_email.setBackgroundResource(R.drawable.edit_black_background);



                        }


                    }


                    else {
                        name.setTextColor(Color.parseColor("#000000"));
                        errortxt1.setVisibility(View.VISIBLE);
                        errortxt1.setText(getResources().getString(R.string.Enter_name));
                        nameicon.setBackgroundResource(R.drawable.user_blck);
                        name.requestFocus();
                        name.setText("");
                        name.setHint(getResources().getString(R.string.name));
                        name.setHintTextColor(Color.BLACK);
                        rl_name.setBackgroundResource(R.drawable.edit_black_background);

                    }

                }

                else
                {

                    Toast.makeText(Signup.this, getResources().getString(R.string.internet_conn), Toast.LENGTH_SHORT).show();

                }
            }
        });
        ll_close=(LinearLayout)findViewById(R.id.ll_close);
        ll_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Signup.this,Login.class);
                startActivity(i);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed(); Intent i=new Intent(Signup.this,Login.class);
        startActivity(i);
        finish();

    }
    private boolean isValidPassword(String pass) {
        if (pass != null && pass.length()>5) {
            return true;
        }
        return false;
    }

    @Override
    protected void onResume() {
        super.onResume();

        AppData.devicetoken = Settings.Secure.getString(getApplicationContext().getContentResolver(),
                Settings.Secure.ANDROID_ID);
        Log.d("DeviceToken",AppData.devicetoken);
         locale = Signup.this.getResources().getConfiguration().locale.getDisplayCountry();
        Log.d("LOCAL",locale);
        if(locale.equals("France"))
        {

            rl_signup.setBackgroundResource(R.drawable.frech_signup);
            Lmode="2";



        }
        else{

            rl_signup.setBackgroundResource(R.drawable.signup);
            Lmode="1";

        }
//        countryidlist=new ArrayList<String>();
//        countrynamelist=new ArrayList<String>();
//        if(cd.isConnectingToInternet())
//        {
//
//
//
//        }
    }
    public void countryfetch()
    {
        urlJsonObj = AppData.url+"/index.php/Signup/fetchcountry";
        Log.d("countryurl",urlJsonObj);
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, urlJsonObj, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                try {
                    status = jsonObject.getBoolean("status");
                    if(status==true)
                    {
                        JSONArray countryData = jsonObject.getJSONArray("countryData");
                        countrystring = new String[countryData.length()];
                        countrycode=new String[countryData.length()];

                                            for (int i = 0; i < countryData.length(); i++)
                    {
                        JSONObject josonobj2 = countryData.getJSONObject(i);

                      String  countryId = josonobj2.getString("countryId");
                      String  countryName = josonobj2.getString("countryName");
                        String dial_code=josonobj2.getString("dial_code");
                        countrystring[i] = countryId;
                        countrycode[i]=dial_code;

                        countryidlist.add(countrystring[i]);
                        //carregistration_number_list.add( strcar[i]);
                        countrynamelist.add(countryName);
                        countrydialcodelist.add(countrycode[i]);
                        // caridlist.add(str[i]);
                        Log.d("Car id", countryId);
                        //Log.d("Car reg no", car_registration_no);
                        AllCountryDetails ab = new AllCountryDetails(countryId, countryName,dial_code);
                        allcoutrydetails.add(ab);


                    }
//


                    }

                } catch (Exception e) {

                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                VolleyLog.d(TAG, "Error: " + volleyError.getMessage());
            }
        }


        );

        jsonObjectRequest.setRetryPolicy(new RetryPolicy() {
            @Override
            public int getCurrentTimeout() {
                return 50000;
            }

            @Override
            public int getCurrentRetryCount() {
                return 50000;
            }

            @Override
            public void retry(VolleyError error) throws VolleyError {


            }
        });
        AppData.getInstance().addToRequestQueue(jsonObjectRequest);


    }
    public void srdv_signup_volly()
    {
        String ph=Dial_code+""+Ph_number;
        try {
            ph=URLEncoder.encode(ph,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        urlJsonObjsignup = AppData.url+"/index.php/Signup?register_type="+"1"+"&name="+Name+"&email="+Email+"&password="+Password+"&gender="+Gender_code+"&country="+Country_id+"&phone="+ph+"&device_token="+AppData.devicetoken+"&device_type="+"1"+"&mode="+langsharedpref.getString("mode", " ");
        Log.d("urlJsonObjsignup",urlJsonObjsignup);
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, urlJsonObjsignup, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                try {
                    Log.d("SignupResponse",jsonObject.toString());
                    status = jsonObject.getBoolean("status");
                    message=jsonObject.getString("message");
                    if(status==true)
                    {

                        JSONObject Details=jsonObject.getJSONObject("Details");
                            String user_id= Details.getString("user_id");
                        String email=Details.getString("email");
                        String name=Details.getString("name");
                        String gender=Details.getString("gender");
                        String country=Details.getString("country");
                        String ph=Details.getString("phone");
                        String device_token=Details.getString("device_token");
                        String image=Details.getString("image");
                        String app_password_status=Details.getString("app_password_status");
                        String app_password=Details.getString("app_password");


                        SharedPreferences.Editor editor3 = getSharedPreferences("loginPrefs", MODE_PRIVATE).edit();
                        editor3.putString("saveLogin", "A");
                        editor3.commit();


                        SharedPreferences.Editor editor = getSharedPreferences("logindetails", MODE_PRIVATE).edit();

                        editor.putString("user_id",user_id );
                        editor.putString("email", email );
                        editor.putString("name",name );
                        editor.putString("image", image);
                        editor.putString("gender", gender);
                        editor.putString("country",     country);
                        editor.putString("phone",ph );
                        editor.putString("devicetoken",device_token);
                        editor.putString("mode",Lmode);
                        editor.putString("register_type","1");
                        editor.putString("HomePage","Signup");
                        editor.putString("first_time","true");
                        editor.putString("app_password_status",app_password_status);
                        editor.putString("app_password",app_password);


                        editor.commit();

//                        JSONArray countryData = jsonObject.getJSONArray("countryData");
//                        countrystring = new String[countryData.length()];
//
//                        for (int i = 0; i < countryData.length(); i++)
//                        {
//                            JSONObject josonobj2 = countryData.getJSONObject(i);
//
//                            String  countryId = josonobj2.getString("countryId");
//                            String  countryName = josonobj2.getString("countryName");
//                            countrystring[i] = countryId;
//
//                            countryidlist.add(countrystring[i]);
//                            //carregistration_number_list.add( strcar[i]);
//                            countrynamelist.add(countryName);
//                            // caridlist.add(str[i]);
//                            Log.d("Car id", countryId);
//                            //Log.d("Car reg no", car_registration_no);
//                            AllCountryDetails ab = new AllCountryDetails(countryId, countryName);
//                            allcoutrydetails.add(ab);
//
//
//                        }
////

                        Toast.makeText(Signup.this,message,Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(Signup.this, FunActivity.class);
                        startActivity(i);
                        finish();

                    }
                    else if(status==false)
                    {


                        Toast.makeText(Signup.this,message,Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e) {

                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                VolleyLog.d(TAG, "Error: " + volleyError.getMessage());
            }
        }


        );

        jsonObjectRequest.setRetryPolicy(new RetryPolicy() {
            @Override
            public int getCurrentTimeout() {
                return 50000;
            }

            @Override
            public int getCurrentRetryCount() {
                return 50000;
            }

            @Override
            public void retry(VolleyError error) throws VolleyError {


            }
        });
        AppData.getInstance().addToRequestQueue(jsonObjectRequest);



    }

}
