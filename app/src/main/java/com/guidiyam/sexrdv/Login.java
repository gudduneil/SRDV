package com.guidiyam.sexrdv;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;

import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
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
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookAuthorizationException;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.LoggingBehavior;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.guidiyam.sexrdv.helper.ConnectionDetector;
import com.guidiyam.sexrdv.helper.GPSTracker;
import com.guidiyam.sexrdv.setget.AllCountryDetails;
import com.guidiyam.sexrdv.volley.AppData;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.MultipartBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.RequestBody;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

import static com.guidiyam.sexrdv.R.id.top_bar_name;


public class Login extends AppCompatActivity implements  GoogleApiClient.OnConnectionFailedListener {
TextView txtsignup;
     String Ph_Number,Dial_code;
    String Gender,Gender_Code;
    String GooglepersonName,GooglePhotoUrl,GoogleEmail;
    // [START declare_auth]
    private FirebaseAuth mAuth;
    public String prefName = "spinner_value";
    // [END declare_auth]
    private static final int RC_SIGN_IN = 9001;

    private GoogleApiClient mGoogleApiClient;
    String urlJsonObjlogin;
    SharedPreferences.Editor editorspedometer,editorlisensebool,editorsupervisorid,selectedcountryid,colorchangeaccoringtotime;
    public String prefName2 = "spinner_value2";
    String[] countrystring,countrycode;
    LinkedList<AllCountryDetails> allcoutrydetails = new LinkedList<AllCountryDetails>();
    ArrayList<String> countryidlist,countrynamelist,arraygender,countrydialcodelist;
    private static String TAG = Login.class.getSimpleName();
    String urlJsonObjfbsignup;
    RelativeLayout rl_login;
    String Country_id;
    EditText email,password;
    String Email,Password;
    RelativeLayout rl_email,rl_pass;
    ConnectionDetector cd;
    ImageView emilicon,pass;
    String fbID, fbUserID, fbName, fbFullName, fbEmail, fbUrl, fbBirthDay, fbFirstName, fbLastName, fbGender, fbLocale, fbUpdateTime, fbLocationID, fbLocationName, fbProfilePicLarge, fbProfilePicSmall,fbimage;
    private static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 1;
    TextView errortxt2,errortxt1,fb;
    boolean status;String message,user_exist;

    static String selectLang = "", Lmode = "";
    GPSTracker gps;
    CallbackManager callbackManager;
    String Country;
    public SharedPreferences prefsGender,prefsCountry;
    TextView google;
    ProgressBar pbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            FacebookSdk.sdkInitialize(getApplicationContext());
        } catch (Exception fbExcep) {
            Log.i("FB SDK exception : ", fbExcep.toString());
        }
        // ---- END
        callbackManager = CallbackManager.Factory.create();
        setContentView(R.layout.activity_login);
        pbar=(ProgressBar)findViewById(R.id.pbar) ;
        pbar.setVisibility(View.GONE);

        cd = new ConnectionDetector(Login.this);


        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        // [END config_signin]

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(Login.this , this )
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();


        mAuth = FirebaseAuth.getInstance();
        /////////////////////////////////


//        mGoogleApiClient = new GoogleApiClient.Builder(this)
//                .enableAutoManage(this, new GoogleApiClient.OnConnectionFailedListener() {
//                    @Override
//                    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
//
//                    }
//                }
//                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
//                .build();
//
//        // [START initialize_auth]
//        mAuth = FirebaseAuth.getInstance();

        ///////////////
       // getFbKeyHash("com.guidiyam.sexrdv");
