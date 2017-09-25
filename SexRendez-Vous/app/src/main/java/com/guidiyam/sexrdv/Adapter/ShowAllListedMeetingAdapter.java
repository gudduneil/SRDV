package com.guidiyam.sexrdv.Adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.guidiyam.sexrdv.R;

/**
 * Created by su on 29/7/17.
 */

public class ShowAllListedMeetingAdapter extends RecyclerView.Adapter<ShowAllListedMeetingAdapter.ViewHolder> {
    Activity activity;
    public ShowAllListedMeetingAdapter(Activity activity) {

        this.activity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.meeting_list, parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {

        //        ImageView image,iv_status;
        //RecyclerView recyclerView;
//        TextView category_name,text,date;

        public ViewHolder(View itemView) {
            super(itemView);
           // recyclerView=(RecyclerView)itemView.findViewById(R.id.recyclerView);

//            image= (ImageView) itemView.findViewById(R.id.image);
//            iv_status= (ImageView) itemView.findViewById(R.id.iv_status);
//            category_name= (TextView) itemView.findViewById(R.id.category_name);
//            text= (TextView) itemView.findViewById(R.id.text);
//            date= (TextView) itemView.findViewById(R.id.date);

        }
    }
}
