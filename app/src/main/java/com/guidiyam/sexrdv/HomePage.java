package com.guidiyam.sexrdv;

import android.*;
import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.os.Handler;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;


import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.firebase.auth.FirebaseAuth;
import com.guidiyam.sexrdv.Adapter.OptionAdapter;
import com.guidiyam.sexrdv.fragment.FirstPage_Frag;
import com.guidiyam.sexrdv.fragment.NewMeetingFrag;
import com.guidiyam.sexrdv.fragment.PartnerFrag;
import com.guidiyam.sexrdv.fragment.Reward_Frag;
import com.guidiyam.sexrdv.helper.ConnectionDetector;
import com.guidiyam.sexrdv.helper.RoundedTransformation;
import com.guidiyam.sexrdv.service.ContactService;
import com.guidiyam.sexrdv.volley.AppData;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;


//import com.imanoweb.calendarview.CustomCalendarView;

public class HomePage extends FragmentActivity

{

    private static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 1;
    private Boolean exit = false;
    boolean doubleBackToExitPressedOnce = false;
    private GoogleApiClient mGoogleApiClient;

    AlertDialog alertDialog2;
    RelativeLayout ll_square,ll_fire,ll_people,ll_chip;
    //CustomCalendarView calendarView;
    ConnectionDetector cd;
    SharedPreferences.Editor editor2,editor23,contacteditor;
    TextView editprofile;
    FragmentManager fragmentManager,fragmentManager2,fragmentManager3;
    FragmentTransaction fragmentTransaction,fragmentTransaction2,fragmentTransaction3;
    FrameLayout blank;
    ImageView imageprofilepicture;
    Context ctx;
    DrawerLayout drawer_layout;
    LinearLayout opendrawer,paneldrawer;
    RelativeLayout topbar, calender_layout,meeting_layout,partner_layout,reward_layout,stat_layout,setting_layout,review_layout,contct_layout,shareapp_layout,logout_layout,extension_layout;
    TextView save,callender,meeting,partner,reward,stat,settings,review,contact,share,logout,extension;
    ImageView image_calender,image_meeting,image_partner,image_reward,image_stat,image_setting,image_review,image_contact,image_share_app,imge_logout,image_extension;
     ImageView img_square,highlighted1,img_fire,highlighted2,img_people,highlighted3,img_chip,highlighted4;
    private AlertDialog alert11;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        setContentView(R.layout.activity_home_page);
        save=(TextView)findViewById(R.id.save);
        Log.d("firsttimeservicerunning",AppData.firsttimeservicerunning);



        imageprofilepicture=(ImageView)findViewById(R.id.imageprofilepicture);




        sharedPreferences = getSharedPreferences("logindetails", MODE_PRIVATE);
       // Log.d("Page:::",sharedPreferences.getString("HomePage", " "));



        topbar=(RelativeLayout)findViewById(R.id.topbar);
        blank = (FrameLayout) findViewById(R.id.blank);
        drawer_layout = (DrawerLayout) findViewById(R.id.drawer_layout);
        opendrawer = (LinearLayout) findViewById(R.id.menu);
        paneldrawer=(LinearLayout)findViewById(R.id.paneldrawer);
        cd=new ConnectionDetector(HomePage.this);
        editprofile=(TextView)findViewById(R.id.editprofile);

        ///////////////////////////////////////////////////////////////


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



        ////////////////////////////////////////////////////////////////////
        callender=(TextView)findViewById(R.id.callender);
        meeting=(TextView)findViewById(R.id.meeting);
        partner=(TextView)findViewById(R.id.partner);
        reward=(TextView)findViewById(R.id.reward);
        stat=(TextView)findViewById(R.id.stat);
        settings=(TextView)findViewById(R.id.settings);
        review=(TextView)findViewById(R.id.review);
        contact=(TextView)findViewById(R.id.contact);
        share=(TextView)findViewById(R.id.share);
        logout=(TextView)findViewById(R.id.logout);
        extension=(TextView)findViewById(R.id.extention);

        /////////////////////////////////////////////////////////////////////////////
        calender_layout=(RelativeLayout)findViewById(R.id.calender_layout);
        meeting_layout=(RelativeLayout)findViewById(R.id.meeting_layout);
        partner_layout=(RelativeLayout)findViewById(R.id.partner_layout);
        reward_layout=(RelativeLayout)findViewById(R.id.reward_layout);
        stat_layout=(RelativeLayout)findViewById(R.id.stat_layout);
        setting_layout=(RelativeLayout)findViewById(R.id.setting_layout);
        review_layout=(RelativeLayout)findViewById(R.id.review_layout);
        contct_layout=(RelativeLayout)findViewById(R.id.contct_layout);
        shareapp_layout=(RelativeLayout)findViewById(R.id.shareapp_layout);
        logout_layout=(RelativeLayout)findViewById(R.id.logout_layout);
        extension_layout=(RelativeLayout)findViewById(R.id.extension_layout);


        ////////////////////////////////////////////////////////////////////////////

        image_calender=(ImageView)findViewById(R.id.h);
        image_meeting=(ImageView)findViewById(R.id.h2);
        image_partner=(ImageView)findViewById(R.id.h3);
        image_reward=(ImageView)findViewById(R.id.h4);
        image_stat=(ImageView)findViewById(R.id.h5);
        image_setting=(ImageView)findViewById(R.id.h6);
        image_review=(ImageView)findViewById(R.id.h7);
        image_contact=(ImageView)findViewById(R.id.h8);
        image_share_app=(ImageView)findViewById(R.id.h9);
        //imge_logout=(ImageView)findViewById(R.id.h10);
        image_extension=(ImageView)findViewById(R.id.h11);



        //////////////////////////////////////////////////////////////////



        ll_square.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {


               // AppData.selectp=false;
                AppData.firsttimeservicerunning="true";
                ///////////////////////////////////////////////////////

                AppData.addextabuttonforpartner=false;
                AppData.addextrabuttonforwhere=false;
                AppData.addextrabuttonforreason=false;
                AppData.addextrabuttonforwhat=false;
                AppData.addextrabuttonforpositions=false;
                AppData.addextrabuttonforgoods=false;
                AppData.wheretxt="";
                AppData.whattxt="";
                AppData.reasontxt="";
                AppData.positiontxt="";
                AppData.goodstxt="";




                AppData.selecttime="Time";
                AppData.progress2=0;
                AppData.duration2="1";
                AppData.date="Select Date";

                try {
                    AppData.partnerjsonarray=new JSONArray("[]");
                    AppData.wherejsonarray=new JSONArray("[]");
                    AppData.whatjsonarray=new JSONArray("[]");
                    AppData.reasonjsonarray=new JSONArray("[]");
                    AppData.positionsjsonarray=new JSONArray("[]");
                    AppData.goodsjsonarray=new JSONArray("[]");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                ///////////////////////////////////


                img_square.setImageResource(R.drawable.squareselect);
                img_fire.setImageResource(R.drawable.firedeselect);
                img_people.setImageResource(R.drawable.peopledeselect);
                img_chip.setImageResource(R.drawable.chipdeselect);

                highlighted1.setVisibility(View.VISIBLE);
                highlighted2.setVisibility(View.INVISIBLE);
                highlighted3.setVisibility(View.INVISIBLE);
                highlighted4.setVisibility(View.INVISIBLE);



                image_calender.setBackgroundResource(R.drawable.calendarselect);
                callender.setTextColor(Color.parseColor("#F80D6B"));
                image_meeting.setBackgroundResource(R.drawable.mettingdeselect);
                meeting.setTextColor(Color.parseColor("#767998"));
                image_partner.setBackgroundResource(R.drawable.partnerdeselect);
                partner.setTextColor(Color.parseColor("#767998"));

                image_reward.setBackgroundResource(R.drawable.rewarddeselect);
                reward.setTextColor(Color.parseColor("#767998"));
                image_stat.setBackgroundResource(R.drawable.statdeselect);
                stat.setTextColor(Color.parseColor("#767998"));

                image_setting.setBackgroundResource(R.drawable.settingdeselect);
                settings.setTextColor(Color.parseColor("#767998"));
                image_review.setBackgroundResource(R.drawable.rewarddeselect);
                review.setTextColor(Color.parseColor("#767998"));

                image_contact.setBackgroundResource(R.drawable.contactdeselect);
                contact.setTextColor(Color.parseColor("#767998"));

                image_share_app.setBackgroundResource(R.drawable.sharedeselect);
                share.setTextColor(Color.parseColor("#767998"));

                //
                //  imge_logout.setBackgroundResource(R.drawable.rewarddeselect);
                logout.setTextColor(Color.parseColor("#767998"));


                image_extension.setBackgroundResource(R.drawable.rewarddeselect);
                extension.setTextColor(Color.parseColor("#767998"));

                AppData.page="FirstPage_Frag";

                editprofile.setText("My calender");
                save.setVisibility(View.INVISIBLE);
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                FirstPage_Frag task_fragment = new FirstPage_Frag();
                fragmentTransaction.replace(R.id.blank, task_fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                AppData.addextabuttonforpartner=false;




//                Intent intent = new Intent(HomePage.this,
//                        ContactService.class);
//                intent.setAction(ContactService.ACTION_ON);
//                startService(intent);

            }
        });
        ll_fire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //////////////////////////////////////////////////////
               // AppData.duration2="1";