//

        //accessLoc();
        google=(TextView)findViewById(R.id.google);
        google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                pbar.setVisibility(View.VISIBLE);
                signIn();
            }
        });
        errortxt1 = (TextView) findViewById(R.id.errortxt);
        errortxt2 = (TextView) findViewById(R.id.errortxt2);
        fb=(TextView)findViewById(R.id.fb);
        errortxt1.setVisibility(View.GONE);
        errortxt2.setVisibility(View.GONE);
        rl_login = (RelativeLayout) findViewById(R.id.rl_login);
        txtsignup = (TextView) findViewById(R.id.txtsignup);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        rl_email = (RelativeLayout) findViewById(R.id.rl_email);
        rl_pass = (RelativeLayout) findViewById(R.id.rl_pass);
        pass = (ImageView) findViewById(R.id.pass);
        emilicon = (ImageView) findViewById(R.id.emilicon);




        if (cd.isConnectingToInternet()) {


            rl_login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Email = email.getText().toString().trim();
                    Password = password.getText().toString().trim();
                    if (cd.isConnectingToInternet()) {
                        if (Email.length() > 0) {
                            errortxt1.setVisibility(View.GONE);
                            if (AppData.isEmailValid(Email)) {


                                email.setTextColor(Color.parseColor("#FFFFFF"));
                                errortxt1.setVisibility(View.GONE);
                                rl_email.setBackgroundResource(R.drawable.edit_background);
                                emilicon.setBackgroundResource(R.drawable.email_icon);

                                if (Password.length() > 0)

                                {
                                    errortxt2.setVisibility(View.GONE);
                                    password.setTextColor(Color.parseColor("#FFFFFF"));
                                    rl_email.setBackgroundResource(R.drawable.edit_background);
                                    //rl_pass.setBackgroundResource(R.drawable.email_icon);
                                    try {
                                        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                                        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                                    } catch (Exception e) {
                                        // TODO: handle exception
                                    }

                                    try {
                                        Email= URLEncoder.encode(Email,"UTF-8");
                                        Password = URLEncoder.encode(Password,"UTF-8");
                                    } catch (UnsupportedEncodingException e) {
                                        e.printStackTrace();
                                    }
                                    pbar.setVisibility(View.VISIBLE);
                                    normalLoginVol();


                                } else {
                                    // {
                                    password.setTextColor(Color.parseColor("#000000"));
                                    errortxt2.setVisibility(View.VISIBLE);
                                    errortxt1.setText(getResources().getString(R.string.Enter_password));
                                    pass.setBackgroundResource(R.drawable.black_pass);
                                    password.requestFocus();
                                    password.setText("");
                                    password.setHint(getResources().getString(R.string.password));
                                    password.setHintTextColor(Color.BLACK);
                                    rl_pass.setBackgroundResource(R.drawable.edit_black_background);


                                }

                            } else {

                                //Toast.makeText(Login.this, "Invalid Email", Toast.LENGTH_SHORT).show();
                                email.setTextColor(Color.parseColor("#000000"));
                                errortxt1.setVisibility(View.VISIBLE);
                                errortxt1.setText(getResources().getString(R.string.Enter_email));
                                emilicon.setBackgroundResource(R.drawable.black_email);
                                email.requestFocus();
                                email.setText("");
                                email.setHint(getResources().getString(R.string.email));
                                email.setHintTextColor(Color.BLACK);
                                rl_email.setBackgroundResource(R.drawable.edit_black_background);
                            }


                        } else {
                            email.setTextColor(Color.parseColor("#000000"));
                            errortxt1.setVisibility(View.VISIBLE);
                            errortxt1.setText(getResources().getString(R.string.Enter_email));
                            emilicon.setBackgroundResource(R.drawable.black_email);
                            email.requestFocus();
                            email.setText("");
                            email.setHint(getResources().getString(R.string.email));
                            email.setHintTextColor(Color.BLACK);
                            rl_email.setBackgroundResource(R.drawable.edit_black_background);

                        }

                    } else {


                        Toast.makeText(Login.this, getResources().getString(R.string.internet_conn), Toast.LENGTH_SHORT).show();
                    }


                }
            });
            txtsignup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(Login.this, Signup.class);
                    startActivity(i);
                    finish();
                }
            });

        }




        /////////////////////////////////////////////////




        //////////////////////////////////////////////

        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFbKeyHash("com.guidiyam.sexrdv");
                try {
                    FacebookSdk.sdkInitialize(getApplicationContext());
                } catch (Exception fbExcep) {
                    Log.i("FB SDK exception : ", fbExcep.toString());
                }


                LoginManager.getInstance().logInWithReadPermissions(
                        Login.this,
                        Arrays.asList("public_profile", "email", "user_friends"));

                Log.d("intotheblock", "HI");
                callbackManager = CallbackManager.Factory.create();
                LoginManager.getInstance().registerCallback(callbackManager,
                        new FacebookCallback<LoginResult>() {
                            @Override
                            public void onSuccess(LoginResult loginResult) {
                                Log.d("Success", "Login");
                                //FacebookSdk.addLoggingBehavior(LoggingBehavior.REQUESTS);

                                AppData.fbToken = loginResult.getAccessToken().getToken();

                                Log.d("acess", String.valueOf(AppData.fbToken ));
                                FacebookSdk.addLoggingBehavior(LoggingBehavior.REQUESTS);


                                // ---- Facebook user details
                                GraphRequest request = GraphRequest.newMeRequest(
                                        loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                                            @Override
                                            public void onCompleted(JSONObject jsonObject, GraphResponse graphResponse) {

                                                try {
                                                    Log.d("JOSONOBJECT",jsonObject.toString());
                                                    Log.d("GraphResponse",graphResponse.toString());
                                                    fbID = "" + jsonObject.getString("id");
                                                    fbUserID = fbID;
                                                    AppData.fbUserID = fbUserID;
                                                    fbName = "" + jsonObject.getString("name");

                                                    //fbPicture=""+jsonObject.getJSONObject()
                                                    if (jsonObject.has("picture"))
                                                    {
                                                        fbimage = jsonObject.getJSONObject("picture").getJSONObject("data").getString("url");

                                                    }
                                                    fbFullName = fbName;
                                                    AppData.fbFullName = fbFullName;
                                                    fbEmail = "" + jsonObject.getString("email");
                                                    AppData.fbEmail = fbEmail;
                                                    fbUrl = "" + jsonObject.getString("link");
                                                    try {
                                                        JSONObject jOBJ = jsonObject.getJSONObject("age_range");
                                                        fbBirthDay = "" + jOBJ.getString("min");
                                                    } catch (Exception e) {
                                                        fbBirthDay = "Not mention";
                                                    }
                                                    fbFirstName = "" + jsonObject.getString("first_name");
                                                    fbLastName = "" + jsonObject.getString("last_name");
                                                    fbGender = "" + jsonObject.getString("gender");
                                                    fbLocale = "" + jsonObject.getString("locale");
                                                    fbUpdateTime = "" + jsonObject.getString("updated_time");

                                                    try {
                                                        JSONObject jOBJ = jsonObject.getJSONObject("location");
                                                        fbLocationID = "" + jOBJ.getString("id");
                                                        fbLocationName = "" + jOBJ.getString("name");
                                                    } catch (Exception e) {
                                                        fbLocationID = "Not mention";
                                                        fbLocationName = "Not mention";
                                                    }

                                                    fbProfilePicLarge = "https://graph.facebook.com/" + fbID + "/picture?type=large";
                                                    fbProfilePicSmall = "https://graph.facebook.com/" + fbID + "/picture?type=small";

                                                    try {
                                                        String decodedString = URLDecoder.decode( fbProfilePicLarge, "UTF-8");
                                                        Log.d("DecodeString",decodedString);
                                                    } catch (UnsupportedEncodingException e) {
                                                        e.printStackTrace();
                                                    }

//                                                    try {
//                                                       // fbName=URLEncoder.encode(fbName,"utf-8");
//                                                        //fbProfilePicLarge=URLEncoder.encode(fbProfilePicLarge,"utf-8");
//                                                    } catch (UnsupportedEncodingException e) {
//                                                        e.printStackTrace();
//                                                    }

                                                    Log.i("fbID :", fbID);
                                                    Log.i("fbName :", fbName);
                                                    Log.i("fbEmail :", fbEmail);
                                                    Log.i("fbUrl :", fbUrl);
                                                    Log.i("fbBirthDay :", fbBirthDay);
                                                    Log.i("fbFirstName :", fbFirstName);
                                                    Log.i("fbLastName :", fbLastName);
                                                    Log.i("fbGender :", fbGender);
                                                    Log.i("fbLocale :", fbLocale);
                                                    Log.i("fbUpdateTime :", fbUpdateTime);
                                                    Log.i("fbLocationID :", fbLocationID);
                                                    Log.i("fbLocationName :", fbLocationName);
                                                    Log.i("fbProfilePicLarge :", fbProfilePicLarge);
                                                    Log.i("fbProfilePicSmall :", fbProfilePicSmall);

                                                    if (fbGender.equals("male")) {
                                                        fbGender = "1";
                                                    } else {
                                                        fbGender = "2";
                                                    }

                                                    if (fbUserID != null && fbUserID.length() > 0) {


                                                     //   new Vol_FB_Signupchking().execute();
                                                       /// Vol_FB_Signupchking();

                                                        new Vol_FB_Signupchking().execute();



                                                        // new Vol_Socisl_SignUp_Login().execute();


                                                    }


                                                    else {
//                                                            if (sharedPreferences2.getString("lang", " ").equals("eng"))
//                                                            Toast.makeText(Login.this, "Login Fail", Toast.LENGTH_LONG).show();
//                                                            else if(sharedPreferences2.getString("lang", " ").equals("ita"))
//                                                                Toast.makeText(Login.this, "Login fallito", Toast.LENGTH_LONG).show();
                                                    }

                                                } catch (JSONException e) {
                                                    Log.i("FB JSON EXCP : ", e.toString());
                                                    e.printStackTrace();
                                                }
                                            }
                                        });
                                Bundle parameters = new Bundle();
                                parameters.putString("fields",
                                        "id, name, email, link, birthday, age_range, first_name, last_name, gender, updated_time, verified, timezone, locale, location,picture.type(large)");
                                request.setParameters(parameters);
                                request.executeAsync();

                            }

                            @Override
                            public void onCancel() {
                                //progressBar.setVisibility(View.INVISIBLE);
                                //fb.performClick();
//                                     if(sharedPreferences2.getString("lang", " ").equals("eng"))
//                                    Toast.makeText(Login.this, "Login Cancel", Toast.LENGTH_LONG).show();
//                                    else if(sharedPreferences2.getString("lang", " ").equals("ita"))
//                                        Toast.makeText(Login.this, "Login Annulla", Toast.LENGTH_LONG).show();
                            }

                            @Override
                            public void onError(FacebookException exception) {
                                Toast.makeText(Login.this, exception.getMessage(), Toast.LENGTH_LONG).show();

                                if (exception instanceof FacebookAuthorizationException) {
                                    if (AccessToken.getCurrentAccessToken() != null) {
                                        //progressBar.setVisibility(View.GONE);
                                        LoginManager.getInstance().logOut();
                                        //fb.performClick();
                                    }
                                }

                            }
                        });

            }
        });
    }




    @Override
    protected void onResume() {
        super.onResume();
        AppData.devicetoken = Settings.Secure.getString(getApplicationContext().getContentResolver(),
                Settings.Secure.ANDROID_ID);
        Log.d("DeviceToken",AppData.devicetoken);
        String locale = Login.this.getResources().getConfiguration().locale.getDisplayCountry();
        Log.d("LOCAL",locale);
        if(locale.equals("France"))
        {
            selectLang="fra";
            Lmode = "2";
rl_login.setBackgroundResource(R.drawable.french_login);

            SharedPreferences.Editor editor = getSharedPreferences("save_lang", Context.MODE_PRIVATE).edit();
            editor.putString("lang", selectLang);
            editor.putString("mode", Lmode);
            editor.commit();

        }
        else{

//            txt_language.setText("English");
//            tv_language.setText("Language");
//            bt_next.setText("Next");
            selectLang="eng";
            Lmode = "1";
            rl_login.setBackgroundResource(R.drawable.login);
            SharedPreferences.Editor editor = getSharedPreferences("save_lang", Context.MODE_PRIVATE).edit();
            editor.putString("lang", selectLang);
            editor.putString("mode", Lmode);
            editor.commit();

        }
//        if (getIntent().getExtras() != null && getIntent().getExtras().getBoolean("EXIT", false)) {
//            finish();
//        }
//if(AppData.logout==true)
//{
//    AppData.logout=false;
//    AppExit();
//
//}
    }

    @SuppressLint("LongLogTag")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);

        System.out.println("### In OnActivity rESULT ###");

        callbackManager.onActivityResult(requestCode, resultCode, data);
        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN)
        {
//            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
//            handleSignInResult(result);

            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess())
            {

                //Toast.makeText(Login.this,"Success block is called",Toast.LENGTH_LONG).show();
                // Google Sign In was successful, authenticate with Firebase

                GoogleSignInAccount account = result.getSignInAccount();
                firebaseAuthWithGoogle(account);
            } else {
                // Google Sign In failed, update UI appropriately
                // ...
            }

        }
