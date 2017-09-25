package com.guidiyam.sexrdv;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.guidiyam.sexrdv.Adapter.Allmeeting_adapter;
import com.guidiyam.sexrdv.fragment.FirstPage_Frag;
import com.guidiyam.sexrdv.fragment.NewMeetingFrag;
import com.guidiyam.sexrdv.fragment.PartnerFrag;
import com.guidiyam.sexrdv.fragment.Reward_Frag;


public class Activitymeeting extends FragmentActivity {
    FragmentManager fragmentManager;
    RelativeLayout ll_square,ll_fire,ll_people,ll_chip;
    FragmentTransaction fragmentTransaction;
    ImageView img_square,highlighted1,img_fire,highlighted2,img_people,highlighted3,img_chip,highlighted4;
    LinearLayout menu;
    RecyclerView rv_notification;
    LinearLayoutManager layoutManager2;
    Allmeeting_adapter allmeeting_adapter=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.meeting_demo);
        menu=(LinearLayout)findViewById(R.id.menu);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent i=new Intent(Activitymeeting.this,HomePage.class);
//                startActivity(i);
                finish();
            }
        });
        ll_square=(RelativeLayout) findViewById(R.id.ll_square);
        ll_fire=(RelativeLayout) findViewById(R.id.ll_fire);
        ll_people=(RelativeLayout) findViewById(R.id.ll_people);
        ll_chip=(RelativeLayout) findViewById(R.id.ll_chip) ;
        img_square=(ImageView)findViewById(R.id.img_square);
        img_fire=(ImageView)findViewById(R.id.img_fire);
        img_people=(ImageView)findViewById(R.id.img_people);
        img_chip=(ImageView)findViewById(R.id.img_chip);

        highlighted1=(ImageView)findViewById(R.id.highlighted1);
        highlighted2=(ImageView)findViewById(R.id.highlighted2);
        highlighted3=(ImageView)findViewById(R.id.highlighted3);
        highlighted4=(ImageView)findViewById(R.id.highlighted4);

        highlighted1.setVisibility(View.VISIBLE);
        highlighted2.setVisibility(View.INVISIBLE);
        highlighted3.setVisibility(View.INVISIBLE);
        highlighted4.setVisibility(View.INVISIBLE);

        img_square.setImageResource(R.drawable.squareselect);
        img_fire.setImageResource(R.drawable.firedeselect);
        img_people.setImageResource(R.drawable.peopledeselect);
        img_chip.setImageResource(R.drawable.chipdeselect);



        ///////////////////////////////////////////////////


        ll_square.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                img_square.setImageResource(R.drawable.squareselect);
                img_fire.setImageResource(R.drawable.firedeselect);
                img_people.setImageResource(R.drawable.peopledeselect);
                img_chip.setImageResource(R.drawable.chipdeselect);

                highlighted1.setVisibility(View.VISIBLE);
                highlighted2.setVisibility(View.INVISIBLE);
                highlighted3.setVisibility(View.INVISIBLE);
                highlighted4.setVisibility(View.INVISIBLE);

//
//                editprofile.setText("My calender");
//                fragmentManager = getSupportFragmentManager();
//                fragmentTransaction = fragmentManager.beginTransaction();
//                FirstPage_Frag task_fragment = new FirstPage_Frag();
//                fragmentTransaction.replace(R.id.blank, task_fragment);
//                fragmentTransaction.commit();

            }
        });
        ll_fire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                img_square.setImageResource(R.drawable.squaredeselect);
                img_fire.setImageResource(R.drawable.fireselect);
                img_people.setImageResource(R.drawable.peopledeselect);
                img_chip.setImageResource(R.drawable.chipdeselect);

                highlighted1.setVisibility(View.INVISIBLE);
                highlighted2.setVisibility(View.VISIBLE);
                highlighted3.setVisibility(View.INVISIBLE);
                highlighted4.setVisibility(View.INVISIBLE);


                //topbar.setVisibility(View.GONE);

