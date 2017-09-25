package com.guidiyam.sexrdv.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.guidiyam.sexrdv.R;
import com.guidiyam.sexrdv.setget.ContactSetGet;

import java.util.ArrayList;
import java.util.zip.Inflater;

/**
 * Created by su on 17/7/17.
 */

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {

    ArrayList<ContactSetGet> list;
    Context context;

    public ContactAdapter(ArrayList<ContactSetGet> conList) {
        list=conList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_layout, parent, false);

        context=parent.getContext();

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        /*Picasso.with(context)
                .load(list.get(position).getImage())
                .transform(new RoundedTransformation()).into(holder.image);*/

        holder.image.setImageResource(list.get(position).getImage());

        holder.name.setText(list.get(position).getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (list.get(position).isSelect()==true){

                    holder.tick.setVisibility(View.INVISIBLE);
                    holder.background.setBackgroundResource(R.drawable.mold_dark_fade_blue_rectangle);
                    list.get(position).setSelect(false);



                }else{

                    holder.tick.setVisibility(View.VISIBLE);
                    holder.background.setBackgroundResource(R.drawable.mold_blue_rectangle);
                    list.get(position).setSelect(true);

                }



            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView image,tick;
        TextView name;
        RelativeLayout background;

        public ViewHolder(View itemView) {
            super(itemView);
            tick= (ImageView) itemView.findViewById(R.id.tick);
            image= (ImageView) itemView.findViewById(R.id.image);
            name= (TextView) itemView.findViewById(R.id.name);
            background= (RelativeLayout) itemView.findViewById(R.id.background);
        }
    }
}
