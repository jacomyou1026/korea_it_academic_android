package com.libra.vaccine.AsyncTasks;

import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.widget.TextView;

import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.libra.vaccine.Vaccine_MainActivity;
import com.libra.vaccine.R;
import com.libra.vaccine.VO.VaccineStatsVO;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class VaccineAPI extends AsyncTask<String, String, ArrayList<VaccineStatsVO>> {
    Context context;

    TextView first_percent,first_all,first_today,
            second_percent,second_all,second_today,
            third_percent,third_all,third_today,
            data_time;

    //유진님 차트
    HorizontalBarChart mchart;
    HorizontalBarChart totalVaccine;
    int[] colorArray = new int[]{Color.parseColor("#EC6E8D"), Color.parseColor("#F39A9B"), Color.parseColor("#F9C7A8")};


    public VaccineAPI(Context context) {
        this.context = context;

        first_percent = ((Vaccine_MainActivity) context).findViewById(R.id.first_perecent);
        first_all = ((Vaccine_MainActivity) context).findViewById(R.id.first_all);
        first_today = ((Vaccine_MainActivity) context).findViewById(R.id.first_today);

        second_percent = ((Vaccine_MainActivity) context).findViewById(R.id.second_percent);
        second_all = ((Vaccine_MainActivity) context).findViewById(R.id.second_all);
        second_today = ((Vaccine_MainActivity) context).findViewById(R.id.second_today);

        third_percent = ((Vaccine_MainActivity) context).findViewById(R.id.third_percent);
        third_all = ((Vaccine_MainActivity) context).findViewById(R.id.third_all);
        third_today = ((Vaccine_MainActivity) context).findViewById(R.id.third_today);

        data_time = ((Vaccine_MainActivity) context).findViewById(R.id.data_time);

        //유진님 차트
        mchart = ((Vaccine_MainActivity) context).findViewById(R.id.oldbarchar);
        totalVaccine = ((Vaccine_MainActivity) context).findViewById(R.id.totalVaccine);
        mchart.getAxisRight().setAxisMaxValue(100);

    }



    @Override
    protected ArrayList<VaccineStatsVO> doInBackground(String... strings) {
        ArrayList<VaccineStatsVO> list = new ArrayList<VaccineStatsVO>();

        String url = "https://nip.kdca.go.kr/irgd/cov19stats.do?list=all";

        try {
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = null;
            builder = builderFactory.newDocumentBuilder();
            Document document = builder.parse(url);

            document.getDocumentElement().normalize();
            //데이터타임
            data_time.setText(document.getElementsByTagName("dataTime").item(0).getTextContent().toString()+" 집계기준");

            NodeList itemTagList = document.getElementsByTagName("item");

            VaccineStatsVO vo1 = new VaccineStatsVO();
            VaccineStatsVO vo2 = new VaccineStatsVO();
            VaccineStatsVO vo3 = new VaccineStatsVO();
            
            //setToday
            NodeList childList = itemTagList.item(0).getChildNodes();//api순서 바뀌면 요기만 수정
            for(int j=0; j < childList.getLength(); j++) {
                switch (childList.item(j).getNodeName()) {
                    case "firstCnt":
                        vo1.setToday(childList.item(j).getTextContent().toString());
                        break;
                    case "secondCnt":
                        vo2.setToday(childList.item(j).getTextContent().toString());
                        break;
                    case "thirdCnt":
                        vo3.setToday(childList.item(j).getTextContent().toString());
                        break;
                }
            }

            //setAll
            childList = itemTagList.item(2).getChildNodes();//api순서 바뀌면 요기만 수정
            for(int j=0; j < childList.getLength(); j++) {
                switch (childList.item(j).getNodeName()) {
                    case "firstCnt":
                        vo1.setAll(childList.item(j).getTextContent().toString());
                        break;
                    case "secondCnt":
                        vo2.setAll(childList.item(j).getTextContent().toString());
                        break;
                    case "thirdCnt":
                        vo3.setAll(childList.item(j).getTextContent().toString());
                        break;
                }
            }

            //setSixty
            childList = itemTagList.item(3).getChildNodes();//api순서 바뀌면 요기만 수정
            for(int j=0; j < childList.getLength(); j++) {
                switch (childList.item(j).getNodeName()) {
                    case "firstCnt":
                        vo1.setSixty(childList.item(j).getTextContent().toString());
                        break;
                    case "secondCnt":
                        vo2.setSixty(childList.item(j).getTextContent().toString());
                        break;
                    case "thirdCnt":
                        vo3.setSixty(childList.item(j).getTextContent().toString());
                        break;
                }
            }


            //setPercent
            Float population = (float) 51306264; //2021 대한민국 인구
            vo1.setPercent(String.format("%.1f", Integer.parseInt(vo1.getAll()) / population * 100));
            vo2.setPercent(String.format("%.1f", Integer.parseInt(vo2.getAll()) / population * 100));
            vo3.setPercent(String.format("%.1f", Integer.parseInt(vo3.getAll()) / population * 100));

            //리스트 추가
            list.add(vo1);
            list.add(vo2);
            list.add(vo3);

        } catch (Exception e) {
            e.printStackTrace();
        }


        return list;
    }

    @Override
    protected void onPostExecute(ArrayList<VaccineStatsVO> list) {
        super.onPostExecute(list);

        first_percent.setText(list.get(0).getPercent()+"%");
        first_all.setText(String.format("%,d",Integer.parseInt(list.get(0).getAll())));
        first_today.setText("+"+String.format("%,d",Integer.parseInt(list.get(0).getToday())));

        second_percent.setText(list.get(1).getPercent()+"%");
        second_all.setText(String.format("%,d",Integer.parseInt(list.get(1).getAll())));
        second_today.setText("+"+String.format("%,d",Integer.parseInt(list.get(1).getToday())));

        third_percent.setText(list.get(2).getPercent()+"%");
        third_all.setText(String.format("%,d",Integer.parseInt(list.get(2).getAll())));
        third_today.setText("+"+String.format("%,d",Integer.parseInt(list.get(2).getToday())));


        //유진님 차트

        //전체 코로나 백신 차트
        ArrayList<BarEntry> datVales = new ArrayList<>();
        ArrayList<ILineDataSet> lineDataSetTotal = new ArrayList<>();
        float two = Float.parseFloat(list.get(1).getPercent())-Float.parseFloat(list.get(2).getPercent()) ;
        float tree = Float.parseFloat(list.get(0).getPercent())-Float.parseFloat(list.get(1).getPercent());

        datVales.add(new BarEntry(0, new float[]{Float.parseFloat(list.get(2).getPercent()), two,tree}));

        totalVaccine.getDescription().setEnabled(false);
        totalVaccine.setDrawValueAboveBar(false);
        totalVaccine.setHighlightFullBarEnabled(false);


        BarDataSet barDataSettotal = new BarDataSet(datVales, "");

        barDataSettotal.setStackLabels(new String[]{"3차"+list.get(2).getPercent()+"%", "2차"+list.get(1).getPercent()+"%", "1차"+list.get(0).getPercent()+"%"});

        barDataSettotal.setColors(colorArray);

        BarData barDataTotal = new BarData(barDataSettotal);
        barDataTotal.setDrawValues(false);
        barDataTotal.setBarWidth(40f);

        totalVaccine.setData(barDataTotal);
        totalVaccine.setPinchZoom(false);
        totalVaccine.setTouchEnabled(false);



        XAxis xAxisTotal = totalVaccine.getXAxis();
        xAxisTotal.setDrawAxisLine(false);
        xAxisTotal.setEnabled(false);
        xAxisTotal.setLabelCount(7);
        xAxisTotal.setLabelRotationAngle(270f);
        totalVaccine.animateY(2000);
        totalVaccine.invalidate();

        YAxis axisLeftTotal = totalVaccine.getAxisLeft();
        //축 설정
        axisLeftTotal.setAxisMinimum(0f);
        axisLeftTotal.setAxisMaxValue(100f);

        axisLeftTotal.setDrawGridLines(false);
        axisLeftTotal.setDrawAxisLine(false);
        //axisLeftTotal.setTextSize(13f);
        axisLeftTotal.setEnabled(false);

        YAxis axisRightTotal = totalVaccine.getAxisRight();
        axisRightTotal.setDrawGridLines(false);
        axisRightTotal.setDrawAxisLine(false);
        axisRightTotal.setEnabled(false);






        //60세 이상 접종률 차트
        ArrayList<BarEntry> datVals = new ArrayList<>();
        ArrayList<ILineDataSet> lineDataSet = new ArrayList<>();
        float oldtwo = Float.parseFloat(list.get(1).getSixty())-Float.parseFloat(list.get(2).getSixty()) ;
        float lodtree = Float.parseFloat(list.get(0).getSixty())-Float.parseFloat(list.get(1).getSixty());

        datVals.add(new BarEntry(0, new float[]{Float.parseFloat(list.get(2).getSixty()),oldtwo,lodtree}));


        mchart.getDescription().setEnabled(false);
        mchart.setDrawValueAboveBar(false);
        mchart.setHighlightFullBarEnabled(false);

        BarDataSet barDataSet = new BarDataSet(datVals, "");
        barDataSet.setStackLabels(new String[]{"3차"+list.get(2).getSixty()+"%", "2차"+list.get(1).getSixty()+"%", "1차"+list.get(0).getSixty()+"%"});
        barDataSet.getStackSize();



        mchart.setPinchZoom(false);
        mchart.setTouchEnabled(false);


        barDataSet.setColors(colorArray);

        BarData barData = new BarData(barDataSet);
        barData.setBarWidth(40f);

        mchart.setData(barData);
        barData.setDrawValues(false);


        ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();

        dataSets.add(barDataSet);


        XAxis xAxis = mchart.getXAxis();
        xAxis.setDrawAxisLine(false);
        xAxis.setEnabled(false);
        xAxis.setLabelCount(7);
        xAxis.setLabelRotationAngle(270f);
        mchart.animateY(2000);
        mchart.invalidate();

        YAxis axisLeft = mchart.getAxisLeft();

        axisLeft.setAxisMinimum(0f);
        axisLeft.setAxisMaxValue(100f);
        axisLeft.setDrawGridLines(false);
        axisLeft.setDrawAxisLine(false);
        axisLeft.setTextSize(13f);
        axisLeft.setEnabled(false);

        YAxis axisRight = mchart.getAxisRight();
        axisRight.setDrawGridLines(false);
        axisRight.setDrawAxisLine(false);
        axisRight.setEnabled(false);


    }


}