//                editprofile.setText("My meetings");
//
//
//                fragmentManager2 = getSupportFragmentManager();
//                fragmentTransaction2 = fragmentManager2.beginTransaction();
//                NewMeetingFrag task_fragment = new NewMeetingFrag();
//                fragmentTransaction2.replace(R.id.blank, task_fragment);
//                fragmentTransaction2.commit();
            }
        });


        ll_people.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                img_square.setImageResource(R.drawable.squaredeselect);
                img_fire.setImageResource(R.drawable.firedeselect);
                img_people.setImageResource(R.drawable.peopleselect);
                img_chip.setImageResource(R.drawable.chipdeselect);

                highlighted1.setVisibility(View.INVISIBLE);
                highlighted2.setVisibility(View.INVISIBLE);
                highlighted3.setVisibility(View.VISIBLE);
                highlighted4.setVisibility(View.INVISIBLE);


//                editprofile.setText("Partners");
//                fragmentManager = getSupportFragmentManager();
//                fragmentTransaction = fragmentManager.beginTransaction();
//                PartnerFrag task_fragment = new PartnerFrag();
//                fragmentTransaction.replace(R.id.blank, task_fragment);
//                fragmentTransaction.commit();
//
//
//                img_square.setImageResource(R.drawable.squaredeselect);
//                highlighted1.setVisibility(View.INVISIBLE);
//                highlighted2.setVisibility(View.INVISIBLE);
//                highlighted3.setVisibility(View.VISIBLE);
//                highlighted4.setVisibility(View.INVISIBLE);
//                img_fire.setImageResource(R.drawable.firedeselect);
//                img_people.setImageResource(R.drawable.peopleselect);
//                img_chip.setImageResource(R.drawable.chipdeselect);


            }
        });

        ll_chip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                img_square.setImageResource(R.drawable.squaredeselect);
                img_fire.setImageResource(R.drawable.firedeselect);
                img_people.setImageResource(R.drawable.peopledeselect);
                img_chip.setImageResource(R.drawable.chipselect);

                highlighted1.setVisibility(View.INVISIBLE);
                highlighted2.setVisibility(View.INVISIBLE);
                highlighted3.setVisibility(View.INVISIBLE);
                highlighted4.setVisibility(View.VISIBLE);




//                editprofile.setText("Rewards & Punishments");
//
//
//                fragmentManager3 = getSupportFragmentManager();
//                fragmentTransaction3 = fragmentManager3.beginTransaction();
//                Reward_Frag task_fragment = new Reward_Frag();
//                fragmentTransaction3.replace(R.id.blank, task_fragment);
//                fragmentTransaction3.commit();
            }
        });

        ///////////////////////////////////////////////////


        rv_notification=(RecyclerView)findViewById(R.id.recyclerview);
        rv_notification.setItemAnimator(new DefaultItemAnimator());
//        layoutManager2 = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
//        rv_notification.setLayoutManager(layoutManager2);
        // for(int i=0;i<6;i++)
        //{

        if(allmeeting_adapter==null) {
//                allmeeting_adapter = new Allmeeting_adapter(getActivity());
//                rv_notification.setAdapter(allmeeting_adapter);

            rv_notification.setHasFixedSize(true);
            rv_notification.setLayoutManager(new GridLayoutManager(Activitymeeting.this, 1));
            // activityadapter = new ActivityAdpater(getActivity(), activitySetGetsLinkedList);
            allmeeting_adapter = new Allmeeting_adapter(Activitymeeting.this);
            rv_notification.setAdapter(allmeeting_adapter);//set an adapter

        }
//        fragmentManager = getSupportFragmentManager();
//        fragmentTransaction = fragmentManager.beginTransaction();
//        Meetingview_frag task_fragment = new Meetingview_frag();
//        fragmentTransaction.replace(R.id.blank2, task_fragment);
//        fragmentTransaction.commit();

    }
}
