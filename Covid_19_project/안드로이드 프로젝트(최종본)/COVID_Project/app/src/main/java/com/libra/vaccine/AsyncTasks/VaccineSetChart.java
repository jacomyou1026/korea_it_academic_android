package com.libra.vaccine.AsyncTasks;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.util.Log;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.libra.vaccine.Vaccine_MainActivity;
import com.libra.vaccine.R;

import org.eazegraph.lib.charts.BarChart;
import org.eazegraph.lib.models.BarModel;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import jxl.Sheet;
import jxl.Workbook;

public class VaccineSetChart extends AsyncTask<String,String,Sheet>{

    Context context;
    BarChart barChart,barChart2,barChart3;
    PieChart pieChart;

    SpannableString s,s1,s2,s3;

    Typeface tf;

    public VaccineSetChart(Context context) {
        this.context = context;

        barChart = ((Vaccine_MainActivity) context).findViewById(R.id.barChart);
        barChart2 = ((Vaccine_MainActivity) context).findViewById(R.id.barChart2);
        barChart3 = ((Vaccine_MainActivity) context).findViewById(R.id.barChart3);
        pieChart = ((Vaccine_MainActivity) context).findViewById(R.id.pieChart);

    }

    @Override
    protected Sheet doInBackground(String... strings) {
        Sheet sheet = null ;

        String dataUrl = "https://ncv.kdca.go.kr/vaccineExcelDownload.es"; //다운로드 받을 엑셀 url
        InputStream is = null;
        try {
            URL url = new URL(dataUrl);
            URLConnection urlConnection = url.openConnection();
            is = urlConnection.getInputStream();

            Workbook wb = Workbook.getWorkbook(is);
            sheet = wb.getSheet(0);



        }catch (Exception e) {
            e.printStackTrace();
        }

        return sheet;
    }


