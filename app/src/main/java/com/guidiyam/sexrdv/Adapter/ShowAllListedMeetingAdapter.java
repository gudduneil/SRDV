package com.guidiyam.sexrdv.Adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.guidiyam.sexrdv.R;
import com.guidiyam.sexrdv.setget.AllMeetingListSetGet;

import org.json.JSONException;

import java.util.ArrayList;

/**
 * Created by su on 29/7/17.
 */

public class ShowAllListedMeetingAdapter extends RecyclerView.Adapter<ShowAllListedMeetingAdapter.ViewHolder> {
    Activity activity;
    ArrayList<AllMeetingListSetGet> arrayList;
    public ShowAllListedMeetingAdapter(Activity activity, ArrayList<AllMeetingListSetGet> arrayList) {

        this.activity = activity;
        this.arrayList=arrayList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.meeting_list, parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder,final int position)
    {
        holder.start_time.setText(arrayList.get(position).getStart_time());
        holder.end_time.setText("- "+arrayList.get(position).getEnd_time());
        holder.day.setText(arrayList.get(position).getDay());
        holder.datemeeting.setText(arrayList.get(position).getDate());
        try {
            holder.partner.setText(arrayList.get(position).getPartners().get(0).toString());
            if(arrayList.get(position).getWhere().length()==1)
            {
               holder.place.setText(arrayList.get(position).getWhere().get(0).toString());
            }
            else
            {
                holder.place.setText(arrayList.get(position).getWhere().get(0).toString()+" ,"+arrayList.get(position).getWhere().get(1).toString());

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {

        //        ImageView image,iv_status;
        //RecyclerView recyclerView;
//        TextView category_name,text,date;
        TextView start_time,end_time,day,datemeeting,partner,place;

        public ViewHolder(View itemView) {
            super(itemView);
           // recyclerView=(RecyclerView)itemView.findViewById(R.id.recyclerView);

//            image= (ImageView) itemView.findViewById(R.id.image);
//            iv_status= (ImageView) itemView.findViewById(R.id.iv_status);
//            category_name= (TextView) itemView.findViewById(R.id.category_name);
            start_time= (TextView) itemView.findViewById(R.id.start_time);
            end_time= (TextView) itemView.findViewById(R.id.end_time);
            day= (TextView) itemView.findViewById(R.id.day);
            datemeeting= (TextView) itemView.findViewById(R.id.date);
            partner= (TextView) itemView.findViewById(R.id.partner);
            place= (TextView) itemView.findViewById(R.id.place);

        }
    }
}
