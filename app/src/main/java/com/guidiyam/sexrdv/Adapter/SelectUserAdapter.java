package com.guidiyam.sexrdv.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.guidiyam.sexrdv.ActivityPartner;
import com.guidiyam.sexrdv.R;


import com.guidiyam.sexrdv.helper.RoundedTransformation;
import com.guidiyam.sexrdv.setget.ContactList_getset;

import com.guidiyam.sexrdv.setget.NewContactList_getset;
import com.guidiyam.sexrdv.volley.AppData;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by su on 23/8/17.
 */

public class SelectUserAdapter  extends RecyclerView.Adapter<SelectUserAdapter.ViewHolder> {
    public  ArrayList<NewContactList_getset> _data;
    private    ArrayList<NewContactList_getset> arraylist;
    Context _c;
    Context context;
    ActivityPartner activityPartner;
    View view;


    //RoundImage roundedImage;

    public SelectUserAdapter(ArrayList<NewContactList_getset> selectUsers, Context context)
    {
        _data = selectUsers;
        _c = context;
        this.arraylist = new ArrayList<NewContactList_getset>();
        arraylist.addAll(_data);

        //AppData.contactarraylistforfilter.addAll(_data);
        activityPartner= (ActivityPartner) context;
    }



    // Filter Class
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        _data.clear();
        if (charText.length() == 0) {
            _data.addAll(arraylist);

            //AppData.contactarraylistforfilter.clear();
        }
        else
        {
            for (NewContactList_getset wp : arraylist)
            {
                if (wp.getName().toLowerCase(Locale.getDefault()).contains(charText))
                {
                    _data.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }
//    // Filter Class
//    public void filter(String charText) {
//        charText = charText.toLowerCase(Locale.getDefault());
//        _data.clear();
//        if (charText.length() == 0) {
//            _data.addAll(arraylist);
//        } else {
//            for (ContactList_getset wp : arraylist) {
//                {
//                    if (wp.getName().toLowerCase(Locale.getDefault())
//                            .contains(charText)) {
//                        _data.add(wp);
//                    }
//
//
//
//                }
//                notifyDataSetChanged();
//            }
//
//            //notifyDataSetChanged();
//
//        }
//    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view= LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_layout, parent, false);

        ///context=parent.getContext();

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position)
    {
/////Set Name/////


        final NewContactList_getset data = (NewContactList_getset) _data.get(position);
        Log.d(":::Data::::",data.getName());
        holder.name.setText(data.getName());
        holder.name.setText(_data.get(position).getName());

        ///////////////////////////


        if (_data.get(position).isTouch()==false)
        {

            holder.tick.setVisibility(View.INVISIBLE);
            holder.background.setBackgroundResource(R.drawable.mold_dark_fade_blue_rectangle);



        }else{


            holder.tick.setVisibility(View.VISIBLE);
            holder.background.setBackgroundResource(R.drawable.mold_blue_rectangle);

        }
        //        // Set image if exists
        try {

            if (data.getImage() != "")
 {

                Picasso.with(_c).load(data.getImage()).error(R.drawable.user_image).placeholder(R.drawable.user_image).transform(new RoundedTransformation()).into(holder.image);
            }

            else {
                holder.image.setImageResource(R.drawable.user_image);
            }

        } catch (OutOfMemoryError e) {
            Toast.makeText(_c,"Go to catch",Toast.LENGTH_SHORT).show();
            // Add default picture
           // holder.image.setImageDrawable(this._c.getDrawable(R.drawable.user_image));
            e.printStackTrace();
        }


        holder.background.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (_data.get(position).isTouch()==true)
                {

                    holder.tick.setVisibility(View.INVISIBLE);
                    holder.background.setBackgroundResource(R.drawable.mold_dark_fade_blue_rectangle);
                    _data.get(position).setTouch(false);
                    AppData.contactarraylist.get(position).setTouch(false);

                    activityPartner.partner_count--;

                }else{

                    holder.tick.setVisibility(View.VISIBLE);
                    holder.background.setBackgroundResource(R.drawable.mold_blue_rectangle);
                    _data.get(position).setTouch(true);
                    AppData.contactarraylist.get(position).setTouch(true);

                    activityPartner.partner_count++;

                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return _data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {

        CircleImageView image;ImageView tick;
        TextView name;
        RelativeLayout background;

        public ViewHolder(View itemView) {
            super(itemView);
            tick= (ImageView) itemView.findViewById(R.id.tick);
            image= (CircleImageView) itemView.findViewById(R.id.image);
            name= (TextView) itemView.findViewById(R.id.name);
            background= (RelativeLayout) itemView.findViewById(R.id.background);
        }
    }

}
