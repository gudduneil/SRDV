package com.guidiyam.sexrdv.fragment;

/**
 * Created by su on 14/7/17.
 */


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

//import com.github.sundeepk.compactcalendarview.CompactCalendarView;
//import com.github.sundeepk.compactcalendarview.CompactCalendarView;
//import com.guidiyam.sexrdv.R;
//import com.stacktips.view.CalendarListener;
//import com.stacktips.view.CustomCalendarView;

//import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.guidiyam.sexrdv.ActivityNewMeeting;
import com.guidiyam.sexrdv.Adapter.FutureMeetingAdapter;
import com.guidiyam.sexrdv.Adapter.RunningMeetingAdapter;
import com.guidiyam.sexrdv.R;
import com.guidiyam.sexrdv.volley.AppData;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class NewMeetingFrag extends Fragment {
    LinearLayout singlemeeting_layout;
    static int yy,mm,dd;
    RecyclerView recyclerview,recyclerview2;
    FutureMeetingAdapter futureMeetingAdapter=null;
    RunningMeetingAdapter runningMeetingAdapter=null;
    RelativeLayout newmeeting;
    ArrayList<String> list;
    ArrayAdapter<String> adp;
    Spinner spinnerdate;
    LinearLayout dt_layout;
    public static TextView txt_year,txt_month;
    public static int year;
    public static int month,day;
    static  String  datemonthtype;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.activity_new_meeting2, container,
                false);
        AppData.page="NewMeetingFrag";
        Log.d("Pageforsevice:",AppData.page);

       // singlemeeting_layout=(LinearLayout)view.findViewById(R.id.singlemeeting_layout);
        dt_layout=(LinearLayout)view.findViewById(R.id.dt_layout);
//        singlemeeting_layout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i=new Intent(getActivity(), ActivityDebrief.class);
//                startActivity(i);
//            }
//        });
        txt_month=(TextView)view.findViewById(R.id.txt_month) ;
        txt_year=(TextView)view.findViewById(R.id.txtyr) ;
       setCurrentMonth();
       setCurrentYear();

        dt_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



//                        DialogFragment dialogfragment = new DatePickerDialogLayout();
//                        dialogfragment.show(getFragmentManager(),"calander");


                DatePickerDialogLayout datePickerDialogLayout = new DatePickerDialogLayout();
               // datePickerDialogLayout.setStyle(DialogFragment.STYLE_NO_TITLE, 0);
                datePickerDialogLayout.show(getFragmentManager(), "datePicker");
//


            }
        });


//        spinnerdate = (Spinner)view.findViewById(R.id.spinnerdate);
//        list = new ArrayList<String>();
//        list.add("January");
//        list.add("February");
//        list.add("March");
//        list.add("April");
//        list.add("May");
//        list.add("June");
//        list.add("July");
//        list.add("August");
//        list.add("September");
//        list.add("October");
//        list.add("November");
//        list.add("December");



//        adp = new ArrayAdapter<String>(getActivity(),
//                R.layout.spinner_item_for_meeting, list);
//        adp.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
//        spinnerdate.setAdapter(adp);

        // prefs = getSharedPreferences(prefName, MODE_PRIVATE);

        //id = prefs.getInt("first_val", 0);
        // sp.setSelection(id);

//        spinnerdate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//
//            @Override
//            public void onItemSelected(AdapterView<?> arg0,
//                                       View arg1, int pos, long arg3) {
//                String imc_met = arg0.getItemAtPosition(pos).toString();
//
//                Log.d("imc_met",imc_met);
////                prefs = getSharedPreferences(prefName, MODE_PRIVATE);
////                SharedPreferences.Editor editor = prefs.edit();
////                //---save the values in the EditText view to preferences---
////                editor.putInt("first_val", pos);
////
////
////                editor.commit();
//
//                //Toast.makeText(getBaseContext(),
//                //	sp.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> arg0) {
//                // TODO Auto-generated method stub
//            }
//        });


        recyclerview=(RecyclerView)view.findViewById(R.id.recyclerview);
        recyclerview2=(RecyclerView)view.findViewById(R.id.recyclerview2);
        newmeeting=(RelativeLayout)view.findViewById(R.id.newmeeting);
        newmeeting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getActivity(),ActivityNewMeeting.class);
                startActivity(i);
            }
        });
        recyclerview.setItemAnimator(new DefaultItemAnimator());
        recyclerview2.setItemAnimator(new DefaultItemAnimator());