                //AppData.selectp=false;
                AppData.firsttimeservicerunning="true";
                ///////////////////////////////////////////////////////
                AppData.addextabuttonforpartner=false;
                AppData.addextrabuttonforwhere=false;
                AppData.addextrabuttonforreason=false;
                AppData.addextrabuttonforwhat=false;
                AppData.addextrabuttonforpositions=false;
                AppData.addextrabuttonforgoods=false;
                AppData.wheretxt="";
                AppData.whattxt="";
                AppData.reasontxt="";
                AppData.positiontxt="";
                AppData.goodstxt="";



                AppData.selecttime="Time";
                AppData.progress2=0;
                AppData.duration2="1";
                AppData.date="Select Date";

                try {
                    AppData.partnerjsonarray=new JSONArray("[]");
                    AppData.wherejsonarray=new JSONArray("[]");
                    AppData.whatjsonarray=new JSONArray("[]");
                    AppData.reasonjsonarray=new JSONArray("[]");
                    AppData.positionsjsonarray=new JSONArray("[]");
                    AppData.goodsjsonarray=new JSONArray("[]");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                ///////////////////////////////////////////////////////
                img_square.setImageResource(R.drawable.squaredeselect);
                img_fire.setImageResource(R.drawable.fireselect);
                img_people.setImageResource(R.drawable.peopledeselect);
                img_chip.setImageResource(R.drawable.chipdeselect);

                highlighted1.setVisibility(View.INVISIBLE);
                highlighted2.setVisibility(View.VISIBLE);
                highlighted3.setVisibility(View.INVISIBLE);
                highlighted4.setVisibility(View.INVISIBLE);



                image_calender.setBackgroundResource(R.drawable.calendardeselect);
                callender.setTextColor(Color.parseColor("#767998"));
                image_meeting.setBackgroundResource(R.drawable.mettingselect);
                meeting.setTextColor(Color.parseColor("#F80D6B"));
                image_partner.setBackgroundResource(R.drawable.partnerdeselect);
                partner.setTextColor(Color.parseColor("#767998"));

                image_reward.setBackgroundResource(R.drawable.rewarddeselect);
                reward.setTextColor(Color.parseColor("#767998"));
                image_stat.setBackgroundResource(R.drawable.statdeselect);
                stat.setTextColor(Color.parseColor("#767998"));

                image_setting.setBackgroundResource(R.drawable.settingdeselect);
                settings.setTextColor(Color.parseColor("#767998"));
                image_review.setBackgroundResource(R.drawable.rewarddeselect);
                review.setTextColor(Color.parseColor("#767998"));

                image_contact.setBackgroundResource(R.drawable.contactdeselect);
                contact.setTextColor(Color.parseColor("#767998"));


                image_share_app.setBackgroundResource(R.drawable.sharedeselect);
                share.setTextColor(Color.parseColor("#767998"));

                //imge_logout.setBackgroundResource(R.drawable.rewarddeselect);
                logout.setTextColor(Color.parseColor("#767998"));


                image_extension.setBackgroundResource(R.drawable.rewarddeselect);
                extension.setTextColor(Color.parseColor("#767998"));

                AppData.page="NewMeetingFrag";
                //topbar.setVisibility(View.GONE);

                editprofile.setText("My meetings");
                AppData.addextabuttonforpartner=false;
                save.setVisibility(View.INVISIBLE);
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                NewMeetingFrag task_fragment = new NewMeetingFrag();
                fragmentTransaction.replace(R.id.blank, task_fragment);
               fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });


        ll_people.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ///////////////////////////////////////
               // AppData.duration2="1";

               // AppData.selectp=false;
                AppData.firsttimeservicerunning="true";
                ///////////////////////////////////////////////////////

                AppData.addextabuttonforpartner=false;
                AppData.addextrabuttonforwhere=false;
                AppData.addextrabuttonforreason=false;
                AppData.addextrabuttonforwhat=false;
                AppData.addextrabuttonforpositions=false;
                AppData.addextrabuttonforgoods=false;
                AppData.wheretxt="";
                AppData.whattxt="";
                AppData.reasontxt="";
                AppData.positiontxt="";
                AppData.goodstxt="";



                AppData.selecttime="Time";
                AppData.progress2=0;
                AppData.duration2="1";
                AppData.date="Select Date";

                try {
                    AppData.partnerjsonarray=new JSONArray("[]");
                    AppData.wherejsonarray=new JSONArray("[]");
                    AppData.whatjsonarray=new JSONArray("[]");
                    AppData.reasonjsonarray=new JSONArray("[]");
                    AppData.positionsjsonarray=new JSONArray("[]");
                    AppData.goodsjsonarray=new JSONArray("[]");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                /////////////////////////////////////////


                img_square.setImageResource(R.drawable.squaredeselect);
                img_fire.setImageResource(R.drawable.firedeselect);
                img_people.setImageResource(R.drawable.peopleselect);
                img_chip.setImageResource(R.drawable.chipdeselect);

                highlighted1.setVisibility(View.INVISIBLE);
                highlighted2.setVisibility(View.INVISIBLE);
                highlighted3.setVisibility(View.VISIBLE);
                highlighted4.setVisibility(View.INVISIBLE);



                image_calender.setBackgroundResource(R.drawable.calendardeselect);
                callender.setTextColor(Color.parseColor("#767998"));
                image_meeting.setBackgroundResource(R.drawable.mettingdeselect);
                meeting.setTextColor(Color.parseColor("#767998"));
                image_partner.setBackgroundResource(R.drawable.partnerselect);
                partner.setTextColor(Color.parseColor("#F80D6B"));

                image_reward.setBackgroundResource(R.drawable.rewarddeselect);
                reward.setTextColor(Color.parseColor("#767998"));
                image_stat.setBackgroundResource(R.drawable.statdeselect);
                stat.setTextColor(Color.parseColor("#767998"));

                image_setting.setBackgroundResource(R.drawable.settingdeselect);
                settings.setTextColor(Color.parseColor("#767998"));
                image_review.setBackgroundResource(R.drawable.rewarddeselect);
                review.setTextColor(Color.parseColor("#767998"));

                image_contact.setBackgroundResource(R.drawable.contactdeselect);
                contact.setTextColor(Color.parseColor("#767998"));


                image_share_app.setBackgroundResource(R.drawable.sharedeselect);
                share.setTextColor(Color.parseColor("#767998"));


                // imge_logout.setBackgroundResource(R.drawable.rewarddeselect);
                logout.setTextColor(Color.parseColor("#767998"));


                image_extension.setBackgroundResource(R.drawable.rewarddeselect);
                extension.setTextColor(Color.parseColor("#767998"));
                AppData.addextabuttonforpartner=false;
                AppData.page="PartnerFrag";
                editprofile.setText("Partners");
                save.setVisibility(View.INVISIBLE);
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                PartnerFrag task_fragment = new PartnerFrag();
                fragmentTransaction.replace(R.id.blank, task_fragment);
               fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();


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
                ////////////////////////////////////////////////
             //   AppData.duration2="1";

                //AppData.selectp=false;
                AppData.firsttimeservicerunning="true";
                ///////////////////////////////////////////////////////

                AppData.addextabuttonforpartner=false;
                AppData.addextrabuttonforwhere=false;
                AppData.addextrabuttonforreason=false;
                AppData.addextrabuttonforwhat=false;
                AppData.addextrabuttonforpositions=false;
                AppData.addextrabuttonforgoods=false;
                AppData.wheretxt="";
                AppData.whattxt="";
                AppData.reasontxt="";
                AppData.positiontxt="";
                AppData.goodstxt="";



                AppData.selecttime="Time";
                AppData.progress2=0;
                AppData.duration2="1";
                AppData.date="Select Date";

                try {
                    AppData.partnerjsonarray=new JSONArray("[]");
                    AppData.wherejsonarray=new JSONArray("[]");
                    AppData.whatjsonarray=new JSONArray("[]");
                    AppData.reasonjsonarray=new JSONArray("[]");
                    AppData.positionsjsonarray=new JSONArray("[]");
                    AppData.goodsjsonarray=new JSONArray("[]");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                ///////////////////////////////////////////////////////

                img_square.setImageResource(R.drawable.squaredeselect);
                img_fire.setImageResource(R.drawable.firedeselect);
                img_people.setImageResource(R.drawable.peopledeselect);
                img_chip.setImageResource(R.drawable.chipselect);

                highlighted1.setVisibility(View.INVISIBLE);
                highlighted2.setVisibility(View.INVISIBLE);
                highlighted3.setVisibility(View.INVISIBLE);
                highlighted4.setVisibility(View.VISIBLE);


                image_calender.setBackgroundResource(R.drawable.calendardeselect);
                callender.setTextColor(Color.parseColor("#767998"));
                image_meeting.setBackgroundResource(R.drawable.mettingdeselect);
                meeting.setTextColor(Color.parseColor("#767998"));
                image_partner.setBackgroundResource(R.drawable.partnerdeselect);
                partner.setTextColor(Color.parseColor("#767998"));

                image_reward.setBackgroundResource(R.drawable.rewardselect);
                reward.setTextColor(Color.parseColor("#F80D6B"));
                image_stat.setBackgroundResource(R.drawable.statdeselect);
                stat.setTextColor(Color.parseColor("#767998"));

                image_setting.setBackgroundResource(R.drawable.settingdeselect);
                settings.setTextColor(Color.parseColor("#767998"));
                image_review.setBackgroundResource(R.drawable.rewarddeselect);
                review.setTextColor(Color.parseColor("#767998"));

                image_contact.setBackgroundResource(R.drawable.contactdeselect);
                contact.setTextColor(Color.parseColor("#767998"));


                image_share_app.setBackgroundResource(R.drawable.sharedeselect);
                share.setTextColor(Color.parseColor("#767998"));

                // imge_logout.setBackgroundResource(R.drawable.rewarddeselect);
                logout.setTextColor(Color.parseColor("#767998"));

                image_extension.setBackgroundResource(R.drawable.rewarddeselect);
                extension.setTextColor(Color.parseColor("#767998"));

                AppData.addextabuttonforpartner=false;
                AppData.page="Reward_Frag";
                editprofile.setText("Rewards & Punishments");
                save.setVisibility(View.INVISIBLE);


                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                Reward_Frag task_fragment = new Reward_Frag();
                fragmentTransaction.replace(R.id.blank, task_fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });



//        calendarView = (CustomCalendarView) findViewById(R.id.calendar_view);

//        if (getIntent().getExtras().getString("LandingPage").equals("home"))
        //{


//            txthome.setTextColor(Color.parseColor("#F08E33"));
//            txtlogout.setTextColor(Color.parseColor("#000000"));
//
//
//            ///////////////////////////////////////
//            txtnotification.setTextColor(Color.parseColor("#000000"));
//            txtseetings.setTextColor(Color.parseColor("#000000"));
//            txtpro.setTextColor(Color.parseColor("#000000"));

        //}

        opendrawer.setOnClickListener(new View.OnClickListener() {

                                          @Override
                                          public void onClick(View arg0) {
                                              // TODO Auto-generated method stub
                                              drawer_layout.openDrawer(paneldrawer);



                                          }
                                      }
        );
        calender_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                drawer_layout.closeDrawer(paneldrawer);

                image_calender.setBackgroundResource(R.drawable.calendarselect);
                callender.setTextColor(Color.parseColor("#F80D6B"));
                image_meeting.setBackgroundResource(R.drawable.mettingdeselect);
                meeting.setTextColor(Color.parseColor("#767998"));
                image_partner.setBackgroundResource(R.drawable.partnerdeselect);
                partner.setTextColor(Color.parseColor("#767998"));

                image_reward.setBackgroundResource(R.drawable.rewarddeselect);
                reward.setTextColor(Color.parseColor("#767998"));
                image_stat.setBackgroundResource(R.drawable.statdeselect);
                stat.setTextColor(Color.parseColor("#767998"));

                image_setting.setBackgroundResource(R.drawable.settingdeselect);
                settings.setTextColor(Color.parseColor("#767998"));
                image_review.setBackgroundResource(R.drawable.rewarddeselect);
                review.setTextColor(Color.parseColor("#767998"));

                image_contact.setBackgroundResource(R.drawable.contactdeselect);
                contact.setTextColor(Color.parseColor("#767998"));


                image_share_app.setBackgroundResource(R.drawable.sharedeselect);
                share.setTextColor(Color.parseColor("#767998"));

               // imge_logout.setBackgroundResource(R.drawable.rewarddeselect);
                logout.setTextColor(Color.parseColor("#767998"));

                image_extension.setBackgroundResource(R.drawable.rewarddeselect);
                extension.setTextColor(Color.parseColor("#767998"));
                AppData.addextabuttonforpartner=false;

                AppData.page="FirstPage_Frag";
                editprofile.setText("My calender");
                save.setVisibility(View.INVISIBLE);

                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                FirstPage_Frag task_fragment = new FirstPage_Frag();
                fragmentTransaction.replace(R.id.blank, task_fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

                img_square.setImageResource(R.drawable.squareselect);
                img_fire.setImageResource(R.drawable.firedeselect);
                img_people.setImageResource(R.drawable.peopledeselect);
                img_chip.setImageResource(R.drawable.chipdeselect);

                highlighted1.setVisibility(View.VISIBLE);
                highlighted2.setVisibility(View.INVISIBLE);
                highlighted3.setVisibility(View.INVISIBLE);
                highlighted4.setVisibility(View.INVISIBLE);



                //AppData.selectp=false;
                AppData.firsttimeservicerunning="true";
                ///////////////////////////////////////////////////////

                AppData.addextabuttonforpartner=false;
                AppData.addextrabuttonforwhere=false;
                AppData.addextrabuttonforreason=false;
                AppData.addextrabuttonforwhat=false;
                AppData.addextrabuttonforpositions=false;
                AppData.addextrabuttonforgoods=false;
                AppData.wheretxt="";
                AppData.whattxt="";
                AppData.reasontxt="";
                AppData.positiontxt="";
                AppData.goodstxt="";



                AppData.selecttime="Time";
                AppData.progress2=0;
                AppData.duration2="1";
                AppData.date="Select Date";

                try {
                    AppData.partnerjsonarray=new JSONArray("[]");
                    AppData.wherejsonarray=new JSONArray("[]");
                    AppData.whatjsonarray=new JSONArray("[]");
                    AppData.reasonjsonarray=new JSONArray("[]");
                    AppData.positionsjsonarray=new JSONArray("[]");
                    AppData.goodsjsonarray=new JSONArray("[]");
                } catch (JSONException e) {
                    e.printStackTrace();
                }


                if(AppData.firsttimeservicerunning.equals("false")) {
                    Intent intent = new Intent(HomePage.this,
                            ContactService.class);
                    intent.setAction(ContactService.ACTION_ON);
                    startService(intent);

                }

            }
        });

        meeting_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                drawer_layout.closeDrawer(paneldrawer);

                image_calender.setBackgroundResource(R.drawable.calendardeselect);
                callender.setTextColor(Color.parseColor("#767998"));
                image_meeting.setBackgroundResource(R.drawable.mettingselect);
                meeting.setTextColor(Color.parseColor("#F80D6B"));
                image_partner.setBackgroundResource(R.drawable.partnerdeselect);
                partner.setTextColor(Color.parseColor("#767998"));

                image_reward.setBackgroundResource(R.drawable.rewarddeselect);
                reward.setTextColor(Color.parseColor("#767998"));
                image_stat.setBackgroundResource(R.drawable.statdeselect);
                stat.setTextColor(Color.parseColor("#767998"));

                image_setting.setBackgroundResource(R.drawable.settingdeselect);
                settings.setTextColor(Color.parseColor("#767998"));
                image_review.setBackgroundResource(R.drawable.rewarddeselect);
                review.setTextColor(Color.parseColor("#767998"));

                image_contact.setBackgroundResource(R.drawable.contactdeselect);
                contact.setTextColor(Color.parseColor("#767998"));


                image_share_app.setBackgroundResource(R.drawable.sharedeselect);
                share.setTextColor(Color.parseColor("#767998"));

                //imge_logout.setBackgroundResource(R.drawable.rewarddeselect);
                logout.setTextColor(Color.parseColor("#767998"));


                image_extension.setBackgroundResource(R.drawable.rewarddeselect);
                extension.setTextColor(Color.parseColor("#767998"));
                save.setVisibility(View.INVISIBLE);

                AppData.page="NewMeetingFrag";
                //topbar.setVisibility(View.GONE);
                editprofile.setText("My meetings");
                AppData.addextabuttonforpartner=false;


                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                NewMeetingFrag task_fragment = new NewMeetingFrag();
                fragmentTransaction.replace(R.id.blank, task_fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

                img_square.setImageResource(R.drawable.squaredeselect);
                img_fire.setImageResource(R.drawable.fireselect);
                img_people.setImageResource(R.drawable.peopledeselect);
                img_chip.setImageResource(R.drawable.chipdeselect);
                highlighted1.setVisibility(View.INVISIBLE);
                highlighted2.setVisibility(View.VISIBLE);
                highlighted3.setVisibility(View.INVISIBLE);
                highlighted4.setVisibility(View.INVISIBLE);



              //  AppData.selectp=false;
                AppData.firsttimeservicerunning="true";
                ///////////////////////////////////////////////////////

                AppData.addextabuttonforpartner=false;
                AppData.addextrabuttonforwhere=false;
                AppData.addextrabuttonforreason=false;
                AppData.addextrabuttonforwhat=false;
                AppData.addextrabuttonforpositions=false;
                AppData.addextrabuttonforgoods=false;
                AppData.wheretxt="";
                AppData.whattxt="";
                AppData.reasontxt="";
                AppData.positiontxt="";
                AppData.goodstxt="";



                AppData.selecttime="Time";
                AppData.progress2=0;
                AppData.duration2="1";
                AppData.date="Select Date";

                try {
                    AppData.partnerjsonarray=new JSONArray("[]");
                    AppData.wherejsonarray=new JSONArray("[]");
                    AppData.whatjsonarray=new JSONArray("[]");
                    AppData.reasonjsonarray=new JSONArray("[]");
                    AppData.positionsjsonarray=new JSONArray("[]");
                    AppData.goodsjsonarray=new JSONArray("[]");
                } catch (JSONException e) {
                    e.printStackTrace();
                }


//                Intent i=new Intent(HomePage.this,NewActivityMeeting.class);
//                startActivity(i);

            }
        });




        partner_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  AppData.selectp=false;
                AppData.firsttimeservicerunning="true";
                ///////////////////////////////////////////////////////


                AppData.addextabuttonforpartner=false;
                AppData.addextrabuttonforwhere=false;
                AppData.addextrabuttonforreason=false;
                AppData.addextrabuttonforwhat=false;
                AppData.addextrabuttonforpositions=false;
                AppData.addextrabuttonforgoods=false;
                AppData.wheretxt="";
                AppData.whattxt="";
                AppData.reasontxt="";
                AppData.positiontxt="";
                AppData.goodstxt="";




                AppData.selecttime="Time";
                AppData.progress2=0;
                AppData.duration2="1";
                AppData.date="Select Date";

                try {
                    AppData.partnerjsonarray=new JSONArray("[]");
                    AppData.wherejsonarray=new JSONArray("[]");
                    AppData.whatjsonarray=new JSONArray("[]");
                    AppData.reasonjsonarray=new JSONArray("[]");
                    AppData.positionsjsonarray=new JSONArray("[]");
                    AppData.goodsjsonarray=new JSONArray("[]");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
               // AppData.selectedContact=AppData.contactList;
                drawer_layout.closeDrawer(paneldrawer);

                image_calender.setBackgroundResource(R.drawable.calendardeselect);
                callender.setTextColor(Color.parseColor("#767998"));
                image_meeting.setBackgroundResource(R.drawable.mettingdeselect);
                meeting.setTextColor(Color.parseColor("#767998"));
                image_partner.setBackgroundResource(R.drawable.partnerselect);
                partner.setTextColor(Color.parseColor("#F80D6B"));

                image_reward.setBackgroundResource(R.drawable.rewarddeselect);
                reward.setTextColor(Color.parseColor("#767998"));
                image_stat.setBackgroundResource(R.drawable.statdeselect);
                stat.setTextColor(Color.parseColor("#767998"));

                image_setting.setBackgroundResource(R.drawable.settingdeselect);
                settings.setTextColor(Color.parseColor("#767998"));
                image_review.setBackgroundResource(R.drawable.rewarddeselect);
                review.setTextColor(Color.parseColor("#767998"));

                image_contact.setBackgroundResource(R.drawable.contactdeselect);
                contact.setTextColor(Color.parseColor("#767998"));


                image_share_app.setBackgroundResource(R.drawable.sharedeselect);
                share.setTextColor(Color.parseColor("#767998"));


               // imge_logout.setBackgroundResource(R.drawable.rewarddeselect);
                logout.setTextColor(Color.parseColor("#767998"));


                image_extension.setBackgroundResource(R.drawable.rewarddeselect);
                extension.setTextColor(Color.parseColor("#767998"));
//                Intent i =new Intent(HomePage.this,ActivityPartner.class);
//                startActivity(i);
                save.setVisibility(View.INVISIBLE);
                AppData.page="PartnerFrag";
                AppData.addextabuttonforpartner=false;

                editprofile.setText("Partners");
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                PartnerFrag task_fragment = new PartnerFrag();
                fragmentTransaction.replace(R.id.blank, task_fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();


                img_square.setImageResource(R.drawable.squaredeselect);
                img_fire.setImageResource(R.drawable.firedeselect);
                img_people.setImageResource(R.drawable.peopleselect);
                img_chip.setImageResource(R.drawable.chipdeselect);
                highlighted1.setVisibility(View.INVISIBLE);
                highlighted2.setVisibility(View.INVISIBLE);
                highlighted3.setVisibility(View.VISIBLE);
                highlighted4.setVisibility(View.INVISIBLE);



            }
        });


        reward_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

             //   AppData.selectp=false;

                AppData.firsttimeservicerunning="true";
                ///////////////////////////////////////////////////////


                AppData.addextabuttonforpartner=false;
                AppData.addextrabuttonforwhere=false;
                AppData.addextrabuttonforreason=false;
                AppData.addextrabuttonforwhat=false;
                AppData.addextrabuttonforpositions=false;
                AppData.addextrabuttonforgoods=false;
                AppData.wheretxt="";
                AppData.whattxt="";
                AppData.reasontxt="";
                AppData.positiontxt="";
                AppData.goodstxt="";




                AppData.selecttime="Time";
                AppData.progress2=0;
                AppData.duration2="1";
                AppData.date="Select Date";

                try {
                    AppData.partnerjsonarray=new JSONArray("[]");
                    AppData.wherejsonarray=new JSONArray("[]");
                    AppData.whatjsonarray=new JSONArray("[]");
                    AppData.reasonjsonarray=new JSONArray("[]");
                    AppData.positionsjsonarray=new JSONArray("[]");
                    AppData.goodsjsonarray=new JSONArray("[]");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                drawer_layout.closeDrawer(paneldrawer);

                image_calender.setBackgroundResource(R.drawable.calendardeselect);
                callender.setTextColor(Color.parseColor("#767998"));
                image_meeting.setBackgroundResource(R.drawable.mettingdeselect);
                meeting.setTextColor(Color.parseColor("#767998"));
                image_partner.setBackgroundResource(R.drawable.partnerdeselect);
                partner.setTextColor(Color.parseColor("#767998"));

                image_reward.setBackgroundResource(R.drawable.rewardselect);
                reward.setTextColor(Color.parseColor("#F80D6B"));
                image_stat.setBackgroundResource(R.drawable.statdeselect);
                stat.setTextColor(Color.parseColor("#767998"));

                image_setting.setBackgroundResource(R.drawable.settingdeselect);
                settings.setTextColor(Color.parseColor("#767998"));
                image_review.setBackgroundResource(R.drawable.rewarddeselect);
                review.setTextColor(Color.parseColor("#767998"));

                image_contact.setBackgroundResource(R.drawable.contactdeselect);
                contact.setTextColor(Color.parseColor("#767998"));


                image_share_app.setBackgroundResource(R.drawable.sharedeselect);
                share.setTextColor(Color.parseColor("#767998"));

               // imge_logout.setBackgroundResource(R.drawable.rewarddeselect);
                logout.setTextColor(Color.parseColor("#767998"));

                image_extension.setBackgroundResource(R.drawable.rewarddeselect);
                extension.setTextColor(Color.parseColor("#767998"));
//
//
//                editprofile.setText("My calender");
//                fragmentManager = getSupportFragmentManager();
//                fragmentTransaction = fragmentManager.beginTransaction();
//                FirstPage_Frag task_fragment = new FirstPage_Frag();
//                fragmentTransaction.replace(R.id.blank, task_fragment);
//                fragmentTransaction.commit();






                AppData.page="Reward_Frag";
                save.setVisibility(View.INVISIBLE);
                editprofile.setText("Rewards & Punishments");

                AppData.addextabuttonforpartner=false;
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                Reward_Frag task_fragment = new Reward_Frag();
                fragmentTransaction.replace(R.id.blank, task_fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

                img_square.setImageResource(R.drawable.squaredeselect);
                img_fire.setImageResource(R.drawable.firedeselect);
                img_people.setImageResource(R.drawable.peopledeselect);
                img_chip.setImageResource(R.drawable.chipselect);
                highlighted1.setVisibility(View.INVISIBLE);
                highlighted2.setVisibility(View.INVISIBLE);
                highlighted3.setVisibility(View.INVISIBLE);
                highlighted4.setVisibility(View.VISIBLE);

            }
        });


        stat_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer_layout.closeDrawer(paneldrawer);

                image_calender.setBackgroundResource(R.drawable.calendardeselect);
                callender.setTextColor(Color.parseColor("#767998"));
                image_meeting.setBackgroundResource(R.drawable.mettingdeselect);
                meeting.setTextColor(Color.parseColor("#767998"));
                image_partner.setBackgroundResource(R.drawable.partnerdeselect);
                partner.setTextColor(Color.parseColor("#767998"));

                image_reward.setBackgroundResource(R.drawable.rewarddeselect);
                reward.setTextColor(Color.parseColor("#767998"));
                image_stat.setBackgroundResource(R.drawable.statselect);
                stat.setTextColor(Color.parseColor("#F80D6B"));

                image_setting.setBackgroundResource(R.drawable.settingdeselect);
                settings.setTextColor(Color.parseColor("#767998"));
                image_review.setBackgroundResource(R.drawable.rewarddeselect);
                review.setTextColor(Color.parseColor("#767998"));

                image_contact.setBackgroundResource(R.drawable.contactdeselect);
                contact.setTextColor(Color.parseColor("#767998"));


                image_share_app.setBackgroundResource(R.drawable.sharedeselect);
                share.setTextColor(Color.parseColor("#767998"));

               // imge_logout.setBackgroundResource(R.drawable.rewarddeselect);
                logout.setTextColor(Color.parseColor("#767998"));

                image_extension.setBackgroundResource(R.drawable.rewarddeselect);
                extension.setTextColor(Color.parseColor("#767998"));
                save.setVisibility(View.INVISIBLE);
                AppData.addextabuttonforpartner=false;
