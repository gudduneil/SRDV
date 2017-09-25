package com.guidiyam.sexrdv.Adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.guidiyam.sexrdv.R;

/**
 * Created by su on 20/7/17.
 */

public class CAdapter extends RecyclerView.Adapter<CAdapter.ViewHolder> {

    Context context;

    public CAdapter(Context activity) {
        this.context = activity;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.reward_child_layout, null);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
//        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//
//        view = mInflater.inflate(R.layout.content_layout, parent, false);
    }


    @Override
    public int getItemCount() {
        return 2;
    }



    public class ViewHolder extends RecyclerView.ViewHolder {

//        ImageView image,iv_status;
//        TextView category_name,text,date;

        public ViewHolder(View itemView) {
            super(itemView);

//            image= (ImageView) itemView.findViewById(R.id.image);
//            iv_status= (ImageView) itemView.findViewById(R.id.iv_status);
//            category_name= (TextView) itemView.findViewById(R.id.category_name);
//            text= (TextView) itemView.findViewById(R.id.text);
//            date= (TextView) itemView.findViewById(R.id.date);

        }
    }
}
