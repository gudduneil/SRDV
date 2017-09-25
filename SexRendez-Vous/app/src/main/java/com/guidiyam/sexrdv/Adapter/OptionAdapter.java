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
import android.widget.TextView;
import android.widget.Toast;

import com.guidiyam.sexrdv.R;

/**
 * Created by su on 28/7/17.
 */

public class OptionAdapter extends RecyclerView.Adapter<OptionAdapter.ViewHolder>{
    private AlertDialog alert11;
    Context context;

    public OptionAdapter(Context context) {
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.option_view, null);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        if(position==0){
            holder.image.setBackgroundResource(R.drawable.vanilla);
            holder.name.setText("Vanilla");
        }else  if(position==1){
            holder.image.setBackgroundResource(R.drawable.quickie);
            holder.name.setText("Quickie");
            holder.tick.setVisibility(View.VISIBLE);

        }else  if(position==2){
            holder.image.setBackgroundResource(R.drawable.vagina);
            holder.name.setText("Vogina");
            holder.tick.setVisibility(View.VISIBLE);

        }
        else  if(position==3){
            holder.image.setBackgroundResource(R.drawable.oral);
            holder.name.setText("Oral");
        }
        else  if(position==4){
            holder.image.setBackgroundResource(R.drawable.anal);
            holder.name.setText("Anal");
        }
        else  if(position==5){
            holder.image.setBackgroundResource(R.drawable.fist);
            holder.name.setText("Fist");
        }else{
            holder.image.setBackgroundResource(R.drawable.lock_icon);
            holder.name.setText("Lock");
            holder.tick.setVisibility(View.GONE);
            holder.image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dailog();

                }
            });

        }

    }

    @Override
    public int getItemCount() {
        return 20;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView image,tick;
        TextView name;

        public ViewHolder(View itemView) {
            super(itemView);

            image= (ImageView) itemView.findViewById(R.id.image);
            tick= (ImageView) itemView.findViewById(R.id.tick);
            name= (TextView) itemView.findViewById(R.id.name);
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