//                editprofile.setText("My calender");
//                fragmentManager = getSupportFragmentManager();
                Intent i =new Intent(HomePage.this,ActivityStat.class);
                startActivity(i);


            }
        });


        setting_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer_layout.closeDrawer(paneldrawer);

                image_calender.setBackgroundResource(R.drawable.calendardeselect);
                callender.setTextColor(Color.parseColor("#767998"));
                image_meeting.setBackgroundResource(R.drawable.mettingdeselect);
                meeting.setTextColor(Color.parseColor("#767998"));
                image_partner.setBackgroundResource(R.drawable.partnerdeselect);
                partner.setTextColor(Color.parseColor("#767998"));

                image_reward.setBackgroundResource(R.drawable.rewarddeselect);
                reward.setTextColor(Color.parseColor("#767998"));
                image_stat.setBackgroundResource(R.drawable.statdeselect);
                stat.setTextColor(Color.parseColor("#767998"));

                image_setting.setBackgroundResource(R.drawable.settingselect);
                settings.setTextColor(Color.parseColor("#F80D6B"));
                image_review.setBackgroundResource(R.drawable.rewarddeselect);
                review.setTextColor(Color.parseColor("#767998"));

                image_contact.setBackgroundResource(R.drawable.contactdeselect);
                contact.setTextColor(Color.parseColor("#767998"));


                image_share_app.setBackgroundResource(R.drawable.sharedeselect);
                share.setTextColor(Color.parseColor("#767998"));

                //imge_logout.setBackgroundResource(R.drawable.rewarddeselect);
                logout.setTextColor(Color.parseColor("#767998"));
                AppData.addextabuttonforpartner=false;

                image_extension.setBackgroundResource(R.drawable.rewarddeselect);
                extension.setTextColor(Color.parseColor("#767998"));
                Intent i=new Intent(HomePage.this,ActivitySetting.class);
                startActivity(i);
            }
        });

        review_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer_layout.closeDrawer(paneldrawer);

                image_calender.setBackgroundResource(R.drawable.calendardeselect);
                callender.setTextColor(Color.parseColor("#767998"));
                image_meeting.setBackgroundResource(R.drawable.mettingdeselect);
                meeting.setTextColor(Color.parseColor("#767998"));
                image_partner.setBackgroundResource(R.drawable.partnerdeselect);
                partner.setTextColor(Color.parseColor("#767998"));

                image_reward.setBackgroundResource(R.drawable.rewarddeselect);
                reward.setTextColor(Color.parseColor("#767998"));
                image_stat.setBackgroundResource(R.drawable.statdeselect);
                stat.setTextColor(Color.parseColor("#767998"));

                image_setting.setBackgroundResource(R.drawable.settingdeselect);
                settings.setTextColor(Color.parseColor("#767998"));
                image_review.setBackgroundResource(R.drawable.rewardselect);
                review.setTextColor(Color.parseColor("#F80D6B"));

                image_contact.setBackgroundResource(R.drawable.contactdeselect);
                contact.setTextColor(Color.parseColor("#767998"));


                image_share_app.setBackgroundResource(R.drawable.sharedeselect);
                share.setTextColor(Color.parseColor("#767998"));

             //   imge_logout.setBackgroundResource(R.drawable.rewarddeselect);
                logout.setTextColor(Color.parseColor("#767998"));
                AppData.addextabuttonforpartner=false;

                image_extension.setBackgroundResource(R.drawable.rewarddeselect);
                extension.setTextColor(Color.parseColor("#767998"));
                addDialog();