//        singlemeeting_layout=(LinearLayout)view.findViewById(R.id.singlemeeting_layout);
//        singlemeeting_layout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i=new Intent(getActivity(), ActivityDebrief.class);
//                startActivity(i);
//            }
//        });

        if(futureMeetingAdapter==null) {
            recyclerview.setHasFixedSize(true);
            recyclerview.setLayoutManager(new GridLayoutManager(getActivity(), 1));
            futureMeetingAdapter = new FutureMeetingAdapter(getActivity());
            recyclerview.setAdapter(futureMeetingAdapter);//set an adapter

        }


        if(runningMeetingAdapter==null) {
            recyclerview2.setHasFixedSize(true);
            recyclerview2.setLayoutManager(new GridLayoutManager(getActivity(), 1));
            runningMeetingAdapter = new RunningMeetingAdapter(getActivity());
            recyclerview2.setAdapter(runningMeetingAdapter);//set an adapter

        }

        return  view;
    }

    public void setCurrentMonth() {



        final Calendar c  = Calendar.getInstance();
        month = c.get(Calendar.MONTH);

        SimpleDateFormat month_date = new SimpleDateFormat("MMMM");
        String monthnum = String.valueOf(month);
        c.set(Calendar.MONTH, (Integer.parseInt(monthnum)));
        String month_name = month_date.format(c.getTime());
        Log.i("mymonth", "" + month_name);
        txt_month.setText(month_name);
        // txt_month.setText(month);

    }

    public void setCurrentYear() {



        final Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        String y=String.valueOf(year);
        Log.d("YEAR", String.valueOf(year));
       txt_year.setText(y);

    }