//
//        if (requestCode == RC_SIGN_IN) {
//            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
//            if (result.isSuccess()) {
//                // Google Sign In was successful, authenticate with Firebase
//                GoogleSignInAccount account = result.getSignInAccount();
//                firebaseAuthWithGoogle(account);
//            } else {
//                // Google Sign In failed, update UI appropriately
//                // ...
//            }
//        }



    }

    //Generate KeyHash for Facebook
    public void getFbKeyHash(String packageName) {

        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    packageName,
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                // --- >> C15+g0w3ofdlyANnJjHrf1tZbqQ=
                Log.d("KeyHash :", Base64.encodeToString(md.digest(), Base64.DEFAULT));
                // --- >> 2jmj7l5rSw0yVb/vlWAYkK/YBwk=
                System.out.println("KeyHash : " + Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {
            Log.i("NameNotFoundExp : ", e.toString());
        } catch (NoSuchAlgorithmException e) {
            Log.i("NoSuchAlgorithmExp : ", e.toString());
        }

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult)
    {
        Log.d(TAG, "onConnectionFailed:" + connectionResult);
        Toast.makeText(Login.this, "Google Play Services error.", Toast.LENGTH_SHORT).show();
    }

    private class Vol_FB_Signupchking extends AsyncTask<Void, Void, Void>
    {
        JSONObject response1;
        JSONObject res;

        String result;
        JSONObject jobj,userinfo;
        boolean statusasyn;
        int statuscode;
        String message;
        boolean status1;
        private static final long CONNECT_TIMEOUT_MILLIS =90000 ;
        private static final long READ_TIMEOUT_MILLIS =90000 ;
        protected void onPreExecute() {

            super.onPreExecute();
            pbar.setVisibility(View.VISIBLE);
           // progressBar.setVisibility(View.VISIBLE);

        }



        protected Void doInBackground(Void... bitmaps) {
            try {

                OkHttpClient client = new OkHttpClient();


                client.setConnectTimeout(CONNECT_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS);
                client.setReadTimeout(READ_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS);
                client.interceptors().add(new Interceptor() {
                    @Override
                    public com.squareup.okhttp.Response intercept(Chain chain) throws IOException {
                        com.squareup.okhttp.Request request = chain.request();

                        // try the request
                        com.squareup.okhttp.Response response = chain.proceed(request);

                        int tryCount = 0;
                        while (!response.isSuccessful() && tryCount < 3) {

                            Log.d("intercept", "Request is not successful - " + tryCount);

                            tryCount++;

                            // retry the request
                            response = chain.proceed(request);
                        }

                        // otherwise just pass the original response on
                        return response;
                    }
                });

                MultipartBuilder mBilder = new MultipartBuilder();

                mBilder.type(MultipartBuilder.FORM);

                String url= AppData.url+"/index.php/Signup/socialSignup";


                Log.d("Driver_Profile_PHP",url);
                mBilder.addFormDataPart("register_type","2");
                mBilder.addFormDataPart("name",fbFullName);
                mBilder.addFormDataPart("email",fbEmail);
                mBilder.addFormDataPart("facebook_id",fbID);
                mBilder.addFormDataPart("facebook_token",    AppData.fbToken );
                mBilder.addFormDataPart("device_token",AppData.devicetoken);
                mBilder.addFormDataPart("mode",Lmode);
                mBilder.addFormDataPart("device_type","1");
                mBilder.addFormDataPart("country","");
                mBilder.addFormDataPart("userimage",fbimage);
                mBilder.addFormDataPart("gender",fbGender);
                mBilder.addFormDataPart("phone","");

                RequestBody requestBody = mBilder.build();

                com.squareup.okhttp.Request request = new com.squareup.okhttp.Request.Builder()

                        .url(url)

                        .post(requestBody)

                        .build();

                com.squareup.okhttp.Response response = client.newCall(request).execute();



                result = response.body().string();

                Log.d("Result",result);

//
                jobj = new JSONObject(result);
                status1=jobj.getBoolean("status");

                //statusasyn=jobj.getBoolean("status");
                //statuscode=jobj.getInt("code");
               // message=jobj.getString("message");
//
//
//                Log.d("statusdrive", String.valueOf(status));
                if(status1==true)
                {
                    message=jobj.getString("message");

                    userinfo=jobj.getJSONObject("userinfo");

                    SharedPreferences.Editor editor3 = getSharedPreferences("loginPrefs", MODE_PRIVATE).edit();
                    editor3.putString("saveLogin", "A");
                    editor3.commit();

                    SharedPreferences.Editor editor = getSharedPreferences("logindetails", MODE_PRIVATE).edit();

                    AppData. user_id =userinfo.getString("user_id");
                    AppData.email =userinfo.getString("email");
                   AppData.name =userinfo.getString("name");
                    AppData.image =userinfo.getString("image");
                   AppData. gender=userinfo.getString("gender");
                    AppData.country=userinfo.getString("country");
                   AppData.ph=userinfo.getString("phone");
                    AppData.devicetoken=userinfo.getString("device_token");
                    AppData.app_password_status=userinfo.getString("app_password_status");
                    AppData.app_password=userinfo.getString("app_password");

//
//

                   editor.putString("user_id",AppData.user_id );
//                    editor.putString("usertype", AppData. usertype );
                    editor.putString("email", AppData.email );
                   editor.putString("name",AppData.name  );
                    editor.putString("image", AppData.image );
                    editor.putString("gender", AppData. gender);
                    editor.putString("country",     AppData.country);
                    editor.putString("phone",AppData.ph );
                    editor.putString("devicetoken",AppData.devicetoken );
                    editor.putString("mode",Lmode);
                    editor.putString("register_type","2");
                    editor.putString("HomePage","Login");
                    editor.putString("first_time","true");
                    editor.putString("app_password_status","false");
                    //editor.putString("app_password_status", AppData.app_password_status);
                    editor.putString("app_password", AppData.app_password);
//                    ////////////////////////////////////////////////////////
//
//                    editor.putString("devicetoken",AppData.devicetoken );
//                    editor.putString("device_type","1" );
//                    editor.putString("facebook_login","true");
//                    editor.putString("notificationStatus",AppData.notificationStatus);
//                    editor.putString("distance",AppData.distance);
//
//                    editor.putString("gender",AppData.gender);
//                    editor.putString("dob",AppData.dob);

                    ///////////////////////////////////////////////////////////////////////

                    editor.commit();




                }
                else  if(status1==false)
                {

                    user_exist=jobj.getString("user_exist");
                }


                Log.d("Result",result.toString());

            }
            catch (Exception e) {
                Log.d("OKHTTP_RESULT", "ERROR : " + e.toString());

            }


            return null;
        }

        protected void onPostExecute(Void resultt)
        {
            super.onPostExecute(resultt);
            pbar.setVisibility(View.GONE);
           // progressBar.setVisibility(View.INVISIBLE);

           if(status1==true)
           {

               // if (user_exist.equals("1"))
               // {
                    //if(sharedPreferences2.getString("lang", " ").equals("eng")) {
                    Toast.makeText(Login.this, message, Toast.LENGTH_SHORT).show();
                    //  }
                    // else if (sharedPreferences2.getString("lang", " ").equals("ita")) {
                    //Toast.makeText(Login.this, "Login di Facebook avvenuto con successo", Toast.LENGTH_SHORT).show();
                    //   }
                    Intent i = new Intent(Login.this, HomePage.class);
             //  i.putExtra("HomePage","Login");
                    //i.putExtra("LandingPage","home");
                    startActivity(i);
                    finish();
               // }


//                else if (user_exist.equals("0")) {
//
//                    customDialog();
//
//                }

           }
            else if(status1==false)
           {
               if(user_exist.equals("0"))
               {
                   customDialog();
//

               }



           }
//            else if(status1==false)
//                Toast.makeText(Login.this, message, Toast.LENGTH_SHORT).show();
        }
    }







    public void customDialogforgoogle() {
        Spinner edt_country,edt_gender;
        RelativeLayout rl_signup;
        final RelativeLayout rl_country,rl_gender,rl_number;
        final TextView errortxt5,gendertxt,top_bar_name,errortxt6;
        final ImageView flagicon, arrow_icon2,love,arrow_icon3,phnumberimg;
        final EditText phnumber;


        View view = LayoutInflater.from(Login.this).inflate(R.layout.customdialog_google_signup, null);
        final Dialog mBottomSheetDialog = new Dialog(Login.this, R.style.MaterialDialogSheet);
        mBottomSheetDialog.setContentView(view);
        mBottomSheetDialog.setCancelable(false);
        mBottomSheetDialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        mBottomSheetDialog.getWindow().setGravity(Gravity.RIGHT);
        String locale = Login.this.getResources().getConfiguration().locale.getDisplayCountry();
        Log.d("LOCAL",locale);
        edt_gender= (Spinner)view.findViewById(R.id.gender_spinner);
        edt_country = (Spinner) view.findViewById(R.id.edt_country);

        rl_signup = (RelativeLayout) view.findViewById(R.id.rl_signup);
        top_bar_name=(TextView)view.findViewById(R.id.top_bar_name) ;
        errortxt5 = (TextView) view.findViewById(R.id.errortxt5);
        gendertxt = (TextView) view.findViewById(R.id.gendertxt);
        flagicon = (ImageView) view.findViewById(R.id.flagicon);
        errortxt6 = (TextView) view.findViewById(R.id.errortxt6);
        phnumberimg=(ImageView)view.findViewById(R.id.phnumberimg);
        phnumber=(EditText)view.findViewById(R.id.ph) ;
        rl_number=(RelativeLayout)view.findViewById(R.id.rl_number);
        arrow_icon2 = (ImageView) view.findViewById(R.id.arrow_icon2);
        love=(ImageView)view.findViewById(R.id.gendericon) ;
        arrow_icon3=(ImageView)view.findViewById(R.id.arrow_icon3);
        rl_country = (RelativeLayout) view.findViewById(R.id.rl_country);
        rl_gender=(RelativeLayout)view.findViewById(R.id.rl_gender) ;
        errortxt5.setVisibility(View.GONE);
        errortxt6.setVisibility(View.GONE);
        gendertxt.setVisibility(View.GONE);
        countryfetch();


        if(locale.equals("France"))
        {
            rl_signup.setBackgroundResource(R.drawable.frech_signup);
            top_bar_name.setText("REMPLIR LES DÉTAILS");

        }
        else {
            rl_signup.setBackgroundResource(R.drawable.signup);
            top_bar_name.setText("FILL THE DETAILS");
        }
        //////////////////
        arraygender=new ArrayList<String>();
        arraygender.add(getResources().getString(R.string.Your_gender));
        arraygender.add(getResources().getString(R.string.Male));
        arraygender.add(getResources().getString(R.string.Female));

        ArrayAdapter<String>   adp = new ArrayAdapter<String>(this, R.layout.spinner_item, arraygender);
        adp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        edt_gender.setAdapter(adp);
        edt_gender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Gender = adapterView.getItemAtPosition(i).toString();
                prefsGender = getSharedPreferences(prefName, MODE_PRIVATE);
                SharedPreferences.Editor editor = prefsGender.edit();
                //---save the values in the EditText view to preferences---
                editor.putInt("first_val", i);


                editor.commit();


                if (i > 0) {
                    if(Gender.equals("Male"))
                    {
                        Gender_Code="1";

                    }
                    else{

                        Gender_Code="2";
                    }
//                    Country_id=countryidlist.get(i-1).toString();
//                    selectedcountryid=getSharedPreferences("sharepreferencecountryid", MODE_PRIVATE).edit();
//                    selectedcountryid.putString("countryid",Country_id);
//                    selectedcountryid.commit();
//
//                    Log.d("Countryid", countryidlist.get(i - 1).toString());
//                    //checkingodomerter();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        /////////////////////////////////////



        countrydialcodelist=new ArrayList<String>();
        countryidlist = new ArrayList<String>();
        countrynamelist = new ArrayList<String>();
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
                    Country_id = countryidlist.get(i - 1).toString();
                    selectedcountryid = getSharedPreferences("sharepreferencecountryid", MODE_PRIVATE).edit();
                    selectedcountryid.putString("countryid", Country_id);
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


        //  Glide.with(getActivity()).load(userimage).placeholder(R.drawable.noimage).diskCacheStrategy(DiskCacheStrategy.RESULT).into((TouchImageView) view.findViewById(R.id.main_image));

        view.findViewById(R.id.ll_bck).setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        mBottomSheetDialog.dismiss();
                    }
                });


        rl_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Ph_Number= phnumber.getText().toString().trim();
                if(Ph_Number.length()>0) {

                    phnumber.setTextColor(Color.parseColor("#FFFFFF"));
                    errortxt6.setVisibility(View.GONE);
                    rl_number.setBackgroundResource(R.drawable.edit_background);
                    phnumberimg.setBackgroundResource(R.drawable.ic_action_name_ph);



                    if (!Gender.equals(getResources().getString(R.string.Your_gender))) {

                        if (Country.equals(getResources().getString(R.string.Country))) {

//

                            errortxt5.setVisibility(View.VISIBLE);
                            errortxt5.setText(getResources().getString(R.string.entercountry));
                            flagicon.setBackgroundResource(R.drawable.flag_black);
                            arrow_icon2.setBackgroundResource(R.drawable.dropdown_black);
                            rl_country.setBackgroundResource(R.drawable.edit_black_background);


                        } else {


                            new Vol_Asyn_TaskGoogle_Again().execute();

                        }
                    } else {

                        gendertxt.setVisibility(View.VISIBLE);
                        gendertxt.setText(getResources().getString(R.string.entergender));
                        love.setBackgroundResource(R.drawable.love_black);
                        arrow_icon3.setBackgroundResource(R.drawable.dropdown_black);
                        rl_gender.setBackgroundResource(R.drawable.edit_black_background);

                    }

                }
                else{

                    phnumber.setTextColor(Color.parseColor("#000000"));
                    errortxt6.setVisibility(View.VISIBLE);
                    errortxt6.setText(getResources().getString(R.string.enter_ph_number));
                    phnumberimg.setBackgroundResource(R.drawable.ic_action_name_ph_deselect);
                    phnumber.requestFocus();
                    phnumber.setText("");
                    phnumber.setHint(getResources().getString(R.string.ph_number));
                    phnumber.setHintTextColor(Color.BLACK);
                    rl_number.setBackgroundResource(R.drawable.edit_black_background);


                }
