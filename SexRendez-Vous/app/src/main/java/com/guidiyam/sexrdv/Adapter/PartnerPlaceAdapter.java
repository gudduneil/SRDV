package com.guidiyam.sexrdv.Adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.guidiyam.sexrdv.ActivityNewMeeting;
import com.guidiyam.sexrdv.R;
import com.guidiyam.sexrdv.ResumeMeeting;

/**
 * Created by su on 8/7/17.
 */

public class PartnerPlaceAdapter extends RecyclerView.Adapter<PartnerPlaceAdapter.ViewHolder> {

    Activity activity;
    String fr_pg;
    ActivityNewMeeting referance;
    ResumeMeeting referance2;
    public PartnerPlaceAdapter(Activity activity,String fr_pg) {
        this.activity = activity;
        this.fr_pg=fr_pg;
        if(this.fr_pg.equals("ResumeMeeting"))
        {

            referance2=(ResumeMeeting)activity;
        }
        else if(this.fr_pg.equals("ActivityNewMeeting"))
        {

            referance=(ActivityNewMeeting)activity;

        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.place_content, parent,false);

  return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        if(position==0){

            holder.image.setImageResource(R.drawable.add1);
            holder.name.setVisibility(View.INVISIBLE);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(position==0){
                    if(fr_pg.equals("ActivityNewMeeting"))
                    {
                        referance.addDialog("Where?");
                    }

                    else if(fr_pg.equals("ResumeMeeting"))
                    {


                        referance2.addDialog("Where?");
                    }

                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return 8;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView name;

        public ViewHolder(View itemView) {
            super(itemView);

            image= (ImageView) itemView.findViewById(R.id.image);
            name= (TextView) itemView.findViewById(R.id.name);


        }
    }
}
