package com.guidiyam.sexrdv.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

//import com.github.sundeepk.compactcalendarview.CompactCalendarView;
//import com.github.sundeepk.compactcalendarview.CompactCalendarView;
//import com.guidiyam.sexrdv.R;
//import com.stacktips.view.CalendarListener;
//import com.stacktips.view.CustomCalendarView;

//import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.guidiyam.sexrdv.ActivityNewMeeting;
import com.guidiyam.sexrdv.Activitymeeting;
import com.guidiyam.sexrdv.Adapter.Allmeeting_adapter;
import com.guidiyam.sexrdv.Adapter.ShowAllListedMeetingAdapter;
import com.guidiyam.sexrdv.R;
import com.guidiyam.sexrdv.ResumeMeeting;
import com.guidiyam.sexrdv.volley.AppData;
import com.stacktips.view.CalendarListener;
import com.stacktips.view.CustomCalendarView;

import org.json.JSONArray;
import org.json.JSONException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;



//import com.imanoweb.calendarview.CustomCalendarView;
//import com.imanoweb.calendarview.DayDecorator;
//import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
//import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
//import com.imanoweb.calendarview.CalendarListener;
//import com.imanoweb.calendarview.CustomCalendarView;
//import com.imanoweb.calendarview.DayDecorator;
//import com.imanoweb.calendarview.DayView;
//import sexrdv.guidiyam.com.srdv.helper.CurrentDayDecorator;

/**
 * Created by su on 30/6/17.
 */

public class FirstPage_Frag extends Fragment {
   // CustomCalendarView calendarView;
    GridView calendarView;
    String currentMonthName;
    Calendar currentCalendar;
    private static final String dateTemplate = "MMM yyyy";
    ImageView newmeeting;
    LinearLayout Resume_ll,showtotal_layout;
    TextView showalltxtview;
    RecyclerView recyclerview;
    ShowAllListedMeetingAdapter showAllListedMeetingAdapter=null;
    boolean first_time=false;
    private Calendar _calendar;
     TextView currentMonth;
    private Button selectedDayMonthYearButton;
     ImageView prevMonth;
     ImageView nextMonth;
    Locale locale;
    private static final int MAX_CALENDAR_COLUMN = 42;
     int month, year;
     GridCellAdapter adapter;
    //CompactCalendarView calendarView;