//
            }
        });
        mBottomSheetDialog.show();
    }
    public void customDialog()
    {
        Spinner edt_country;
        RelativeLayout rl_signup;
        final RelativeLayout rl_country,rl_number;
       final TextView errortxt5,top_bar_name,errortxt6;
        final ImageView flagicon,arrow_icon2,phnumberimg;
        final EditText phnumber;



        View view = LayoutInflater.from(Login.this).inflate(R.layout.customdialog_fb_signup, null);
        final Dialog mBottomSheetDialog = new Dialog(Login.this, R.style.MaterialDialogSheet);
        mBottomSheetDialog.setContentView(view);
        mBottomSheetDialog.setCancelable(false);
        mBottomSheetDialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        mBottomSheetDialog.getWindow().setGravity(Gravity.RIGHT);
        String locale = Login.this.getResources().getConfiguration().locale.getDisplayCountry();
        Log.d("LOCAL",locale);


        edt_country=(Spinner)view.findViewById(R.id.edt_country) ;
        phnumber=(EditText)view.findViewById(R.id.phnumber);
        top_bar_name=(TextView)view.findViewById(R.id.top_bar_name);
        rl_signup=(RelativeLayout)view.findViewById(R.id.rl_signup);
        errortxt5=(TextView)view.findViewById(R.id.errortxt5) ;
        errortxt6=(TextView)view.findViewById(R.id.errortxt6) ;
        flagicon=(ImageView)view.findViewById(R.id.flagicon);
        arrow_icon2=(ImageView)view.findViewById(R.id.arrow_icon2);
        phnumberimg=(ImageView)view.findViewById(R.id.phnumberimg);
        rl_number=(RelativeLayout)view.findViewById(R.id.rl_number);
        rl_country=(RelativeLayout)view.findViewById(R.id.rl_country);
        errortxt5.setVisibility(View.GONE);
        errortxt6.setVisibility(View.GONE);
        countryfetch();

        if(locale.equals("France"))
        {
rl_signup.setBackgroundResource(R.drawable.frech_signup);
            top_bar_name.setText("REMPLIR LES DÉTAILS");

        }
        else {
            rl_signup.setBackgroundResource(R.drawable.signup);
            top_bar_name.setText("FILL THE DETAILS");
        }
        countrydialcodelist=new ArrayList<String>();
        countryidlist=new ArrayList<String>();
        countrynamelist=new ArrayList<String>();
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


        //  Glide.with(getActivity()).load(userimage).placeholder(R.drawable.noimage).diskCacheStrategy(DiskCacheStrategy.RESULT).into((TouchImageView) view.findViewById(R.id.main_image));

        view.findViewById(R.id.ll_bck).setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        mBottomSheetDialog.dismiss();
                    }
                });


        rl_signup .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Ph_Number=phnumber.getText().toString().trim();

                if(Ph_Number.length()>0)

                {

                    phnumber.setTextColor(Color.parseColor("#FFFFFF"));
                    errortxt6.setVisibility(View.GONE);
                    rl_number.setBackgroundResource(R.drawable.edit_background);
                    phnumberimg.setBackgroundResource(R.drawable.ic_action_name_ph);




                    if (Country.equals(getResources().getString(R.string.Country))) {

                        errortxt5.setVisibility(View.VISIBLE);
                        errortxt5.setText(getResources().getString(R.string.entercountry));
                        flagicon.setBackgroundResource(R.drawable.flag_black);
                        arrow_icon2.setBackgroundResource(R.drawable.dropdown_black);
                        rl_country.setBackgroundResource(R.drawable.edit_black_background);


                    } else {


                        new Vol_Asyn_Task_Again().execute();

                    }

                }
                else{

                    phnumber.setTextColor(Color.parseColor("#000000"));
                    errortxt6.setVisibility(View.VISIBLE);
                    errortxt6.setText(getResources().getString(R.string.enter_ph_number));
                    phnumberimg.setBackgroundResource(R.drawable.ic_action_name_ph_deselect);
                    phnumber.requestFocus();
                    phnumber.setText("");
                    phnumber.setHint(getResources().getString(R.string.ph_number));
                    phnumber.setHintTextColor(Color.BLACK);
                    rl_number.setBackgroundResource(R.drawable.edit_black_background);


                }



