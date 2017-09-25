package com.guidiyam.sexrdv;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.guidiyam.sexrdv.Adapter.OptionAdapter;
import com.guidiyam.sexrdv.Adapter.PartnerPlaceAdapter;
import com.guidiyam.sexrdv.Adapter.PartnerWantingAdapter;
import com.guidiyam.sexrdv.Adapter.Partneradapter;
import com.guidiyam.sexrdv.Adapter.PartneradapterResumeMeeting;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class ResumeMeeting extends AppCompatActivity {
    SeekBar bar;
    LinearLayout close;
    RecyclerView recyclerView,recyclerView2,recyclerView3;
    LinearLayoutManager layoutManager2,layoutManager3,layoutManager4;
    PartneradapterResumeMeeting partneradapter=null;
    PartnerPlaceAdapter partnerPlaceAdapter=null;
    PartnerWantingAdapter partnerWanting=null;
    TextView hour1,hour2,hour3,hour4,hour5,hour6,hour7,hour8,hour9,hour10,hour11,hour12;
    TextView time1,time2,time3,time4,time5,time6,time7,time8,time9,time10,time11,time12;

    List<String> date_list,time_list;
    Spinner spinner_date,spinner_time;

    static int y=0,m=0,d=0,dd=0;

    static boolean first_time=true;
    RelativeLayout select_date,select_time;
    static  TextView date_show,time_show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resume_meeting);
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




        ////////////////////////////////////////////////////

        bar= (SeekBar) findViewById(R.id.seekbar);
        bar.setProgress(0);
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
        recyclerView=(RecyclerView)findViewById(R.id.recyclerView);
        recyclerView2=(RecyclerView)findViewById(R.id.recyclerView2);
        recyclerView3=(RecyclerView)findViewById(R.id.recyclerView3);
        layoutManager2 = new LinearLayoutManager(ResumeMeeting.this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager2);
        layoutManager3 = new LinearLayoutManager(ResumeMeeting.this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView2.setLayoutManager(layoutManager3);


        layoutManager4 = new LinearLayoutManager(ResumeMeeting.this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView3.setLayoutManager(layoutManager4);

        close=(LinearLayout)findViewById(R.id.menu);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.d("PROGRESS", String.valueOf(progress));
                if (fromUser == true) {
                    seekBar.setProgress(progress);

                    if (progress == 0) {
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

        if(partneradapter==null) {
//                allmeeting_adapter = new Allmeeting_adapter(getActivity());
//                rv_notification.setAdapter(allmeeting_adapter);


            // activityadapter = new ActivityAdpater(getActivity(), activitySetGetsLinkedList);
            partneradapter = new PartneradapterResumeMeeting(ResumeMeeting.this);
            recyclerView.setAdapter(partneradapter);//set an adapter

        }

        if(partnerPlaceAdapter==null)
        {
            partnerPlaceAdapter = new PartnerPlaceAdapter(ResumeMeeting.this,"ResumeMeeting");
            //partnerPlaceAdapter = new PartnerPlaceAdapter(ResumeMeeting.this);
            recyclerView2.setAdapter(partnerPlaceAdapter);//set an adapter


        }
        if(partnerWanting==null)
        {
            partnerWanting = new PartnerWantingAdapter(ResumeMeeting.this,"ResumeMeeting");
           // partnerPlaceAdapter = new PartnerPlaceAdapter(ResumeMeeting.this);
            recyclerView3.setAdapter(partnerWanting);//set an adapter


        }

        select_date= (RelativeLayout) findViewById(R.id.select_date);
        date_show= (TextView) findViewById(R.id.date_show);

        select_time= (RelativeLayout) findViewById(R.id.select_time);
        time_show= (TextView) findViewById(R.id.time_show);


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
                m=month;
                d=day;

                first_time=false;

                Log.d("Date----------------","Fist Time");
            }else {

                datepickerdialog.updateDate(y,m,d);

                Log.d("Date----------------","Not Fist Time");


            }

            return datepickerdialog;
        }


        public void onDateSet(DatePicker view, int year, int month, int day)
        {

            y=year;
            m=month;
            d=day;

            String weekDay;
            SimpleDateFormat dayFormat = new SimpleDateFormat("EEE", Locale.US);

            Calendar calendar = Calendar.getInstance();
            calendar.set(y,m,d);
            weekDay = dayFormat.format(calendar.getTime());


            Log.d("Day","----------"+weekDay);



            String date = weekDay+", "+String.valueOf(day)  +"."+String.valueOf(month)
                    +"."+String.valueOf(year);
            date_show.setText(date);

        }


        public void onCancel(DialogInterface dialog)
        {
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

            time_show.setText(hourOfDay+":"+minute);
        }
    }

    public void addDialog(String top_name){

        OptionAdapter optionAdapter;

        final Dialog dialog=new Dialog(this,android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.add_dialog_layout);
        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);

        TextView header_name= (TextView) dialog.findViewById(R.id.header_name);
        header_name.setText(top_name);
        ImageView back= (ImageView) dialog.findViewById(R.id.back);
        RecyclerView rv_option= (RecyclerView) dialog.findViewById(R.id.rv_option);
        rv_option.setHasFixedSize(true);

        optionAdapter=new OptionAdapter(ResumeMeeting.this);
        rv_option.setLayoutManager(new GridLayoutManager(ResumeMeeting.this,4));
        rv_option.setAdapter(optionAdapter);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });


        dialog.show();

    }
}
