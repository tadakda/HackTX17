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
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

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
        chart.setTouchEnabled(true);
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

        List<Entry> entries1 = new ArrayList<Entry>();
        ArrayList<String> sorted1 = new ArrayList<>();
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
            while ((line = reader.readLine()) != null) {
                sorted1.add(line);
            }
            Collections.sort(sorted1);

            String[] temp;
            for (int i = 0; i < sorted1.size(); i++) {
                temp = sorted1.get(i).split(" ");
                lines.add(temp[0]);
                lines.add(temp[1]);
                lines.add(temp[2]);
                lines.add(temp[3]);
                lines.add(temp[4]);
            }

            int i = 0;
            int counter = 0;
            while (i < lines.size()) {
                //entries.add(new Entry(Float.parseFloat(lines.get(i+1)), Float.parseFloat(lines.get(i+2))));
                //entries.add(new Entry((float)i, Float.parseFloat(lines.get(i+2))));
                entries1.add(new Entry((float)counter, Float.parseFloat(lines.get(i+3))));
                i += 5;
                counter++;
            }

            float minX = entries1.get(0).getX();
            float maxX = entries1.get(0).getX();
            for (int z = 0; z < entries1.size(); z++) {
                minX = min(minX, entries1.get(z).getX());
                maxX = max(maxX, entries1.get(z).getX());
            }

            float minY = entries1.get(1).getY();
            float maxY = entries1.get(1).getY();
            for (int z = 0; z < entries1.size(); z++) {
                minY = min(minY, entries1.get(z).getY());
                maxY = max(maxY, entries1.get(z).getY());
            }

            xAxis.setAxisMinimum(minX);
            xAxis.setAxisMaximum(maxX);

            leftAxis.setAxisMinimum(minY);
            leftAxis.setAxisMaximum(maxY);

        } catch (IOException e) {
            Log.d("1", "Invalid input file!");
        }

        LineDataSet dataSet = new LineDataSet(entries1, "Label");
        dataSet.setDrawValues(false);
        dataSet.setDrawHighlightIndicators(false);
        dataSet.setCircleColor(Color.GREEN);
        dataSet.setCircleColorHole(Color.GREEN);

        /*
        LineData lineData1 = new LineData(dataSet);
        chart.setData(lineData1);
        chart.invalidate();
        */

        List<Entry> entries2 = new ArrayList<Entry>();
        ArrayList<String> sorted2 = new ArrayList<>();
        try {
            List<String> lines = new ArrayList<>();
            //InputStream is = this.getAssets().open("ProcessedData.txt");
            InputStream is = this.getAssets().open("FullDataBCC.txt");
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

            while ((line = reader.readLine()) != null) {
                sorted2.add(line);
            }
            Collections.sort(sorted2);

            String[] temp;
            for (int i = 0; i < sorted2.size(); i++) {
                temp = sorted2.get(i).split(" ");
                lines.add(temp[0]);
                lines.add(temp[1]);
                lines.add(temp[2]);
                lines.add(temp[3]);
            }

            int i = 0;
            int counter = 0;
            while (i < lines.size()) {
                //entries.add(new Entry(Float.parseFloat(lines.get(i+1)), Float.parseFloat(lines.get(i+2))));
                //entries.add(new Entry((float)i, Float.parseFloat(lines.get(i+2))));
                entries2.add(new Entry((float)counter, Float.parseFloat(lines.get(i+3))));
                i += 4;
                counter++;
            }

            for (int k = 0; k < entries2.size(); k++) {
                System.out.println("Entries[k]: " + entries2.get(k));
            }

            float minX = entries2.get(0).getX();
            float maxX = entries2.get(0).getX();
            for (int z = 0; z < entries2.size(); z++) {
                minX = min(minX, entries2.get(z).getX());
                maxX = max(maxX, entries2.get(z).getX());
            }

            float minY = entries2.get(1).getY();
            float maxY = entries2.get(1).getY();
            for (int z = 0; z < entries2.size(); z++) {
                minY = min(minY, entries2.get(z).getY());
                maxY = max(maxY, entries2.get(z).getY());
            }

            xAxis.setAxisMinimum(minX);
            xAxis.setAxisMaximum(maxX);

            leftAxis.setAxisMinimum(minY);
            leftAxis.setAxisMaximum(maxY);

        } catch (IOException e) {
            Log.d("1", "Invalid input file!");
        }

        LineDataSet dataSet2 = new LineDataSet(entries2, "Label");
        dataSet2.setDrawValues(false);
        dataSet2.setDrawHighlightIndicators(false);
        dataSet2.setCircleColor(Color.RED);
        dataSet2.setCircleColorHole(Color.RED);
        List<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
        dataSets.add(dataSet);
        dataSets.add(dataSet2);

        LineData lineData = new LineData(dataSets);
        chart.setData(lineData);
        chart.invalidate();

    }

    }

