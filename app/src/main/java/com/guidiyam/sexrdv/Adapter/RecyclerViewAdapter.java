package com.guidiyam.sexrdv.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
//
//import com.daimajia.swipe.SwipeLayout;
import com.guidiyam.sexrdv.ActivityPartner;
import com.guidiyam.sexrdv.R;
import com.guidiyam.sexrdv.fragment.PartnerFrag;
import com.guidiyam.sexrdv.helper.RoundedTransformation;
import com.guidiyam.sexrdv.setget.ContactList_getset;
import com.guidiyam.sexrdv.setget.PartnerSetGet;
import com.guidiyam.sexrdv.volley.AppData;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by su on 19/7/17.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.SimpleViewHolder>



{


     Context mContext;
    PartnerFrag activityPartner;
    //private ArrayList<String> mDataset;
    ArrayList<PartnerSetGet> mDataset;


    public RecyclerViewAdapter( Context con,ArrayList<PartnerSetGet>objects,PartnerFrag partnerFrag) {
       this.mContext=con;
        this.mDataset = objects;


       // AppData.selectedcontact2.addAll(mDataset);
        this.activityPartner=partnerFrag;
    }

    @Override
    public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.partner_layout,parent, false);

        return new SimpleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final SimpleViewHolder viewHolder, final int position) {
        viewHolder.SwipView.close();

        if (mDataset.get(position).isSelect()==false)
        {

            //holder.tick.setVisibility(View.INVISIBLE);
            viewHolder.background.setBackgroundResource(R.drawable.mold_dark_fade_blue_rectangle);

           // mDataset.get(position).setSelect(true);

        }else{


           // holder.tick.setVisibility(View.VISIBLE);
            viewHolder.background.setBackgroundResource(R.drawable.mold_blue_rectangle);
           // mDataset.get(position).setSelect(false);

        }
        if (!mDataset.get(position).getImage().equals("")){

            Picasso.with(mContext)
                    .load(mDataset.get(position).getImage()).transform(new RoundedTransformation())
                    .placeholder(R.drawable.user_image)
                    .into(viewHolder.image);

        }
        else {

            Picasso.with(mContext)
                    .load(R.drawable.user_image).transform(new RoundedTransformation())
                    .placeholder(R.drawable.user_image)
                    .into(viewHolder.image);

        }




        viewHolder.background.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDataset.get(position).isSelect()==true)
                {

                    //holder.tick.setVisibility(View.INVISIBLE);
                    viewHolder.background.setBackgroundResource(R.drawable.mold_dark_fade_blue_rectangle);
                    mDataset.get(position).setSelect(false);
                    AppData.selectedcontact.get(position).setSelect(false);
                    //AppData.selectedcontact2.get(position).setSelect(false);

                    activityPartner.partner_count--;

                }else{

                    //holder.tick.setVisibility(View.VISIBLE);
                    viewHolder.background.setBackgroundResource(R.drawable.mold_blue_rectangle);
                    mDataset.get(position).setSelect(true);
                    AppData.selectedcontact.get(position).setSelect(true);
                  //  AppData.selectedcontact2.get(position).setSelect(true);
                    activityPartner.partner_count++;

                }
            }
        });
        viewHolder.textViewData.setText(mDataset.get(position).getName());

        viewHolder.Button_Delete.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

            }
        });

    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

//    @Override
//    public int getSwipeLayoutResourceId(int position) {
//         return R.id.swipe;
//    }


    public static class SimpleViewHolder extends RecyclerView.ViewHolder {
        //SwipeLayout swipeLayout;
        //TextView textViewPos;
        TextView textViewData;
        ImageView buttonDelete;
        CircleImageView image;
        com.guidiyam.sexrdv.helper.SwipeLayout SwipView;
        LinearLayout Button_Delete,background;

        public SimpleViewHolder(View itemView) {
            super(itemView);
            ///swipeLayout = (SwipeLayout) itemView.findViewById(R.id.swipe);
            //textViewPos = (TextView) itemView.findViewById(R.id.position);
            textViewData = (TextView) itemView.findViewById(R.id.name);
            image=(CircleImageView)itemView.findViewById(R.id.image);
            SwipView=(com.guidiyam.sexrdv.helper.SwipeLayout)itemView.findViewById(R.id.swipe);
            SwipView.close();
            Button_Delete=(LinearLayout)itemView.findViewById(R.id.ll_deletegroupo);
            background=(LinearLayout)itemView.findViewById(R.id.background);
           // buttonDelete = (ImageView) itemView.findViewById(R.id.delete);

//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Log.d(getClass().getSimpleName(), "onItemSelected: " + textViewData.getText().toString());
//                    Toast.makeText(view.getContext(), "onItemSelected: " + textViewData.getText().toString(), Toast.LENGTH_SHORT).show();
//                }
//            });
        }
    }
}