//
            }
        });
//        if(apiresponse==false)
//        {
//            mBottomSheetDialog.dismiss();
//            if(AppData.language.equals("eng"))
//                Toast.makeText(Login.this,"Failed to fetch response",Toast.LENGTH_SHORT).show();
//            else if(AppData.language.equals("ita"))
//                Toast.makeText(Login.this,"Impossibile recuperare la risposta",Toast.LENGTH_SHORT).show();
//
//
//        }
        mBottomSheetDialog.show();
    }

    private class Vol_Asyn_Task_Again extends AsyncTask<Void, Void, Void> {
        JSONObject response1;
        JSONObject res;

        String result;
        JSONObject jobj,userinfo;
        boolean statusasyn;
        int statuscode;
        String message;
        boolean status1;
        private static final long CONNECT_TIMEOUT_MILLIS =90000 ;
        private static final long READ_TIMEOUT_MILLIS =90000 ;
        protected void onPreExecute() {

            super.onPreExecute();
            // progressBar.setVisibility(View.VISIBLE);

        }



        protected Void doInBackground(Void... bitmaps) {
            try {

                OkHttpClient client = new OkHttpClient();


                client.setConnectTimeout(CONNECT_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS);
                client.setReadTimeout(READ_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS);
                client.interceptors().add(new Interceptor() {
                    @Override
                    public com.squareup.okhttp.Response intercept(Chain chain) throws IOException {
                        com.squareup.okhttp.Request request = chain.request();

                        // try the request
                        com.squareup.okhttp.Response response = chain.proceed(request);

                        int tryCount = 0;
                        while (!response.isSuccessful() && tryCount < 3) {

                            Log.d("intercept", "Request is not successful - " + tryCount);

                            tryCount++;

                            // retry the request
                            response = chain.proceed(request);
                        }

                        // otherwise just pass the original response on
                        return response;
                    }
                });

                MultipartBuilder mBilder = new MultipartBuilder();

                mBilder.type(MultipartBuilder.FORM);

                String url= AppData.url+"/index.php/Signup/socialSignup";


                Log.d("Driver_Profile_PHP",url);
                mBilder.addFormDataPart("register_type","2");
                mBilder.addFormDataPart("name",fbFullName);
                mBilder.addFormDataPart("email",fbEmail);
                mBilder.addFormDataPart("facebook_id",fbID);
                mBilder.addFormDataPart("facebook_token",    AppData.fbToken );
                mBilder.addFormDataPart("device_token",AppData.devicetoken);
                mBilder.addFormDataPart("mode",Lmode);
                mBilder.addFormDataPart("device_type","1");
                mBilder.addFormDataPart("country",Country_id);
                mBilder.addFormDataPart("userimage",fbimage);
                mBilder.addFormDataPart("gender",fbGender);
                mBilder.addFormDataPart("phone",Dial_code+""+Ph_Number);
                RequestBody requestBody = mBilder.build();

                com.squareup.okhttp.Request request = new com.squareup.okhttp.Request.Builder()

                        .url(url)

                        .post(requestBody)

                        .build();

                com.squareup.okhttp.Response response = client.newCall(request).execute();



                result = response.body().string();

                Log.d("Result",result);

//
                jobj = new JSONObject(result);
                status1=jobj.getBoolean("status");
                user_exist=jobj.getString("user_exist");
                //statusasyn=jobj.getBoolean("status");
                //statuscode=jobj.getInt("code");
                message=jobj.getString("message");
//
//
//                Log.d("statusdrive", String.valueOf(status));
                if(user_exist.equals("1"))
                {

                    userinfo=jobj.getJSONObject("userinfo");



                    SharedPreferences.Editor editor3 = getSharedPreferences("loginPrefs", MODE_PRIVATE).edit();
                    editor3.putString("saveLogin", "A");
                    editor3.commit();


                    SharedPreferences.Editor editor = getSharedPreferences("logindetails", MODE_PRIVATE).edit();

                    AppData. user_id =userinfo.getString("user_id");
                    AppData.email =userinfo.getString("email");
                    AppData.name =userinfo.getString("name");
                    AppData.image =userinfo.getString("image");
                    AppData. gender=userinfo.getString("gender");
                    AppData.country=userinfo.getString("country");
                    AppData.ph=userinfo.getString("phone");
                    AppData.devicetoken=userinfo.getString("device_token");



                    AppData. app_password_status=userinfo.getString("app_password_status");
                    AppData. app_password=userinfo.getString("app_password");
//
//

                    editor.putString("user_id",AppData.user_id );
//                    editor.putString("usertype", AppData. usertype );
                    editor.putString("email", AppData.email );
                    editor.putString("name",AppData.name  );
                    editor.putString("image", AppData.image );
                    editor.putString("gender", AppData. gender);
                    editor.putString("country",     AppData.country);
                    editor.putString("phone",AppData.ph );
                    editor.putString("devicetoken",AppData.devicetoken );
                    editor.putString("mode",Lmode);
                    editor.putString("register_type","2");
                    editor.putString("HomePage","Login");
                    editor.putString("first_time","true");
                   // editor.putString("app_password_status",AppData. app_password_status);
                    editor.putString("app_password_status","false");
                    editor.putString("app_password",AppData. app_password);
                    editor.commit();




                }

                Log.d("Result",result.toString());

            }
            catch (Exception e) {
                Log.d("OKHTTP_RESULT", "ERROR : " + e.toString());

            }


            return null;
        }

        protected void onPostExecute(Void resultt) {
            super.onPostExecute(resultt);
            // progressBar.setVisibility(View.INVISIBLE);
if(status1==true) {

   // {
        //if(sharedPreferences2.getString("lang", " ").equals("eng")) {
        Toast.makeText(Login.this, message, Toast.LENGTH_SHORT).show();
        //  }
        // else if (sharedPreferences2.getString("lang", " ").equals("ita")) {
        //Toast.makeText(Login.this, "Login di Facebook avvenuto con successo", Toast.LENGTH_SHORT).show();
        //   }
        Intent i = new Intent(Login.this, HomePage.class);
        //i.putExtra("LandingPage","home");
    //i.putExtra("HomePage","Login");
        startActivity(i);
        finish();
    //}



}
            else if(status1==false)
    Toast.makeText(Login.this, message, Toast.LENGTH_SHORT).show();
        }
    }


    private class Vol_Asyn_TaskGoogle_Again extends AsyncTask<Void, Void, Void> {
        JSONObject response1;
        JSONObject res;
        String user_exitforggogle;

        String result;
        JSONObject jobj,userinfo;
        boolean statusforgoogle=false;
        int statuscode;
        String message;
        boolean status1;
        private static final long CONNECT_TIMEOUT_MILLIS =90000 ;
        private static final long READ_TIMEOUT_MILLIS =90000 ;
        protected void onPreExecute() {

            super.onPreExecute();
            pbar.setVisibility(View.VISIBLE);

        }



        protected Void doInBackground(Void... bitmaps) {
            try {

                OkHttpClient client = new OkHttpClient();


                client.setConnectTimeout(CONNECT_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS);
                client.setReadTimeout(READ_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS);
                client.interceptors().add(new Interceptor() {
                    @Override
                    public com.squareup.okhttp.Response intercept(Chain chain) throws IOException {
                        com.squareup.okhttp.Request request = chain.request();

                        // try the request
                        com.squareup.okhttp.Response response = chain.proceed(request);

                        int tryCount = 0;
                        while (!response.isSuccessful() && tryCount < 3) {

                            Log.d("intercept", "Request is not successful - " + tryCount);

                            tryCount++;

                            // retry the request
                            response = chain.proceed(request);
                        }

                        // otherwise just pass the original response on
                        return response;
                    }
                });

                MultipartBuilder mBilder = new MultipartBuilder();

                mBilder.type(MultipartBuilder.FORM);

                String url= AppData.url+"/index.php/Signup/socialSignup";


                Log.d("Driver_Profile_PHP",url);
                mBilder.addFormDataPart("register_type","3");
                mBilder.addFormDataPart("name",GooglepersonName);
                mBilder.addFormDataPart("email",GoogleEmail);
                mBilder.addFormDataPart("facebook_id","");
                mBilder.addFormDataPart("facebook_token",    "" );
                mBilder.addFormDataPart("device_token",AppData.devicetoken);
                mBilder.addFormDataPart("mode",Lmode);
                mBilder.addFormDataPart("device_type","1");
                mBilder.addFormDataPart("country",Country_id);
                mBilder.addFormDataPart("userimage",GooglePhotoUrl);
                mBilder.addFormDataPart("gender",Gender_Code);
                mBilder.addFormDataPart("phone",Dial_code+""+Ph_Number);
                RequestBody requestBody = mBilder.build();

                com.squareup.okhttp.Request request = new com.squareup.okhttp.Request.Builder()

                        .url(url)

                        .post(requestBody)

                        .build();

                com.squareup.okhttp.Response response = client.newCall(request).execute();



                result = response.body().string();

                Log.d("Result",result);

//
                jobj = new JSONObject(result);
                statusforgoogle=jobj.getBoolean("status");
                user_exitforggogle=jobj.getString("user_exist");
                //statusasyn=jobj.getBoolean("status");
                //statuscode=jobj.getInt("code");
                message=jobj.getString("message");
//
//
//                Log.d("statusdrive", String.valueOf(status));
                if(user_exitforggogle.equals("1"))
                {

                    userinfo=jobj.getJSONObject("userinfo");


                    SharedPreferences.Editor editor3 = getSharedPreferences("loginPrefs", MODE_PRIVATE).edit();
                    editor3.putString("saveLogin", "A");
                    editor3.commit();
                    SharedPreferences.Editor editor = getSharedPreferences("logindetails", MODE_PRIVATE).edit();

                    AppData. user_id =userinfo.getString("user_id");
                    AppData.email =userinfo.getString("email");
                    AppData.name =userinfo.getString("name");
                    AppData.image =userinfo.getString("image");
                    AppData. gender=userinfo.getString("gender");
                    AppData.country=userinfo.getString("country");
                    AppData.ph=userinfo.getString("phone");
                    AppData.devicetoken=userinfo.getString("device_token");
                    AppData.app_password=userinfo.getString("app_password");
                    AppData.app_password_status=userinfo.getString("app_password_status");
//
//

                    editor.putString("user_id",AppData.user_id );
//                    editor.putString("usertype", AppData. usertype );
                    editor.putString("email", AppData.email );
                    editor.putString("name",AppData.name  );
                    editor.putString("image", AppData.image );
                    editor.putString("gender", AppData. gender);
                    editor.putString("country",     AppData.country);
                    editor.putString("phone",AppData.ph );
                    editor.putString("devicetoken",AppData.devicetoken );
                    editor.putString("mode",Lmode);
                    editor.putString("register_type","3");
                    editor.putString("HomePage","Login");
                    editor.putString("first_time","true");

                    editor.putString("app_password",AppData.app_password);
                   // editor.putString("app_password_status",AppData.app_password_status);
                    editor.putString("app_password_status","false");
                    editor.commit();




                }

                Log.d("Result",result.toString());

            }
            catch (Exception e) {
                Log.d("OKHTTP_RESULT", "ERROR : " + e.toString());

            }


            return null;
        }

        protected void onPostExecute(Void resultt) {
            super.onPostExecute(resultt);
            pbar.setVisibility(View.GONE);
            if(statusforgoogle==true) {

                // {
                //if(sharedPreferences2.getString("lang", " ").equals("eng")) {
                Toast.makeText(Login.this, message, Toast.LENGTH_SHORT).show();
                //  }
                // else if (sharedPreferences2.getString("lang", " ").equals("ita")) {
                //Toast.makeText(Login.this, "Login di Facebook avvenuto con successo", Toast.LENGTH_SHORT).show();
                //   }
                Intent i = new Intent(Login.this, HomePage.class);
                //i.putExtra("LandingPage","home");
                //i.putExtra("HomePage","Login");
                startActivity(i);
                finish();
                //}



            }
            else if(statusforgoogle==false)
                Toast.makeText(Login.this, message, Toast.LENGTH_SHORT).show();
        }


    }
    public void countryfetch()
    {
      String  urlJsonObj = AppData.url+"/index.php/Signup/fetchcountry";
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
                            String  dial_code = josonobj2.getString("dial_code");
                            countrystring[i] = countryId;
                            countrycode[i]=dial_code;

                            countryidlist.add(countrystring[i]);
                            countrydialcodelist.add(countrycode[i]);
                            //carregistration_number_list.add( strcar[i]);
                            countrynamelist.add(countryName);
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
    public void normalLoginVol()
    {

        urlJsonObjlogin = AppData.url+"/index.php/Signup/login?email="+Email+"&password="+Password+"&device_token="+AppData.devicetoken+"&device_type=1&mode="+Lmode;
        Log.d("urlJsonObjsignup",urlJsonObjlogin);
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, urlJsonObjlogin, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                try {
                    Log.d("LoginResponse",jsonObject.toString());
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
                        AppData.app_password=Details.getString("app_password");
               AppData. app_password_status=Details.getString("app_password_status");




                        /////////////////autologin///////////////////

//                        SharedPreferences.Editor editor2 = getSharedPreferences("MY_PREFS_NAME234", MODE_PRIVATE).edit();
//                        editor2.putString("name", Email);
//                        editor2.putString("idName", Password);
//                        editor2.commit();

                        SharedPreferences.Editor editor3 = getSharedPreferences("loginPrefs", MODE_PRIVATE).edit();
                        editor3.putString("saveLogin", "A");
                        editor3.commit();

                        //Log.d("Savelogin", String.valueOf(editor2.putString("saveLogin", "A")));


                        ///////////////////////////////////////////

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
                        editor.putString("HomePage","Login");
                        editor.putString("first_time","true");

                        editor.putString("app_password",AppData.app_password);
                       // editor.putString("app_password_status",AppData.app_password_status);
                        editor.putString("app_password_status","false");
                        editor.commit();

//if(app_password_status.equals("0"))

{
    Toast.makeText(Login.this, message, Toast.LENGTH_SHORT).show();
    Intent i = new Intent(Login.this, HomePage.class);
    startActivity(i);
    finish();
}
//else
//    {
//    Toast.makeText(Login.this, message, Toast.LENGTH_SHORT).show();
//    Intent i = new Intent(Login.this, ActivityPIN.class);
//    startActivity(i);
//    finish();
//}

                    }
                    else if(status==false)
                    {


                        Toast.makeText(Login.this,message,Toast.LENGTH_SHORT).show();
                    }
                    pbar.setVisibility(View.GONE);

                } catch (Exception e) {

                    e.printStackTrace();
                    pbar.setVisibility(View.GONE);
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


//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
//
//    }


    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        Log.d("Current_User", String.valueOf(currentUser));

//        OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi.silentSignIn(mGoogleApiClient);
//        if (opr.isDone()) {
//            // If the user's cached credentials are valid, the OptionalPendingResult will be "done"
//            // and the GoogleSignInResult will be available instantly.
//            Log.d(TAG, "Got cached sign-in");
//            GoogleSignInResult result = opr.get();
//            handleSignInResult(result);
//        } else {
//            // If the user has not previously signed in on this device or the sign-in has expired,
//            // this asynchronous branch will attempt to sign in the user silently.  Cross-device
//            // single sign-on will occur in this branch.
//            //showProgressDialog();
//            opr.setResultCallback(new ResultCallback<GoogleSignInResult>() {
//                @Override
//                public void onResult(GoogleSignInResult googleSignInResult) {
//                    //hideProgressDialog();
//                    handleSignInResult(googleSignInResult);
//                }
//            });
//        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful())
                        {
                            // Sign in success, update UI with the signed-in user's information

                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            GoogleEmail= user.getEmail();
                            GooglepersonName=user.getDisplayName();
                            GooglePhotoUrl=user.getPhotoUrl().toString();

                            //if(result.isSuccess()==true)

                           // {

                                new Asyntask_google_signup().execute();
                                //Toast.makeText(Login.this,"Go to Asyntask Page",Toast.LENGTH_SHORT).show();

//                Log.e(TAG, "Name: " + GooglepersonName + ", email: " + GoogleEmail
//                        + ", Image: " + GooglePhotoUrl + ", Tokenid=" + tokenid + ", Google id" + google_id);


                            //}
//                            Intent i=new Intent(Login.this,HomePage.class);
//                            startActivity(i);
//                            finish();
                           // updateUI(user);
                        }


                        else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(Login.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }

                        // ...
                    }
                });
    }



    private class Asyntask_google_signup extends AsyncTask<Void, Void, Void>
    {
        JSONObject response1;
        JSONObject res;

        String result,user_exitforggogle;
        JSONObject jobj,userinfo;
        boolean statusasyn;
        int statuscode;
        String message;
        boolean statusgoogle=false;
        private static final long CONNECT_TIMEOUT_MILLIS =90000 ;
        private static final long READ_TIMEOUT_MILLIS =90000 ;
        protected void onPreExecute() {

            super.onPreExecute();


        }



        protected Void doInBackground(Void... bitmaps) {
            try {

                OkHttpClient client = new OkHttpClient();


                client.setConnectTimeout(CONNECT_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS);
                client.setReadTimeout(READ_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS);
                client.interceptors().add(new Interceptor() {
                    @Override
                    public com.squareup.okhttp.Response intercept(Chain chain) throws IOException {
                        com.squareup.okhttp.Request request = chain.request();

                        // try the request
                        com.squareup.okhttp.Response response = chain.proceed(request);

                        int tryCount = 0;
                        while (!response.isSuccessful() && tryCount < 3) {

                            Log.d("intercept", "Request is not successful - " + tryCount);

                            tryCount++;

                            // retry the request
                            response = chain.proceed(request);
                        }

                        // otherwise just pass the original response on
                        return response;
                    }
                });

                MultipartBuilder mBilder = new MultipartBuilder();

                mBilder.type(MultipartBuilder.FORM);

                String url= AppData.url+"/index.php/Signup/socialSignup";


                Log.d("Driver_Profile_PHP",url);
                mBilder.addFormDataPart("register_type","3");
                mBilder.addFormDataPart("name",GooglepersonName);
                mBilder.addFormDataPart("email",GoogleEmail);
                mBilder.addFormDataPart("facebook_id","");
                mBilder.addFormDataPart("facebook_token","");
                mBilder.addFormDataPart("device_token",AppData.devicetoken);
                mBilder.addFormDataPart("mode",Lmode);
                mBilder.addFormDataPart("device_type","1");
                mBilder.addFormDataPart("country","");
                mBilder.addFormDataPart("userimage",GooglePhotoUrl);
                mBilder.addFormDataPart("gender","");
                mBilder.addFormDataPart("phone","");
                RequestBody requestBody = mBilder.build();

                com.squareup.okhttp.Request request = new com.squareup.okhttp.Request.Builder()

                        .url(url)

                        .post(requestBody)

                        .build();

                com.squareup.okhttp.Response response = client.newCall(request).execute();



                result = response.body().string();

                Log.d("Result",result);

//
                jobj = new JSONObject(result);
                statusgoogle=jobj.getBoolean("status");

                //statusasyn=jobj.getBoolean("status");
                //statuscode=jobj.getInt("code");
                // message=jobj.getString("message");
//
//
//                Log.d("statusdrive", String.valueOf(status));
                if(statusgoogle==true)
                {
                    message=jobj.getString("message");

                    userinfo=jobj.getJSONObject("userinfo");

                    SharedPreferences.Editor editor3 = getSharedPreferences("loginPrefs", MODE_PRIVATE).edit();
                    editor3.putString("saveLogin", "A");
                    editor3.commit();

                    SharedPreferences.Editor editor = getSharedPreferences("logindetails", MODE_PRIVATE).edit();

                    AppData. user_id =userinfo.getString("user_id");
                    AppData.email =userinfo.getString("email");
                    AppData.name =userinfo.getString("name");
                    AppData.image =userinfo.getString("image");
                    AppData. gender=userinfo.getString("gender");
                    AppData.country=userinfo.getString("country");
                    AppData.ph=userinfo.getString("phone");
                    AppData.devicetoken=userinfo.getString("device_token");

                    AppData.app_password=userinfo.getString("app_password");
                    AppData.app_password_status=userinfo.getString("app_password_status");
//
//

                    editor.putString("user_id",AppData.user_id );
//                    editor.putString("usertype", AppData. usertype );
                    editor.putString("email", AppData.email );
                    editor.putString("name",AppData.name  );
                    editor.putString("image", AppData.image );
                    editor.putString("gender", AppData. gender);
                    editor.putString("country",     AppData.country);
                    editor.putString("phone",AppData.ph );
                    editor.putString("devicetoken",AppData.devicetoken );
                    editor.putString("mode",Lmode);
                    editor.putString("register_type","3");
                    editor.putString("HomePage","Login");
                    editor.putString("first_time","true");

                    editor.putString("app_password",AppData.app_password);
                   // editor.putString("app_password_status",AppData.app_password_status);
                    editor.putString("app_password_status","false");
//                    ////////////////////////////////////////////////////////
//
//                    editor.putString("devicetoken",AppData.devicetoken );
//                    editor.putString("device_type","1" );
//                    editor.putString("facebook_login","true");
//                    editor.putString("notificationStatus",AppData.notificationStatus);
//                    editor.putString("distance",AppData.distance);
//
//                    editor.putString("gender",AppData.gender);
//                    editor.putString("dob",AppData.dob);

                    ///////////////////////////////////////////////////////////////////////

                    editor.commit();




                }
                else  if(statusgoogle==false)
                {

                    user_exitforggogle=jobj.getString("user_exist");
                }


                Log.d("Result",result.toString());

            }
            catch (Exception e) {
                Log.d("OKHTTP_RESULT", "ERROR : " + e.toString());

            }


            return null;
        }

        protected void onPostExecute(Void resultt)
        {
            super.onPostExecute(resultt);
            // progressBar.setVisibility(View.INVISIBLE);

            if(statusgoogle==true)
            {

                // if (user_exist.equals("1"))
                // {
                //if(sharedPreferences2.getString("lang", " ").equals("eng")) {
                Toast.makeText(Login.this, message, Toast.LENGTH_SHORT).show();
                //  }
                // else if (sharedPreferences2.getString("lang", " ").equals("ita")) {
                //Toast.makeText(Login.this, "Login di Facebook avvenuto con successo", Toast.LENGTH_SHORT).show();
                //   }
                Intent i = new Intent(Login.this, HomePage.class);
                //i.putExtra("LandingPage","home");
            //    i.putExtra("HomePage","Login");

                startActivity(i);
                finish();
                // }


//                else if (user_exist.equals("0")) {
//
//                    customDialog();
//
//                }

            }
            else if(statusgoogle==false)
            {
                if(user_exitforggogle.equals("0"))
                {
                    //Toast.makeText(Login.this,"Go to dialog",Toast.LENGTH_SHORT).show();
                    //customDialog();
                    customDialogforgoogle();
//

                }



            }
            pbar.setVisibility(View.GONE);
//            else if(status1==false)
//                Toast.makeText(Login.this, message, Toast.LENGTH_SHORT).show();
        }
    }

    public void AppExit()
    {

        //this.finish();
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);


    }
}
