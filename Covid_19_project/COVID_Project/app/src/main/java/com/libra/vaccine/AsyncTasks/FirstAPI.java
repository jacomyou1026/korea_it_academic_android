package com.libra.vaccine.AsyncTasks;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.TextView;
import com.libra.vaccine.Covid_MainActivity;
import com.libra.vaccine.R;
import com.libra.vaccine.VO.ValueVO;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class FirstAPI extends AsyncTask<String,String, ArrayList<ValueVO>> {
    Context context;
    TextView T_hj,T_death,T_care,T_recover,
            P_hj,P_death,P_care,P_recover,
            data_time;

    public FirstAPI(Context context) {
        this.context = context;
        //객체 등록
        T_hj = ((Covid_MainActivity) context).findViewById(R.id.T_hj);
        T_death = ((Covid_MainActivity) context).findViewById(R.id.T_death);
        T_care = ((Covid_MainActivity) context).findViewById(R.id.T_care);
        T_recover = ((Covid_MainActivity) context).findViewById(R.id.T_recover);

        P_hj = ((Covid_MainActivity) context).findViewById(R.id.P_hj);
        P_death = ((Covid_MainActivity) context).findViewById(R.id.P_death);
        P_care = ((Covid_MainActivity) context).findViewById(R.id.P_care);
        P_recover = ((Covid_MainActivity) context).findViewById(R.id.P_recover);

        data_time = ((Covid_MainActivity) context).findViewById(R.id.data_time);

    }

    @Override
    protected ArrayList<ValueVO> doInBackground(String... strings) {

        ArrayList<ValueVO> list = new ArrayList<ValueVO>();

        try {
            String url = "http://openapi.seoul.go.kr:8088/58756249496b616e39355472686d49/xml/TbCorona19CountStatus/1/2/"; //1->오늘 2->어제
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            Document document = null;
            document = builder.parse(url);

            document.normalize();

            //row 0번째 리스트 ( 오늘 )
            NodeList TodayList = document.getElementsByTagName("row").item(0).getChildNodes();

            //row 1번째 리스트 ( 어제 )
            NodeList YesterdayList = document.getElementsByTagName("row").item(1).getChildNodes();

            //오늘값
            String T_date_value = TodayList.item(21).getTextContent(); //날짜
            String T_hj_value =  TodayList.item(23).getTextContent(); //오늘누적 확진자
            String T_death_value =  TodayList.item(31).getTextContent(); //오늘누적 사망자
            String T_care_value =  TodayList.item(27).getTextContent(); //오늘누적 치료중
            String T_recover_value =  TodayList.item(29).getTextContent(); //오늘누적 퇴원

            //어젯값
            String Y_death_value =  YesterdayList.item(31).getTextContent(); //어제누적 사망자
            String Y_care_value =  YesterdayList.item(27).getTextContent(); //어제누적 치료중
            String Y_recover_value =  YesterdayList.item(29).getTextContent(); //어제누적 퇴원

            //플러스값(오늘값 - 어젯값)
            String P_hj_value = TodayList.item(25).getTextContent();
            String P_death_value = Integer.parseInt(T_death_value)-Integer.parseInt(Y_death_value)+"";
            String P_care_value = Integer.parseInt(T_care_value)-Integer.parseInt(Y_care_value)+"";
            String P_recover_value = Integer.parseInt(T_recover_value)-Integer.parseInt(Y_recover_value)+"";

            list.add(new ValueVO(T_hj_value,T_death_value,T_care_value,T_recover_value));
            list.add(new ValueVO(P_hj_value,P_death_value,P_care_value,P_recover_value));

            data_time.setText(T_date_value.substring(0,10)+"기준");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    protected void onPostExecute(ArrayList<ValueVO> list) {
        super.onPostExecute(list);

        T_hj.setText(list.get(0).getHj());
        T_death.setText(list.get(0).getDeath());
        T_care.setText(list.get(0).getCare());
        T_recover.setText(list.get(0).getRecover());

        P_hj.setText("+"+list.get(1).getHj());
        P_death.setText("+"+list.get(1).getDeath());
        P_care.setText("+"+list.get(1).getCare());
        P_recover.setText("+"+list.get(1).getRecover());
    }

}