    @Override
    protected void onPostExecute(Sheet sheet) {
        super.onPostExecute(sheet);

        barChart.clearChart();
        barChart2.clearChart();
        barChart3.clearChart();


        //BarChart 데이터 셋팅--------------------------------------------------------
        for(int i = 7; i >= 2 ; i--){
            String row = sheet.getCell(0,i).getContents().substring(5);

            int value = Integer.parseInt(sheet.getCell(1,i).getContents());
            int value2 = Integer.parseInt(sheet.getCell(2,i).getContents());
            int value3 = Integer.parseInt(sheet.getCell(3,i).getContents());

            barChart.addBar(new BarModel(row, value , 0xFF56B7F1));
            barChart2.addBar(new BarModel(row, value2, 0xFF56B7F1));
            barChart3.addBar(new BarModel(row, value3, 0xFF56B7F1));
        }//for

        //centerText
        String first = sheet.getCell(4,1).getContents();
        String second = sheet.getCell(5,1).getContents();
        String third = sheet.getCell(6,1).getContents();

        //pieChart-------------------------------------------------------------
        pieChart.setUsePercentValues(true);
        pieChart.getDescription().setEnabled(false);
        pieChart.setExtraOffsets(5,10,10,5);

        pieChart.setDragDecelerationFrictionCoef(0.95f);

        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleColor(Color.WHITE);
        pieChart.setHoleRadius(60f);
        pieChart.setTransparentCircleRadius(65f);

        //centerText===========================================================
        SpannableString s = new SpannableString("차트를\n 선택하세요");
        s.setSpan(new RelativeSizeSpan(1.2f),0,s.length(),0);
        s.setSpan(new ForegroundColorSpan(Color.parseColor("#d3d3d3")),0,s.length(),0);

        SpannableString s1 = new SpannableString("화이자\n\n" +
                "1차 : "+String.format("%,d",Integer.parseInt(sheet.getCell(4,1).getContents()))+
                "\n2차 : "+String.format("%,d",Integer.parseInt(sheet.getCell(5,1).getContents()))+
                "\n3차 : "+String.format("%,d",Integer.parseInt(sheet.getCell(6,1).getContents())));
        s1.setSpan(new RelativeSizeSpan(2.0f),0,3,0);
        s1.setSpan(new ForegroundColorSpan(Color.BLUE),0,3,0);
        s1.setSpan(new RelativeSizeSpan(1f),4,s1.length(),0);

        SpannableString s2 = new SpannableString("모더나\n\n" +
                "1차 : "+String.format("%,d",Integer.parseInt(sheet.getCell(7,1).getContents()))+
                "\n2차 : "+String.format("%,d",Integer.parseInt(sheet.getCell(8,1).getContents()))+
                "\n3차 : "+String.format("%,d",Integer.parseInt(sheet.getCell(9,1).getContents())));
        s2.setSpan(new RelativeSizeSpan(2.0f),0,3,0);
        s2.setSpan(new ForegroundColorSpan(Color.BLUE),0,3,0);
        s2.setSpan(new RelativeSizeSpan(1f),4,s2.length(),0);

        SpannableString s3 = new SpannableString("AZ\n\n" +
                "1차 : "+String.format("%,d",Integer.parseInt(sheet.getCell(10,1).getContents()))+
                "\n2차 : "+String.format("%,d",Integer.parseInt(sheet.getCell(11,1).getContents()))+
                "\n3차 : "+String.format("%,d",Integer.parseInt(sheet.getCell(12,1).getContents())));
        s3.setSpan(new RelativeSizeSpan(2.0f),0,2,0);
        s3.setSpan(new ForegroundColorSpan(Color.BLUE),0,2,0);
        s3.setSpan(new RelativeSizeSpan(1f),3,s3.length(),0);

        SpannableString s4 = new SpannableString("기타\n\n" +
                "얀센 : "+String.format("%,d",Integer.parseInt(sheet.getCell(13,1).getContents())+Integer.parseInt(sheet.getCell(14,1).getContents()))+
                "\n노바백스 : "+String.format("%,d",Integer.parseInt(sheet.getCell(15,1).getContents())+Integer.parseInt(sheet.getCell(16,1).getContents())+Integer.parseInt(sheet.getCell(17,1).getContents()))+
                "\n기타 : "+String.format("%,d",Integer.parseInt(sheet.getCell(18,1).getContents())+Integer.parseInt(sheet.getCell(19,1).getContents())+Integer.parseInt(sheet.getCell(20,1).getContents())));
        s4.setSpan(new RelativeSizeSpan(2.0f),0,2,0);
        s4.setSpan(new ForegroundColorSpan(Color.BLUE),0,2,0);
        s4.setSpan(new RelativeSizeSpan(1f),3,s4.length(),0);

        //=======================================================================
        pieChart.setCenterText(s);

        Float pf = Float.parseFloat(sheet.getCell(4,1).getContents())
                +Float.parseFloat(sheet.getCell(5,1).getContents())
                +Float.parseFloat(sheet.getCell(6,1).getContents());

        Float md = Float.parseFloat(sheet.getCell(7,1).getContents())
                +Float.parseFloat(sheet.getCell(8,1).getContents())
                +Float.parseFloat(sheet.getCell(9,1).getContents());

        Float az = Float.parseFloat(sheet.getCell(10,1).getContents())
                +Float.parseFloat(sheet.getCell(11,1).getContents())
                +Float.parseFloat(sheet.getCell(12,1).getContents());

        Float all = Float.parseFloat(sheet.getCell(1,1).getContents())
                +Float.parseFloat(sheet.getCell(2,1).getContents())
                +Float.parseFloat(sheet.getCell(3,1).getContents());
//        Description description = new Description();
//        description.setText("(1차 2차 3차 통합)"); //라벨
//        description.setTextSize(12);
//        pieChart.setDescription(description);

        pieChart.animateY(1000, Easing.EaseInOutCubic); //애니메이션

        ArrayList yValues = new ArrayList();
        yValues.add(new PieEntry(pf,"화이자"));
        yValues.add(new PieEntry(md,"모더나"));
        yValues.add(new PieEntry(az,"아스트라제네카"));
        yValues.add(new PieEntry(all-pf-md-az,"기타"));

        PieDataSet dataSet = new PieDataSet(yValues,"dataSet1");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);
        dataSet.setColors(ColorTemplate.MATERIAL_COLORS);

        //선
        dataSet.setXValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
        dataSet.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);


        PieData data = new PieData((dataSet));
        data.setValueTextSize(20f);
        data.setValueTextColor(Color.BLACK);
        pieChart.setData(data);


        //라벨
        pieChart.setEntryLabelColor(Color.BLACK);
        pieChart.setEntryLabelTextSize(16f);
        //시작점
        pieChart.setRotationAngle(330);
        pieChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {

                if( e.getY() == dataSet.getValues().get(0).getY()){
                    //화이자
                    Log.d("tc", "화이자 클릭");
                    pieChart.setCenterText(s1);

                }else if(e.getY() == dataSet.getValues().get(1).getY()){
                    //모더나
                    Log.d("tc", "모더나 클릭");
                    pieChart.setCenterText(s2);

                }else if(e.getY() == dataSet.getValues().get(2).getY()){
                    //az
                    Log.d("tc", "아제 클릭");
                    pieChart.setCenterText(s3);
                }else if(e.getY() == dataSet.getValues().get(3).getY()){
                    //az
                    Log.d("tc", "기타클릭");
                    pieChart.setCenterText(s4);
                }

            }

            @Override
            public void onNothingSelected() {

            }
        });

        //퍼센트
        data.setValueFormatter(new PercentFormatter(pieChart));
        pieChart.setUsePercentValues(true);


        barChart.startAnimation();




    }

}
