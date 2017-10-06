package com.guidiyam.sexrdv;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.guidiyam.sexrdv.Adapter.GoodsAdapter;
import com.guidiyam.sexrdv.Adapter.GoodsHorizontalAdapter;
import com.guidiyam.sexrdv.Adapter.OptionAdapter;
import com.guidiyam.sexrdv.Adapter.PartnerPlaceAdapter;
import com.guidiyam.sexrdv.Adapter.PartnerWantingAdapter;
import com.guidiyam.sexrdv.Adapter.Partneradapter;
import com.guidiyam.sexrdv.Adapter.PositionsAdapter;
import com.guidiyam.sexrdv.Adapter.PositionsHorizontalAdapter;
import com.guidiyam.sexrdv.Adapter.ReasonAdapter;
import com.guidiyam.sexrdv.Adapter.ReasonHorizontalAdapter;
import com.guidiyam.sexrdv.Adapter.RecyclerViewAdapter;
import com.guidiyam.sexrdv.Adapter.WhatAdapter;
import com.guidiyam.sexrdv.Adapter.WhatHorizontalAdapter;
import com.guidiyam.sexrdv.Adapter.WhereAdapter;
import com.guidiyam.sexrdv.Adapter.WhereHorizontalAdapter;
import com.guidiyam.sexrdv.fragment.PartnerFrag;
import com.guidiyam.sexrdv.helper.ConnectionDetector;
import com.guidiyam.sexrdv.setget.AllCountryDetails;
import com.guidiyam.sexrdv.setget.GoodsListingsSetGet;
import com.guidiyam.sexrdv.setget.GoodsSetGet;
import com.guidiyam.sexrdv.setget.PartnerListing;
import com.guidiyam.sexrdv.setget.PositionsListingsSetGet;
import com.guidiyam.sexrdv.setget.PositionsSetGet;
import com.guidiyam.sexrdv.setget.ReasonListingsSetGet;
import com.guidiyam.sexrdv.setget.ReasonSetGet;
import com.guidiyam.sexrdv.setget.WhatListingsSetGet;
import com.guidiyam.sexrdv.setget.WhatSetGet;
import com.guidiyam.sexrdv.setget.WhereListingsSetGet;
import com.guidiyam.sexrdv.setget.WhereSetGet;
import com.guidiyam.sexrdv.volley.AppData;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.MultipartBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.RequestBody;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class ActivityNewMeeting extends AppCompatActivity {
    SeekBar bar;
    RelativeLayout havefun;
   // public static String date="Select Date";

    String where_ids="",partner_ids="",what_ids="",reason_ids="",position_ids="",goods_ids="";
    EditText wheresex,whatsex,reasonsex,positionssex;
    boolean dialogstatus;
    public static int where_count=0,what_count=0,reason_count=0,positions_count=0,goods_count=0;
    private static String TAG = ActivityNewMeeting.class.getSimpleName();
    WhereAdapter whereAdapter;
    PositionsAdapter positionsAdapter;
    GoodsAdapter goodsAdapter;
    RecyclerView dialog_recyclerview,dialog_recyclerview_what,dialog_recyclerview_reason,dialog_recyclerview_positions,dialog_recyclerview_goods;
    ImageView dialogback,dialogbackreason,dialogbackpositions,dialogbackgoods;
    TextView header_name;
    EditText edt_dialog_extra_comment;
    ProgressBar dialogprog,dialogprogpositions,dialogproggoods;
    ImageView add4,add5,add6,add7,add8,add9;
    LinearLayout close;
    WhatAdapter whatAdapter;
    ReasonAdapter reasonAdapter;
    RecyclerView recyclerView,recyclerView2,recyclerView3,recyclerView4,recyclerView5,recyclerView6;
    LinearLayout first_divider,second_divider,third_divider,forth_divider,fifth_divider;
    LinearLayoutManager layoutManager2,layoutManager3,layoutManager4,layoutManager5,layoutManager6,layoutManager7;
    Partneradapter partneradapter=null;
    WhereHorizontalAdapter partnerPlaceAdapter=null;
    WhatHorizontalAdapter partnerWhatAdapter=null;
    ReasonHorizontalAdapter partnerReasonAdapter=null;
    PositionsHorizontalAdapter partnerPositionsAdapter=null;
    GoodsHorizontalAdapter partnerGoodsAdapter=null;
    PartnerWantingAdapter partnerWanting=null;
    TextView hour1,hour2,hour3,hour4,hour5,hour6,hour7,hour8,hour9,hour10,hour11,hour12;
    TextView time1,time2,time3,time4,time5,time6,time7,time8,time9,time10,time11,time12;
    RelativeLayout rl_what_layout,rl_reason_layout,rl_positions_layout,rl_goods_layout;

    List<String> date_list,time_list;
    Spinner spinner_date,spinner_time;

    static int y=0,m=0,d=0,dd=0;
    ConnectionDetector cd;

    static boolean first_time=true;
    RelativeLayout select_date,select_time;
    static  TextView date_show,time_show;
    RelativeLayout partnercylinder,rl_partner_layout,rl_where_layout,wherecylinder,whatcylinder,reasoncylinder,positioncylinder,goodscylinder;
    LinearLayout partnerlinearlayout,wherelinearlayout,whatlinearlayout,reasonlinearlayout,positionslinearlayout,goodslinearlayout;
    SharedPreferences sharedPreferences;
    ProgressBar dialogprogwhat,dialogprogreason,pbar;
    EditText edt_dialog_extra_comment_what,edt_dialog_extra_comment_reason,edt_dialog_extra_comment_positions,edt_dialog_extra_comment_goods;
    TextView header_name_what,header_name_reason,header_name_positions,header_name_goods;
    ImageView dialogbackwhat;
    LinearLayout ll_edtreason;
    EditText goodssex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitynew_meeting);
        cd= new ConnectionDetector(ActivityNewMeeting.this);
        sharedPreferences = getSharedPreferences("logindetails", MODE_PRIVATE);
        pbar=(ProgressBar)findViewById(R.id.pbar);
        pbar.setVisibility(View.GONE);
        ll_edtreason=(LinearLayout)findViewById(R.id.ll_edtreason);
        wheresex=(EditText)findViewById(R.id.wheresex);
        whatsex=(EditText)findViewById(R.id.whatsex);
        reasonsex=(EditText)findViewById(R.id.reasonsex);
        positionssex=(EditText)findViewById(R.id.positionssex);
        goodssex=(EditText)findViewById(R.id.goodssex);
        havefun=(RelativeLayout)findViewById(R.id.havefun);
        select_date= (RelativeLayout) findViewById(R.id.select_date);
        date_show= (TextView) findViewById(R.id.date_show);
        date_show.setText(AppData.date);

        select_time= (RelativeLayout) findViewById(R.id.select_time);
        time_show= (TextView) findViewById(R.id.time_show);


        ////////////////////////////////////////////////

        rl_partner_layout=(RelativeLayout)findViewById(R.id.rl_partner_layout);
        rl_where_layout=(RelativeLayout)findViewById(R.id.rl_where_layout);
        rl_what_layout=(RelativeLayout)findViewById(R.id.rl_what_layout);
        rl_reason_layout=(RelativeLayout)findViewById(R.id.rl_reason_layout);
        rl_positions_layout=(RelativeLayout)findViewById(R.id.rl_position_layout);
        rl_goods_layout=(RelativeLayout)findViewById(R.id.rl_goods_layout);

        //////////////////////////////////////////////
        partnerlinearlayout=(LinearLayout)findViewById(R.id.partnerlinearlayout) ;
        wherelinearlayout=(LinearLayout)findViewById(R.id.wherelinearlayout);
        whatlinearlayout=(LinearLayout)findViewById(R.id.whatlinearlayout);
        reasonlinearlayout=(LinearLayout)findViewById(R.id.reasonlinearlayout);
        positionslinearlayout=(LinearLayout)findViewById(R.id.positionslinearlayout);
        goodslinearlayout=(LinearLayout)findViewById(R.id.goodslinearlayout);



        //////////////////////////////////////////
        //// set id for add /////////////////
        add4=(ImageView)findViewById(R.id.add4);///partner add
        add5=(ImageView)findViewById(R.id.add5);///Where add
        add6=(ImageView)findViewById(R.id.add6);///What add

        add7=(ImageView)findViewById(R.id.add7);/// Reason add
        add8=(ImageView)findViewById(R.id.add8);///Positions add
        add9=(ImageView)findViewById(R.id.add9);///Goods add


        //////set id for add///////////////
        ////////////////////////

        partnercylinder=(RelativeLayout)findViewById(R.id.partnercylinder);
        wherecylinder=(RelativeLayout)findViewById(R.id.wherecylinder);
        whatcylinder=(RelativeLayout)findViewById(R.id.whatcylinder);
        reasoncylinder=(RelativeLayout)findViewById(R.id.reasoncylinder);
        positioncylinder=(RelativeLayout)findViewById(R.id.positionscylinder);
        goodscylinder=(RelativeLayout)findViewById(R.id.goodscylinder);




















        /////////////////////////////////////////


        //////////////////////////////////////////
        partnercylinder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AppData.partnerclick=true;

                Intent intent = new Intent(ActivityNewMeeting.this, HomePage.class);
                intent.putExtra("HomePage","Partner");
                startActivity(intent);
                finish();

            }
        });


        wherecylinder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addDialogforwhere();
            }
        });


        whatcylinder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addDialogforwhat();
            }
        });



        reasoncylinder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addDialogforreason();
            }
        });
        positioncylinder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addDialogforpositions();
            }
        });

        goodscylinder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addDialogforgoods();
            }
        });
        /////////////////////////////////////

        add4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AppData.partnerclick=true;
                Intent intent = new Intent(ActivityNewMeeting.this, HomePage.class);
                intent.putExtra("HomePage","Partner");
          startActivity(intent);
            finish();

            }
        });
add5.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        addDialogforwhere();
    }
});



        add6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addDialogforwhat();
            }
        });



        add7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addDialogforreason();
            }
        });


        add8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addDialogforpositions();
            }
        });


        add9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addDialogforgoods();
            }
        });
        //////////////////set the id of divider///////
        first_divider=(LinearLayout)findViewById(R.id.first_divider);
        second_divider=(LinearLayout)findViewById(R.id.second_divider);
        third_divider=(LinearLayout)findViewById(R.id.third_divider);
        forth_divider=(LinearLayout)findViewById(R.id.forth_divider);
        fifth_divider=(LinearLayout)findViewById(R.id.fifth_divider);
        //////////////////set the id of divider///////

////////////////////////////////////////set the id of hous////////////////////////
        hour1=(TextView)findViewById(R.id.hour1);
        hour2=(TextView)findViewById(R.id.hour2);
        hour3=(TextView)findViewById(R.id.hour3);
        hour4=(TextView)findViewById(R.id.hour4);
        hour5=(TextView)findViewById(R.id.hour5);
        hour6=(TextView)findViewById(R.id.hour6);
        hour7=(TextView)findViewById(R.id.hour7);


        hour8=(TextView)findViewById(R.id.hour8);
        hour9=(TextView)findViewById(R.id.hour9);
        hour10=(TextView)findViewById(R.id.hour10);
        hour11=(TextView)findViewById(R.id.hour11);
        hour12=(TextView)findViewById(R.id.hour12);
        ///////////////////////////////////////////////////////////


        time1=(TextView)findViewById(R.id.time1);
        time2=(TextView)findViewById(R.id.time2);
        time3=(TextView)findViewById(R.id.time3);
        time4=(TextView)findViewById(R.id.time4);
        time5=(TextView)findViewById(R.id.time5);
        time6=(TextView)findViewById(R.id.time6);
        time7=(TextView)findViewById(R.id.time7);



        time8=(TextView)findViewById(R.id.time8);
        time9=(TextView)findViewById(R.id.time9);
        time10=(TextView)findViewById(R.id.time10);
        time11=(TextView)findViewById(R.id.time11);
        time12=(TextView)findViewById(R.id.time12);



////////////////////////////////////////set the id of hous////////////////////////