//    public static class DatePickerDialogLayout extends DialogFragment implements DatePickerDialog.OnDateSetListener
//    {
//
//        Calendar calendar;
//
//
//
//        @Override
//        public Dialog onCreateDialog(Bundle savedInstanceState)
//        {
//            calendar = Calendar.getInstance();
//            year = calendar.get(Calendar.YEAR);
//            month = calendar.get(Calendar.MONTH);
//            day = calendar.get(Calendar.DAY_OF_MONTH);
//
//
//            // Toast.makeText(getActivity(),"Go to onCreateDialog block",Toast.LENGTH_SHORT).show();
//
//            DatePickerDialog datepickerdialog = new DatePickerDialog(getActivity(),
//                    R.style.dialog,this,year,month,day);
//            if(AppData.first_time==false ) {
//                //Toast.makeText(getActivity(),"Go to if if block",Toast.LENGTH_SHORT).show();
//                datepickerdialog.getDatePicker().setMinDate( System.currentTimeMillis());
//                AppData.first_time=true;
//                Log.d("First_time", String.valueOf(AppData.first_time));
//            }
//            else if(AppData.first_time==true) {
//
//                if(AppData.okbuttonclicked==false)
//                {
//                    //Toast.makeText(getActivity(),"Go to if block",Toast.LENGTH_SHORT).show();
//                    datepickerdialog.updateDate(year, month, day);
//                    datepickerdialog.getDatePicker().setMinDate(System.currentTimeMillis());
//                }
//                else  if(AppData.okbuttonclicked==true)
//                {
//                    // Toast.makeText(getActivity(),"Go to else if block",Toast.LENGTH_SHORT).show();
//                    datepickerdialog.updateDate(yy, mm, dd);
//                    datepickerdialog.getDatePicker().setMinDate(System.currentTimeMillis());
//
//
//                }
//            }
//
//
//            return datepickerdialog;
//        }
//
//
//
//        public void onDateSet(DatePicker view, int year, int month, int day)
//        {
//            AppData.okbuttonclicked=true;
//            // Toast.makeText(getActivity(),"Go to OnDataset block",Toast.LENGTH_SHORT).show();
//            SimpleDateFormat month_date = new SimpleDateFormat("MMMM");
//            String monthnum = String.valueOf(month);
//            calendar.set(Calendar.MONTH, (Integer.parseInt(monthnum)));
//            String month_name = month_date.format(calendar.getTime());
//            Log.i("mymonth", "" + month_name);
//            NewMeetingFrag.txt_month.setText(month_name);
//            NewMeetingFrag.txt_year.setText(String.valueOf(year));
//            //txtdt.setText(year + "-" + monthOfYear + "-" + dayOfMonth);
//
//
//            int month_val=month+1;
//            datemonthtype=(year + "-" + month_val+ "-" + day);
//
//            yy=year;
//            mm=month;
//            dd=day;
////
////
////
////            Log.d("datemonthtype", day + " " + month_name + " " + year);
////            txtdt.setTextColor(Color.GRAY);
////
////            Log.d("txtdate", "" + txtdt.getText().toString()+","+datemonthtype);
////            // dob.setText(month+"/"+day+"/"+year);
////
////            //populateSetDate(year, month+1, day);
////
//////
////            String dob=(day + " " + (month+1) + " " + year);
//
//
//            String dob=(day + " " + (month+1) + " " + year);
//            Log.d("DOB::::",dob);
//
//
//
//
//            // onCreateDialog2(999);
//
//        }
//
//
//
//        public void onCancel(DialogInterface dialog)
//        {
//            //Toast.makeText(getActivity(),"Cancel button is pressed",Toast.LENGTH_SHORT).show();
//           // AppData.okbuttonclicked=false;
//            final Calendar calendar = Calendar.getInstance();
//            year = calendar.get(Calendar.YEAR);
//            month = calendar.get(Calendar.MONTH);
//            day = calendar.get(Calendar.DAY_OF_MONTH);
//            Log.d("DAYMONTHYEAR"," "+day+" "+month+" "+year);
//            DatePickerDialog datepickerdialog = new DatePickerDialog(getActivity(),
//                    R.style.dialog,this,year,month,day);
//            datepickerdialog.updateDate(year,month,day);
//            datepickerdialog.getDatePicker().setMaxDate( System.currentTimeMillis());
//
//            dialog.dismiss();
//            //Toast.makeText(getActivity(),"Date Picker Canceled.", Toast.LENGTH_SHORT).show();
//        }
//
//
//    }



    public static class DatePickerDialogLayout extends DialogFragment implements DatePickerDialog.OnDateSetListener
    {

        Calendar calendar;
        int year,month,day;



        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState)
        {

            Dialog dialog = super.onCreateDialog(savedInstanceState);
            calendar = Calendar.getInstance();
            year = calendar.get(Calendar.YEAR);
            month = calendar.get(Calendar.MONTH);
            day = calendar.get(Calendar.DAY_OF_MONTH);


            // Toast.makeText(getActivity(),"Go to onCreateDialog block",Toast.LENGTH_SHORT).show();

            DatePickerDialog datepickerdialog = new DatePickerDialog(getActivity(),
                    R.style.dialog,this,year,month,day);
            dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
            if(AppData.first_time==false ) {
                //Toast.makeText(getActivity(),"Go to if if block",Toast.LENGTH_SHORT).show();
                datepickerdialog.getDatePicker().setMinDate( System.currentTimeMillis());
                AppData.first_time=true;
                Log.d("First_time", String.valueOf(AppData.first_time));
            }
            else if(AppData.first_time==true) {

                if(AppData.okbuttonclicked==false)
                {
                    //Toast.makeText(getActivity(),"Go to if block",Toast.LENGTH_SHORT).show();
                    datepickerdialog.updateDate(year, month, day);
                    datepickerdialog.getDatePicker().setMinDate(System.currentTimeMillis());
                }
                else  if(AppData.okbuttonclicked==true)
                {
                    // Toast.makeText(getActivity(),"Go to else if block",Toast.LENGTH_SHORT).show();
                    datepickerdialog.updateDate(yy, mm, dd);
                    datepickerdialog.getDatePicker().setMinDate(System.currentTimeMillis());


                }
            }


            return datepickerdialog;
        }



        public void onDateSet(DatePicker view, int year, int month, int day)
        {
            AppData.okbuttonclicked=true;
            // Toast.makeText(getActivity(),"Go to OnDataset block",Toast.LENGTH_SHORT).show();
            SimpleDateFormat month_date = new SimpleDateFormat("MMMM");
            String monthnum = String.valueOf(month);
            calendar.set(Calendar.MONTH, (Integer.parseInt(monthnum)));
            String month_name = month_date.format(calendar.getTime());
            Log.i("mymonth", "" + month_name);
            NewMeetingFrag.txt_month.setText(month_name);
            NewMeetingFrag.txt_year.setText(String.valueOf(year));
            //txtdt.setText(year + "-" + monthOfYear + "-" + dayOfMonth);


            int month_val=month+1;
            datemonthtype=(year + "-" + month_val+ "-" + day);

            yy=year;
            mm=month;
            dd=day;
//
//
//
//            Log.d("datemonthtype", day + " " + month_name + " " + year);
//            txtdt.setTextColor(Color.GRAY);
//
//            Log.d("txtdate", "" + txtdt.getText().toString()+","+datemonthtype);
//            // dob.setText(month+"/"+day+"/"+year);
//
//            //populateSetDate(year, month+1, day);
//
////
//            String dob=(day + " " + (month+1) + " " + year);


            String dob=(day + " " + (month+1) + " " + year);
            Log.d("DOB::::",dob);



        }



        public void onCancel(DialogInterface dialog)
        {

            //Toast.makeText(getActivity(),"Cancel button is pressed",Toast.LENGTH_SHORT).show();
            // AppData.okbuttonclicked=false;
            final Calendar calendar = Calendar.getInstance();
            year = calendar.get(Calendar.YEAR);
            month = calendar.get(Calendar.MONTH);
            day = calendar.get(Calendar.DAY_OF_MONTH);
            Log.d("DAYMONTHYEAR"," "+day+" "+month+" "+year);
            DatePickerDialog datepickerdialog = new DatePickerDialog(getActivity(),
                    R.style.dialog,this,year,month,day);
            datepickerdialog.updateDate(year,month,day);
            datepickerdialog.getDatePicker().setMinDate( System.currentTimeMillis());

            dialog.dismiss();
            //Toast.makeText(getActivity(),"Date Picker Canceled.", Toast.LENGTH_SHORT).show();
        }


    }
    }



