package com.guidiyam.sexrdv;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.guidiyam.sexrdv.customview.CustomScrollView;
import com.guidiyam.sexrdv.helper.CircularSeekBar;
import com.jesusm.holocircleseekbar.lib.HoloCircleSeekBar;

public class ActivityDebrief extends AppCompatActivity {
HoloCircleSeekBar picker;
    CustomScrollView scrv_bar;
    LinearLayout main_layout;
    int a=0;
    ImageView star1,star2,star3,star5,star4;
    LinearLayout menu;
    RelativeLayout punishment_layout,reward_layout,justforme_layout,notify_partner_layout;
    ImageView tick1,tick2,tick3,tick4;
    TextView punishment,reward;
    TextView justformetv,notifypartnertv;
    EditText edittext;
    //CircularSeekBar picker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debrief);
        main_layout=(LinearLayout)findViewById(R.id.main_layout);
        scrv_bar=(CustomScrollView)findViewById(R.id.scrv_bar);
        edittext=(EditText)findViewById(R.id.edittext);
        edittext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                scrv_bar.setEnableScrolling(false);
            }
        });
main_layout.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        scrv_bar.setEnableScrolling(true);
    }
});
        notifypartnertv=(TextView)findViewById(R.id.notifypartnertv);
        justformetv=(TextView)findViewById(R.id.justformetv);
        punishment=(TextView)findViewById(R.id.punishment);
        reward=(TextView)findViewById(R.id.reward);
        picker= (HoloCircleSeekBar) findViewById(R.id.picker);
        punishment_layout=(RelativeLayout) findViewById(R.id.punishment_layout);
        reward_layout=(RelativeLayout)findViewById(R.id.reward_layout);
        justforme_layout=(RelativeLayout)findViewById(R.id.justforme_layout);
        notify_partner_layout=(RelativeLayout)findViewById(R.id.notify_partner_layout);
        tick1=(ImageView)findViewById(R.id.tick1);
        tick2=(ImageView)findViewById(R.id.tick2);
        tick3=(ImageView)findViewById(R.id.tick3);
        tick4=(ImageView)findViewById(R.id.tick4);
        punishment_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tick1.setVisibility(View.VISIBLE);
                tick2.setVisibility(View.INVISIBLE);
                punishment_layout.setBackgroundResource(R.drawable.rewarddrawable);
                reward_layout.setBackgroundResource(R.drawable.punishmentdrawable);
                punishment.setTextColor(Color.parseColor("#FFFFFF"));
                reward.setTextColor(Color.parseColor("#767998"));
            }
        });


        reward_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tick1.setVisibility(View.INVISIBLE);
                tick2.setVisibility(View.VISIBLE);
                punishment_layout.setBackgroundResource(R.drawable.punishmentdrawable);
                reward_layout.setBackgroundResource(R.drawable.rewarddrawable);
                reward.setTextColor(Color.parseColor("#FFFFFF"));
                punishment.setTextColor(Color.parseColor("#767998"));
            }
        });

        justforme_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tick3.setVisibility(View.VISIBLE);
                tick4.setVisibility(View.INVISIBLE);

                justforme_layout.setBackgroundResource(R.drawable.rewarddrawable);
                notify_partner_layout.setBackgroundResource(R.drawable.punishmentdrawable);
                justformetv.setTextColor(Color.parseColor("#FFFFFF"));
                notifypartnertv.setTextColor(Color.parseColor("#767998"));
            }
        });



        notify_partner_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tick3.setVisibility(View.INVISIBLE);
                tick4.setVisibility(View.VISIBLE);

                notify_partner_layout.setBackgroundResource(R.drawable.rewarddrawable);
                justforme_layout.setBackgroundResource(R.drawable.punishmentdrawable);
                notifypartnertv.setTextColor(Color.parseColor("#FFFFFF"));
                justformetv.setTextColor(Color.parseColor("#767998"));
            }
        });
        //picker= (CircularSeekBar) findViewById(R.id.picker);

       // circularSeekbar = new CircularSeekBar(this);
