package com.libra.vaccine.AsyncTasks;

import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.util.Log;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.libra.vaccine.Covid_MainActivity;
import com.libra.vaccine.Maker.MyMarkerView;
import com.libra.vaccine.R;
import com.libra.vaccine.VO.SecondVO;

import org.w3c.dom.Document;

import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class SecondAPI extends AsyncTask<String,String,ArrayList<SecondVO>> {
    Context context;
    LineChart lineChart;

    public SecondAPI(Context context) {
        this.context = context;
        lineChart = ((Covid_MainActivity) context).findViewById(R.id.LineChart);

    }

    @Override
    protected ArrayList<SecondVO> doInBackground(String... strings) {

        //차트 세팅=====================================================================================================================
        lineChart.setExtraBottomOffset(15f); // 간격
        lineChart.getDescription().setEnabled(false); // chart 밑에 description 표시 유무

        // XAxis (아래쪽) - 선 유무, 사이즈, 색상, 축 위치 설정
        XAxis xAxis = lineChart.getXAxis();
        xAxis.setDrawAxisLine(false);
        xAxis.setDrawGridLines(false);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM); // x축 데이터 표시 위치
        xAxis.setGranularity(2f);
        xAxis.setTextSize(14f);
        xAxis.setTextColor(Color.rgb(118, 118, 118));
        xAxis.setSpaceMin(0.1f); // Chart 맨 왼쪽 간격 띄우기
        xAxis.setSpaceMax(0.9f); // Chart 맨 오른쪽 간격 띄우기



        // YAxis(Right) (왼쪽) - 선 유무, 데이터 최솟값/최댓값, 색상
        YAxis yAxisLeft = lineChart.getAxisLeft();
        yAxisLeft.setTextSize(14f);
        yAxisLeft.setTextColor(Color.rgb(163, 163, 163));
        yAxisLeft.setDrawAxisLine(false);
        yAxisLeft.setAxisLineWidth(2);
        yAxisLeft.setGridColor(Color.rgb(163, 163, 163));
        yAxisLeft.setGridLineWidth(1f);
        yAxisLeft.setAxisMinimum(100000f); // 최솟값
        yAxisLeft.setAxisMaximum(450000);
        yAxisLeft.setGranularity(100000f);
        yAxisLeft.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                String str = value+"";
                str = str.substring(0,2)+"만";
                return str;
            }
        });

        YAxis yAxisRight = lineChart.getAxisRight(); //Y축의 오른쪽면 설정
        yAxisRight.setDrawLabels(false);
        yAxisRight.setDrawAxisLine(false);
        yAxisRight.setDrawGridLines(false);

        lineChart.setDescription(null);
        lineChart.getLegend().setEnabled(false);


        ArrayList<SecondVO> list = new ArrayList<>();
        try {
        String url = "http://openapi.seoul.go.kr:8088/58756249496b616e39355472686d49/xml/TbCorona19CountStatus/1/14/"; //1->오늘 2->어제
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        builder = builderFactory.newDocumentBuilder();
        Document document = null;
        document = builder.parse(url);

        document.normalize();

        for(int i = 12; i >=0; i--){
            String date = document.getElementsByTagName("row").item(i).getChildNodes().item(21).getTextContent();
            String value = document.getElementsByTagName("row").item(i).getChildNodes().item(25).getTextContent();

            list.add(new SecondVO(date,value));
        }

            //maker
            MyMarkerView mv = new MyMarkerView(context,R.layout.custom_marker_view,list);
            mv.setChartView(lineChart);
            lineChart.setMarker(mv);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }


    @Override
    protected void onPostExecute(ArrayList<SecondVO> list) {
        super.onPostExecute(list);
        //데이터 세팅===============================================================================
        ArrayList<Entry> entry1 = new ArrayList<>(); // 앱1

        for(int i = 0; i < 13; i++){
            entry1.add(new Entry(i,list.get(i).getFvalue()));
        }

        LineData chartData = new LineData();

        LineDataSet lineDataSet1 = new LineDataSet(entry1,"");
        chartData.addDataSet(lineDataSet1);
        lineDataSet1.setLineWidth(3);
        lineDataSet1.setCircleRadius(6);
        lineDataSet1.setDrawValues(false);
        lineDataSet1.setDrawCircleHole(true);
        lineDataSet1.setDrawCircles(true);
        lineDataSet1.setDrawHorizontalHighlightIndicator(false);
        lineDataSet1.setDrawHighlightIndicators(false);
        lineDataSet1.setColor(Color.rgb(255, 155, 155));
        lineDataSet1.setCircleColor(Color.rgb(255, 155, 155));
        lineChart.setData(chartData); // LineData 전달
        lineChart.invalidate(); // LineChart 갱신해 데이터 표시

        lineChart.getXAxis().setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                int a = (int) value;
                Log.d("v", "getFormattedValue: "+a);
                return list.get(a).getDate();
            }
        });

        //부가기능
        lineChart.setDoubleTapToZoomEnabled(false);
        lineChart.animateXY(1500,1500);

        //===============================================================================================================
    }

}
