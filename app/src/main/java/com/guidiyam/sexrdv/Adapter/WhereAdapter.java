package com.guidiyam.sexrdv.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.guidiyam.sexrdv.ActivityNewMeeting;
import com.guidiyam.sexrdv.R;
import com.guidiyam.sexrdv.fragment.PartnerFrag;
import com.guidiyam.sexrdv.helper.RoundedTransformation;
import com.guidiyam.sexrdv.setget.WhereSetGet;
import com.guidiyam.sexrdv.volley.AppData;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by su on 25/9/17.
 */

public class WhereAdapter extends RecyclerView.Adapter<WhereAdapter.ViewHolder> {




    private AlertDialog alert11;
    ActivityNewMeeting wheredialogactivity;
    Context context;
    ArrayList<WhereSetGet> arrayList;

    public WhereAdapter(Context context,ArrayList<WhereSetGet>arrayList,ActivityNewMeeting wheredialogactivity) {
        this.context = context;
        this.arrayList=arrayList;
        this.wheredialogactivity=wheredialogactivity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.option_view, parent,false);
        //context=parent.getContext();

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        if(!arrayList.get(position).getImage().equals(""))
        {
if(arrayList.get(position).getImage_status().equals("1"))
{

    if (arrayList.get(position).isTouch()==false)
    {

        holder.tick.setVisibility(View.INVISIBLE);
        Picasso.with(context).load(arrayList.get(position).getDeselected_image()).placeholder(R.drawable.lock_icon).error(R.drawable.lock_icon).into(holder.image);
        //holder.background.setBackgroundResource(R.drawable.mold_dark_fade_blue_rectangle);

        // mDataset.get(position).setSelect(true);

    }
    else{


        holder.tick.setVisibility(View.VISIBLE);

        Picasso.with(context).load(arrayList.get(position).getImage()).placeholder(R.drawable.lock_icon).error(R.drawable.lock_icon).into(holder.image);
        // holder.background.setBackgroundResource(R.drawable.mold_blue_rectangle);
        // mDataset.get(position).setSelect(false);

    }

}
            //Picasso.with(context).load(arrayList.get(position).getImage()).placeholder(R.drawable.lock_icon).error(R.drawable.lock_icon).into(holder.image);

            else
                {
    Picasso.with(context).load(R.drawable.lock_icon).placeholder(R.drawable.lock_icon).error(R.drawable.lock_icon).into(holder.image);
}
        }
        else
        {
            Picasso.with(context).load(R.drawable.lock_icon).placeholder(R.drawable.lock_icon).error(R.drawable.lock_icon).into(holder.image);
        }

        holder.name.setText(arrayList.get(position).getEn_name());











        holder.mainlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(arrayList.get(position).getImage_status().equals("0"))

                {

                    dailog();


                }

                else{
                    if (arrayList.get(position).isTouch() == true) {

                        holder.tick.setVisibility(View.INVISIBLE);
                        Picasso.with(context).load(arrayList.get(position).getDeselected_image()).placeholder(R.drawable.lock_icon).error(R.drawable.lock_icon).into(holder.image);
                        //viewHolder.background.setBackgroundResource(R.drawable.mold_dark_fade_blue_rectangle);
                        arrayList.get(position).setTouch(false);
//                    AppData.selectedcontact2.get(position).setSelect(false);
                        AppData.wherelistings.get(position).setTouch(false);

                        ActivityNewMeeting.where_count--;

                    } else {

                        holder.tick.setVisibility(View.VISIBLE);
                        Picasso.with(context).load(arrayList.get(position).getImage()).placeholder(R.drawable.lock_icon).error(R.drawable.lock_icon).into(holder.image);
                        // viewHolder.background.setBackgroundResource(R.drawable.mold_blue_rectangle);
                        arrayList.get(position).setTouch(true);
//                   AppData.selectedcontact2.get(position).setSelect(true);
                        AppData.wherelistings.get(position).setTouch(true);
                        ActivityNewMeeting.where_count++;

                    }
                }
            }
        });

//        if(position==0){
//            holder.image.setBackgroundResource(R.drawable.vanilla);
//            holder.name.setText("Vanilla");
//        }else  if(position==1){
//            holder.image.setBackgroundResource(R.drawable.quickie);
//            holder.name.setText("Quickie");
//            holder.tick.setVisibility(View.VISIBLE);
//
//        }else  if(position==2){
//            holder.image.setBackgroundResource(R.drawable.vagina);
//            holder.name.setText("Vogina");
//            holder.tick.setVisibility(View.VISIBLE);
//
//        }
//        else  if(position==3){
//            holder.image.setBackgroundResource(R.drawable.oral);
//            holder.name.setText("Oral");
//        }
//        else  if(position==4){
//            holder.image.setBackgroundResource(R.drawable.anal);
//            holder.name.setText("Anal");
//        }
//        else  if(position==5){
//            holder.image.setBackgroundResource(R.drawable.fist);
//            holder.name.setText("Fist");
//        }else{
//            holder.image.setBackgroundResource(R.drawable.lock_icon);
//            holder.name.setText("Lock");
//            holder.tick.setVisibility(View.GONE);
//            holder.image.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    dailog();
//
//                }
//            });
//
//        }

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView image,tick;
        TextView name;
        RelativeLayout mainlayout;

        public ViewHolder(View itemView) {
            super(itemView);

            image= (ImageView) itemView.findViewById(R.id.image);
            tick= (ImageView) itemView.findViewById(R.id.tick);
            name= (TextView) itemView.findViewById(R.id.name);
            mainlayout=(RelativeLayout)itemView.findViewById(R.id.mainlayout);
        }
    }
    public void dailog()
    {

        LinearLayout back;
        LayoutInflater li = LayoutInflater.from(context);
        View promptsView = li.inflate(R.layout.need_more_ideas_dialog, null);
        AlertDialog.Builder alert = new AlertDialog.Builder(context);
        alert.setCancelable(true);
        alert.setView(promptsView);
        back= (LinearLayout) promptsView.findViewById(R.id.menu);

        alert11 = alert.create();

        WindowManager.LayoutParams wmlp = alert11.getWindow().getAttributes();

        wmlp.gravity = Gravity.CENTER;
        wmlp.x = 0;   //x position
        wmlp.y = 0;   //y position
        alert11.show();
        alert11.getWindow().setBackgroundDrawable(null);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert11.dismiss();
                Toast.makeText(context,"No thanks, some other time",Toast.LENGTH_SHORT).show();
            }
        });


    }
}
