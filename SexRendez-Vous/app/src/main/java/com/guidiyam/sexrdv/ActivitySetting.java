package com.guidiyam.sexrdv;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.guidiyam.sexrdv.Adapter.OptionAdapter;
import com.guidiyam.sexrdv.volley.AppData;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.MultipartBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.RequestBody;

import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class ActivitySetting extends AppCompatActivity {
    Switch aSwitch1,aSwitch2,aSwitch3;
    LinearLayout menu;
    LinearLayout back;
    String conpin="";
    RelativeLayout setPIN,error_layout;
    EditText pedt1,pedt2,pedt3,pedt4;
    EditText pedt11,pedt22,pedt33,pedt44;
    TextView change_pin;
    SharedPreferences sharedPreferences;
private AlertDialog alert11;
    String app_password_status;
    boolean aswitchon=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        sharedPreferences = getSharedPreferences("logindetails", MODE_PRIVATE);
        menu=(LinearLayout)findViewById(R.id.menu) ;
        change_pin=(TextView)findViewById(R.id.change_pin);
        change_pin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addDialog();
            }
        });
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        aSwitch1=(Switch)findViewById(R.id.switch1);
        aSwitch2=(Switch)findViewById(R.id.switch2);
        aSwitch3=(Switch)findViewById(R.id.switch3);

        aSwitch1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Is the switch is on?
                aswitchon = ((Switch) v).isChecked();
                if (aswitchon)
                {
                    if(!sharedPreferences.getString("app_password", " ").equals(""))

                    {
                        new UpdateAppPin_status().execute();
                    }
                    else{
                        aSwitch1.setChecked(false);
                        addDialog();
                        //addDialog();


                    }
          //addDialog();
//                    Intent i=new Intent(ActivitySetting.this,ActivityPIN.class);
//                    startActivity(i);

                    //bt_new_goal.setClickable(false);
                }

                else {


                    if(!sharedPreferences.getString("app_password", " ").equals(""))

                    {
                        new UpdateAppPin_status().execute();
                    }
                    else{
                        aSwitch1.setChecked(false);
                        addDialog();
                       // addDialog();
                        //new UpdatePin().execute();

                    }
                    //Do something when switch is off/unchecked
                   // aSwitch1.setText("NO");
                    //aSwitch1.setTextColor(Color.parseColor("#FFFFFF"));

                }
            }
        });

        aSwitch2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean on=false;
                //Is the switch is on?
                on = ((Switch) v).isChecked();
                if (on) {
                    //Do something when switch is on/checked
                   // aSwitch2.setText("YES");
                   // aSwitch2.setTextColor(Color.parseColor("#5758D6"));

                    //bt_new_goal.setClickable(false);
                } else {
                    //Do something when switch is off/unchecked
                    //aSwitch2.setText("NO");
                  //  aSwitch2.setTextColor(Color.parseColor("#FFFFFF"));

                }
            }
        });


        aSwitch3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean on=false;
                //Is the switch is on?
                on = ((Switch) v).isChecked();
                if (on) {
                    //Do something when switch is on/checked
                    //aSwitch3.setText("YES");
                    //aSwitch3.setTextColor(Color.parseColor("#5758D6"));
                    //bt_new_goal.setClickable(false);
                } else {
                    //Do something when switch is off/unchecked
                    //aSwitch3.setText("NO");
                   // aSwitch3.setTextColor(Color.parseColor("#FFFFFF"));

                }
            }
        });

    }

  private void addDialog() {
    LinearLayout back;
    LayoutInflater li = LayoutInflater.from(this);
    View promptsView = li.inflate(R.layout.pin_dialog, null);
    AlertDialog.Builder alert = new AlertDialog.Builder(this);
    alert.setCancelable(true);
    alert.setView(promptsView);
    pedt1=(EditText)promptsView.findViewById(R.id.pedt1);
    pedt2=(EditText)promptsView.findViewById(R.id.pedt2);
    pedt3=(EditText)promptsView.findViewById(R.id.pedt3);
    pedt4=(EditText)promptsView.findViewById(R.id.pedt4);
    /////////////////////////////////////////////////////////
    pedt1.setInputType(InputType.TYPE_CLASS_NUMBER );
    pedt2.setInputType(InputType.TYPE_CLASS_NUMBER );
    pedt3.setInputType(InputType.TYPE_CLASS_NUMBER );
    pedt4.setInputType(InputType.TYPE_CLASS_NUMBER );

      pedt1.setTransformationMethod(new AsteriskPasswordTransformationMethod());
      pedt2.setTransformationMethod(new AsteriskPasswordTransformationMethod());
      pedt3.setTransformationMethod(new AsteriskPasswordTransformationMethod());
      pedt4.setTransformationMethod(new AsteriskPasswordTransformationMethod());

    pedt11=(EditText)promptsView.findViewById(R.id.pedt11);
    pedt22=(EditText)promptsView.findViewById(R.id.pedt22);
    pedt33=(EditText)promptsView.findViewById(R.id.pedt33);
    pedt44=(EditText)promptsView.findViewById(R.id.pedt44);



    pedt11.setInputType(InputType.TYPE_CLASS_NUMBER );
    pedt22.setInputType(InputType.TYPE_CLASS_NUMBER );
    pedt33.setInputType(InputType.TYPE_CLASS_NUMBER );
    pedt44.setInputType(InputType.TYPE_CLASS_NUMBER );
      pedt11.setTransformationMethod(new AsteriskPasswordTransformationMethod());
      pedt22.setTransformationMethod(new AsteriskPasswordTransformationMethod());
      pedt33.setTransformationMethod(new AsteriskPasswordTransformationMethod());
      pedt44.setTransformationMethod(new AsteriskPasswordTransformationMethod());



      pedt1.addTextChangedListener(new TextWatcher() {

          public void onTextChanged(CharSequence s, int start, int before,
                                    int count) {
              Integer textlength1 = pedt1.getText().length();

              if (textlength1 >= 1) {
                  pedt2.requestFocus();
              }
          }

          @Override
          public void afterTextChanged(Editable s) {
              // TODO Auto-generated method stub
          }

          @Override
          public void beforeTextChanged(CharSequence s, int start, int count,
                                        int after) {
              // TODO Auto-generated method stub
          }
      });

      pedt2.addTextChangedListener(new TextWatcher() {

          public void onTextChanged(CharSequence s, int start, int before,
                                    int count) {
              Integer textlength2 = pedt2.getText().length();

              if (textlength2 >= 1) {
                  pedt3.requestFocus();

              }

              if(textlength2==0)
              {
                  pedt1.requestFocus();
              }
          }

          @Override
          public void afterTextChanged(Editable s) {
              // TODO Auto-generated method stub
          }

          @Override
          public void beforeTextChanged(CharSequence s, int start, int count,
                                        int after) {
              // TODO Auto-generated method stub

          }
      });


      pedt3.addTextChangedListener(new TextWatcher() {

          public void onTextChanged(CharSequence s, int start, int before,
                                    int count) {
              Integer textlength2 = pedt3.getText().length();

              if (textlength2 >= 1) {
                  pedt4.requestFocus();

              }

              if(textlength2==0)
              {
                  pedt2.requestFocus();
              }
          }

          @Override
          public void afterTextChanged(Editable s) {
              // TODO Auto-generated method stub
          }

          @Override
          public void beforeTextChanged(CharSequence s, int start, int count,
                                        int after) {
              // TODO Auto-generated method stub

          }
      });


      pedt4.addTextChangedListener(new TextWatcher() {

          public void onTextChanged(CharSequence s, int start, int before,
                                    int count) {
              Integer textlength2 = pedt4.getText().length();

              if (textlength2 >= 1) {
                  pedt11.requestFocus();

              }
              if(textlength2==0)
              {
                  pedt3.requestFocus();
              }
          }

          @Override
          public void afterTextChanged(Editable s) {
              // TODO Auto-generated method stub


              //Toast.makeText(getApplicationContext(),"After text change is called",Toast.LENGTH_SHORT).show();
          }

          @Override
          public void beforeTextChanged(CharSequence s, int start, int count,
                                        int after) {
              // TODO Auto-generated method stub

          }
      });



      ////////////////////////////////////////////////////
      pedt11.addTextChangedListener(new TextWatcher() {

          public void onTextChanged(CharSequence s, int start, int before,
                                    int count) {
              Integer textlength1 = pedt11.getText().length();

              if (textlength1 >= 1) {
                  pedt22.requestFocus();
              }

              if(textlength1==0)
              {
                  pedt4.requestFocus();
              }
          }

          @Override
          public void afterTextChanged(Editable s) {
              // TODO Auto-generated method stub
          }

          @Override
          public void beforeTextChanged(CharSequence s, int start, int count,
                                        int after) {
              // TODO Auto-generated method stub

              //Toast.makeText(getApplicationContext(),"before text change is called",Toast.LENGTH_SHORT).show();
          }
      });

      pedt22.addTextChangedListener(new TextWatcher() {

          public void onTextChanged(CharSequence s, int start, int before,
                                    int count) {
              Integer textlength2 = pedt22.getText().length();

              if (textlength2 >= 1) {
                  pedt33.requestFocus();

              }

              if(textlength2==0)
              {
                  pedt11.requestFocus();
              }
          }

          @Override
          public void afterTextChanged(Editable s) {
              // TODO Auto-generated method stub
          }

          @Override
          public void beforeTextChanged(CharSequence s, int start, int count,
                                        int after) {
              // TODO Auto-generated method stub

          }
      });


      pedt33.addTextChangedListener(new TextWatcher() {

          public void onTextChanged(CharSequence s, int start, int before,
                                    int count) {
              Integer textlength2 = pedt33.getText().length();

              if (textlength2 >= 1) {
                  pedt44.requestFocus();

              }
              if(textlength2==0)
              {
                  pedt22.requestFocus();
              }
          }

          @Override
          public void afterTextChanged(Editable s) {
              // TODO Auto-generated method stub
          }

          @Override
          public void beforeTextChanged(CharSequence s, int start, int count,
                                        int after) {
              // TODO Auto-generated method stub

          }
      });



      pedt44.addTextChangedListener(new TextWatcher() {

          public void onTextChanged(CharSequence s, int start, int before,
                                    int count) {
              Integer textlength2 = pedt44.getText().length();

//              if (textlength2 >= 1) {
//                  pedt44.requestFocus();
//
//              }
              if(textlength2==0)
              {
                  pedt33.requestFocus();
              }
          }

          @Override
          public void afterTextChanged(Editable s) {
              // TODO Auto-generated method stub
          }

          @Override
          public void beforeTextChanged(CharSequence s, int start, int count,
                                        int after) {
              // TODO Auto-generated method stub

          }
      });



      ///////////////////////////////////////////////////
    back= (LinearLayout) promptsView.findViewById(R.id.menu);
    setPIN=(RelativeLayout)promptsView.findViewById(R.id.setPin);
    error_layout=(RelativeLayout)promptsView.findViewById(R.id.error_layout) ;

    alert11 = alert.create();

    WindowManager.LayoutParams wmlp = alert11.getWindow().getAttributes();

    wmlp.gravity = Gravity.CENTER;
    wmlp.x = 0;   //x position
    wmlp.y = 0;   //y position
    alert11.show();
    alert11.getWindow().setBackgroundDrawable(null);

    back.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            alert11.dismiss();
        }
    });

    setPIN.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String a=pedt1.getText().toString().trim();
            String b=pedt2.getText().toString().trim();
            String c=pedt3.getText().toString().trim();
            String d=pedt4.getText().toString().trim();
            String a11=pedt11.getText().toString().trim();
            String a22=pedt22.getText().toString().trim();
            String a33=pedt33.getText().toString().trim();
            String a44=pedt44.getText().toString().trim();
            String pin=a+""+b+""+c+""+d;
             conpin=a11+""+a22+""+a33+""+a44;

            if(a.length()==0 || b.length()==0 || c.length()==0 || d.length()==0 || a11.length()==0|| a22.length()==0 || a33.length()==0|| a44.length()==0|| !pin.equals(conpin))
            {

                error_layout.setVisibility(View.VISIBLE);
            }
            else{
                AppData.app_password=pin;
                error_layout.setVisibility(View.INVISIBLE);
                //Toast.makeText(ActivitySetting.this,"Pin changed succesfully",Toast.LENGTH_SHORT).show();
                alert11.dismiss();
                new UpdatePin().execute();

            }

        }
    });
}

    public class AsteriskPasswordTransformationMethod extends PasswordTransformationMethod {
        @Override
        public CharSequence getTransformation(CharSequence source, View view) {
            return new PasswordCharSequence(source);
        }

        private class PasswordCharSequence implements CharSequence {
            private CharSequence mSource;
            public PasswordCharSequence(CharSequence source) {
                mSource = source; // Store char sequence
            }
            public char charAt(int index) {
                return '*'; // This is the important part
            }
            public int length() {
                return mSource.length(); // Return default
            }
            public CharSequence subSequence(int start, int end) {
                return mSource.subSequence(start, end); // Return default
            }
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        sharedPreferences = getSharedPreferences("logindetails", MODE_PRIVATE);


        if(sharedPreferences.getString("app_password_status", " ").equals("0"))
        {

            aSwitch1.setChecked(false);

        }
        else {
            aSwitch1.setChecked(true);
        }
        //new UpdateAppPin_status().execute();


    }

    private class UpdatePin extends  AsyncTask<Void,Void,Void>
    {
        String result,user_exitforggogle;
        JSONObject jobj,userinfo;
        boolean statusasyn;
        int statuscode;
        String message;
        boolean status;
        private static final long CONNECT_TIMEOUT_MILLIS =90000 ;
        private static final long READ_TIMEOUT_MILLIS =90000 ;

        protected void onPreExecute() {

            super.onPreExecute();
            // pbar.setVisibility(View.VISIBLE);

        }
        @Override
        protected Void doInBackground(Void... params) {

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

                String url= AppData.url+"/index.php/useraction/updateapppassword";


                Log.d("URL",url);
                mBilder.addFormDataPart("apppassword",AppData.app_password);
                mBilder.addFormDataPart("mode",sharedPreferences.getString("mode", " "));
                mBilder.addFormDataPart("user_id",sharedPreferences.getString("user_id", " "));

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
                status=jobj.getBoolean("status");
                message=jobj.getString("message");



            }
            catch (Exception e) {
                Log.d("OKHTTP_RESULT", "ERROR : " + e.toString());

            }
            return null;
        }

        protected void onPostExecute(Void resultt)
        {
            super.onPostExecute(resultt);



//            else if(status1==false)
                Toast.makeText(ActivitySetting.this, message, Toast.LENGTH_SHORT).show();
            SharedPreferences.Editor editor = getSharedPreferences("logindetails", MODE_PRIVATE).edit();
            //editor.putString("app_password_status",app_password_status);
            editor.putString("app_password", AppData.app_password);

            editor.commit();
        }
    }

    private class UpdateAppPin_status extends AsyncTask<Void, Void, Void>
    {
        JSONObject response1;
        JSONObject res;

        String result,user_exitforggogle;
        JSONObject jobj,userinfo;
        boolean statusasyn;
        int statuscode;
        String message;
        boolean status;
        private static final long CONNECT_TIMEOUT_MILLIS =90000 ;
        private static final long READ_TIMEOUT_MILLIS =90000 ;
        protected void onPreExecute() {

            super.onPreExecute();
           // pbar.setVisibility(View.VISIBLE);

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

                String url= AppData.url+"/index.php/useraction/updateapppasswordstatus";


                Log.d("URL",url);
                mBilder.addFormDataPart("user_id",sharedPreferences.getString("user_id", " "));
                if(aswitchon==true) {
                    mBilder.addFormDataPart("apppasswordstatus", "1");
                }
                else {
                    mBilder.addFormDataPart("apppasswordstatus", "0");
                }
                mBilder.addFormDataPart("mode",sharedPreferences.getString("mode", " "));

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
                status=jobj.getBoolean("status");
               app_password_status=jobj.getString("password_status");
                AppData.app_password_status=app_password_status;


            }
            catch (Exception e) {
                Log.d("OKHTTP_RESULT", "ERROR : " + e.toString());

            }


            return null;
        }

        protected void onPostExecute(Void resultt)
        {
            super.onPostExecute(resultt);
            SharedPreferences.Editor editor = getSharedPreferences("logindetails", MODE_PRIVATE).edit();
            editor.putString("app_password_status", AppData.app_password_status);
            //editor.putString("app_password",conpin);

            editor.commit();


//            else if(status1==false)
//                Toast.makeText(Login.this, message, Toast.LENGTH_SHORT).show();
        }
    }
}
