package com.guidiyam.sexrdv.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.guidiyam.sexrdv.ActivityDebrief;
import com.guidiyam.sexrdv.R;

/**
 * Created by su on 21/8/17.
 */

public class RunningMeetingAdapter extends RecyclerView.Adapter<RunningMeetingAdapter.ViewHolder>
{


    Activity activity;
    CAdapter2 cAdapter2;
    Context context;


    public LinearLayoutManager lln;
    public RunningMeetingAdapter(Activity activity) {
        this.activity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.running_meeting_content, parent,false);
        context=parent.getContext();

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        int pos=position;
        holder.singlemeeting_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ActivityDebrief.class);
                v.getContext().startActivity(intent);
            }
        });

        if(pos!=0){

           holder.tick.setVisibility(View.INVISIBLE);
            holder.dot.setVisibility(View.INVISIBLE);
        }
        else {


            holder.tick.setVisibility(View.VISIBLE);
        }
//        if(position!=0)
//        {
//
//
//            holder.from_time.setTextColor(Color.parseColor("#F80D6B"));
//            holder.day.setTextColor(Color.parseColor("#FFFFFF"));
//            holder.name.setTextColor(Color.parseColor("#FFFFFF"));
//            holder.address.setTextColor(Color.parseColor("#FFFFFF"));
//        }
        lln = new LinearLayoutManager(context);

        lln.setOrientation(LinearLayoutManager.HORIZONTAL);
        holder.recyclerView .setHasFixedSize(true);
        holder.recyclerView.setLayoutManager(lln);
        cAdapter2 = new CAdapter2( context);
        holder.recyclerView.setAdapter(cAdapter2);


    }

    @Override
    public int getItemCount() {
        return 6;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

//        ImageView image,iv_status;
//        TextView category_name,text,date;

        RecyclerView recyclerView;
        LinearLayout singlemeeting_layout;
        ImageView tick,dot;
        TextView from_time,day,name,address;

        public ViewHolder(View itemView) {
            super(itemView);
            recyclerView=(RecyclerView)itemView.findViewById(R.id.recyclerView);
            tick=(ImageView)itemView.findViewById(R.id.tick);
            dot=(ImageView)itemView.findViewById(R.id.dot);
            singlemeeting_layout=(LinearLayout)itemView.findViewById(R.id.singlemeeting_layout);
            from_time=(TextView)itemView.findViewById(R.id.from_time);
            day=(TextView)itemView.findViewById(R.id.day);
            name=(TextView)itemView.findViewById(R.id.name);
            address=(TextView)itemView.findViewById(R.id.adresss);
            //            image= (ImageView) itemView.findViewById(R.id.image);
//            iv_status= (ImageView) itemView.findViewById(R.id.iv_status);
//            category_name= (TextView) itemView.findViewById(R.id.category_name);
//            text= (TextView) itemView.findViewById(R.id.text);
//            date= (TextView) itemView.findViewById(R.id.date);

        }
    }
}
