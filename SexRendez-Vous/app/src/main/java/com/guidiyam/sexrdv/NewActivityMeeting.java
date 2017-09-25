package com.guidiyam.sexrdv;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.guidiyam.sexrdv.Adapter.FutureMeetingAdapter;

import java.util.ArrayList;

public class NewActivityMeeting extends AppCompatActivity {
LinearLayout singlemeeting_layout;
    RecyclerView recyclerview;
    FutureMeetingAdapter newMeeingApapter=null;
    ArrayList<String> list;
    ArrayAdapter<String> adp;
    Spinner spinnerdate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_meeting2);
        recyclerview=(RecyclerView)findViewById(R.id.recyclerview);

        recyclerview.setItemAnimator(new DefaultItemAnimator());
        singlemeeting_layout=(LinearLayout)findViewById(R.id.singlemeeting_layout);
        singlemeeting_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(NewActivityMeeting.this, ActivityDebrief.class);
                startActivity(i);
            }
        });

        if(newMeeingApapter==null) {
//                allmeeting_adapter = new Allmeeting_adapter(getActivity());
//                rv_notification.setAdapter(allmeeting_adapter);

            recyclerview.setHasFixedSize(true);
            recyclerview.setLayoutManager(new GridLayoutManager(NewActivityMeeting.this, 1));
            // activityadapter = new ActivityAdpater(getActivity(), activitySetGetsLinkedList);
            newMeeingApapter = new FutureMeetingAdapter(NewActivityMeeting.this);
            recyclerview.setAdapter(newMeeingApapter);//set an adapter

        }
    }
}
