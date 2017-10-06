package com.guidiyam.sexrdv.setget;

import org.json.JSONArray;

/**
 * Created by su on 5/10/17.
 */

public class AllMeetingListSetGet
{
    String start_time, end_time, day, date;
    JSONArray where, partners;


    public AllMeetingListSetGet(String start_time, String end_time, String day, String date, JSONArray where, JSONArray partners) {
        this.start_time = start_time;
        this.end_time = end_time;
        this.day = day;
        this.date = date;
        this.where = where;
        this.partners = partners;
    }

    public String getStart_time() {
        return start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public String getDay() {
        return day;
    }

    public String getDate() {
        return date;
    }

    public JSONArray getWhere() {
        return where;
    }

    public JSONArray getPartners() {
        return partners;
    }
}
