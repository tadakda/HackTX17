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
import java.util.ArrayList;
import java.util.List;

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
        leftAxis.setAxisMinimum(0);
        leftAxis.setAxisMaximum(.2f);

        chart.getAxisRight().setDrawAxisLine(false);
        chart.getAxisRight().setDrawGridLines(false);
        chart.getAxisRight().setDrawLabels(false);

        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setAxisMinimum(0);
        xAxis.setAxisMaximum(.2f);

        List<Entry> entries = new ArrayList<Entry>();
        try {
            List<String> lines = new ArrayList<>();
            InputStream is = this.getAssets().open("ProcessedData.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line;

            while ((line = reader.readLine()) != null) {
                String[] strings = line.split(" ");
                for (int i = 0; i < strings.length; i++) {
                    lines.add(strings[i]);
                }
            }
            System.out.println("lines[i+1] ==" + lines.get(1));
            int i = 0;
            while (i < lines.size()) {
                entries.add(new Entry(Float.parseFloat(lines.get(i+1)), Float.parseFloat(lines.get(i+2))));
                i += 3;
            }
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
