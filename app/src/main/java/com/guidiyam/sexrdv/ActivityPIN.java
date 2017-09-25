package com.guidiyam.sexrdv;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.andrognito.pinlockview.IndicatorDots;
import com.andrognito.pinlockview.PinLockListener;
import com.andrognito.pinlockview.PinLockView;
import com.guidiyam.sexrdv.volley.AppData;

public class ActivityPIN extends AppCompatActivity {
    private static final String TAG = "ActivityPIN";
    EditText enter_mpin;
    ImageView i1, i2, i3, i4;
    PinLockView mPinLockView;
    IndicatorDots mIndicatorDots;
    String passcode;
    LinearLayout Button_Reset;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin);

        sharedPreferences = getSharedPreferences("logindetails", MODE_PRIVATE);
      //  Button_Reset = (LinearLayout)findViewById(R.id.ll_reset);
        mPinLockView = (PinLockView) findViewById(R.id.pin_lock_view);
        mIndicatorDots = (IndicatorDots) findViewById(R.id.indicator_dots);
        /////////////////////////////////////////////////////////////////////////////////////////////
        mPinLockView.attachIndicatorDots(mIndicatorDots);
        mPinLockView.setPinLockListener(mPinLockListener);
        mPinLockView.setPinLength(4);
        mPinLockView.setTextColor(ContextCompat.getColor(ActivityPIN.this, R.color.white));
        //mIndicatorDots.setB(R.drawable.round_white);

        //mIndicatorDots.setIndicatorType(IndicatorDots.IndicatorType.FILL_WITH_ANIMATION);



    }

    private PinLockListener mPinLockListener = new PinLockListener() {
        @Override
        public void onComplete(String pin) {
            Log.d(TAG, "Pin complete: " + pin);

            if(pin.length()>=4){

                // Toast.makeText(Order.this, "Correct Password", Toast.LENGTH_SHORT).show();

                passcode=pin;
                AppData.pin=passcode;


if(passcode.equals(sharedPreferences.getString("app_password", "")))
{
    Intent i = new Intent(ActivityPIN.this, HomePage.class);
    startActivity(i);
    finish();
}
//                    mBottomSheetDialog.cancel();
//                    bar.setVisibility(View.VISIBLE);
//
//                    placeOrder();

                    else{

                    Toast.makeText(ActivityPIN.this, "incorrect pin", Toast.LENGTH_SHORT).show();
//            mIndicatorDots.updateDot(0);
//            mPinLockView.mPin = "";

                }

            }



        }

        @Override
        public void onEmpty() {

        }

        @Override
        public void onPinChange(int pinLength, String intermediatePin) {
           // mPinLockView.setBackgroundResource(R.drawable.white_astrics);
            //mIndicatorDots.setB
        }
    };

}
