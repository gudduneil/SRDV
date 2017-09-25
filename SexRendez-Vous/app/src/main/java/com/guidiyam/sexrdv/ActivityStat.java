package com.guidiyam.sexrdv;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;

import java.util.ArrayList;

public class ActivityStat extends AppCompatActivity {
BarChart barChart;
    LinearLayout menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stat);
        barChart=(BarChart)findViewById(R.id.barchart);
        menu=(LinearLayout)findViewById(R.id.menu);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



//        BarGraphSeries<DataPoint> series = new BarGraphSeries<DataPoint>(new DataPoint[] {
//                new DataPoint(0, 1),
//                new DataPoint(1, 5),
//                new DataPoint(2, 3),
//                new DataPoint(3, 2),
//                new DataPoint(4, 6)
//        });
//        barChart.addSeries(series);

        ArrayList<BarEntry> barEntries=new ArrayList<>();
        barEntries.add(new BarEntry(2f,0));
        barEntries.add(new BarEntry(4f,1));
        barEntries.add(new BarEntry(3f,2));
        barEntries.add(new BarEntry(1f,3));
        barEntries.add(new BarEntry(1.5f,4));
        barEntries.add(new BarEntry(2f,5));

        BarDataSet dataset=new BarDataSet(barEntries,"Dates");
        dataset.setColors(new int[]{Color.parseColor("#5758D6")});//////5758D6

        dataset.setBarSpacePercent(80f);
        dataset.setDrawValues(false);


        ArrayList<String> labels=new ArrayList<>();
        labels.add("NOV");
        labels.add("DEC");
        labels.add("JAN");
        labels.add("FEB");
        labels.add("MAR");
        labels.add("APR");


         BarData barData=new BarData(labels,dataset);
       // barData.setBarWidth(1f);

        //BarData data = new BarData(dataset);
       // barChart.setBarW(0.5f);
        barChart.setData(barData);
        barChart.setTouchEnabled(false);
        barChart.setDragEnabled(false);
        barChart.setScaleEnabled(false);
        barChart.setDescription("");
        barChart.setGridBackgroundColor(Color.parseColor("#1B1F38"));

        YAxis left = barChart.getAxisLeft();
        left.setTextColor(Color.parseColor("#1B1F38"));
        left.setAxisLineColor(Color.parseColor("#1B1F38"));

        barChart.getAxisRight().setEnabled(false);
        XAxis bottomAxis = barChart.getXAxis();
        bottomAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        bottomAxis.setTextColor(Color.parseColor("#767998"));
        bottomAxis.setAxisLineColor(Color.parseColor("#1B1F38"));

        ///////////////vanish grid////////////////////
        barChart.getAxisLeft().setDrawGridLines(false);
        barChart.getXAxis().setDrawGridLines(false);
        ///////////////vanish grid////////////////////
    }
}