   // MaterialCalendarView calendarView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.frag_homepage, container,
                false);
        AppData.page="FirstPage_Frag";
        Log.d("Pageforsevice:",AppData.page);
       // AppData.isServiceCompleted=false;
        showtotal_layout=(LinearLayout)view.findViewById(R.id.showtotal_layout);
        showtotal_layout.setVisibility(View.GONE);
        showalltxtview=(TextView)view.findViewById(R.id.showalltxtview);
        showalltxtview.setText("Show all 10 meetings");
        recyclerview=(RecyclerView)view.findViewById(R.id.recyclerviewforlist);
        showalltxtview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(first_time==false) {
                    showalltxtview.setText("Hide all 10 meetings");

                    first_time=true;
                    showtotal_layout.setVisibility(View.VISIBLE);


                    recyclerview.setItemAnimator(new DefaultItemAnimator());

                    if (showAllListedMeetingAdapter == null) {
//                allmeeting_adapter = new Allmeeting_adapter(getActivity());
//                rv_notification.setAdapter(allmeeting_adapter);

                        recyclerview.setHasFixedSize(true);
                        recyclerview.setLayoutManager(new GridLayoutManager(getActivity(), 1));
                        // activityadapter = new ActivityAdpater(getActivity(), activitySetGetsLinkedList);
                        showAllListedMeetingAdapter = new ShowAllListedMeetingAdapter(getActivity());
                        recyclerview.setAdapter(showAllListedMeetingAdapter);//set an adapter

                    }

                }
                else if(first_time==true)
                {
                    showalltxtview.setText("Show all 10 meetings");
                    first_time=false;
                    showtotal_layout.setVisibility(View.GONE);


                }
            }
        });
        newmeeting=(ImageView)view.findViewById(R.id.newmeeting);
        Resume_ll=(LinearLayout)view.findViewById(R.id.Resume_ll);
        Resume_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getActivity(),ResumeMeeting.class);
                startActivity(i);
            }
        });
        newmeeting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    AppData.partnerjsonarray=new JSONArray("[]");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Intent i=new Intent(getActivity(),ActivityNewMeeting.class);
                startActivity(i);
            }
        });


        _calendar = Calendar.getInstance(Locale.getDefault());
        month = _calendar.get(Calendar.MONTH) + 1;
        year = _calendar.get(Calendar.YEAR);


        locale = getActivity().getResources().getConfiguration().locale;
        Log.d("LOCale", String.valueOf(locale));
        Calendar currentCalendar = Calendar.getInstance(Locale.getDefault());

        prevMonth = (ImageView) view.findViewById(R.id.prevMonth);
       prevMonth.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if (month <= 1) {
                   month = 12;
                   year--;
               } else {
                   month--;
               }

               setGridCellAdapterToDate(month, year);
           }
       });

        currentMonth = (TextView) view.findViewById(R.id.currentMonth);





        Log.d("DATE::::", (String) DateFormat.format(dateTemplate,
                _calendar.getTime()));
        String CurrentString =(String) DateFormat.format(dateTemplate,
                _calendar.getTime());
        String[] separated = CurrentString.split(" ");
        String month2=separated[0]; // this will contain "Fruit"
        if(month2.equals("Jan"))
        {
            month2="JAN";
        }
        else if(month2.equals("Feb"))
        {

            month2="FEB";
        }

        else if(month2.equals("Mar"))
        {

            month2="MAR";
        }
        else if(month2.equals("Apr"))
        {

            month2="APR";
        }
        else if(month2.equals("May"))
        {

            month2="MAY";
        }
        else if(month2.equals("Jun"))
        {

            month2="JUN";
        }
        else if(month2.equals("Jul"))
        {

            month2="JUL";
        }

        else if(month2.equals("Aug"))
        {

            month2="AUG";
        }
        else if(month2.equals("Sep"))
        {

            month2="SEP";
        }
        else if(month2.equals("Oct"))
        {

            month2="OCT";
        }
        else if(month2.equals("Nov"))
        {

            month2="NOV";
        }
        else if(month2.equals("Dec"))
        {

            month2="DEC";
        }
        String year2=separated[1];

        currentMonth.setText(month2+" "+year2);

        nextMonth = (ImageView) view.findViewById(R.id.nextMonth);
        nextMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (month > 11) {
                    month = 1;
                    year++;
                } else {
                    month++;
                }
                setGridCellAdapterToDate(month, year);
            }
        });

        calendarView = (GridView) view.findViewById(R.id.calendar_view);
//        calendarView = (CustomCalendarView)view. findViewById(R.id.calendar_view);
//
//
//         currentCalendar = Calendar.getInstance(Locale.getDefault());
//
//
//        calendarView.setShowOverflowDate(true);
//
//
//        calendarView.setCalendarListener(new CalendarListener() {
//            @Override
//            public void onDateSelected(Date date) {
//                SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
//                Toast.makeText(getActivity(), df.format(date), Toast.LENGTH_SHORT).show();
//                Intent i=new Intent(getActivity(), Activitymeeting.class);
//                startActivity(i);
//
////                List<DayDecorator> decorators = new ArrayList<>();
////                decorators.add(new DisabledColorDecorator());
////                calendarView.setDecorators(decorators);
////                calendarView.refreshCalendar(currentCalendar);
//               // calendarView.setBackgroundResource(R.drawable.ring);
//            }
//
//            @Override
//            public void onMonthChanged(Date date) {
//                SimpleDateFormat df = new SimpleDateFormat("MM-yyyy");
//                Toast.makeText(getActivity(), df.format(date), Toast.LENGTH_SHORT).show();
//            }
//        });


// Initialised

