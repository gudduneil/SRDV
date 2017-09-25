package com.guidiyam.sexrdv.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.guidiyam.sexrdv.ActivityDebrief;
import com.guidiyam.sexrdv.ActivityPartner;
import com.guidiyam.sexrdv.HomePage;
import com.guidiyam.sexrdv.R;
import com.guidiyam.sexrdv.helper.RoundedTransformation;
import com.guidiyam.sexrdv.setget.PartnerListing;
import com.guidiyam.sexrdv.volley.AppData;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

/**
 * Created by su on 8/7/17.
 */

public class  Partneradapter  extends RecyclerView.Adapter<Partneradapter.ViewHolder>

{
    Context context;

    Activity activity;
    ArrayList<PartnerListing> jsonarraylist;

    public Partneradapter(Activity activity, ArrayList<PartnerListing> json) {
        this.activity = activity;
        this.jsonarraylist=json;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.partner_content, parent, false);

context=parent.getContext();
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        if(position==0)
        {

            holder.image.setImageResource(R.drawable.add);
            holder.image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Intent intent = new Intent(v.getContext(), ActivityPartner.class);
//                    v.getContext().startActivity(intent);
                    AppData.partnerclick=true;
                    AppData.addextabuttonforpartner=true;

                    Intent intent = new Intent(v.getContext(), HomePage.class);
                    intent.putExtra("HomePage","Partner");
                    v.getContext().startActivity(intent);
                    ((Activity)context).finish();


                }
            });
            holder.name.setVisibility(View.INVISIBLE);

        }

        else{
            holder.name.setVisibility(View.VISIBLE);

           holder.name.setText(jsonarraylist.get(position-1).getName());
            //try {
                if(!jsonarraylist.get(position-1).getImage().equals(""))
                {
                    Picasso.with(context).load(jsonarraylist.get(position-1).getImage()).placeholder(R.drawable.user_image).error(R.drawable.user_image).transform(new RoundedTransformation()).into(holder.image);
                }
                else
                {
                    Picasso.with(context).load(R.drawable.user_image).placeholder(R.drawable.user_image).error(R.drawable.user_image).transform(new RoundedTransformation()).into(holder.image);

                }



        }

//        else  if(position==1){
//
//            holder.image.setImageResource(R.drawable.image3);
//            holder.name.setText("jhon");
//        }
//        else  if(position==2) {
//
//            holder.image.setImageResource(R.drawable.image2);
//            holder.name.setText("beeek");
//        }
//        else if (position == 3) {
//
//                holder.image.setImageResource(R.drawable.image4);
//                holder.name.setText("mark");
//            }
//        else if(position==4){
//
//            holder.image.setImageResource(R.drawable.image4);
//            holder.name.setText("avik");
//
//        }


        }

    @Override
    public int getItemCount() {
        return jsonarraylist.size()+1;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView name;

        public ViewHolder(View itemView) {
            super(itemView);

            image= (ImageView) itemView.findViewById(R.id.image);
//            iv_status= (ImageView) itemView.findViewById(R.id.iv_status);
            name= (TextView) itemView.findViewById(R.id.name);
//            text= (TextView) itemView.findViewById(R.id.text);
//            date= (TextView) itemView.findViewById(R.id.date);

        }
    }
}
