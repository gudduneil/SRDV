package com.guidiyam.sexrdv;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class ActivityExtension extends AppCompatActivity {
LinearLayout ll_bck;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.extention);
        ll_bck=(LinearLayout)findViewById(R.id.ll_bck);
        ll_bck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
