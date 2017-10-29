package com.example.kevin.hacktx17;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LineChart chart = (LineChart) findViewById(R.id.chart);
        chart.setTouchEnabled(false);
        chart.setDrawGridBackground(false);
        chart.getDescription().setEnabled(false);
        chart.getLegend().setEnabled(false);

        YAxis leftAxis = chart.getAxisLeft();
        leftAxis.setDrawGridLines(false);

        chart.getAxisRight().setDrawAxisLine(false);
        chart.getAxisRight().setDrawGridLines(false);
        chart.getAxisRight().setDrawLabels(false);

        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);

        List<Entry> entries = new ArrayList<Entry>();
        try {
            List<String> lines = new ArrayList<>();
            //InputStream is = this.getAssets().open("ProcessedData.txt");
            InputStream is = this.getAssets().open("PointBCC.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line;

            /*
            while ((line = reader.readLine()) != null) {
                String[] strings = line.split(" ");
                for (int i = 0; i < strings.length; i++) {
                    lines.add(strings[i]);
                }
            }
            */
            ArrayList<String> sorted = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                sorted.add(line);
            }
            Collections.sort(sorted);

            String[] temp;
            for (int i = 0; i < sorted.size(); i++) {
                temp = sorted.get(i).split(" ");
                lines.add(temp[0]);
                lines.add(temp[1]);
                lines.add(temp[2]);
                lines.add(temp[3]);
                lines.add(temp[4]);
            }




            int i = 0;
            while (i < lines.size()) {
                //entries.add(new Entry(Float.parseFloat(lines.get(i+1)), Float.parseFloat(lines.get(i+2))));
                //entries.add(new Entry((float)i, Float.parseFloat(lines.get(i+2))));
                entries.add(new Entry((float)i, Float.parseFloat(lines.get(i+3))));
                i += 5;
            }

            float minX = entries.get(0).getX();
            float maxX = entries.get(0).getX();
            for (int z = 0; z < entries.size(); z += 2) {
                minX = min(minX, entries.get(z).getX());
                maxX = max(maxX, entries.get(z).getX());
            }

            float minY = entries.get(1).getY();
            float maxY = entries.get(1).getY();
            for (int z = 0; z < entries.size(); z += 2) {
                minY = min(minY, entries.get(z).getY());
                maxY = max(maxY, entries.get(z).getY());
            }

            xAxis.setAxisMinimum(minX);
            xAxis.setAxisMaximum(maxX);

            leftAxis.setAxisMinimum(minY);
            leftAxis.setAxisMaximum(maxY);

        } catch (IOException e) {
            Log.d("1", "Invalid input file!");
        }


        entries.add(new Entry(.1f, .1f));

        LineDataSet dataSet = new LineDataSet(entries, "Label");
        dataSet.setDrawValues(false);
        dataSet.setDrawHighlightIndicators(false);
        dataSet.setCircleColor(Color.BLACK);
        dataSet.setCircleColorHole(Color.BLACK);



        LineData lineData = new LineData(dataSet);
        chart.setData(lineData);
        chart.invalidate();




    }
}