//        calendarView.setHasFixedSize(true);
//        calendarView.setLayoutManager(new GridLayoutManager(getActivity(), 7));
        adapter = new GridCellAdapter(getActivity(),
                R.id.calendar_day_gridcell, month, year);
        adapter.notifyDataSetChanged();
        calendarView.setAdapter(adapter);
        return view;
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
    }
    /**
     * @param month
     * @param year
     */
    private void setGridCellAdapterToDate(int month, int year) {

///////////////////////////////////////////////////////////////////////
//        List<Date> dayValueInCells = new ArrayList<Date>();
//        mQuery = new DatabaseQuery(MainActivity.this);
//        List<EventObjects> mEvents = mQuery.getAllFutureEvents();
//        Calendar mCal = (Calendar)cal.clone();
//        mCal.set(Calendar.DAY_OF_MONTH, 1);
//        int firstDayOfTheMonth = mCal.get(Calendar.DAY_OF_WEEK) - 1;
//        mCal.add(Calendar.DAY_OF_MONTH, -firstDayOfTheMonth);
//        while(dayValueInCells.size() < MAX_CALENDAR_COLUMN){
//            dayValueInCells.add(mCal.getTime());
//            mCal.add(Calendar.DAY_OF_MONTH, 1);
//        }




        ///////////////////////////////////////////
        adapter = new GridCellAdapter(getActivity(),
                R.id.calendar_day_gridcell, month, year);
        _calendar.set(year, month -1, _calendar.get(Calendar.DAY_OF_MONTH));
//        currentMonth.setText(DateFormat.format(dateTemplate,
//                _calendar.getTime()));


        Log.d("DATE::::", (String) DateFormat.format(dateTemplate,
                _calendar.getTime()));
        String CurrentString =(String) DateFormat.format(dateTemplate,
                _calendar.getTime());
        String[] separated = CurrentString.split(" ");
        String month2=separated[0]; // this will contain "Fruit"
        if(month2.equals("Jan"))
        {
            month2="JAN";
        }
        else if(month2.equals("Feb"))
        {

            month2="FEB";
        }

        else if(month2.equals("Mar"))
        {

            month2="MAR";
        }
        else if(month2.equals("Apr"))
        {

            month2="APR";
        }
        else if(month2.equals("May"))
        {

            month2="MAY";
        }
        else if(month2.equals("Jun"))
        {

            month2="JUN";
        }
        else if(month2.equals("Jul"))
        {

            month2="JUL";
        }

        else if(month2.equals("Aug"))
        {

            month2="AUG";
        }
        else if(month2.equals("Sep"))
        {

            month2="SEP";
        }
        else if(month2.equals("Oct"))
        {

            month2="OCT";
        }
        else if(month2.equals("Nov"))
        {

            month2="NOV";
        }
        else if(month2.equals("Dec"))
        {

            month2="DEC";
        }
        String year2=separated[1];

        currentMonth.setText(month2+" "+year2);
        adapter.notifyDataSetChanged();
        calendarView.setAdapter(adapter);
    }

    public class GridCellAdapter extends BaseAdapter implements View.OnClickListener
    {
        HashMap<String ,Integer> map;
        private List<Date> monthlyDates;
        //private List<EventObjects> allEvents;
        private static final String tag = "GridCellAdapter";
        private final Context _context;

        private final List<String> list;
        private static final int DAY_OFFSET = 1;
        private final String[] weekdays = new String[]{"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
        private final String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        private final int[] daysOfMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        private final int month, year;
        private int daysInMonth, prevMonthDays;
        private int currentDayOfMonth;
        private int currentWeekDay;
        private Button gridcell;
        private TextView num_events_per_day;
        private final HashMap eventsPerMonthMap;
        private final SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MMM-yyyy");

        private final  SimpleDateFormat dateCreated= new SimpleDateFormat("dd-MMM-yyyy");

        public  class ViewHolder extends RecyclerView.ViewHolder {


            public ViewHolder(View layout) {
                super(layout);

                //time=(TextView)layout.findViewById(R.id.time);
            }
        }

        // Days in Current Month
        public GridCellAdapter(Context context, int textViewResourceId, int month, int year)
        {
            super();
            this._context = context;
            this.list = new ArrayList<String>();
            this.month = month;
            this.year = year;



            Log.d(tag, "==> Passed in Date FOR Month: " + month + " " + "Year: " + year);
            Calendar calendar = Calendar.getInstance();
            setCurrentDayOfMonth(calendar.get(Calendar.DAY_OF_MONTH));
            setCurrentWeekDay(calendar.get(Calendar.DAY_OF_WEEK));
            Log.d(tag, "New Calendar:= " + calendar.getTime().toString());
            Log.d(tag, "CurrentDayOfWeek :" + getCurrentWeekDay());
            Log.d(tag, "CurrentDayOfMonth :" + getCurrentDayOfMonth());

            // Print Month
            printMonth(this.month, this.year);

            // Find Number of Events
            eventsPerMonthMap = findNumberOfEventsPerMonth(this.month, this.year);

            Log.d("eventsPerMonthMap", String.valueOf(eventsPerMonthMap));
        }
        private String getMonthAsString(int i)
        {
            return months[i];
        }

        private String getWeekDayAsString(int i)
        {
            return weekdays[i];
        }

        private int getNumberOfDaysOfMonth(int i)
        {
            return daysOfMonth[i];
        }

        public String getItem(int position)
        {
            return list.get(position);
        }

        @Override
        public int getCount()
        {
            return list.size();
        }

        /**
         * Prints Month
         *
         * @param mm
         * @param yy
         */
        private void printMonth(int mm, int yy)
        {
            Log.d(tag, "==> printMonth: mm: " + mm + " " + "yy: " + yy);
            // The number of days to leave blank at
            // the start of this month.
            int trailingSpaces = 0;
            int leadSpaces = 0;
            int daysInPrevMonth = 0;
            int prevMonth = 0;
            int prevYear = 0;
            int nextMonth = 0;
            int nextYear = 0;

            int currentMonth = mm - 1;
            currentMonthName = getMonthAsString(currentMonth);
            Log.d("currentMonthName",currentMonthName);
            daysInMonth = getNumberOfDaysOfMonth(currentMonth);
            Log.d("DaysInMonth", String.valueOf(daysInMonth));

            Log.d(tag, "Current Month: " + " " + currentMonthName + " having " + daysInMonth + " days.");

            // Gregorian Calendar : MINUS 1, set to FIRST OF MONTH
            GregorianCalendar cal = new GregorianCalendar(yy, currentMonth, 1);
            Log.d(tag, "Gregorian Calendar:= " + cal.getTime().toString());

            if (currentMonth == 11)
            {
                prevMonth = currentMonth - 1;
                daysInPrevMonth = getNumberOfDaysOfMonth(prevMonth);
                nextMonth = 0;
                prevYear = yy;
                nextYear = yy + 1;
                Log.d(tag, "*->PrevYear: " + prevYear + " PrevMonth:" + prevMonth + " NextMonth: " + nextMonth + " NextYear: " + nextYear);
            }
            else if (currentMonth == 0)
            {
                prevMonth = 11;
                prevYear = yy - 1;
                nextYear = yy;
                daysInPrevMonth = getNumberOfDaysOfMonth(prevMonth);
                nextMonth = 1;
                Log.d(tag, "**--> PrevYear: " + prevYear + " PrevMonth:" + prevMonth + " NextMonth: " + nextMonth + " NextYear: " + nextYear);
            }
            else
            {
                prevMonth = currentMonth - 1;
                nextMonth = currentMonth + 1;
                nextYear = yy;
                prevYear = yy;
                daysInPrevMonth = getNumberOfDaysOfMonth(prevMonth);
                Log.d(tag, "***---> PrevYear: " + prevYear + " PrevMonth:" + prevMonth + " NextMonth: " + nextMonth + " NextYear: " + nextYear);
            }

            // Compute how much to leave before before the first day of the
            // month.
            // getDay() returns 0 for Sunday.
            int currentWeekDay = cal.get(Calendar.DAY_OF_WEEK) - 1;
            trailingSpaces = currentWeekDay;

            Log.d(tag, "Week Day:" + currentWeekDay + " is " + getWeekDayAsString(currentWeekDay));
            Log.d(tag, "No. Trailing space to Add: " + trailingSpaces);
            Log.d(tag, "No. of Days in Previous Month: " + daysInPrevMonth);

            if (cal.isLeapYear(cal.get(Calendar.YEAR)) && mm == 1)
            {
                ++daysInMonth;
            }

            // Trailing Month days
            for (int i = 0; i < trailingSpaces; i++)
            {
                Log.d(tag, "PREV MONTH:= " + prevMonth + " => " + getMonthAsString(prevMonth) + " " + String.valueOf((daysInPrevMonth - trailingSpaces + DAY_OFFSET) + i));
                list.add(String.valueOf((daysInPrevMonth - trailingSpaces + DAY_OFFSET) + i) + "-GREY" + "-" + getMonthAsString(prevMonth) + "-" + prevYear);
            }

            // Current Month Days
            for (int i = 1; i <= daysInMonth; i++)
            {
                Log.d(currentMonthName, String.valueOf(i) + " " + getMonthAsString(currentMonth) + " " + yy);
                if (i == getCurrentDayOfMonth())
                {
                    list.add(String.valueOf(i) + "-BLUE" + "-" + getMonthAsString(currentMonth) + "-" + yy);
                }
                else
                {
                    list.add(String.valueOf(i) + "-WHITE" + "-" + getMonthAsString(currentMonth) + "-" + yy);
                }
            }

            // Leading Month days
            for (int i = 0; i < list.size() % 7; i++)
            {
                Log.d(tag, "NEXT MONTH:= " + getMonthAsString(nextMonth));
                list.add(String.valueOf(i + 1) + "-GREY" + "-" + getMonthAsString(nextMonth) + "-" + nextYear);
            }
        }

        /**
         * NOTE: YOU NEED TO IMPLEMENT THIS PART Given the YEAR, MONTH, retrieve
         * ALL entries from a SQLite database for that month. Iterate over the
         * List of All entries, and get the dateCreated, which is converted into
         * day.
         *
         * @param year
         * @param month
         * @return
         */
        public HashMap findNumberOfEventsPerMonth(int year, int month)
        {
            map = new HashMap<String, Integer>();
//             DateFormat dateFormatter2 = new DateFormat();
//            String CurrentString =(String) DateFormat.format(dateTemplate,
//                    _calendar.getTime());
//            // String day = dateFormatter2.format("dd", dateFormatter).toString();
//            String day=(String)DateFormat.format( dateTemplate2,
//                    _calendar.getTime());
//             if (map.containsKey(day))
//             {
//             Integer val = (Integer) map.get(day) + 1;
//             map.put(day, val);
//             }
//             else
//             {
//             map.put(day, 1);
//             }
            return map;
        }



        @Override
        public long getItemId(int position)
        {
            return position;
        }



        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {



//            Date mDate = monthlyDates.get(position);
//            Calendar dateCal = Calendar.getInstance();
//            dateCal.setTime(mDate);
//            int dayValue = dateCal.get(Calendar.DAY_OF_MONTH);
//            int displayMonth = dateCal.get(Calendar.MONTH) + 1;
//            int displayYear = dateCal.get(Calendar.YEAR);
//           // int currentMonth = currentDate.get(Calendar.MONTH) + 1;
//            //int currentYear = currentDate.get(Calendar.YEAR);





            View row = convertView;
            if (row == null)
            {
                LayoutInflater inflater = (LayoutInflater) _context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                row = inflater.inflate(R.layout.day_gridcell, parent, false);
            }

            // Get a reference to the Day gridcell
            gridcell = (Button) row.findViewById(R.id.calendar_day_gridcell);
            gridcell.setOnClickListener(this);


            // ACCOUNT FOR SPACING

            Log.d(tag, "Current Day: " + getCurrentDayOfMonth());
            String[] day_color = list.get(position).split("-");
            String theday = day_color[0];
            String themonth = day_color[2];
            String theyear = day_color[3];



            Log.d("theDay",theday);

            gridcell.setText(theday);
            gridcell.setTag(theday + "-" + themonth + "-" + theyear);
            Log.d(tag, "Setting GridCell " + theday + "-" + themonth + "-" + theyear);

            if (day_color[1].equals("GREY"))
            {
                gridcell.setTextColor(Color.parseColor("#757996"));
            }
            if (day_color[1].equals("WHITE"))
            {
                gridcell.setTextColor(Color.WHITE);
            }
            if (day_color[1].equals("BLUE"))
            {
                //num_events_per_day.setBackgroundResource(R.drawable.ring);
                //gridcell.setTextColor(getResources().getColor(R.color.static_text_color));
                //gridcell.setBackgroundResource(R.drawable.ring);
                gridcell.setBackground( getResources().getDrawable(R.drawable.ring));
            }
            if(currentMonthName.equals("January")) {
                if (theday.equals("24") || theday.equals("20") || theday.equals("9") || theday.equals("10") || theday.equals("5")) {


                    gridcell.setTextColor(Color.parseColor("#FF4081"));
                }
            }
            if(currentMonthName.equals("February"))
            {

                if ( theday.equals("5") )
                {


                    gridcell.setTextColor(Color.parseColor("#FF4081"));
                }




            }
            if(currentMonthName.equals("March"))
            {

                if (theday.equals("11") || theday.equals("13") )
                {


                    gridcell.setTextColor(Color.parseColor("#FF4081"));
                }

            }


            if(currentMonthName.equals("April"))
            {

                if (theday.equals("17") || theday.equals("13") )
                {


                    gridcell.setTextColor(Color.parseColor("#FF4081"));
                }

            }
            if(currentMonthName.equals("May"))
            {

                if (theday.equals("23") || theday.equals("13") )
                {


                    gridcell.setTextColor(Color.parseColor("#FF4081"));
                }

            }

            if(currentMonthName.equals("June"))
            {

                if (theday.equals("3") || theday.equals("13") )
                {


                    gridcell.setTextColor(Color.parseColor("#FF4081"));
                }

            }


            if(currentMonthName.equals("July"))
            {

                if ( theday.equals("13") )
                {


                    gridcell.setTextColor(Color.parseColor("#FF4081"));
                }

            }

            if(currentMonthName.equals("August"))
            {

                if (theday.equals("9") || theday.equals("13") )
                {


                    gridcell.setTextColor(Color.parseColor("#FF4081"));
                }

            }


            if(currentMonthName.equals("September"))
            {

                if (theday.equals("23") || theday.equals("13") )
                {


                    gridcell.setTextColor(Color.parseColor("#FF4081"));
                }

            }


            if(currentMonthName.equals("October"))
            {

                if (theday.equals("5") || theday.equals("13") )
                {


                    gridcell.setTextColor(Color.parseColor("#FF4081"));
                }

            }



            if(currentMonthName.equals("November"))
            {

                if (theday.equals("5") || theday.equals("13") )
                {


                    gridcell.setTextColor(Color.parseColor("#FF4081"));
                }

            }


            if(currentMonthName.equals("December"))
            {

                if (theday.equals("7") || theday.equals("13") )
                {


                    gridcell.setTextColor(Color.parseColor("#FF4081"));
                }

            }
            /////////////////////////////////////
//            gridcell.setText(theday);
//            gridcell.setTag(theday + "-" + themonth + "-" + theyear);
//            Log.d(tag, "Setting GridCell " + theday + "-" + themonth + "-" + theyear);
//
//            if (day_color[1].equals("GREY"))
//            {
//                gridcell.setTextColor(Color.parseColor("#757996"));
//            }
//            if (day_color[1].equals("WHITE"))
//            {
//                gridcell.setTextColor(Color.WHITE);
//            }
//            if (day_color[1].equals("BLUE"))
//            {
//                gridcell.setTextColor(getResources().getColor(R.color.static_text_color));
//               //gridcell.setBackgroundResource(R.drawable.ring);
//            }
            return row;
        }
        @Override
        public void onClick(View view)
        {
            Date date=new Date();
//            String date_month_year = (String) view.getTag();
//            Toast.makeText(getActivity(),date_month_year,Toast.LENGTH_SHORT).show();
            //   selectedDayMonthYearButton.setText("Selected: " + date_month_year);

            SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
                Toast.makeText(getActivity(), df.format(date), Toast.LENGTH_SHORT).show();
                Intent i=new Intent(getActivity(), Activitymeeting.class);
                startActivity(i);

//            try
//            {
//                Date parsedDate = dateFormatter.parse(date_month_year);
//                Log.d(tag, "Parsed Date: " + parsedDate.toString());
//
//            }
//            catch (ParseException e)
//            {
//                e.printStackTrace();
//            }
        }

        public int getCurrentDayOfMonth()
        {
            return currentDayOfMonth;
        }

        private void setCurrentDayOfMonth(int currentDayOfMonth)
        {
            this.currentDayOfMonth = currentDayOfMonth;
        }
        public void setCurrentWeekDay(int currentWeekDay)
        {
            this.currentWeekDay = currentWeekDay;
        }
        public int getCurrentWeekDay()
        {
            return currentWeekDay;
        }
    }
    }


//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // handle arrow click here
//        if (item.getItemId() == android.R.id.home) {
//            finish(); // close this activity and return to preview activity (if there is any)
//        }
//        return super.onOptionsItemSelected(item);
//    }