///////////set the id of seekbar///////////////
        bar= (SeekBar) findViewById(R.id.seekbar);
        bar.setProgress( AppData.progress2);

        ///////////set the id of seekbar///////////////

        ///////initially hour1 is visible rest is invisible/////
        hour1.setVisibility(View.VISIBLE);
        time1.setVisibility(View.VISIBLE);
        hour2.setVisibility(View.INVISIBLE);
        time2.setVisibility(View.INVISIBLE);
        hour3.setVisibility(View.INVISIBLE);
        time3.setVisibility(View.INVISIBLE);
        hour4.setVisibility(View.INVISIBLE);
        time4.setVisibility(View.INVISIBLE);
        hour5.setVisibility(View.INVISIBLE);
        time5.setVisibility(View.INVISIBLE);
        hour6.setVisibility(View.INVISIBLE);
        time6.setVisibility(View.INVISIBLE);
        hour7.setVisibility(View.INVISIBLE);
        time7.setVisibility(View.INVISIBLE);
        hour8.setVisibility(View.INVISIBLE);
        time8.setVisibility(View.INVISIBLE);
        hour9.setVisibility(View.INVISIBLE);
        time9.setVisibility(View.INVISIBLE);
        hour10.setVisibility(View.INVISIBLE);
        time10.setVisibility(View.INVISIBLE);
        hour11.setVisibility(View.INVISIBLE);
        time11.setVisibility(View.INVISIBLE);
        hour12.setVisibility(View.INVISIBLE);
        time12.setVisibility(View.INVISIBLE);

        ///////initially hour1 is visible rest is invisible/////

        //////set the id of all horizontal recycler view/////
        recyclerView=(RecyclerView)findViewById(R.id.recyclerView);
        recyclerView2=(RecyclerView)findViewById(R.id.recyclerView2);
        recyclerView3=(RecyclerView)findViewById(R.id.recyclerView3);
        recyclerView4=(RecyclerView)findViewById(R.id.recyclerView4);
        recyclerView5=(RecyclerView)findViewById(R.id.recyclerView5);
        recyclerView6=(RecyclerView)findViewById(R.id.recyclerView6);
        //////set the id of all horizontal recycler view/////

        layoutManager2 = new LinearLayoutManager(ActivityNewMeeting.this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager2);
        layoutManager3 = new LinearLayoutManager(ActivityNewMeeting.this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView2.setLayoutManager(layoutManager3);
        layoutManager4 = new LinearLayoutManager(ActivityNewMeeting.this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView3.setLayoutManager(layoutManager4);


        layoutManager5 = new LinearLayoutManager(ActivityNewMeeting.this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView4.setLayoutManager(layoutManager5);



        layoutManager6 = new LinearLayoutManager(ActivityNewMeeting.this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView5.setLayoutManager(layoutManager6);

        layoutManager7 = new LinearLayoutManager(ActivityNewMeeting.this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView6.setLayoutManager(layoutManager7);

        close=(LinearLayout)findViewById(R.id.menu);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


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
                Intent i=new Intent(ActivityNewMeeting.this,HomePage.class);
                startActivity(i);
                finish();
            }
        });

        bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.d("PROGRESS", String.valueOf(progress));
                if (fromUser == true) {
                    seekBar.setProgress(progress);

                    if (progress == 0)
                    {
                        AppData.progress2=0;
                        AppData.duration2="1";
                        hour1.setVisibility(View.VISIBLE);
                        time1.setVisibility(View.VISIBLE);
                        hour2.setVisibility(View.INVISIBLE);
                        time2.setVisibility(View.INVISIBLE);
                        hour3.setVisibility(View.INVISIBLE);
                        time3.setVisibility(View.INVISIBLE);
                        hour4.setVisibility(View.INVISIBLE);
                        time4.setVisibility(View.INVISIBLE);
                        hour5.setVisibility(View.INVISIBLE);
                        time5.setVisibility(View.INVISIBLE);
                        hour6.setVisibility(View.INVISIBLE);
                        time6.setVisibility(View.INVISIBLE);
                        hour7.setVisibility(View.INVISIBLE);
                        time7.setVisibility(View.INVISIBLE);


                        hour8.setVisibility(View.INVISIBLE);
                        time8.setVisibility(View.INVISIBLE);
                        hour9.setVisibility(View.INVISIBLE);
                        time9.setVisibility(View.INVISIBLE);
                        hour10.setVisibility(View.INVISIBLE);
                        time10.setVisibility(View.INVISIBLE);
                        hour11.setVisibility(View.INVISIBLE);
                        time11.setVisibility(View.INVISIBLE);
                        hour12.setVisibility(View.INVISIBLE);
                        time12.setVisibility(View.INVISIBLE);



                    } else if (progress == 1) {
                        AppData.progress2=1;
                        AppData.duration2="2";
                        hour1.setVisibility(View.INVISIBLE);
                        time1.setVisibility(View.INVISIBLE);
                        hour2.setVisibility(View.VISIBLE);
                        time2.setVisibility(View.VISIBLE);
                        hour3.setVisibility(View.INVISIBLE);
                        time3.setVisibility(View.INVISIBLE);
                        hour4.setVisibility(View.INVISIBLE);
                        time4.setVisibility(View.INVISIBLE);
                        hour5.setVisibility(View.INVISIBLE);
                        time5.setVisibility(View.INVISIBLE);
                        hour6.setVisibility(View.INVISIBLE);
                        time6.setVisibility(View.INVISIBLE);
                        hour7.setVisibility(View.INVISIBLE);
                        time7.setVisibility(View.INVISIBLE);



                        hour8.setVisibility(View.INVISIBLE);
                        time8.setVisibility(View.INVISIBLE);
                        hour9.setVisibility(View.INVISIBLE);
                        time9.setVisibility(View.INVISIBLE);
                        hour10.setVisibility(View.INVISIBLE);
                        time10.setVisibility(View.INVISIBLE);
                        hour11.setVisibility(View.INVISIBLE);
                        time11.setVisibility(View.INVISIBLE);
                        hour12.setVisibility(View.INVISIBLE);
                        time12.setVisibility(View.INVISIBLE);

                    } else if (progress == 2) {
                        AppData.progress2=2;
                        AppData.duration2="3";
                        hour1.setVisibility(View.INVISIBLE);
                        time1.setVisibility(View.INVISIBLE);
                        hour2.setVisibility(View.INVISIBLE);
                        time2.setVisibility(View.INVISIBLE);
                        hour3.setVisibility(View.VISIBLE);
                        time3.setVisibility(View.VISIBLE);
                        hour4.setVisibility(View.INVISIBLE);
                        time4.setVisibility(View.INVISIBLE);
                        hour5.setVisibility(View.INVISIBLE);
                        time5.setVisibility(View.INVISIBLE);
                        hour6.setVisibility(View.INVISIBLE);
                        time6.setVisibility(View.INVISIBLE);
                        hour7.setVisibility(View.INVISIBLE);
                        time7.setVisibility(View.INVISIBLE);



                        hour8.setVisibility(View.INVISIBLE);
                        time8.setVisibility(View.INVISIBLE);
                        hour9.setVisibility(View.INVISIBLE);
                        time9.setVisibility(View.INVISIBLE);
                        hour10.setVisibility(View.INVISIBLE);
                        time10.setVisibility(View.INVISIBLE);
                        hour11.setVisibility(View.INVISIBLE);
                        time11.setVisibility(View.INVISIBLE);
                        hour12.setVisibility(View.INVISIBLE);
                        time12.setVisibility(View.INVISIBLE);


                    } else if (progress == 3) {
                        AppData.progress2=3;
                        AppData.duration2="4";
                        hour1.setVisibility(View.INVISIBLE);
                        time1.setVisibility(View.INVISIBLE);
                        hour2.setVisibility(View.INVISIBLE);
                        time2.setVisibility(View.INVISIBLE);
                        hour3.setVisibility(View.INVISIBLE);
                        time3.setVisibility(View.INVISIBLE);
                        hour4.setVisibility(View.VISIBLE);
                        time4.setVisibility(View.VISIBLE);
                        hour5.setVisibility(View.INVISIBLE);
                        time5.setVisibility(View.INVISIBLE);
                        hour6.setVisibility(View.INVISIBLE);
                        time6.setVisibility(View.INVISIBLE);
                        hour7.setVisibility(View.INVISIBLE);
                        time7.setVisibility(View.INVISIBLE);



                        hour8.setVisibility(View.INVISIBLE);
                        time8.setVisibility(View.INVISIBLE);
                        hour9.setVisibility(View.INVISIBLE);
                        time9.setVisibility(View.INVISIBLE);
                        hour10.setVisibility(View.INVISIBLE);
                        time10.setVisibility(View.INVISIBLE);
                        hour11.setVisibility(View.INVISIBLE);
                        time11.setVisibility(View.INVISIBLE);
                        hour12.setVisibility(View.INVISIBLE);
                        time12.setVisibility(View.INVISIBLE);


                    } else if (progress == 4) {
                        AppData.progress2=4;
                        AppData.duration2="5";
                        hour1.setVisibility(View.INVISIBLE);
                        time1.setVisibility(View.INVISIBLE);
                        hour2.setVisibility(View.INVISIBLE);
                        time2.setVisibility(View.INVISIBLE);
                        hour3.setVisibility(View.INVISIBLE);
                        time3.setVisibility(View.INVISIBLE);
                        hour4.setVisibility(View.INVISIBLE);
                        time4.setVisibility(View.INVISIBLE);
                        hour5.setVisibility(View.VISIBLE);
                        time5.setVisibility(View.VISIBLE);
                        hour6.setVisibility(View.INVISIBLE);
                        time6.setVisibility(View.INVISIBLE);
                        hour7.setVisibility(View.INVISIBLE);
                        time7.setVisibility(View.INVISIBLE);


                        hour8.setVisibility(View.INVISIBLE);
                        time8.setVisibility(View.INVISIBLE);
                        hour9.setVisibility(View.INVISIBLE);
                        time9.setVisibility(View.INVISIBLE);
                        hour10.setVisibility(View.INVISIBLE);
                        time10.setVisibility(View.INVISIBLE);
                        hour11.setVisibility(View.INVISIBLE);
                        time11.setVisibility(View.INVISIBLE);
                        hour12.setVisibility(View.INVISIBLE);
                        time12.setVisibility(View.INVISIBLE);

                    } else if (progress == 5) {
                        AppData.progress2=5;
                        AppData.duration2="6";
                        hour1.setVisibility(View.INVISIBLE);
                        time1.setVisibility(View.INVISIBLE);
                        hour2.setVisibility(View.INVISIBLE);
                        time2.setVisibility(View.INVISIBLE);
                        hour3.setVisibility(View.INVISIBLE);
                        time3.setVisibility(View.INVISIBLE);
                        hour4.setVisibility(View.INVISIBLE);
                        time4.setVisibility(View.INVISIBLE);
                        hour5.setVisibility(View.INVISIBLE);
                        time5.setVisibility(View.INVISIBLE);
                        hour6.setVisibility(View.VISIBLE);
                        time6.setVisibility(View.VISIBLE);
                        hour7.setVisibility(View.INVISIBLE);
                        time7.setVisibility(View.INVISIBLE);



                        hour8.setVisibility(View.INVISIBLE);
                        time8.setVisibility(View.INVISIBLE);
                        hour9.setVisibility(View.INVISIBLE);
                        time9.setVisibility(View.INVISIBLE);
                        hour10.setVisibility(View.INVISIBLE);
                        time10.setVisibility(View.INVISIBLE);
                        hour11.setVisibility(View.INVISIBLE);
                        time11.setVisibility(View.INVISIBLE);
                        hour12.setVisibility(View.INVISIBLE);
                        time12.setVisibility(View.INVISIBLE);


                    }

                    else if(progress==6)

                    {
                        AppData.progress2=6;
                        AppData.duration2="7";

                        hour1.setVisibility(View.INVISIBLE);
                        time1.setVisibility(View.INVISIBLE);
                        hour2.setVisibility(View.INVISIBLE);
                        time2.setVisibility(View.INVISIBLE);
                        hour3.setVisibility(View.INVISIBLE);
                        time3.setVisibility(View.INVISIBLE);
                        hour4.setVisibility(View.INVISIBLE);
                        time4.setVisibility(View.INVISIBLE);
                        hour5.setVisibility(View.INVISIBLE);
                        time5.setVisibility(View.INVISIBLE);
                        hour6.setVisibility(View.INVISIBLE);
                        time6.setVisibility(View.INVISIBLE);
                        hour7.setVisibility(View.VISIBLE);
                        time7.setVisibility(View.VISIBLE);


                        hour8.setVisibility(View.INVISIBLE);
                        time8.setVisibility(View.INVISIBLE);
                        hour9.setVisibility(View.INVISIBLE);
                        time9.setVisibility(View.INVISIBLE);
                        hour10.setVisibility(View.INVISIBLE);
                        time10.setVisibility(View.INVISIBLE);
                        hour11.setVisibility(View.INVISIBLE);
                        time11.setVisibility(View.INVISIBLE);
                        hour12.setVisibility(View.INVISIBLE);
                        time12.setVisibility(View.INVISIBLE);


                    }

                    else if(progress==7)
                    {
                        AppData.progress2=7;
                        AppData.duration2="8";
                        hour1.setVisibility(View.INVISIBLE);
                        time1.setVisibility(View.INVISIBLE);
                        hour2.setVisibility(View.INVISIBLE);
                        time2.setVisibility(View.INVISIBLE);
                        hour3.setVisibility(View.INVISIBLE);
                        time3.setVisibility(View.INVISIBLE);
                        hour4.setVisibility(View.INVISIBLE);
                        time4.setVisibility(View.INVISIBLE);
                        hour5.setVisibility(View.INVISIBLE);
                        time5.setVisibility(View.INVISIBLE);
                        hour6.setVisibility(View.INVISIBLE);
                        time6.setVisibility(View.INVISIBLE);
                        hour7.setVisibility(View.INVISIBLE);
                        time7.setVisibility(View.INVISIBLE);


                        hour8.setVisibility(View.VISIBLE);
                        time8.setVisibility(View.VISIBLE);
                        hour9.setVisibility(View.INVISIBLE);
                        time9.setVisibility(View.INVISIBLE);
                        hour10.setVisibility(View.INVISIBLE);
                        time10.setVisibility(View.INVISIBLE);
                        hour11.setVisibility(View.INVISIBLE);
                        time11.setVisibility(View.INVISIBLE);
                        hour12.setVisibility(View.INVISIBLE);
                        time12.setVisibility(View.INVISIBLE);


                    }
                    else if(progress==8)
                    {
                        AppData.progress2=8;
                        AppData.duration2="9";

                        hour1.setVisibility(View.INVISIBLE);
                        time1.setVisibility(View.INVISIBLE);
                        hour2.setVisibility(View.INVISIBLE);
                        time2.setVisibility(View.INVISIBLE);
                        hour3.setVisibility(View.INVISIBLE);
                        time3.setVisibility(View.INVISIBLE);
                        hour4.setVisibility(View.INVISIBLE);
                        time4.setVisibility(View.INVISIBLE);
                        hour5.setVisibility(View.INVISIBLE);
                        time5.setVisibility(View.INVISIBLE);
                        hour6.setVisibility(View.INVISIBLE);
                        time6.setVisibility(View.INVISIBLE);
                        hour7.setVisibility(View.INVISIBLE);
                        time7.setVisibility(View.INVISIBLE);


                        hour8.setVisibility(View.INVISIBLE);
                        time8.setVisibility(View.INVISIBLE);
                        hour9.setVisibility(View.VISIBLE);
                        time9.setVisibility(View.VISIBLE);
                        hour10.setVisibility(View.INVISIBLE);
                        time10.setVisibility(View.INVISIBLE);
                        hour11.setVisibility(View.INVISIBLE);
                        time11.setVisibility(View.INVISIBLE);
                        hour12.setVisibility(View.INVISIBLE);
                        time12.setVisibility(View.INVISIBLE);

                    }

                    else if(progress==9)
                    {
                        AppData.progress2=9;
                        AppData.duration2="10";

                        hour1.setVisibility(View.INVISIBLE);
                        time1.setVisibility(View.INVISIBLE);
                        hour2.setVisibility(View.INVISIBLE);
                        time2.setVisibility(View.INVISIBLE);
                        hour3.setVisibility(View.INVISIBLE);
                        time3.setVisibility(View.INVISIBLE);
                        hour4.setVisibility(View.INVISIBLE);
                        time4.setVisibility(View.INVISIBLE);
                        hour5.setVisibility(View.INVISIBLE);
                        time5.setVisibility(View.INVISIBLE);
                        hour6.setVisibility(View.INVISIBLE);
                        time6.setVisibility(View.INVISIBLE);
                        hour7.setVisibility(View.INVISIBLE);
                        time7.setVisibility(View.INVISIBLE);


                        hour8.setVisibility(View.INVISIBLE);
                        time8.setVisibility(View.INVISIBLE);
                        hour9.setVisibility(View.INVISIBLE);
                        time9.setVisibility(View.INVISIBLE);
                        hour10.setVisibility(View.VISIBLE);
                        time10.setVisibility(View.VISIBLE);
                        hour11.setVisibility(View.INVISIBLE);
                        time11.setVisibility(View.INVISIBLE);
                        hour12.setVisibility(View.INVISIBLE);
                        time12.setVisibility(View.INVISIBLE);

                    }


                    else if(progress==10)
                    {
                        AppData.progress2=10;
                        AppData.duration2="11";

                        hour1.setVisibility(View.INVISIBLE);
                        time1.setVisibility(View.INVISIBLE);
                        hour2.setVisibility(View.INVISIBLE);
                        time2.setVisibility(View.INVISIBLE);
                        hour3.setVisibility(View.INVISIBLE);
                        time3.setVisibility(View.INVISIBLE);
                        hour4.setVisibility(View.INVISIBLE);
                        time4.setVisibility(View.INVISIBLE);
                        hour5.setVisibility(View.INVISIBLE);
                        time5.setVisibility(View.INVISIBLE);
                        hour6.setVisibility(View.INVISIBLE);
                        time6.setVisibility(View.INVISIBLE);
                        hour7.setVisibility(View.INVISIBLE);
                        time7.setVisibility(View.INVISIBLE);


                        hour8.setVisibility(View.INVISIBLE);
                        time8.setVisibility(View.INVISIBLE);
                        hour9.setVisibility(View.INVISIBLE);
                        time9.setVisibility(View.INVISIBLE);
                        hour10.setVisibility(View.INVISIBLE);
                        time10.setVisibility(View.INVISIBLE);
                        hour11.setVisibility(View.VISIBLE);
                        time11.setVisibility(View.VISIBLE);
                        hour12.setVisibility(View.INVISIBLE);
                        time12.setVisibility(View.INVISIBLE);

                    }

                    else {
                        AppData.progress2=11;
                        AppData.duration2="12";
                        hour1.setVisibility(View.INVISIBLE);
                        time1.setVisibility(View.INVISIBLE);
                        hour2.setVisibility(View.INVISIBLE);
                        time2.setVisibility(View.INVISIBLE);
                        hour3.setVisibility(View.INVISIBLE);
                        time3.setVisibility(View.INVISIBLE);
                        hour4.setVisibility(View.INVISIBLE);
                        time4.setVisibility(View.INVISIBLE);
                        hour5.setVisibility(View.INVISIBLE);
                        time5.setVisibility(View.INVISIBLE);
                        hour6.setVisibility(View.INVISIBLE);
                        time6.setVisibility(View.INVISIBLE);
                        hour7.setVisibility(View.INVISIBLE);
                        time7.setVisibility(View.INVISIBLE);


                        hour8.setVisibility(View.INVISIBLE);
                        time8.setVisibility(View.INVISIBLE);
                        hour9.setVisibility(View.INVISIBLE);
                        time9.setVisibility(View.INVISIBLE);
                        hour10.setVisibility(View.INVISIBLE);
                        time10.setVisibility(View.INVISIBLE);
                        hour11.setVisibility(View.INVISIBLE);
                        time11.setVisibility(View.INVISIBLE);
                        hour12.setVisibility(View.VISIBLE);
                        time12.setVisibility(View.VISIBLE);

                    }
                }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

//        if(partnerWanting==null)
//        {
//            partnerWanting = new PartnerWantingAdapter(ActivityNewMeeting.this,"ActivityNewMeeting");
//           // partnerWanting = new PartnerWantingAdapter(ActivityNewMeeting.this);
//            recyclerView3.setAdapter(partnerWanting);//set an adapter
//
//
//        }




        select_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                DatePickerDialogLayout datePickerDialogLayout = new DatePickerDialogLayout();
                datePickerDialogLayout.show(getSupportFragmentManager(), "datePicker");


            }
        });

        select_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DialogFragment newFragment = new TimePickerFragment();
                newFragment.show(getSupportFragmentManager(), "timePicker");
            }
        });

        wheresex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppData.addextrabuttonforwhere=true;
                addDialogforwhere();
            }
        });
        whatsex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppData.addextrabuttonforwhat=true;
                addDialogforwhat();
            }
        });
        reasonsex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Toast.makeText(ActivityNewMeeting.this,"Reason Edit text is clicked",Toast.LENGTH_SHORT).show();
                AppData.addextrabuttonforreason=true;
                addDialogforreason();
            }
        });

        positionssex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toast.makeText(ActivityNewMeeting.this,"Reason Edit text is clicked",Toast.LENGTH_SHORT).show();
                AppData.addextrabuttonforpositions=true;
                addDialogforpositions();
            }
        });
        goodssex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppData.addextrabuttonforgoods=true;
                addDialogforgoods();
            }
        });

      /*  spinner_date = (Spinner) findViewById(R.id.spinner_date);

        date_list = new ArrayList<String>();
        date_list.add("Mon, 18.03.2017");
        date_list.add("Tues, 18.03.2017");
        date_list.add("Wed, 18.03.2017");
        date_list.add("Thus, 18.03.2017");
        date_list.add("Fri, 18.03.2017");
        date_list.add("Sat, 18.03.2017");
        date_list.add("Sun, 18.03.2017");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item_view, date_list);

        dataAdapter.setDropDownViewResource(R.layout.spinner_item_view);

        spinner_date.setAdapter(dataAdapter);*/


   /*     spinner_time = (Spinner) findViewById(R.id.spinner_time);

        time_list = new ArrayList<String>();
        time_list.add("18:00");
        time_list.add("19:00");
        time_list.add("20:00");
        time_list.add("21:00");
        time_list.add("22:00");
        time_list.add("23:00");
        time_list.add("24:00");

        ArrayAdapter<String> timeAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item_view, time_list);

        timeAdapter.setDropDownViewResource(R.layout.spinner_item_view);

        spinner_time.setAdapter(timeAdapter);*/
        havefun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String d=date_show.getText().toString().trim();
                String t=time_show.getText().toString().trim();
                Log.d("HAVEFUNSELECTEDATE",AppData.selectdate);


                if(cd.isConnectingToInternet())
                {

                    if(!d.equals("Select Date"))
                    {

                        if(!t.equals("Time"))
                        {

                            new CreatemeetingAsync().execute();
                        }
                        else{


                            Toast.makeText(ActivityNewMeeting.this,"Select Time",Toast.LENGTH_SHORT).show();
                        }

                        // Toast.makeText(ActivityNewMeeting.this,"Select Date",Toast.LENGTH_SHORT).show();
                    }
             else
                 {
                        Toast.makeText(ActivityNewMeeting.this,"Select Date",Toast.LENGTH_SHORT).show();
                    }



                }
                else{

                    Toast.makeText(ActivityNewMeeting.this,"No Internet Connection",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }



/*    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it

            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            // Do something with the date chosen by the user
        }
    }*/

    public static class DatePickerDialogLayout extends DialogFragment implements DatePickerDialog.OnDateSetListener
    {

        Calendar calendar;

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState)
        {

            int year=0,month=0,day=0;

            DatePickerDialog datepickerdialog = new DatePickerDialog(getActivity(),
                    R.style.dialog,this,year,month,day);
            datepickerdialog.getDatePicker().setMinDate( System.currentTimeMillis());


            if(first_time) {
                calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_MONTH);

                dd=calendar.get(Calendar.DAY_OF_WEEK);

                y=year;
                m=month+1;
                d=day;

                first_time=false;

                Log.d("Date----------------","Fist Time");
            }else {

                datepickerdialog.updateDate(y,month,d);

                Log.d("Date----------------","Not Fist Time");


            }

            return datepickerdialog;
        }


        public void onDateSet(DatePicker view, int year, int month, int day)
        {

            y=year;
            m=month+1;
            d=day;

            String weekDay;
            SimpleDateFormat dayFormat = new SimpleDateFormat("EEE", Locale.US);

            Calendar calendar = Calendar.getInstance();
            calendar.set(y,m+1,d);
            weekDay = dayFormat.format(calendar.getTime());


            Log.d("Day","----------"+weekDay);


if(m<=9)
{
if(d<10) {
    AppData.selectdate = String.valueOf(y) + "-" + "0" + String.valueOf(m) + "-"+"0"+String.valueOf(d);
    //Log.d("Selecteddate", AppData.selectdate);
    AppData.date = weekDay + ", "+"0"+String.valueOf(d) + "." + "0" + String.valueOf(m)
            + "." + String.valueOf(y);
    date_show.setText(   AppData.date);
    Log.d("Selecteddate", AppData.selectdate);
}
else
{
    AppData.selectdate = String.valueOf(y) + "-" + "0" + String.valueOf(m) + "-"+String.valueOf(d);
    //Log.d("Selecteddate", AppData.selectdate);
    AppData.date = weekDay + ", " + String.valueOf(d) + "." + "0" + String.valueOf(m)
            + "." + String.valueOf(y);
    date_show.setText(   AppData.date);
    Log.d("Selecteddate", AppData.selectdate);
}


}
else
{
    if(d<10)
    {
        AppData.selectdate = String.valueOf(y) + "-" + String.valueOf(m) + "-" + "0"+String.valueOf(d);
        AppData.date = weekDay + ", " + "0"+String.valueOf(d) + "." + String.valueOf(m)
                + "." + String.valueOf(y);
        date_show.setText(   AppData.date);
        Log.d("Selecteddate", AppData.selectdate);
    }
    else
    {
        AppData.selectdate = String.valueOf(y) + "-" + String.valueOf(m) + "-"+String.valueOf(d);
        AppData.date = weekDay + ", " + String.valueOf(d) + "." + String.valueOf(m)
                + "." + String.valueOf(y);
        date_show.setText(   AppData.date);
        Log.d("Selecteddate", AppData.selectdate);

    }

}


        }


        public void onCancel(DialogInterface dialog)
        {

           // year=0;month=0;day=0;
            dialog.dismiss();
        }

    }


    public static class TimePickerFragment extends DialogFragment
            implements TimePickerDialog.OnTimeSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current time as the default values for the picker
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            // Create a new instance of TimePickerDialog and return it
            return new TimePickerDialog(getActivity(),R.style.dialog, this, hour, minute,
                    DateFormat.is24HourFormat(getActivity()));
        }

        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            // Do something with the time chosen by the user