//                editprofile.setText("My calender");
//                fragmentManager = getSupportFragmentManager();
//                fragmentTransaction = fragmentManager.beginTransaction();
//                FirstPage_Frag task_fragment = new FirstPage_Frag();
//                fragmentTransaction.replace(R.id.blank, task_fragment);
//                fragmentTransaction.commit();

            }
        });

        contct_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer_layout.closeDrawer(paneldrawer);

                image_calender.setBackgroundResource(R.drawable.calendardeselect);
                callender.setTextColor(Color.parseColor("#767998"));
                image_meeting.setBackgroundResource(R.drawable.mettingdeselect);
                meeting.setTextColor(Color.parseColor("#767998"));
                image_partner.setBackgroundResource(R.drawable.partnerdeselect);
                partner.setTextColor(Color.parseColor("#767998"));

                image_reward.setBackgroundResource(R.drawable.rewarddeselect);
                reward.setTextColor(Color.parseColor("#767998"));
                image_stat.setBackgroundResource(R.drawable.statdeselect);
                stat.setTextColor(Color.parseColor("#767998"));

                image_setting.setBackgroundResource(R.drawable.settingdeselect);
                settings.setTextColor(Color.parseColor("#767998"));
                image_review.setBackgroundResource(R.drawable.rewarddeselect);
                review.setTextColor(Color.parseColor("#767998"));

                image_contact.setBackgroundResource(R.drawable.contactselect);
                contact.setTextColor(Color.parseColor("#F80D6B"));


                image_share_app.setBackgroundResource(R.drawable.sharedeselect);
                share.setTextColor(Color.parseColor("#767998"));

               // imge_logout.setBackgroundResource(R.drawable.rewarddeselect);
                logout.setTextColor(Color.parseColor("#767998"));


                image_extension.setBackgroundResource(R.drawable.rewarddeselect);
                extension.setTextColor(Color.parseColor("#767998"));
                save.setVisibility(View.INVISIBLE);
                AppData.addextabuttonforpartner=false;

                editprofile.setText("My calender");
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                FirstPage_Frag task_fragment = new FirstPage_Frag();
                fragmentTransaction.replace(R.id.blank, task_fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        });

        shareapp_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer_layout.closeDrawer(paneldrawer);

                image_calender.setBackgroundResource(R.drawable.calendardeselect);
                callender.setTextColor(Color.parseColor("#767998"));
                image_meeting.setBackgroundResource(R.drawable.mettingdeselect);
                meeting.setTextColor(Color.parseColor("#767998"));
                image_partner.setBackgroundResource(R.drawable.partnerdeselect);
                partner.setTextColor(Color.parseColor("#767998"));

                image_reward.setBackgroundResource(R.drawable.rewarddeselect);
                reward.setTextColor(Color.parseColor("#767998"));
                image_stat.setBackgroundResource(R.drawable.statdeselect);
                stat.setTextColor(Color.parseColor("#767998"));

                image_setting.setBackgroundResource(R.drawable.settingdeselect);
                settings.setTextColor(Color.parseColor("#767998"));
                image_review.setBackgroundResource(R.drawable.rewarddeselect);
                review.setTextColor(Color.parseColor("#767998"));

                image_contact.setBackgroundResource(R.drawable.contactdeselect);
                contact.setTextColor(Color.parseColor("#767998"));


                image_share_app.setBackgroundResource(R.drawable.shareselect);
                share.setTextColor(Color.parseColor("#F80D6B"));

                //imge_logout.setBackgroundResource(R.drawable.rewarddeselect);
                logout.setTextColor(Color.parseColor("#767998"));



                image_extension.setBackgroundResource(R.drawable.rewarddeselect);
                extension.setTextColor(Color.parseColor("#767998"));

                save.setVisibility(View.INVISIBLE);
                AppData.addextabuttonforpartner=false;
                editprofile.setText("My calender");
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                FirstPage_Frag task_fragment = new FirstPage_Frag();
                fragmentTransaction.replace(R.id.blank, task_fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        });
        logout_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                drawer_layout.closeDrawer(paneldrawer);
                image_calender.setBackgroundResource(R.drawable.calendardeselect);
                callender.setTextColor(Color.parseColor("#767998"));
                image_meeting.setBackgroundResource(R.drawable.mettingdeselect);
                meeting.setTextColor(Color.parseColor("#767998"));
                image_partner.setBackgroundResource(R.drawable.partnerdeselect);
                partner.setTextColor(Color.parseColor("#767998"));

                image_reward.setBackgroundResource(R.drawable.rewarddeselect);
                reward.setTextColor(Color.parseColor("#767998"));
                image_stat.setBackgroundResource(R.drawable.statdeselect);
                stat.setTextColor(Color.parseColor("#767998"));

                image_setting.setBackgroundResource(R.drawable.settingdeselect);
                settings.setTextColor(Color.parseColor("#767998"));
                image_review.setBackgroundResource(R.drawable.rewarddeselect);
                review.setTextColor(Color.parseColor("#767998"));

                image_contact.setBackgroundResource(R.drawable.contactdeselect);
                contact.setTextColor(Color.parseColor("#767998"));


                image_share_app.setBackgroundResource(R.drawable.sharedeselect);
                share.setTextColor(Color.parseColor("#767998"));

               // imge_logout.setBackgroundResource(R.drawable.rewardselect);
                logout.setTextColor(Color.parseColor("#F80D6B"));



                image_extension.setBackgroundResource(R.drawable.rewarddeselect);
                extension.setTextColor(Color.parseColor("#767998"));

                if(sharedPreferences.getString("register_type", " ").equals("3")) {
                    Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                            new ResultCallback<Status>() {
                                @Override
                                public void onResult(Status status)

                                {
                                    // ...
                                    FirebaseAuth.getInstance().signOut();
                                    AppData.firsttimeservicerunning="false";

                                    contacteditor=getSharedPreferences("shareprefcontactlist", MODE_PRIVATE).edit();
                                    contacteditor.clear();
                                    contacteditor.commit();

                                    editor23 = getSharedPreferences("logindetails", MODE_PRIVATE).edit();
                                    editor23.clear();
                                    editor23.commit();

                                    editor2 = getSharedPreferences("loginPrefs", MODE_PRIVATE).edit();
                                    editor2.clear();
                                    editor2.commit();
                                    Intent i = new Intent(HomePage.this, Login.class);
                                    startActivity(i);
                                    finish();
                                    //android.os.Process.killProcess(android.os.Process.myPid());
                                }
                            });


                }
                else{
                    AppData.firsttimeservicerunning="false";
                    contacteditor=getSharedPreferences("shareprefcontactlist", MODE_PRIVATE).edit();
                    contacteditor.clear();
                    contacteditor.commit();

                    editor23 = getSharedPreferences("logindetails", MODE_PRIVATE).edit();
                    editor23.clear();
                    editor23.commit();

                    editor2 = getSharedPreferences("loginPrefs", MODE_PRIVATE).edit();
                    editor2.clear();
                    editor2.commit();
                    Intent i = new Intent(HomePage.this, Login.class);
                    startActivity(i);
                    finish();
                   // android.os.Process.killProcess(android.os.Process.myPid());

                }


