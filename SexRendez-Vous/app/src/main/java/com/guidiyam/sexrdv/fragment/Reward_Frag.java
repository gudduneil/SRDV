package com.guidiyam.sexrdv.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.guidiyam.sexrdv.Adapter.ContactAdapter;
import com.guidiyam.sexrdv.Adapter.RewardAdapter;
import com.guidiyam.sexrdv.R;
import com.guidiyam.sexrdv.setget.ContactSetGet;
import com.guidiyam.sexrdv.setget.RewardSetget;
import com.guidiyam.sexrdv.volley.AppData;

import java.util.ArrayList;

/**
 * Created by su on 20/7/17.
 */

public class Reward_Frag extends Fragment {

    RecyclerView rv_contact;
    RewardAdapter adapter;

    ArrayList<RewardSetget> conList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.frag_reward_layout, container,
                false);
        AppData.page="Reward_Frag";
        Log.d("Pageforsevice:",AppData.page);
        rv_contact= (RecyclerView)v. findViewById(R.id.rv_contact);
        rv_contact.setHasFixedSize(true);

        conList=new ArrayList<>();

        RewardSetget rewardSetGet=new RewardSetget();
        rewardSetGet.setImage(R.drawable.image1);
        rewardSetGet.setName("Avik Ray");
       // contactSetGet.setSelect(false);
        conList.add(0,rewardSetGet);

        RewardSetget rewardSetGet1=new RewardSetget();
        rewardSetGet1.setImage(R.drawable.image2);
        rewardSetGet1.setName("Bikash Babu");
      //  contactSetGet1.setSelect(false);
        conList.add(1,rewardSetGet1);

        RewardSetget rewardSetGet2=new RewardSetget();
        rewardSetGet2.setImage(R.drawable.image3);
        rewardSetGet2.setName("Ajit Ray");
       // contactSetGet2.setSelect(false);
        conList.add(2,rewardSetGet2);

        RewardSetget rewardSetGet3=new RewardSetget();
        rewardSetGet3.setImage(R.drawable.image4);
        rewardSetGet3.setName("Kalki Ray");
       // contactSetGet3.setSelect(false);
        conList.add(3,rewardSetGet3);

        RewardSetget rewardSetGet4=new RewardSetget();
        rewardSetGet4.setImage(R.drawable.image1);
        rewardSetGet4.setName("Sam John");
       // contactSetGet4.setSelect(false);

        conList.add(4,rewardSetGet4);

        RewardSetget rewardSetGet5=new RewardSetget();
        rewardSetGet5.setImage(R.drawable.image3);
        rewardSetGet5.setName("Peter Parker");
        //contactSetGet5.setSelect(false);

        conList.add(5,rewardSetGet5);

        rv_contact.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter=new RewardAdapter(conList);
        rv_contact.setAdapter(adapter);
        return v;
    }
}