if(minute>=10) {
    AppData.selecttime=hourOfDay + ":" + minute;
    time_show.setText(hourOfDay + ":" + minute);
}
else {
    AppData.selecttime=hourOfDay + ":"+"0"+ minute;
    time_show.setText(AppData.selecttime);
}
        }
    }

    public void addDialog(String top_name)
    {

        OptionAdapter optionAdapter;

        final Dialog dialog=new Dialog(this,android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.add_dialog_layout);
        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);

        TextView header_name= (TextView) dialog.findViewById(R.id.header_name);
        header_name.setText(top_name);
        ImageView back= (ImageView) dialog.findViewById(R.id.back);
        RecyclerView rv_option= (RecyclerView) dialog.findViewById(R.id.rv_option);
        rv_option.setHasFixedSize(true);

        optionAdapter=new OptionAdapter(ActivityNewMeeting.this);
        rv_option.setLayoutManager(new GridLayoutManager(ActivityNewMeeting.this,4));
        rv_option.setAdapter(optionAdapter);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });


        dialog.show();

    }







    @Override
    protected void onResume() {
        super.onResume();
        Log.d("Selecteddate",AppData.selectdate);
       // Toast.makeText(ActivityNewMeeting.this,"Go to resume page",Toast.LENGTH_SHORT).show();
        //rl_partner_layout.setVisibility(View.GONE);
         partnerlinearlayout.setVisibility(View.VISIBLE);
        wherelinearlayout.setVisibility(View.VISIBLE);
        whatlinearlayout.setVisibility(View.VISIBLE);
        reasonlinearlayout.setVisibility(View.VISIBLE);
        positionslinearlayout.setVisibility(View.VISIBLE);
      //  first_divider.setVisibility(View.INVISIBLE);
        date_show.setText(AppData.date);
        time_show.setText(AppData.selecttime);
        bar.setProgress( AppData.progress2);



        if ( AppData.progress2 == 0)
        {
            AppData.progress2=0;
            AppData.duration2="1";
            hour1.setVisibility(View.VISIBLE);
            time1.setVisibility(View.VISIBLE);
            hour2.setVisibility(View.INVISIBLE);
            time2.setVisibility(View.INVISIBLE);
            hour3.setVisibility(View.INVISIBLE);
            time3.setVisibility(View.INVISIBLE);
            hour4.setVisibility(View.INVISIBLE);
            time4.setVisibility(View.INVISIBLE);
            hour5.setVisibility(View.INVISIBLE);
            time5.setVisibility(View.INVISIBLE);
            hour6.setVisibility(View.INVISIBLE);
            time6.setVisibility(View.INVISIBLE);
            hour7.setVisibility(View.INVISIBLE);
            time7.setVisibility(View.INVISIBLE);


            hour8.setVisibility(View.INVISIBLE);
            time8.setVisibility(View.INVISIBLE);
            hour9.setVisibility(View.INVISIBLE);
            time9.setVisibility(View.INVISIBLE);
            hour10.setVisibility(View.INVISIBLE);
            time10.setVisibility(View.INVISIBLE);
            hour11.setVisibility(View.INVISIBLE);
            time11.setVisibility(View.INVISIBLE);
            hour12.setVisibility(View.INVISIBLE);
            time12.setVisibility(View.INVISIBLE);



        } else if ( AppData.progress2 == 1) {
            AppData.progress2=1;
            AppData.duration2="2";
            hour1.setVisibility(View.INVISIBLE);
            time1.setVisibility(View.INVISIBLE);
            hour2.setVisibility(View.VISIBLE);
            time2.setVisibility(View.VISIBLE);
            hour3.setVisibility(View.INVISIBLE);
            time3.setVisibility(View.INVISIBLE);
            hour4.setVisibility(View.INVISIBLE);
            time4.setVisibility(View.INVISIBLE);
            hour5.setVisibility(View.INVISIBLE);
            time5.setVisibility(View.INVISIBLE);
            hour6.setVisibility(View.INVISIBLE);
            time6.setVisibility(View.INVISIBLE);
            hour7.setVisibility(View.INVISIBLE);
            time7.setVisibility(View.INVISIBLE);



            hour8.setVisibility(View.INVISIBLE);
            time8.setVisibility(View.INVISIBLE);
            hour9.setVisibility(View.INVISIBLE);
            time9.setVisibility(View.INVISIBLE);
            hour10.setVisibility(View.INVISIBLE);
            time10.setVisibility(View.INVISIBLE);
            hour11.setVisibility(View.INVISIBLE);
            time11.setVisibility(View.INVISIBLE);
            hour12.setVisibility(View.INVISIBLE);
            time12.setVisibility(View.INVISIBLE);

        } else if ( AppData.progress2 == 2) {
            AppData.progress2=2;
            AppData.duration2="3";
            hour1.setVisibility(View.INVISIBLE);
            time1.setVisibility(View.INVISIBLE);
            hour2.setVisibility(View.INVISIBLE);
            time2.setVisibility(View.INVISIBLE);
            hour3.setVisibility(View.VISIBLE);
            time3.setVisibility(View.VISIBLE);
            hour4.setVisibility(View.INVISIBLE);
            time4.setVisibility(View.INVISIBLE);
            hour5.setVisibility(View.INVISIBLE);
            time5.setVisibility(View.INVISIBLE);
            hour6.setVisibility(View.INVISIBLE);
            time6.setVisibility(View.INVISIBLE);
            hour7.setVisibility(View.INVISIBLE);
            time7.setVisibility(View.INVISIBLE);



            hour8.setVisibility(View.INVISIBLE);
            time8.setVisibility(View.INVISIBLE);
            hour9.setVisibility(View.INVISIBLE);
            time9.setVisibility(View.INVISIBLE);
            hour10.setVisibility(View.INVISIBLE);
            time10.setVisibility(View.INVISIBLE);
            hour11.setVisibility(View.INVISIBLE);
            time11.setVisibility(View.INVISIBLE);
            hour12.setVisibility(View.INVISIBLE);
            time12.setVisibility(View.INVISIBLE);


        } else if ( AppData.progress2 == 3) {
            AppData.progress2=3;
            AppData.duration2="4";
            hour1.setVisibility(View.INVISIBLE);
            time1.setVisibility(View.INVISIBLE);
            hour2.setVisibility(View.INVISIBLE);
            time2.setVisibility(View.INVISIBLE);
            hour3.setVisibility(View.INVISIBLE);
            time3.setVisibility(View.INVISIBLE);
            hour4.setVisibility(View.VISIBLE);
            time4.setVisibility(View.VISIBLE);
            hour5.setVisibility(View.INVISIBLE);
            time5.setVisibility(View.INVISIBLE);
            hour6.setVisibility(View.INVISIBLE);
            time6.setVisibility(View.INVISIBLE);
            hour7.setVisibility(View.INVISIBLE);
            time7.setVisibility(View.INVISIBLE);



            hour8.setVisibility(View.INVISIBLE);
            time8.setVisibility(View.INVISIBLE);
            hour9.setVisibility(View.INVISIBLE);
            time9.setVisibility(View.INVISIBLE);
            hour10.setVisibility(View.INVISIBLE);
            time10.setVisibility(View.INVISIBLE);
            hour11.setVisibility(View.INVISIBLE);
            time11.setVisibility(View.INVISIBLE);
            hour12.setVisibility(View.INVISIBLE);
            time12.setVisibility(View.INVISIBLE);


        } else if ( AppData.progress2 == 4) {
            AppData.progress2=4;
            AppData.duration2="5";
            hour1.setVisibility(View.INVISIBLE);
            time1.setVisibility(View.INVISIBLE);
            hour2.setVisibility(View.INVISIBLE);
            time2.setVisibility(View.INVISIBLE);
            hour3.setVisibility(View.INVISIBLE);
            time3.setVisibility(View.INVISIBLE);
            hour4.setVisibility(View.INVISIBLE);
            time4.setVisibility(View.INVISIBLE);
            hour5.setVisibility(View.VISIBLE);
            time5.setVisibility(View.VISIBLE);
            hour6.setVisibility(View.INVISIBLE);
            time6.setVisibility(View.INVISIBLE);
            hour7.setVisibility(View.INVISIBLE);
            time7.setVisibility(View.INVISIBLE);


            hour8.setVisibility(View.INVISIBLE);
            time8.setVisibility(View.INVISIBLE);
            hour9.setVisibility(View.INVISIBLE);
            time9.setVisibility(View.INVISIBLE);
            hour10.setVisibility(View.INVISIBLE);
            time10.setVisibility(View.INVISIBLE);
            hour11.setVisibility(View.INVISIBLE);
            time11.setVisibility(View.INVISIBLE);
            hour12.setVisibility(View.INVISIBLE);
            time12.setVisibility(View.INVISIBLE);

        } else if ( AppData.progress2 == 5) {
            AppData.progress2=5;
            AppData.duration2="6";
            hour1.setVisibility(View.INVISIBLE);
            time1.setVisibility(View.INVISIBLE);
            hour2.setVisibility(View.INVISIBLE);
            time2.setVisibility(View.INVISIBLE);
            hour3.setVisibility(View.INVISIBLE);
            time3.setVisibility(View.INVISIBLE);
            hour4.setVisibility(View.INVISIBLE);
            time4.setVisibility(View.INVISIBLE);
            hour5.setVisibility(View.INVISIBLE);
            time5.setVisibility(View.INVISIBLE);
            hour6.setVisibility(View.VISIBLE);
            time6.setVisibility(View.VISIBLE);
            hour7.setVisibility(View.INVISIBLE);
            time7.setVisibility(View.INVISIBLE);



            hour8.setVisibility(View.INVISIBLE);
            time8.setVisibility(View.INVISIBLE);
            hour9.setVisibility(View.INVISIBLE);
            time9.setVisibility(View.INVISIBLE);
            hour10.setVisibility(View.INVISIBLE);
            time10.setVisibility(View.INVISIBLE);
            hour11.setVisibility(View.INVISIBLE);
            time11.setVisibility(View.INVISIBLE);
            hour12.setVisibility(View.INVISIBLE);
            time12.setVisibility(View.INVISIBLE);


        }

        else if( AppData.progress2==6)

        {
            AppData.progress2=6;
            AppData.duration2="7";

            hour1.setVisibility(View.INVISIBLE);
            time1.setVisibility(View.INVISIBLE);
            hour2.setVisibility(View.INVISIBLE);
            time2.setVisibility(View.INVISIBLE);
            hour3.setVisibility(View.INVISIBLE);
            time3.setVisibility(View.INVISIBLE);
            hour4.setVisibility(View.INVISIBLE);
            time4.setVisibility(View.INVISIBLE);
            hour5.setVisibility(View.INVISIBLE);
            time5.setVisibility(View.INVISIBLE);
            hour6.setVisibility(View.INVISIBLE);
            time6.setVisibility(View.INVISIBLE);
            hour7.setVisibility(View.VISIBLE);
            time7.setVisibility(View.VISIBLE);


            hour8.setVisibility(View.INVISIBLE);
            time8.setVisibility(View.INVISIBLE);
            hour9.setVisibility(View.INVISIBLE);
            time9.setVisibility(View.INVISIBLE);
            hour10.setVisibility(View.INVISIBLE);
            time10.setVisibility(View.INVISIBLE);
            hour11.setVisibility(View.INVISIBLE);
            time11.setVisibility(View.INVISIBLE);
            hour12.setVisibility(View.INVISIBLE);
            time12.setVisibility(View.INVISIBLE);


        }

        else if( AppData.progress2==7)
        {
            AppData.progress2=7;
            AppData.duration2="8";
            hour1.setVisibility(View.INVISIBLE);
            time1.setVisibility(View.INVISIBLE);
            hour2.setVisibility(View.INVISIBLE);
            time2.setVisibility(View.INVISIBLE);
            hour3.setVisibility(View.INVISIBLE);
            time3.setVisibility(View.INVISIBLE);
            hour4.setVisibility(View.INVISIBLE);
            time4.setVisibility(View.INVISIBLE);
            hour5.setVisibility(View.INVISIBLE);
            time5.setVisibility(View.INVISIBLE);
            hour6.setVisibility(View.INVISIBLE);
            time6.setVisibility(View.INVISIBLE);
            hour7.setVisibility(View.INVISIBLE);
            time7.setVisibility(View.INVISIBLE);


            hour8.setVisibility(View.VISIBLE);
            time8.setVisibility(View.VISIBLE);
            hour9.setVisibility(View.INVISIBLE);
            time9.setVisibility(View.INVISIBLE);
            hour10.setVisibility(View.INVISIBLE);
            time10.setVisibility(View.INVISIBLE);
            hour11.setVisibility(View.INVISIBLE);
            time11.setVisibility(View.INVISIBLE);
            hour12.setVisibility(View.INVISIBLE);
            time12.setVisibility(View.INVISIBLE);


        }
        else if( AppData.progress2==8)
        {
            AppData.progress2=8;
            AppData.duration2="9";

            hour1.setVisibility(View.INVISIBLE);
            time1.setVisibility(View.INVISIBLE);
            hour2.setVisibility(View.INVISIBLE);
            time2.setVisibility(View.INVISIBLE);
            hour3.setVisibility(View.INVISIBLE);
            time3.setVisibility(View.INVISIBLE);
            hour4.setVisibility(View.INVISIBLE);
            time4.setVisibility(View.INVISIBLE);
            hour5.setVisibility(View.INVISIBLE);
            time5.setVisibility(View.INVISIBLE);
            hour6.setVisibility(View.INVISIBLE);
            time6.setVisibility(View.INVISIBLE);
            hour7.setVisibility(View.INVISIBLE);
            time7.setVisibility(View.INVISIBLE);


            hour8.setVisibility(View.INVISIBLE);
            time8.setVisibility(View.INVISIBLE);
            hour9.setVisibility(View.VISIBLE);
            time9.setVisibility(View.VISIBLE);
            hour10.setVisibility(View.INVISIBLE);
            time10.setVisibility(View.INVISIBLE);
            hour11.setVisibility(View.INVISIBLE);
            time11.setVisibility(View.INVISIBLE);
            hour12.setVisibility(View.INVISIBLE);
            time12.setVisibility(View.INVISIBLE);

        }

        else if( AppData.progress2==9)
        {
            AppData.progress2=9;
            AppData.duration2="10";

            hour1.setVisibility(View.INVISIBLE);
            time1.setVisibility(View.INVISIBLE);
            hour2.setVisibility(View.INVISIBLE);
            time2.setVisibility(View.INVISIBLE);
            hour3.setVisibility(View.INVISIBLE);
            time3.setVisibility(View.INVISIBLE);
            hour4.setVisibility(View.INVISIBLE);
            time4.setVisibility(View.INVISIBLE);
            hour5.setVisibility(View.INVISIBLE);
            time5.setVisibility(View.INVISIBLE);
            hour6.setVisibility(View.INVISIBLE);
            time6.setVisibility(View.INVISIBLE);
            hour7.setVisibility(View.INVISIBLE);
            time7.setVisibility(View.INVISIBLE);


            hour8.setVisibility(View.INVISIBLE);
            time8.setVisibility(View.INVISIBLE);
            hour9.setVisibility(View.INVISIBLE);
            time9.setVisibility(View.INVISIBLE);
            hour10.setVisibility(View.VISIBLE);
            time10.setVisibility(View.VISIBLE);
            hour11.setVisibility(View.INVISIBLE);
            time11.setVisibility(View.INVISIBLE);
            hour12.setVisibility(View.INVISIBLE);
            time12.setVisibility(View.INVISIBLE);

        }


        else if( AppData.progress2==10)
        {
            AppData.progress2=10;
            AppData.duration2="11";

            hour1.setVisibility(View.INVISIBLE);
            time1.setVisibility(View.INVISIBLE);
            hour2.setVisibility(View.INVISIBLE);
            time2.setVisibility(View.INVISIBLE);
            hour3.setVisibility(View.INVISIBLE);
            time3.setVisibility(View.INVISIBLE);
            hour4.setVisibility(View.INVISIBLE);
            time4.setVisibility(View.INVISIBLE);
            hour5.setVisibility(View.INVISIBLE);
            time5.setVisibility(View.INVISIBLE);
            hour6.setVisibility(View.INVISIBLE);
            time6.setVisibility(View.INVISIBLE);
            hour7.setVisibility(View.INVISIBLE);
            time7.setVisibility(View.INVISIBLE);


            hour8.setVisibility(View.INVISIBLE);
            time8.setVisibility(View.INVISIBLE);
            hour9.setVisibility(View.INVISIBLE);
            time9.setVisibility(View.INVISIBLE);
            hour10.setVisibility(View.INVISIBLE);
            time10.setVisibility(View.INVISIBLE);
            hour11.setVisibility(View.VISIBLE);
            time11.setVisibility(View.VISIBLE);
            hour12.setVisibility(View.INVISIBLE);
            time12.setVisibility(View.INVISIBLE);

        }

        else {
            AppData.progress2=11;
            AppData.duration2="12";
            hour1.setVisibility(View.INVISIBLE);
            time1.setVisibility(View.INVISIBLE);
            hour2.setVisibility(View.INVISIBLE);
            time2.setVisibility(View.INVISIBLE);
            hour3.setVisibility(View.INVISIBLE);
            time3.setVisibility(View.INVISIBLE);
            hour4.setVisibility(View.INVISIBLE);
            time4.setVisibility(View.INVISIBLE);
            hour5.setVisibility(View.INVISIBLE);
            time5.setVisibility(View.INVISIBLE);
            hour6.setVisibility(View.INVISIBLE);
            time6.setVisibility(View.INVISIBLE);
            hour7.setVisibility(View.INVISIBLE);
            time7.setVisibility(View.INVISIBLE);


            hour8.setVisibility(View.INVISIBLE);
            time8.setVisibility(View.INVISIBLE);
            hour9.setVisibility(View.INVISIBLE);
            time9.setVisibility(View.INVISIBLE);
            hour10.setVisibility(View.INVISIBLE);
            time10.setVisibility(View.INVISIBLE);
            hour11.setVisibility(View.INVISIBLE);
            time11.setVisibility(View.INVISIBLE);
            hour12.setVisibility(View.VISIBLE);
            time12.setVisibility(View.VISIBLE);

        }

        if(AppData.partnerjsonarray.length()>0)
        {

            AppData.partnerListings=new ArrayList<PartnerListing>();
            AppData.partnerListings.clear();
            first_divider.setVisibility(View.VISIBLE);
            rl_partner_layout.setVisibility(View.VISIBLE);
            partnerlinearlayout.setVisibility(View.GONE);
            for(int i=0;i<AppData.partnerjsonarray.length();i++)
            {

                try {
                    JSONObject jobj=AppData.partnerjsonarray.getJSONObject(i);
                    String name=jobj.getString("name");
                    String image=jobj.getString("image");
                    PartnerListing partnerListing=new PartnerListing(name,image);
                    AppData.partnerListings.add(partnerListing);
                    Log.d("Parnersee:","name "+partnerListing.getName()+"image "+partnerListing.getImage());

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
            partneradapter = new Partneradapter(ActivityNewMeeting.this, AppData.partnerListings);
            recyclerView.setAdapter(partneradapter);//set an adapter


        }

        if(AppData.wherejsonarray.length()>0)

        {

            rl_where_layout.setVisibility(View.VISIBLE);
            wherelinearlayout.setVisibility(View.GONE);
            second_divider.setVisibility(View.VISIBLE);
            wheresex.setText(AppData.wheretxt);

            AppData.whereListings=new ArrayList<WhereListingsSetGet>();
            AppData.whereListings.clear();
//            rl_partner_layout.setVisibility(View.VISIBLE);
//            partnerlinearlayout.setVisibility(View.GONE);
            for(int i=0;i<AppData.wherejsonarray.length();i++)
            {

                try {
                    JSONObject jobj=AppData.wherejsonarray.getJSONObject(i);
                    String name=jobj.getString("name");
                    String image=jobj.getString("image");
                    WhereListingsSetGet whereListingsSetGet=new WhereListingsSetGet(name,image);
                    AppData.whereListings.add(whereListingsSetGet);
                    Log.d("Parnersee:","name "+whereListingsSetGet.getName()+"image "+whereListingsSetGet.getImage());

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
            partnerPlaceAdapter = new WhereHorizontalAdapter(ActivityNewMeeting.this, AppData.whereListings,ActivityNewMeeting.this);
            recyclerView2.setAdapter(partnerPlaceAdapter);//set an adapter


        }

       if(AppData.whatjsonarray.length()>0)
       {
           rl_what_layout.setVisibility(View.VISIBLE);
           whatlinearlayout.setVisibility(View.GONE);
           third_divider.setVisibility(View.VISIBLE);
           whatsex.setText(AppData.whattxt);




           AppData.whatListings=new ArrayList<WhatListingsSetGet>();
           AppData.whatListings.clear();
//            rl_partner_layout.setVisibility(View.VISIBLE);
//            partnerlinearlayout.setVisibility(View.GONE);
           for(int i=0;i<AppData.whatjsonarray.length();i++)
           {

               try {
                   JSONObject jobj=AppData.whatjsonarray.getJSONObject(i);
                   String name=jobj.getString("name");
                   String image=jobj.getString("image");
                   WhatListingsSetGet whatListingsSetGet=new WhatListingsSetGet(name,image);
                   AppData.whatListings.add(whatListingsSetGet);
                   Log.d("Parnersee:","name "+whatListingsSetGet.getName()+"image "+whatListingsSetGet.getImage());

               } catch (JSONException e) {
                   e.printStackTrace();
               }

           }
           partnerWhatAdapter = new WhatHorizontalAdapter(ActivityNewMeeting.this, AppData.whatListings,ActivityNewMeeting.this);
           recyclerView3.setAdapter(partnerWhatAdapter);//s


       }


        if(AppData.reasonjsonarray.length()>0)
        {
            //Toast.makeText(ActivityNewMeeting.this,"go to reasonjsonarray greater than zero block",Toast.LENGTH_SHORT).show();
            rl_reason_layout.setVisibility(View.VISIBLE);
            reasonlinearlayout.setVisibility(View.GONE);
            forth_divider.setVisibility(View.VISIBLE);
            reasonsex.setText(AppData.reasontxt);




            AppData.reasonListings=new ArrayList<ReasonListingsSetGet>();
            AppData.reasonListings.clear();
//            rl_partner_layout.setVisibility(View.VISIBLE);
//            partnerlinearlayout.setVisibility(View.GONE);
            for(int i=0;i<AppData.reasonjsonarray.length();i++)
            {

                try {
                    JSONObject jobj=AppData.reasonjsonarray.getJSONObject(i);
                    String name=jobj.getString("name");
                    String image=jobj.getString("image");
                    ReasonListingsSetGet reasonListingsSetGet=new ReasonListingsSetGet(name,image);
                    AppData.reasonListings.add(reasonListingsSetGet);
                    Log.d("Parnersee:","name "+reasonListingsSetGet.getName()+"image "+reasonListingsSetGet.getImage());

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
            partnerReasonAdapter = new ReasonHorizontalAdapter(ActivityNewMeeting.this, AppData.reasonListings,ActivityNewMeeting.this);
            recyclerView4.setAdapter(partnerReasonAdapter);//s


        }



        if(AppData.positionsjsonarray.length()>0)
        {
            //Toast.makeText(ActivityNewMeeting.this,"go to reasonjsonarray greater than zero block",Toast.LENGTH_SHORT).show();
            rl_positions_layout.setVisibility(View.VISIBLE);
            positionslinearlayout.setVisibility(View.GONE);
            fifth_divider.setVisibility(View.VISIBLE);
            positionssex.setText(AppData.positiontxt);




            AppData.positionsListings=new ArrayList<PositionsListingsSetGet>();
            AppData.positionsListings.clear();

            for(int i=0;i<AppData.positionsjsonarray.length();i++)
            {

                try {
                    JSONObject jobj=AppData.positionsjsonarray.getJSONObject(i);
                    String name=jobj.getString("name");
                    String image=jobj.getString("image");
                    PositionsListingsSetGet positionsListingsSetGet=new PositionsListingsSetGet(name,image);
                    AppData.positionsListings.add(positionsListingsSetGet);
                    Log.d("Parnersee:","name "+positionsListingsSetGet.getName()+"image "+positionsListingsSetGet.getImage());

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
            partnerPositionsAdapter = new PositionsHorizontalAdapter(ActivityNewMeeting.this, AppData.positionsListings,ActivityNewMeeting.this);
            recyclerView5.setAdapter(partnerPositionsAdapter);//s


        }



        if(AppData.goodsjsonarray.length()>0)
        {
            //Toast.makeText(ActivityNewMeeting.this,"go to reasonjsonarray greater than zero block",Toast.LENGTH_SHORT).show();
            rl_goods_layout.setVisibility(View.VISIBLE);
            goodslinearlayout.setVisibility(View.GONE);

            goodssex.setText(AppData.goodstxt);




            AppData.goodsListings=new ArrayList<GoodsListingsSetGet>();
            AppData.goodsListings.clear();


            for(int i=0;i<AppData.goodsjsonarray.length();i++)
            {

                try {
                    JSONObject jobj=AppData.goodsjsonarray.getJSONObject(i);
                    String name=jobj.getString("name");
                    String image=jobj.getString("image");
                    GoodsListingsSetGet goodsListingsSetGet=new GoodsListingsSetGet(name,image);
                    AppData.goodsListings.add(goodsListingsSetGet);
                    Log.d("Parnersee:","name "+goodsListingsSetGet.getName()+"image "+goodsListingsSetGet.getImage());

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
            partnerGoodsAdapter = new GoodsHorizontalAdapter(ActivityNewMeeting.this, AppData.goodsListings,ActivityNewMeeting.this);
            recyclerView6.setAdapter(partnerGoodsAdapter);//s


        }
        Log.d("partnerjsonarray",AppData.partnerjsonarray.toString());
        Log.d("wherejsonarray",AppData.wherejsonarray.toString());
        Log.d("whatjsonarray",AppData.whatjsonarray.toString());
        Log.d("reasonjsonarray",AppData.reasonjsonarray.toString());
        Log.d("positionsjsonarray",AppData.positionsjsonarray.toString());
        Log.d("goodsjsonarray",AppData.goodsjsonarray.toString());

        for(int i=0;i<AppData.partnerjsonarray.length();i++)
        {
            try {
                JSONObject object=AppData.partnerjsonarray.getJSONObject(i);
                String partnerid=object.getString("id");

                partner_ids=partner_ids+partnerid+",";

            } catch (JSONException e) {
                e.printStackTrace();
            }


        }

        if(!partner_ids.equals("")) {

            partner_ids =  partner_ids.substring(0, partner_ids.length() - 1);

        }

        for(int i=0;i<AppData.wherejsonarray.length();i++)
        {
            try {
                JSONObject object=AppData.wherejsonarray.getJSONObject(i);
                String whereid=object.getString("id");

                where_ids=where_ids+whereid+",";

            } catch (JSONException e) {
                e.printStackTrace();
            }


        }

        if(!where_ids.equals("")) {

            where_ids =  where_ids.substring(0, where_ids.length() - 1);

        }


        for(int i=0;i<AppData.whatjsonarray.length();i++)
        {
            try {
                JSONObject object=AppData.whatjsonarray.getJSONObject(i);
                String whatid=object.getString("id");

                what_ids=what_ids+whatid+",";

            } catch (JSONException e) {
                e.printStackTrace();
            }


        }

        if(!what_ids.equals("")) {

            what_ids =  what_ids.substring(0, what_ids.length() - 1);

        }


        for(int i=0;i<AppData.reasonjsonarray.length();i++)
        {
            try {
                JSONObject object=AppData.reasonjsonarray.getJSONObject(i);
                String reasonid=object.getString("id");

                reason_ids=reason_ids+reasonid+",";

            } catch (JSONException e) {
                e.printStackTrace();
            }


        }

        if(!reason_ids.equals("")) {

            reason_ids =  reason_ids.substring(0, reason_ids.length() - 1);

        }

        for(int i=0;i<AppData.positionsjsonarray.length();i++)
        {
            try {
                JSONObject object=AppData.positionsjsonarray.getJSONObject(i);
                String positionid=object.getString("id");

                position_ids=position_ids+positionid+",";

            } catch (JSONException e) {
                e.printStackTrace();
            }


        }

        if(!position_ids.equals("")) {

            position_ids =  position_ids.substring(0, position_ids.length() - 1);

        }


        for(int i=0;i<AppData.goodsjsonarray.length();i++)
        {
            try {
                JSONObject object=AppData.goodsjsonarray.getJSONObject(i);
                String goodsid=object.getString("id");

                goods_ids=goods_ids+goodsid+",";

            } catch (JSONException e) {
                e.printStackTrace();
            }


        }

        if(!goods_ids.equals("")) {

            goods_ids =  goods_ids.substring(0, goods_ids.length() - 1);

        }
        Log.d("Partnerid", partner_ids);
        Log.d("Whereid", where_ids);
        Log.d("Whatid", what_ids);
        Log.d("Reasonid", reason_ids);
        Log.d("Positionid", position_ids);
        Log.d("Goodsid", goods_ids);
    }

    public void addDialogforgoods()
    {
        final Dialog dialog=new Dialog(this,android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.add_dialog_layout2);
        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);

        dialogproggoods=(ProgressBar)dialog.findViewById(R.id.pbar) ;
        dialogproggoods.setVisibility(View.GONE);

        edt_dialog_extra_comment_goods=(EditText)dialog.findViewById(R.id.edt_dialog_extra_comment);
        edt_dialog_extra_comment_goods.setText(AppData.goodstxt);

        header_name_goods= (TextView) dialog.findViewById(R.id.header_name);
        header_name_goods.setText("Goods");

        dialogbackgoods= (ImageView) dialog.findViewById(R.id.back);

        dialog_recyclerview_goods= (RecyclerView) dialog.findViewById(R.id.rv_option);
        dialog_recyclerview_goods.setHasFixedSize(true);



        dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event)
            {
                if (keyCode == KeyEvent.KEYCODE_BACK)
                {

                    AppData.goodstxt=edt_dialog_extra_comment_goods.getText().toString().trim();
                    if(AppData.addextrabuttonforgoods==false)

                    {


                        if(goods_count==0)
                        {


                        }
                        else {

                            int p = 0;
                            //jsonArray = new JSONArray();

                            for (int i = 0; i < AppData.goodslistings.size(); i++) {

                                if (AppData.goodslistings.get(i).isTouch() == true) {

                                    JSONObject jsonObject = new JSONObject();
                                    try {
                                        jsonObject.put("id", AppData.goodslistings.get(i).getGoodId());
                                        jsonObject.put("name", AppData.goodslistings.get(i).getEn_name());
                                        jsonObject.put("image", AppData.goodslistings.get(i).getImage());
                                        AppData.goodsjsonarray.put(p, jsonObject);

                                        p++;


                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        }
                        Log.d("Data", "::::::" + AppData.goodsjsonarray.toString());









                    }


                    else{

                        if(goods_count==0)
                        {


                        }
                        else {

                            int p = 0;
                            //jsonArray = new JSONArray();

                            for (int i = 0; i < AppData.goodslistings.size(); i++) {

                                if (AppData.goodslistings.get(i).isTouch() == true) {

                                    JSONObject jsonObject = new JSONObject();
                                    try {
                                        jsonObject.put("id", AppData.goodslistings.get(i).getGoodId());
                                        jsonObject.put("name", AppData.goodslistings.get(i).getEn_name());
                                        jsonObject.put("image", AppData.goodslistings.get(i).getImage());
                                        AppData.goodsjsonarray.put(p, jsonObject);

                                        p++;


                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        }
                        Log.d("Data", "::::::" + AppData.goodsjsonarray.toString());

                    }
                    finish();
                    startActivity(getIntent());
                    dialog.cancel();

                    return true;
                }
                return false;
            }
        });

        dialogbackgoods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppData.goodstxt=edt_dialog_extra_comment_goods.getText().toString().trim();
                if(AppData.addextrabuttonforgoods==false)

                {


                    if(goods_count==0)
                    {


                    }
                    else {

                        int p = 0;
                        //jsonArray = new JSONArray();

                        for (int i = 0; i < AppData.goodslistings.size(); i++) {

                            if (AppData.goodslistings.get(i).isTouch() == true) {

                                JSONObject jsonObject = new JSONObject();
                                try {
                                    jsonObject.put("id", AppData.goodslistings.get(i).getGoodId());
                                    jsonObject.put("name", AppData.goodslistings.get(i).getEn_name());
                                    jsonObject.put("image", AppData.goodslistings.get(i).getImage());
                                    AppData.goodsjsonarray.put(p, jsonObject);

                                    p++;


                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                    Log.d("Data", "::::::" + AppData.goodsjsonarray.toString());









                }


                else{

                    if(goods_count==0)
                    {


                    }
                    else {

                        int p = 0;
                        //jsonArray = new JSONArray();

                        for (int i = 0; i < AppData.goodslistings.size(); i++) {

                            if (AppData.goodslistings.get(i).isTouch() == true) {

                                JSONObject jsonObject = new JSONObject();
                                try {
                                    jsonObject.put("id", AppData.goodslistings.get(i).getGoodId());
                                    jsonObject.put("name", AppData.goodslistings.get(i).getEn_name());
                                    jsonObject.put("image", AppData.goodslistings.get(i).getImage());
                                    AppData.goodsjsonarray.put(p, jsonObject);

                                    p++;


                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                    Log.d("Data", "::::::" + AppData.goodsjsonarray.toString());

                }
                finish();
                startActivity(getIntent());
                dialog.cancel();
            }
        });



        if(AppData.addextrabuttonforgoods==false)
        {
            goodsapi();


        }

        else{
            goodsAdapter=new GoodsAdapter(ActivityNewMeeting.this, AppData.goodslistings,ActivityNewMeeting.this);
            dialog_recyclerview_goods.setLayoutManager(new GridLayoutManager(ActivityNewMeeting.this,4));
            dialog_recyclerview_goods.setAdapter(goodsAdapter);

        }
        dialog.show();



    }
    public void addDialogforpositions()
    {
        final Dialog dialog=new Dialog(this,android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.add_dialog_layout2);
        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);

        dialogprogpositions=(ProgressBar)dialog.findViewById(R.id.pbar) ;
        dialogprogpositions.setVisibility(View.GONE);

        edt_dialog_extra_comment_positions=(EditText)dialog.findViewById(R.id.edt_dialog_extra_comment);
        edt_dialog_extra_comment_positions.setText(AppData.positiontxt);

        header_name_positions= (TextView) dialog.findViewById(R.id.header_name);
        header_name_positions.setText("Positions");

        dialogbackpositions= (ImageView) dialog.findViewById(R.id.back);

        dialog_recyclerview_positions= (RecyclerView) dialog.findViewById(R.id.rv_option);
        dialog_recyclerview_positions.setHasFixedSize(true);

dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
    @Override
    public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event)
    {
        if (keyCode == KeyEvent.KEYCODE_BACK)
        {
            AppData.positiontxt=edt_dialog_extra_comment_positions.getText().toString().trim();
            if(AppData.addextrabuttonforpositions==false)

            {


                if(positions_count==0)
                {


                }
                else {

                    int p = 0;
                    //jsonArray = new JSONArray();

                    for (int i = 0; i < AppData.positionslistings.size(); i++) {

                        if (AppData.positionslistings.get(i).isTouch() == true) {

                            JSONObject jsonObject = new JSONObject();
                            try {
                                jsonObject.put("id", AppData.positionslistings.get(i).getPostionId());
                                jsonObject.put("name", AppData.positionslistings.get(i).getEn_name());
                                jsonObject.put("image", AppData.positionslistings.get(i).getImage());
                                AppData.positionsjsonarray.put(p, jsonObject);

                                p++;


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
                Log.d("Data", "::::::" + AppData.positionsjsonarray.toString());









            }


            else{

                if(positions_count==0)
                {


                }
                else {

                    int p = 0;
                    //jsonArray = new JSONArray();

                    for (int i = 0; i < AppData.positionslistings.size(); i++) {

                        if (AppData.positionslistings.get(i).isTouch() == true) {

                            JSONObject jsonObject = new JSONObject();
                            try {
                                jsonObject.put("id", AppData.positionslistings.get(i).getPostionId());
                                jsonObject.put("name", AppData.positionslistings.get(i).getEn_name());
                                jsonObject.put("image", AppData.positionslistings.get(i).getImage());
                                AppData.positionsjsonarray.put(p, jsonObject);

                                p++;


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
                Log.d("Data", "::::::" + AppData.positionsjsonarray.toString());

            }
            finish();
            startActivity(getIntent());
            dialog.cancel();


            return true;
        }
        return false;
    }
});



        dialogbackpositions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppData.positiontxt=edt_dialog_extra_comment_positions.getText().toString().trim();
                if(AppData.addextrabuttonforpositions==false)

                {


                    if(positions_count==0)
                    {


                    }
                    else {

                        int p = 0;
                        //jsonArray = new JSONArray();

                        for (int i = 0; i < AppData.positionslistings.size(); i++) {

                            if (AppData.positionslistings.get(i).isTouch() == true) {

                                JSONObject jsonObject = new JSONObject();
                                try {
                                    jsonObject.put("id", AppData.positionslistings.get(i).getPostionId());
                                    jsonObject.put("name", AppData.positionslistings.get(i).getEn_name());
                                    jsonObject.put("image", AppData.positionslistings.get(i).getImage());
                                    AppData.positionsjsonarray.put(p, jsonObject);

                                    p++;


                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                    Log.d("Data", "::::::" + AppData.positionsjsonarray.toString());









                }


                else{

                    if(positions_count==0)
                    {


                    }
                    else {

                        int p = 0;
                        //jsonArray = new JSONArray();

                        for (int i = 0; i < AppData.positionslistings.size(); i++) {

                            if (AppData.positionslistings.get(i).isTouch() == true) {

                                JSONObject jsonObject = new JSONObject();
                                try {
                                    jsonObject.put("id", AppData.positionslistings.get(i).getPostionId());
                                    jsonObject.put("name", AppData.positionslistings.get(i).getEn_name());
                                    jsonObject.put("image", AppData.positionslistings.get(i).getImage());
                                    AppData.positionsjsonarray.put(p, jsonObject);

                                    p++;


                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                    Log.d("Data", "::::::" + AppData.positionsjsonarray.toString());

                }
                finish();
                startActivity(getIntent());
                dialog.cancel();
            }
        });



        if(AppData.addextrabuttonforpositions==false)
        {
                positionsapi();


        }

        else{
            positionsAdapter=new PositionsAdapter(ActivityNewMeeting.this, AppData.positionslistings,ActivityNewMeeting.this);
            dialog_recyclerview_positions.setLayoutManager(new GridLayoutManager(ActivityNewMeeting.this,4));
            dialog_recyclerview_positions.setAdapter(positionsAdapter);

        }
        dialog.show();



    }
    public void addDialogforwhere()
    {


        final Dialog dialog=new Dialog(this,android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.add_dialog_layout2);
        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        dialogprog=(ProgressBar)dialog.findViewById(R.id.pbar) ;
        dialogprog.setVisibility(View.GONE);
        edt_dialog_extra_comment=(EditText)dialog.findViewById(R.id.edt_dialog_extra_comment);
        edt_dialog_extra_comment.setText(AppData.wheretxt);

        header_name= (TextView) dialog.findViewById(R.id.header_name);
        header_name.setText("Where");
        dialogback= (ImageView) dialog.findViewById(R.id.back);
         dialog_recyclerview= (RecyclerView) dialog.findViewById(R.id.rv_option);
        dialog_recyclerview.setHasFixedSize(true);


        dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    // DO WHAT YOU WANT ON BACK PRESSED

                    AppData.wheretxt=edt_dialog_extra_comment.getText().toString().trim();
                    if(AppData.addextrabuttonforwhere==false)

                    {


                        if(where_count==0)
                        {


                        }
                        else {

                            int p = 0;
                            //jsonArray = new JSONArray();

                            for (int i = 0; i < AppData.wherelistings.size(); i++) {

                                if (AppData.wherelistings.get(i).isTouch() == true) {

                                    JSONObject jsonObject = new JSONObject();
                                    try {
                                        jsonObject.put("id", AppData.wherelistings.get(i).getWhereId());
                                        jsonObject.put("name", AppData.wherelistings.get(i).getEn_name());
                                        jsonObject.put("image", AppData.wherelistings.get(i).getImage());
                                        AppData.wherejsonarray.put(p, jsonObject);

                                        p++;


                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        }
                        Log.d("Data", "::::::" + AppData.wherejsonarray.toString());









                    }


                    else{

                        if(where_count==0)
                        {


                        }
                        else {

                            int p = 0;
                            //jsonArray = new JSONArray();

                            for (int i = 0; i < AppData.wherelistings.size(); i++) {

                                if (AppData.wherelistings.get(i).isTouch() == true) {

                                    JSONObject jsonObject = new JSONObject();
                                    try {
                                        jsonObject.put("id", AppData.wherelistings.get(i).getWhereId());
                                        jsonObject.put("name", AppData.wherelistings.get(i).getEn_name());
                                        jsonObject.put("image", AppData.wherelistings.get(i).getImage());
                                        AppData.wherejsonarray.put(p, jsonObject);

                                        p++;


                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        }
                        Log.d("Data", "::::::" + AppData.wherejsonarray.toString());

                    }
                    finish();
                    startActivity(getIntent());
                    dialog.cancel();

                    return true;
                }
                return false;
            }
        });


        dialogback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                AppData.wheretxt=edt_dialog_extra_comment.getText().toString().trim();
                if(AppData.addextrabuttonforwhere==false)

                {


                    if(where_count==0)
                    {


                    }
                    else {

                        int p = 0;
                        //jsonArray = new JSONArray();

                        for (int i = 0; i < AppData.wherelistings.size(); i++) {

                            if (AppData.wherelistings.get(i).isTouch() == true) {

                                JSONObject jsonObject = new JSONObject();
                                try {
                                    jsonObject.put("id", AppData.wherelistings.get(i).getWhereId());
                                    jsonObject.put("name", AppData.wherelistings.get(i).getEn_name());
                                    jsonObject.put("image", AppData.wherelistings.get(i).getImage());
                                    AppData.wherejsonarray.put(p, jsonObject);

                                    p++;


                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                    Log.d("Data", "::::::" + AppData.wherejsonarray.toString());









                }


                else{

                    if(where_count==0)
                    {


                    }
                    else {

                        int p = 0;
                        //jsonArray = new JSONArray();

                        for (int i = 0; i < AppData.wherelistings.size(); i++) {

                            if (AppData.wherelistings.get(i).isTouch() == true) {

                                JSONObject jsonObject = new JSONObject();
                                try {
                                    jsonObject.put("id", AppData.wherelistings.get(i).getWhereId());
                                    jsonObject.put("name", AppData.wherelistings.get(i).getEn_name());
                                    jsonObject.put("image", AppData.wherelistings.get(i).getImage());
                                    AppData.wherejsonarray.put(p, jsonObject);

                                    p++;


                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                    Log.d("Data", "::::::" + AppData.wherejsonarray.toString());

                }
                finish();
                startActivity(getIntent());
                dialog.cancel();
            }
        });



if(AppData.addextrabuttonforwhere==false) {

    whereapi();


}

        else{
        whereAdapter=new WhereAdapter(ActivityNewMeeting.this, AppData.wherelistings,ActivityNewMeeting.this);
        dialog_recyclerview.setLayoutManager(new GridLayoutManager(ActivityNewMeeting.this,4));
        dialog_recyclerview.setAdapter(whereAdapter);

    }
        dialog.show();
    }




    public void addDialogforwhat()
    {
        final Dialog dialog=new Dialog(this,android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.add_dialog_layout2);
        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        dialogprogwhat=(ProgressBar)dialog.findViewById(R.id.pbar) ;
        dialogprogwhat.setVisibility(View.GONE);
        edt_dialog_extra_comment_what=(EditText)dialog.findViewById(R.id.edt_dialog_extra_comment);

        edt_dialog_extra_comment_what.setText(AppData.whattxt);

        header_name_what= (TextView) dialog.findViewById(R.id.header_name);
        header_name_what.setText("What");
        dialogbackwhat= (ImageView) dialog.findViewById(R.id.back);
        dialog_recyclerview_what= (RecyclerView) dialog.findViewById(R.id.rv_option);
        dialog_recyclerview_what.setHasFixedSize(true);


        dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event)
            {
                if (keyCode == KeyEvent.KEYCODE_BACK)
                {
                    AppData.whattxt=edt_dialog_extra_comment_what.getText().toString().trim();
                    if(AppData.addextrabuttonforwhat==false)

                    {


                        if(what_count==0)
                        {


                        }
                        else {

                            int p = 0;
                            //jsonArray = new JSONArray();

                            for (int i = 0; i < AppData.whatlistings.size(); i++) {

                                if (AppData.whatlistings.get(i).isTouch() == true) {

                                    JSONObject jsonObject = new JSONObject();
                                    try {
                                        jsonObject.put("id", AppData.whatlistings.get(i).getWhatId());
                                        jsonObject.put("name", AppData.whatlistings.get(i).getEn_name());
                                        jsonObject.put("image", AppData.whatlistings.get(i).getImage());
                                        AppData.whatjsonarray.put(p, jsonObject);

                                        p++;


                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        }
                        Log.d("Data", "::::::" + AppData.whatjsonarray.toString());









                    }


                    else{

                        if(what_count==0)
                        {


                        }
                        else {

                            int p = 0;
                            //jsonArray = new JSONArray();

                            for (int i = 0; i < AppData.whatlistings.size(); i++) {

                                if (AppData.whatlistings.get(i).isTouch() == true) {

                                    JSONObject jsonObject = new JSONObject();
                                    try {
                                        jsonObject.put("id", AppData.whatlistings.get(i).getWhatId());
                                        jsonObject.put("name", AppData.whatlistings.get(i).getEn_name());
                                        jsonObject.put("image", AppData.whatlistings.get(i).getImage());
                                        AppData.whatjsonarray.put(p, jsonObject);

                                        p++;


                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        }
                        Log.d("Data", "::::::" + AppData.whatjsonarray.toString());

                    }
                    finish();
                    startActivity(getIntent());
                    dialog.cancel();


                    return true;
                }
                return false;
            }
        });


        dialogbackwhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppData.whattxt=edt_dialog_extra_comment_what.getText().toString().trim();
                if(AppData.addextrabuttonforwhat==false)

                {


                    if(what_count==0)
                    {


                    }
                    else {

                        int p = 0;
                        //jsonArray = new JSONArray();

                        for (int i = 0; i < AppData.whatlistings.size(); i++) {

                            if (AppData.whatlistings.get(i).isTouch() == true) {

                                JSONObject jsonObject = new JSONObject();
                                try {
                                    jsonObject.put("id", AppData.whatlistings.get(i).getWhatId());
                                    jsonObject.put("name", AppData.whatlistings.get(i).getEn_name());
                                    jsonObject.put("image", AppData.whatlistings.get(i).getImage());
                                    AppData.whatjsonarray.put(p, jsonObject);

                                    p++;


                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                    Log.d("Data", "::::::" + AppData.whatjsonarray.toString());









                }


                else{

                    if(what_count==0)
                    {


                    }
                    else {

                        int p = 0;
                        //jsonArray = new JSONArray();

                        for (int i = 0; i < AppData.whatlistings.size(); i++) {

                            if (AppData.whatlistings.get(i).isTouch() == true) {

                                JSONObject jsonObject = new JSONObject();
                                try {
                                    jsonObject.put("id", AppData.whatlistings.get(i).getWhatId());
                                    jsonObject.put("name", AppData.whatlistings.get(i).getEn_name());
                                    jsonObject.put("image", AppData.whatlistings.get(i).getImage());
                                    AppData.whatjsonarray.put(p, jsonObject);

                                    p++;


                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                    Log.d("Data", "::::::" + AppData.whatjsonarray.toString());

                }
                finish();
                startActivity(getIntent());
                dialog.cancel();
            }
        });



        if(AppData.addextrabuttonforwhat==false) {

            whatapi();


        }

        else{
            whatAdapter=new WhatAdapter(ActivityNewMeeting.this, AppData.whatlistings,ActivityNewMeeting.this);
            dialog_recyclerview_what.setLayoutManager(new GridLayoutManager(ActivityNewMeeting.this,4));
            dialog_recyclerview_what.setAdapter(whatAdapter);

        }
        dialog.show();


    }



    public void addDialogforreason()
    {
        final Dialog dialog=new Dialog(this,android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.add_dialog_layout2);
        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        dialogprogreason=(ProgressBar)dialog.findViewById(R.id.pbar) ;
        dialogprogreason.setVisibility(View.GONE);
        edt_dialog_extra_comment_reason=(EditText)dialog.findViewById(R.id.edt_dialog_extra_comment);

        edt_dialog_extra_comment_reason.setText(AppData.reasontxt);

        header_name_reason= (TextView) dialog.findViewById(R.id.header_name);
        header_name_reason.setText("Reason");
        dialogbackreason= (ImageView) dialog.findViewById(R.id.back);

        dialog_recyclerview_reason= (RecyclerView) dialog.findViewById(R.id.rv_option);
        dialog_recyclerview_reason.setHasFixedSize(true);


        dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event)
            {
                if (keyCode == KeyEvent.KEYCODE_BACK)
                {
                    AppData.reasontxt=edt_dialog_extra_comment_reason.getText().toString().trim();
                    if(AppData.addextrabuttonforreason==false)

                    {


                        if(reason_count==0)
                        {


                        }
                        else {

                            int p = 0;
                            //jsonArray = new JSONArray();

                            for (int i = 0; i < AppData.reasonlistings.size(); i++) {

                                if (AppData.reasonlistings.get(i).isTouch() == true) {

                                    JSONObject jsonObject = new JSONObject();
                                    try {
                                        jsonObject.put("id", AppData.reasonlistings.get(i).getReasonId());
                                        jsonObject.put("name", AppData.reasonlistings.get(i).getEn_name());
                                        jsonObject.put("image", AppData.reasonlistings.get(i).getImage());
                                        AppData.reasonjsonarray.put(p, jsonObject);

                                        p++;


                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        }
                        Log.d("Data", "::::::" + AppData.reasonjsonarray.toString());









                    }


                    else{

                        if(reason_count==0)
                        {


                        }
                        else {

                            int p = 0;
                            //jsonArray = new JSONArray();

                            for (int i = 0; i < AppData.reasonlistings.size(); i++) {

                                if (AppData.reasonlistings.get(i).isTouch() == true) {

                                    JSONObject jsonObject = new JSONObject();
                                    try {
                                        jsonObject.put("id", AppData.reasonlistings.get(i).getReasonId());
                                        jsonObject.put("name", AppData.reasonlistings.get(i).getEn_name());
                                        jsonObject.put("image", AppData.reasonlistings.get(i).getImage());
                                        AppData.reasonjsonarray.put(p, jsonObject);

                                        p++;


                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        }
                        Log.d("Data", "::::::" + AppData.reasonjsonarray.toString());

                    }
                    finish();
                    startActivity(getIntent());
                    dialog.cancel();


                    return true;
                }
                return false;
            }
        });


        dialogbackreason.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppData.reasontxt=edt_dialog_extra_comment_reason.getText().toString().trim();
                if(AppData.addextrabuttonforreason==false)

                {


                    if(reason_count==0)
                    {


                    }
                    else {

                        int p = 0;
                        //jsonArray = new JSONArray();

                        for (int i = 0; i < AppData.reasonlistings.size(); i++) {

                            if (AppData.reasonlistings.get(i).isTouch() == true) {

                                JSONObject jsonObject = new JSONObject();
                                try {
                                    jsonObject.put("id", AppData.reasonlistings.get(i).getReasonId());
                                    jsonObject.put("name", AppData.reasonlistings.get(i).getEn_name());
                                    jsonObject.put("image", AppData.reasonlistings.get(i).getImage());
                                    AppData.reasonjsonarray.put(p, jsonObject);

                                    p++;


                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                    Log.d("Data", "::::::" + AppData.reasonjsonarray.toString());









                }


                else{

                    if(reason_count==0)
                    {


                    }
                    else {

                        int p = 0;
                        //jsonArray = new JSONArray();

                        for (int i = 0; i < AppData.reasonlistings.size(); i++) {

                            if (AppData.reasonlistings.get(i).isTouch() == true) {

                                JSONObject jsonObject = new JSONObject();
                                try {
                                    jsonObject.put("id", AppData.reasonlistings.get(i).getReasonId());
                                    jsonObject.put("name", AppData.reasonlistings.get(i).getEn_name());
                                    jsonObject.put("image", AppData.reasonlistings.get(i).getImage());
                                    AppData.reasonjsonarray.put(p, jsonObject);

                                    p++;


                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                    Log.d("Data", "::::::" + AppData.reasonjsonarray.toString());

                }
                finish();
                startActivity(getIntent());
                dialog.cancel();
            }
        });



        if(AppData.addextrabuttonforreason==false) {

            reasonapi();


        }

        else{
            reasonAdapter=new ReasonAdapter(ActivityNewMeeting.this, AppData.reasonlistings,ActivityNewMeeting.this);
            dialog_recyclerview_reason.setLayoutManager(new GridLayoutManager(ActivityNewMeeting.this,4));
            dialog_recyclerview_reason.setAdapter(reasonAdapter);

        }
        dialog.show();



    }
public void reasonapi()
{
    dialogprogreason.setVisibility(View.VISIBLE);
    String  urlJsonObj = AppData.url+"/index.php/Signup/fetchreasons?mode="+sharedPreferences.getString("mode", " ");
    Log.d("reasonurl",urlJsonObj);
    JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, urlJsonObj, null, new Response.Listener<JSONObject>() {
        @Override
        public void onResponse(JSONObject jsonObject) {
            try {

                dialogstatus = jsonObject.getBoolean("status");
                if(dialogstatus==true)
                {
                    JSONArray countryData = jsonObject.getJSONArray("details");

                    if(countryData.length()>0)
                    {
                        AppData.reasonlistings=new ArrayList<ReasonSetGet>();


                        for (int i = 0; i < countryData.length(); i++)
                        {
                            JSONObject josonobj2 = countryData.getJSONObject(i);

                            String reasonId = josonobj2.getString("id");
                            String en_name = josonobj2.getString("en_name");
                            String fr_name = josonobj2.getString("fre_name");
                            String image_status = josonobj2.getString("image_status");
                            String image = josonobj2.getString("image");
                            String deselected_image = josonobj2.getString("deselected_image");

                            ReasonSetGet ab = new ReasonSetGet(reasonId, en_name, fr_name, image_status, image, deselected_image,false);
                            AppData.reasonlistings.add(ab);


                        }
                        reasonAdapter=new ReasonAdapter(ActivityNewMeeting.this, AppData.reasonlistings,ActivityNewMeeting.this);
                        dialog_recyclerview_reason.setLayoutManager(new GridLayoutManager(ActivityNewMeeting.this,4));
                        dialog_recyclerview_reason.setAdapter(reasonAdapter);
//
                    }

                    dialogprogreason.setVisibility(View.GONE);
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

    public void whatapi()
    {
        dialogprogwhat.setVisibility(View.VISIBLE);
        String  urlJsonObj = AppData.url+"/index.php/Signup/fetchsextype?mode="+sharedPreferences.getString("mode", " ");
        Log.d("whaturl",urlJsonObj);
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, urlJsonObj, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                try {

                    dialogstatus = jsonObject.getBoolean("status");
                    if(dialogstatus==true)
                    {
                        JSONArray countryData = jsonObject.getJSONArray("details");

                        if(countryData.length()>0)
                        {
                            AppData.whatlistings=new ArrayList<WhatSetGet>();


                            for (int i = 0; i < countryData.length(); i++)
                            {
                                JSONObject josonobj2 = countryData.getJSONObject(i);

                                String whatId = josonobj2.getString("id");
                                String en_name = josonobj2.getString("en_name");
                                String fr_name = josonobj2.getString("fre_name");
                                String image_status = josonobj2.getString("image_status");
                                String image = josonobj2.getString("image");
                                String deselected_image = josonobj2.getString("deselected_image");

                                WhatSetGet ab = new WhatSetGet(whatId, en_name, fr_name, image_status, image, deselected_image,false);
                                AppData.whatlistings.add(ab);


                            }
                            whatAdapter=new WhatAdapter(ActivityNewMeeting.this, AppData.whatlistings,ActivityNewMeeting.this);
                            dialog_recyclerview_what.setLayoutManager(new GridLayoutManager(ActivityNewMeeting.this,4));
                            dialog_recyclerview_what.setAdapter(whatAdapter);
//
                        }

                        dialogprogwhat.setVisibility(View.GONE);
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

    public void whereapi()
    {
        dialogprog.setVisibility(View.VISIBLE);
        String  urlJsonObj = AppData.url+"/index.php/Signup/fetchlocation?mode="+sharedPreferences.getString("mode", " ");
        Log.d("whereurl",urlJsonObj);
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, urlJsonObj, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                try {

                    dialogstatus = jsonObject.getBoolean("status");
                    if(dialogstatus==true)
                    {
                        JSONArray countryData = jsonObject.getJSONArray("details");

                        if(countryData.length()>0)
                        {
                              AppData.wherelistings=new ArrayList<WhereSetGet>();


                            for (int i = 0; i < countryData.length(); i++)
                            {
                                JSONObject josonobj2 = countryData.getJSONObject(i);

                                String whereId = josonobj2.getString("id");
                                String en_name = josonobj2.getString("en_name");
                                String fr_name = josonobj2.getString("fr_name");
                                String image_status = josonobj2.getString("image_status");
                                String image = josonobj2.getString("image");
                                String deselected_image = josonobj2.getString("deselected_image");

                                WhereSetGet ab = new WhereSetGet(whereId, en_name, fr_name, image_status, image, deselected_image,false);
                                AppData.wherelistings.add(ab);


                            }
                            whereAdapter=new WhereAdapter(ActivityNewMeeting.this, AppData.wherelistings,ActivityNewMeeting.this);
                            dialog_recyclerview.setLayoutManager(new GridLayoutManager(ActivityNewMeeting.this,4));
                            dialog_recyclerview.setAdapter(whereAdapter);
//
                        }

                        dialogprog.setVisibility(View.GONE);
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
    public void  positionsapi()
    {


        dialogprogpositions.setVisibility(View.VISIBLE);
        String  urlJsonObj = AppData.url+"/index.php/Signup/fetchpositions?mode="+sharedPreferences.getString("mode", " ");
        Log.d("positionsurl",urlJsonObj);
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, urlJsonObj, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                try {

                    dialogstatus = jsonObject.getBoolean("status");
                    if(dialogstatus==true)
                    {
                        JSONArray countryData = jsonObject.getJSONArray("details");

                        if(countryData.length()>0)
                        {
                            AppData.positionslistings=new ArrayList<PositionsSetGet>();


                            for (int i = 0; i < countryData.length(); i++)
                            {
                                JSONObject josonobj2 = countryData.getJSONObject(i);

                                String positionid = josonobj2.getString("id");
                                String en_name = josonobj2.getString("en_name");
                                String fr_name = josonobj2.getString("fre_name");
                                String image_status = josonobj2.getString("image_status");
                                String image = josonobj2.getString("image");
                                String deselected_image = josonobj2.getString("deselected_image");

                                PositionsSetGet ab = new PositionsSetGet(positionid, en_name, fr_name, image_status, image, deselected_image,false);
                                AppData.positionslistings.add(ab);


                            }
                            positionsAdapter=new PositionsAdapter(ActivityNewMeeting.this, AppData.positionslistings,ActivityNewMeeting.this);
                            dialog_recyclerview_positions.setLayoutManager(new GridLayoutManager(ActivityNewMeeting.this,4));
                            dialog_recyclerview_positions.setAdapter(positionsAdapter);
//
                        }

                        dialogprogpositions.setVisibility(View.GONE);
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
    public void goodsapi()
    {



        dialogproggoods.setVisibility(View.VISIBLE);
        String  urlJsonObj = AppData.url+"/index.php/Signup/fetchgoods?mode="+sharedPreferences.getString("mode", " ");
        Log.d("goodsurl",urlJsonObj);
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, urlJsonObj, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                try {

                    dialogstatus = jsonObject.getBoolean("status");
                    if(dialogstatus==true)
                    {
                        JSONArray countryData = jsonObject.getJSONArray("details");

                        if(countryData.length()>0)
                        {
                            AppData.goodslistings=new ArrayList<GoodsSetGet>();


                            for (int i = 0; i < countryData.length(); i++)
                            {
                                JSONObject josonobj2 = countryData.getJSONObject(i);

                                String positionid = josonobj2.getString("id");
                                String en_name = josonobj2.getString("en_name");
                                String fr_name = josonobj2.getString("fr_name");
                                String image_status = josonobj2.getString("image_status");
                                String image = josonobj2.getString("image");
                                String deselected_image = josonobj2.getString("deselected_image");

                                GoodsSetGet ab = new GoodsSetGet(positionid, en_name, fr_name, image_status, image, deselected_image,false);
                                AppData.goodslistings.add(ab);


                            }
                            goodsAdapter=new GoodsAdapter(ActivityNewMeeting.this, AppData.goodslistings,ActivityNewMeeting.this);
                            dialog_recyclerview_goods.setLayoutManager(new GridLayoutManager(ActivityNewMeeting.this,4));
                            dialog_recyclerview_goods.setAdapter(goodsAdapter);
//
                        }

                        dialogproggoods.setVisibility(View.GONE);
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
    private class CreatemeetingAsync extends AsyncTask<Void, Void, Void>
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

                String url= AppData.url+"/index.php/useraction/create_meeting";


                Log.d("URL",url);
                mBilder.addFormDataPart("user_id",sharedPreferences.getString("user_id", " "));
                mBilder.addFormDataPart("date",AppData.selectdate);
                mBilder.addFormDataPart("time",AppData.selecttime);
                mBilder.addFormDataPart("duration",AppData.duration2);
                mBilder.addFormDataPart("partenersid",partner_ids);
                mBilder.addFormDataPart("location",where_ids);
                mBilder.addFormDataPart("locationtext",AppData.wheretxt);
                mBilder.addFormDataPart("sextype",what_ids);
                mBilder.addFormDataPart("sextypetext",AppData.whattxt);
                mBilder.addFormDataPart("reasons",reason_ids);
                mBilder.addFormDataPart("reasonstext",AppData.reasontxt);
                mBilder.addFormDataPart("goods",goods_ids);
                mBilder.addFormDataPart("goodstext",AppData.goodstxt);
                mBilder.addFormDataPart("positions",position_ids);
                mBilder.addFormDataPart("positionstext",AppData.positiontxt);
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
                message=jobj.getString("message");
//                app_password_status=jobj.getString("password_status");
//                AppData.app_password_status=app_password_status;


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
            if(status==true)
            {
                Intent i=new Intent(ActivityNewMeeting.this,HomePage.class);
                startActivity(i);
                finish();

                Toast.makeText(ActivityNewMeeting.this,message,Toast.LENGTH_SHORT).show();
            }

            else{
                Toast.makeText(ActivityNewMeeting.this,message,Toast.LENGTH_SHORT).show();
            }




//            else if(status1==false)
//                Toast.makeText(Login.this, message, Toast.LENGTH_SHORT).show();
        }
    }
}