killService();


                //exitByBackKey();

//                final AlertDialog alertDialog = new AlertDialog.Builder(
//                        HomePage.this).create();
//                //alertDialog.setCanceledOnTouchOutside(false);
//                alertDialog.setMessage("Do you want to Log out?");
//
//                // alertDialog.setMessage("Drive was not successfull ,please redrive");
//
//                alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int which) {
//                        alertDialog.cancel();
//
//                        FirebaseAuth.getInstance().signOut();
//
//
//
//                        editor2 = getSharedPreferences("loginPrefs", MODE_PRIVATE).edit();
//                        editor2.clear();
//                        editor2.commit();
//                        Intent i=new Intent(HomePage.this,Login.class);
//                        startActivity(i);
//                        finish();
//                    }
//                });
//
//
//                alertDialog.show();






            }
        });



        extension_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i=new Intent(HomePage.this,ActivityExtension.class);
                startActivity(i);
                drawer_layout.closeDrawer(paneldrawer);
                image_calender.setBackgroundResource(R.drawable.calendardeselect);
                callender.setTextColor(Color.parseColor("#767998"));
                image_meeting.setBackgroundResource(R.drawable.mettingdeselect);
                meeting.setTextColor(Color.parseColor("#767998"));
                image_partner.setBackgroundResource(R.drawable.partnerdeselect);
                partner.setTextColor(Color.parseColor("#767998"));

                image_reward.setBackgroundResource(R.drawable.rewarddeselect);
                reward.setTextColor(Color.parseColor("#767998"));
                image_stat.setBackgroundResource(R.drawable.statdeselect);
                stat.setTextColor(Color.parseColor("#767998"));

                image_setting.setBackgroundResource(R.drawable.settingdeselect);
                settings.setTextColor(Color.parseColor("#767998"));
                image_review.setBackgroundResource(R.drawable.rewarddeselect);
                review.setTextColor(Color.parseColor("#767998"));

                image_contact.setBackgroundResource(R.drawable.contactdeselect);
                contact.setTextColor(Color.parseColor("#767998"));


                image_share_app.setBackgroundResource(R.drawable.sharedeselect);
                share.setTextColor(Color.parseColor("#767998"));

