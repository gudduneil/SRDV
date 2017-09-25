package com.guidiyam.sexrdv;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class FunActivity extends Activity {
RelativeLayout rl_fun;
    String locale;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fun);
        rl_fun=(RelativeLayout)findViewById(R.id.rl_fun);
        rl_fun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FunActivity.this,HomePage.class);
                //intent.putExtra("LandingPage","home");
                startActivity(intent);
                finish();

                Toast.makeText(FunActivity.this,"Sign up successfully", Toast.LENGTH_SHORT).show();
               // Toast.makeText(FunActivity.this,"Go to home page",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i=new Intent(FunActivity.this,Signup.class);
        startActivity(i);
        finish();
    }
    @Override
    protected void onResume() {
        super.onResume();

        locale = FunActivity.this.getResources().getConfiguration().locale.getDisplayCountry();
        Log.d("LOCAL", locale);
        if (locale.equals("France")) {

            rl_fun.setBackgroundResource(R.drawable.french_fun);
           // Lmode = "2";


        } else {

            rl_fun.setBackgroundResource(R.drawable.fun);
           // Lmode = "1";

        }
    }
}
