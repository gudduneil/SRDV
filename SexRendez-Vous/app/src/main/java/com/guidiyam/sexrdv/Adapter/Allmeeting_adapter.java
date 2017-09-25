package com.guidiyam.sexrdv.Adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.guidiyam.sexrdv.R;


/**
 * Created by su on 4/7/17.
 */

public class Allmeeting_adapter extends RecyclerView.Adapter<Allmeeting_adapter.ViewHolder> {
    Activity activity;
    Context context;
    CAdapter2 cAdapter2;

    public LinearLayoutManager lln;


    public Allmeeting_adapter(Activity activity) {
        this.activity = activity;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.my_meeting_content_layout2, parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.from_time.setTextColor(Color.parseColor("#F80D6B"));
        holder.day.setTextColor(Color.parseColor("#FFFFFF"));
        holder.name.setTextColor(Color.parseColor("#FFFFFF"));
        holder.address.setTextColor(Color.parseColor("#FFFFFF"));
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
RecyclerView recyclerView;
        TextView from_time,day,name,address;
//        TextView category_name,text,date;

        public ViewHolder(View itemView) {
            super(itemView);
            recyclerView=(RecyclerView)itemView.findViewById(R.id.recyclerView);
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