//        picker.setMaxProgress(100);
//        picker.setProgress(100);
//        //setContentView(picker);
//        picker.invalidate();


        menu=(LinearLayout)findViewById(R.id.menu);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        star1=(ImageView)findViewById(R.id.star8);
        star3=(ImageView)findViewById(R.id.star1);

        star2=(ImageView)findViewById(R.id.star2);
        star4=(ImageView)findViewById(R.id.star4);

        star5=(ImageView)findViewById(R.id.star9);

        star1.setBackgroundResource(R.drawable.no_star);
        star2.setBackgroundResource(R.drawable.no_star);
        star3.setBackgroundResource(R.drawable.no_star);
        star4.setBackgroundResource(R.drawable.no_star);
        star5.setBackgroundResource(R.drawable.no_star);

        star1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //picker.setProgress(20);
                picker.setValue(1.0f);
                star1.setBackgroundResource(R.drawable.star);
                star2.setBackgroundResource(R.drawable.no_star);
                star3.setBackgroundResource(R.drawable.no_star);
                star4.setBackgroundResource(R.drawable.no_star);
                star5.setBackgroundResource(R.drawable.no_star);
            }
        });


        star2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                picker.setValue(2.0f);
               // picker.setProgress(40);
                star1.setBackgroundResource(R.drawable.star);
                star2.setBackgroundResource(R.drawable.star);
                star3.setBackgroundResource(R.drawable.no_star);
                star4.setBackgroundResource(R.drawable.no_star);
                star5.setBackgroundResource(R.drawable.no_star);
            }
        });

        star3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                picker.setValue(3.0f);
               // picker.setProgress(60);
                star1.setBackgroundResource(R.drawable.star);
                star2.setBackgroundResource(R.drawable.star);
                star3.setBackgroundResource(R.drawable.star);
                star4.setBackgroundResource(R.drawable.no_star);
                star5.setBackgroundResource(R.drawable.no_star);
            }
        });


        star4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                picker.setValue(4.0f);
                //picker.setProgress(80);
                star1.setBackgroundResource(R.drawable.star);
                star2.setBackgroundResource(R.drawable.star);
                star3.setBackgroundResource(R.drawable.star);
                star4.setBackgroundResource(R.drawable.star);
                star5.setBackgroundResource(R.drawable.no_star);
            }
        });


        star5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                picker.setValue(5.0f);
                //picker.setProgress(100);
                star1.setBackgroundResource(R.drawable.star);
                star2.setBackgroundResource(R.drawable.star);
                star3.setBackgroundResource(R.drawable.star);
                star4.setBackgroundResource(R.drawable.star);
                star5.setBackgroundResource(R.drawable.star);
            }
        });


//        picker.setSeekBarChangeListener(new CircularSeekBar.OnSeekChangeListener() {
//            @Override
//            public void onProgressChange(CircularSeekBar view, int newProgress) {
//
//            }
//        });
        picker.setOnSeekBarChangeListener(new HoloCircleSeekBar.OnCircleSeekBarChangeListener() {
            @Override
            public void onStartTrackingTouch(HoloCircleSeekBar seekBar) {
                // Nothing to do

                a = picker.getValue();
                //  Toast.makeText(getActivity(),"in the onStartTrackingTouch block",Toast.LENGTH_SHORT).show();


                Log.d("progline", String.valueOf(a));
            }

            @Override
            public void onStopTrackingTouch(HoloCircleSeekBar seekBar) {
                // Nothing to do
            }

            @Override
            public void onProgressChanged(HoloCircleSeekBar seekBar, int progress, boolean fromUser) {
                //picker.setText(String.valueOf(progress));

                Log.d("PROGRESS", String.valueOf(progress));
               if (fromUser == true) {
                   seekBar.setValue(progress);

                   if (progress == 0) {
                       star1.setBackgroundResource(R.drawable.no_star);
                       star2.setBackgroundResource(R.drawable.no_star);
                       star3.setBackgroundResource(R.drawable.no_star);
                       star5.setBackgroundResource(R.drawable.no_star);
                       star4.setBackgroundResource(R.drawable.no_star);


                   } else if (progress == 1) {
                       star1.setBackgroundResource(R.drawable.star);
                       star2.setBackgroundResource(R.drawable.no_star);
                       star3.setBackgroundResource(R.drawable.no_star);
                       star5.setBackgroundResource(R.drawable.no_star);
                       star4.setBackgroundResource(R.drawable.no_star);


                   } else if (progress == 2) {
                       star1.setBackgroundResource(R.drawable.star);
                       star2.setBackgroundResource(R.drawable.star);
                       star3.setBackgroundResource(R.drawable.no_star);
                       star5.setBackgroundResource(R.drawable.no_star);
                       star4.setBackgroundResource(R.drawable.no_star);


                   } else if (progress == 3) {
                       star1.setBackgroundResource(R.drawable.star);
                       star2.setBackgroundResource(R.drawable.star);
                       star3.setBackgroundResource(R.drawable.star);
                       star5.setBackgroundResource(R.drawable.no_star);
                       star4.setBackgroundResource(R.drawable.no_star);


                   } else if (progress == 4) {
                       star1.setBackgroundResource(R.drawable.star);
                       star2.setBackgroundResource(R.drawable.star);
                       star3.setBackgroundResource(R.drawable.star);
                       star5.setBackgroundResource(R.drawable.no_star);
                       star4.setBackgroundResource(R.drawable.star);


                   } else if (progress == 5) {

                       star1.setBackgroundResource(R.drawable.star);
                       star2.setBackgroundResource(R.drawable.star);
                       star3.setBackgroundResource(R.drawable.star);
                       star5.setBackgroundResource(R.drawable.star);
                       star4.setBackgroundResource(R.drawable.star);

                   }
              }

            }
        });

    }
}
