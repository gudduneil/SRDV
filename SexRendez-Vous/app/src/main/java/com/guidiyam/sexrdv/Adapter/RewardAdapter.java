package com.guidiyam.sexrdv.Adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.guidiyam.sexrdv.R;
import com.guidiyam.sexrdv.helper.CustomLinearLayoutManager;
import com.guidiyam.sexrdv.setget.ContactSetGet;
import com.guidiyam.sexrdv.setget.RewardSetget;

import java.util.ArrayList;

/**
 * Created by su on 20/7/17.
 */

public class RewardAdapter extends RecyclerView.Adapter<RewardAdapter.ViewHolder> {


    ArrayList<RewardSetget> list;
    Context context;
    public  LinearLayoutManager lln;
    public CAdapter cAdapter;



    public RewardAdapter(ArrayList<RewardSetget> conList) {
        list=conList;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.reward_layout, parent, false);

        context=parent.getContext();

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        lln = new LinearLayoutManager(context);
//
       holder.recyclerView .setHasFixedSize(true);

//        conList=new ArrayList<>();
//
//        RewardSetget rewardSetGet=new RewardSetget();
//        rewardSetGet.setImage(R.drawable.image1);
//        rewardSetGet.setName("Avik Ray");
//        // contactSetGet.setSelect(false);
//        conList.add(0,rewardSetGet);
//
//        RewardSetget rewardSetGet1=new RewardSetget();
//        rewardSetGet1.setImage(R.drawable.image2);
//        rewardSetGet1.setName("Bikash Babu");
//        //  contactSetGet1.setSelect(false);
//        conList.add(1,rewardSetGet1);
//
//        RewardSetget rewardSetGet2=new RewardSetget();
//        rewardSetGet2.setImage(R.drawable.image3);
//        rewardSetGet2.setName("Ajit Ray");
//        // contactSetGet2.setSelect(false);
//        conList.add(2,rewardSetGet2);
//
//        RewardSetget rewardSetGet3=new RewardSetget();
//        rewardSetGet3.setImage(R.drawable.image4);
//        rewardSetGet3.setName("Kalki Ray");
//        // contactSetGet3.setSelect(false);
//        conList.add(3,rewardSetGet3);
//
//        RewardSetget rewardSetGet4=new RewardSetget();
//        rewardSetGet4.setImage(R.drawable.image1);
//        rewardSetGet4.setName("Sam John");
//        // contactSetGet4.setSelect(false);
//
//        conList.add(4,rewardSetGet4);
//
//        RewardSetget rewardSetGet5=new RewardSetget();
//        rewardSetGet5.setImage(R.drawable.image3);
//        rewardSetGet5.setName("Peter Parker");
//        //contactSetGet5.setSelect(false);
//
//        conList.add(5,rewardSetGet5);
//        holder.recyclerView.setLayoutManager(new LinearLayoutManager(context));












        holder.recyclerView.setLayoutManager(lln);
        cAdapter = new CAdapter( context);
        holder.recyclerView.setAdapter(cAdapter);



        holder.image.setImageResource(list.get(position).getImage());
        holder.name.setText(list.get(position).getName());

    }


    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView image,tick;
        TextView name;
        public RecyclerView recyclerView;
        //RelativeLayout background;

        public ViewHolder(View itemView) {
            super(itemView);
           // tick= (ImageView) itemView.findViewById(R.id.tick);
            image= (ImageView) itemView.findViewById(R.id.image);
            name= (TextView) itemView.findViewById(R.id.name);
            recyclerView=(RecyclerView)itemView.findViewById(R.id.rv_contact);
            //background= (RelativeLayout) itemView.findViewById(R.id.background);
        }
    }
}