//                imge_logout.setBackgroundResource(R.drawable.rewarddeselect);
                logout.setTextColor(Color.parseColor("#767998"));



                image_extension.setBackgroundResource(R.drawable.rewardselect);
                extension.setTextColor(Color.parseColor("#F80D6B"));
            }
        });
    }

    @Override
    public void onBackPressed() {
       // if (exit)

      //  {

            exitByBackKey();

       // }

// else {
//
//            Toast.makeText(this, getResources().getString(R.string.backagain),
//                    Toast.LENGTH_SHORT).show();
//            exit = true;
//            new Handler().postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    exit = false;
//                }
//            }, 3 * 1000);
//
//        }
    }



//public boolean onKeyDown(int keyCode,KeyEvent event)
//{
//
//    if (keyCode == KeyEvent.KEYCODE_BACK)
//    {
//        if (exit)
//        {
//
//
//            exitByBackKey();
//           // finish();
//            return true;
//        }
//
//
//         else {
//            Toast.makeText(this, getResources().getString(R.string.backagain),
//                    Toast.LENGTH_SHORT).show();
//            exit = true;
//            new Handler().postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    exit = false;
//                }
//            }, 3 * 1000);
//
//        }
//
//    }
//
//    return super.onKeyDown(keyCode, event);
//}
//
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (keyCode == KeyEvent.KEYCODE_BACK)
//
//        {
//            if(doubleBackToExitPressedOnce) {
//                exitByBackKey();
//
//                //moveTaskToBack(false);
//
//                return true;
//            }
//
//            else{
//                doubleBackToExitPressedOnce = true;
//                Toast.makeText(getApplicationContext(), "Press again", Toast.LENGTH_SHORT).show();
//            }
//            mHandler.sendEmptyMessageDelayed(1, 2000/*time interval to next press in milli second*/);// if not pressed within 2seconds then will be setted(canExit) as false
//        }
//        return super.onKeyDown(keyCode, event);
//    }




    protected void exitByBackKey() {

finish();

//        ////////////////////////////////////////////////
//
//        if(sharedPreferences.getString("register_type", " ").equals("3"))
//        {
//            Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
//                    new ResultCallback<Status>() {
//                        @Override
//                        public void onResult(Status status)
//
//                        {
//                            // ...
//                            AppData.logout=true;
//                            FirebaseAuth.getInstance().signOut();
//
//                            editor23 = getSharedPreferences("logindetails", MODE_PRIVATE).edit();
//                            editor23.clear();
//                            editor23.commit();
//
//                            editor2 = getSharedPreferences("loginPrefs", MODE_PRIVATE).edit();
//                            editor2.clear();
//                            editor2.commit();
//                            Intent i = new Intent(HomePage.this, Login.class);
//                            //i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                            startActivity(i);
//                            finish();
//                           // android.os.Process.killProcess(android.os.Process.myPid());
//                        }
//                    });
//
//
//        }
//        else{
//
//            AppData.logout=true;
//            editor23 = getSharedPreferences("logindetails", MODE_PRIVATE).edit();
//            editor23.clear();
//            editor23.commit();
//            editor2 = getSharedPreferences("loginPrefs", MODE_PRIVATE).edit();
//            editor2.clear();
//            editor2.commit();
//            Intent i = new Intent(HomePage.this, Login.class);
//            startActivity(i);
//            finish();
//        }


    }

    private void addDialog() {
        LinearLayout back;
        LayoutInflater li = LayoutInflater.from(this);
        View promptsView = li.inflate(R.layout.rate_dialog, null);
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setCancelable(true);
        alert.setView(promptsView);
        back= (LinearLayout) promptsView.findViewById(R.id.menu);

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


    }

    @Override
    protected void onStart() {
        super.onStart();
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
        mGoogleApiClient.connect();
        //super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        ctx = HomePage.this;

        sharedPreferences = getSharedPreferences("logindetails", MODE_PRIVATE);
        Log.d("First_time",sharedPreferences.getString("first_time", " "));



        try {
            if(!sharedPreferences.getString("image", "").equals(""))
            {
                try {
                    Picasso.with(HomePage.this).load(sharedPreferences.getString("image", "")).transform(new RoundedTransformation()).fit().centerCrop().placeholder(R.drawable.user_image).error(R.drawable.user_image).into(imageprofilepicture);
                    //imgblk.setBackgroundResource(R.drawable.no);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }

            }
            else if(sharedPreferences.getString("image", "").equals(""))
            {

                imageprofilepicture.setBackgroundResource(R.drawable.user_image);
                //imgblk.setBackgroundResource(R.drawable.no);

            }

        }
        catch(Exception e)

        {
            e.printStackTrace();

        }


        try{
            if(AppData.partnerclick==false)
            {
                Log.d("Page:::",sharedPreferences.getString("HomePage", " "));

                if (sharedPreferences.getString("HomePage", " ").equals("Login"))
                {
                    SharedPreferences.Editor editor = getSharedPreferences("logindetails", MODE_PRIVATE).edit();
                    editor.putString("first_time","false");
                    editor.commit();

                    highlighted1.setVisibility(View.VISIBLE);
                    highlighted2.setVisibility(View.INVISIBLE);
                    highlighted3.setVisibility(View.INVISIBLE);
                    highlighted4.setVisibility(View.INVISIBLE);

                    img_square.setImageResource(R.drawable.squareselect);
                    img_fire.setImageResource(R.drawable.firedeselect);
                    img_people.setImageResource(R.drawable.peopledeselect);
                    img_chip.setImageResource(R.drawable.chipdeselect);


                    image_calender.setBackgroundResource(R.drawable.calendarselect);
                    callender.setTextColor(Color.parseColor("#F80D6B"));
                    image_meeting.setBackgroundResource(R.drawable.mettingdeselect);
                    meeting.setTextColor(Color.parseColor("#767998"));
                    image_partner.setBackgroundResource(R.drawable.partnerdeselect);
                    partner.setTextColor(Color.parseColor("#767998"));

                    image_reward.setBackgroundResource(R.drawable.rewarddeselect);
                    reward.setTextColor(Color.parseColor("#767998"));
                    image_stat.setBackgroundResource(R.drawable.statdeselect);
                    stat.setTextColor(Color.parseColor("#767998"));

                    image_setting.setBackgroundResource(R.drawable.settingdeselect);
                    settings.setTextColor(Color.parseColor("#767998"));
                    image_review.setBackgroundResource(R.drawable.rewarddeselect);
                    review.setTextColor(Color.parseColor("#767998"));

                    image_contact.setBackgroundResource(R.drawable.contactdeselect);
                    contact.setTextColor(Color.parseColor("#767998"));

                    image_share_app.setBackgroundResource(R.drawable.sharedeselect);
                    share.setTextColor(Color.parseColor("#767998"));

                    //
                    //  imge_logout.setBackgroundResource(R.drawable.rewarddeselect);
                    logout.setTextColor(Color.parseColor("#767998"));


                    image_extension.setBackgroundResource(R.drawable.rewarddeselect);
                    extension.setTextColor(Color.parseColor("#767998"));

                    AppData.addextabuttonforpartner=false;
                    AppData.page="FirstPage_Frag";
                    save.setVisibility(View.INVISIBLE);

                    editprofile.setText("My calender");
                    fragmentManager = getSupportFragmentManager();
                    fragmentTransaction = fragmentManager.beginTransaction();
                    FirstPage_Frag task_fragment = new FirstPage_Frag();
                    fragmentTransaction.replace(R.id.blank, task_fragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();

                } else if (sharedPreferences.getString("HomePage", " ").equals("Signup"))
                {


                    SharedPreferences.Editor editor = getSharedPreferences("logindetails", MODE_PRIVATE).edit();
                    editor.putString("first_time","false");
                    editor.commit();

                    highlighted1.setVisibility(View.VISIBLE);
                    highlighted2.setVisibility(View.INVISIBLE);
                    highlighted3.setVisibility(View.INVISIBLE);
                    highlighted4.setVisibility(View.INVISIBLE);

                    img_square.setImageResource(R.drawable.squareselect);
                    img_fire.setImageResource(R.drawable.firedeselect);
                    img_people.setImageResource(R.drawable.peopledeselect);
                    img_chip.setImageResource(R.drawable.chipdeselect);


                    image_calender.setBackgroundResource(R.drawable.calendarselect);
                    callender.setTextColor(Color.parseColor("#F80D6B"));
                    image_meeting.setBackgroundResource(R.drawable.mettingdeselect);
                    meeting.setTextColor(Color.parseColor("#767998"));
                    image_partner.setBackgroundResource(R.drawable.partnerdeselect);
                    partner.setTextColor(Color.parseColor("#767998"));

                    image_reward.setBackgroundResource(R.drawable.rewarddeselect);
                    reward.setTextColor(Color.parseColor("#767998"));
                    image_stat.setBackgroundResource(R.drawable.statdeselect);
                    stat.setTextColor(Color.parseColor("#767998"));

                    image_setting.setBackgroundResource(R.drawable.settingdeselect);
                    settings.setTextColor(Color.parseColor("#767998"));
                    image_review.setBackgroundResource(R.drawable.rewarddeselect);
                    review.setTextColor(Color.parseColor("#767998"));

                    image_contact.setBackgroundResource(R.drawable.contactdeselect);
                    contact.setTextColor(Color.parseColor("#767998"));

                    image_share_app.setBackgroundResource(R.drawable.sharedeselect);
                    share.setTextColor(Color.parseColor("#767998"));

                    //
                    //  imge_logout.setBackgroundResource(R.drawable.rewarddeselect);
                    logout.setTextColor(Color.parseColor("#767998"));


                    image_extension.setBackgroundResource(R.drawable.rewarddeselect);
                    extension.setTextColor(Color.parseColor("#767998"));

                    AppData.page="FirstPage_Frag";
                    AppData.addextabuttonforpartner=false;
                    save.setVisibility(View.INVISIBLE);

                    editprofile.setText("My calender");
                    fragmentManager = getSupportFragmentManager();
                    fragmentTransaction = fragmentManager.beginTransaction();
                    FirstPage_Frag task_fragment = new FirstPage_Frag();
                    fragmentTransaction.replace(R.id.blank, task_fragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();

                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        try{


            if(AppData.partnerclick==true)
            {
               // Toast.makeText(HomePage.this,"Go to partnerclicktrue block",Toast.LENGTH_SHORT).show();
                if (getIntent().getExtras().getString("HomePage").equals("Partner")) {
                    AppData.partnerclick=false;
                    Log.d("PAGE:::::", getIntent().getExtras().getString("HomePage"));
                    img_square.setImageResource(R.drawable.squaredeselect);
                    img_fire.setImageResource(R.drawable.firedeselect);
                    img_people.setImageResource(R.drawable.peopleselect);
                    img_chip.setImageResource(R.drawable.chipdeselect);

                    highlighted1.setVisibility(View.INVISIBLE);
                    highlighted2.setVisibility(View.INVISIBLE);
                    highlighted3.setVisibility(View.VISIBLE);
                    highlighted4.setVisibility(View.INVISIBLE);


                    image_calender.setBackgroundResource(R.drawable.calendardeselect);
                    callender.setTextColor(Color.parseColor("#767998"));
                    image_meeting.setBackgroundResource(R.drawable.mettingdeselect);
                    meeting.setTextColor(Color.parseColor("#767998"));
                    image_partner.setBackgroundResource(R.drawable.partnerselect);
                    partner.setTextColor(Color.parseColor("#F80D6B"));

                    image_reward.setBackgroundResource(R.drawable.rewarddeselect);
                    reward.setTextColor(Color.parseColor("#767998"));
                    image_stat.setBackgroundResource(R.drawable.statdeselect);
                    stat.setTextColor(Color.parseColor("#767998"));

                    image_setting.setBackgroundResource(R.drawable.settingdeselect);
                    settings.setTextColor(Color.parseColor("#767998"));
                    image_review.setBackgroundResource(R.drawable.rewarddeselect);
                    review.setTextColor(Color.parseColor("#767998"));

                    image_contact.setBackgroundResource(R.drawable.contactdeselect);
                    contact.setTextColor(Color.parseColor("#767998"));


                    image_share_app.setBackgroundResource(R.drawable.sharedeselect);
                    share.setTextColor(Color.parseColor("#767998"));


                    // imge_logout.setBackgroundResource(R.drawable.rewarddeselect);
                    logout.setTextColor(Color.parseColor("#767998"));


                    image_extension.setBackgroundResource(R.drawable.rewarddeselect);
                    extension.setTextColor(Color.parseColor("#767998"));

                    AppData.page="PartnerFrag";
                    save.setVisibility(View.INVISIBLE);
                    //AppData.addextabuttonforpartner=false;

                    editprofile.setText("Partners");
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    PartnerFrag bdf = new PartnerFrag();
                    ft.replace(R.id.blank, bdf);
                    //ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                    ft.addToBackStack(null);
                    ft.commit();
                }
            }

        }
        catch (Exception e)
        {

            e.printStackTrace();
        }

readcontactspermission();

    }
   public void readcontactspermission()
   {
       if(ContextCompat.checkSelfPermission(HomePage.this, android.Manifest.permission.READ_CONTACTS)
               != PackageManager.PERMISSION_GRANTED)

       {

// Should we show an explanation?
           if (ActivityCompat.shouldShowRequestPermissionRationale(HomePage.this,
                   android.Manifest.permission.READ_CONTACTS))
           {
               Log.d("GRANTED","YES");

// Show an expanation to the user *asynchronously* -- don't block
// this thread waiting for the user's response! After the user
// sees the explanation, try again to request the permission.


           } else {

// No explanation needed, we can request the permission.

               ActivityCompat.requestPermissions(HomePage.this,
                       new String[]{Manifest.permission.READ_CONTACTS},
                       MY_PERMISSIONS_REQUEST_READ_CONTACTS);
               Log.d("GRANTED", "NO");

// MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
// app-defined int constant. The callback method gets the
// result of the request.
           }


       }
       else{

          // AppData.allcontactsfetch=false;
          // AppData.isServiceCompleted=false;

if(AppData.page.equals("FirstPage_Frag")  )
{
    if(AppData.firsttimeservicerunning.equals("false"))
    {
        //AppData.firsttimeservicerunning="true";
        // Toast.makeText(HomePage.this,"go to service block",Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(HomePage.this,
                ContactService.class);
        intent.setAction(ContactService.ACTION_ON);
        startService(intent);
    }
}
else {

    //Toast.makeText(HomePage.this,"go to else block",Toast.LENGTH_SHORT).show();
}

       }




   }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //Toast.makeText(HomePage.this,"on Destroy is called",Toast.LENGTH_SHORT).show();
//        AppData.isServiceCompleted=false;
//        AppData. allcontactsfetch=false;
killService();
    }

public void killService()
{
    Intent intent = new Intent(HomePage.this,
            ContactService.class);
    intent.setAction(ContactService.ACTION_KILL);
    startService(intent);



}

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults)

    {

        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_CONTACTS: {
// If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {

                    //AppData.allcontactsfetch=false;
                    //AppData.isServiceCompleted=false;
                    Toast.makeText(HomePage.this,"permission is granted",Toast.LENGTH_SHORT).show();

                    if(AppData.page.equals("FirstPage_Frag") || AppData.page.equals("page") )
                    {
                        if(AppData.firsttimeservicerunning.equals("false"))
                        {
                            //AppData.firsttimeservicerunning="true";
                            Intent intent = new Intent(HomePage.this,
                                    ContactService.class);
                            intent.setAction(ContactService.ACTION_ON);
                            startService(intent);
                        }
                    }
                    else {

                        //Toast.makeText(HomePage.this,"go to else block",Toast.LENGTH_SHORT).show();
                    }




                }

                else {

                    AlertDialog alertDialog = new AlertDialog.Builder(
                            HomePage.this).create();
                    alertDialog.setCanceledOnTouchOutside(false);
                    alertDialog.setMessage("Without this permission the app will not work in Marshmallow.Please uninstall and reinstall again");
                    alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which)
                        {

//                            finish();
//                            startActivity(getIntent());


                            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                            Uri uri = Uri.fromParts("package", getPackageName(), null);
                            intent.setData(uri);
                            startActivityForResult(intent, MY_PERMISSIONS_REQUEST_READ_CONTACTS);
                        }
                    });
                    alertDialog.show();

                }
                return;
            }

// other 'case' lines to check for other
// permissions this app might request
        }

    }

}

