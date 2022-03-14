package com.jyj.corona;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class MainActivity extends AppCompatActivity {
    Button btn;
    TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.btn);
        txt = findViewById(R.id.txt);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Covid19 covid19 = new Covid19();
                covid19.execute();
            }
        });
    }

    public class  Covid19 extends AsyncTask<String,String,String>{

        @Override
        protected String doInBackground(String... strings) {

            String result = "";
            try {
                StringBuilder urlBuilder = new StringBuilder(
                        "http://openapi.data.go.kr/openapi/service/rest/Covid19/getCovid19InfStateJson"); /* URL */
                urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=PJgXs4FpAyoSQm3FnFxDCtIg1thkVBDIrn6DqPM38DFpD43CMzE%2BPisiMxns%2FZ6X2AbN%2FL0e0PZYrl0JVokqTA%3D%3D");
                urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /* 페이지번호 */
                urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "="
                        + URLEncoder.encode("10", "UTF-8")); /* 한 페이지 결과 수 */
                urlBuilder.append("&" + URLEncoder.encode("startCreateDt", "UTF-8") + "="
                        + URLEncoder.encode("20220226", "UTF-8")); /* 검색할 생성일 범위의 시작 */
                urlBuilder.append("&" + URLEncoder.encode("endCreateDt", "UTF-8") + "="
                        + URLEncoder.encode("20220303", "UTF-8")); /* 검색할 생성일 범위의 종료 */

                DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = builderFactory.newDocumentBuilder();
                Document document = builder.parse(urlBuilder.toString());
                document.normalize();

                NodeList tagList = document.getElementsByTagName("item");
                ArrayList<String> datelist =new ArrayList<>();
                ArrayList<Integer> decidelist = new ArrayList<>();

                for (int i = 0; i < tagList.getLength(); i++) {
                    NodeList list = tagList.item(i).getChildNodes();
                    for (int j = 0; j < list.getLength(); j++) {
                        if("decideCnt".equals(list.item(j).getNodeName())){
                            Log.d("covid19","doinbackground : "+list.item(j).getNodeName() + " : " + list.item(j).getTextContent()); //태그명 - 내용
                            decidelist.add(Integer.parseInt(list.item(j).getTextContent()));

                        }

                        //3월 3일날 발표한거는 3월 2일꺼
                        //3월 2일날 발표시 3월 1일꺼
                        //-1해야함
                        if("stateDt".equals(list.item(j).getNodeName())){
                            Log.d("covid19","doinbackground : "+list.item(j).getNodeName() + " : " + list.item(j).getTextContent()); //태그명 - 내용
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                            Date date = sdf.parse(list.item(j).getTextContent());
                            date.setDate(date.getDate()-1);
                            sdf = new SimpleDateFormat("yyyy-MM-dd");
                            datelist.add(sdf.format(date));


                        }

                    }
                    Log.d("covid19","-------------------------------------------");
                }
                //두개 묶어서 빼기때문에 마지막에 없음
                for (int i =0;i<decidelist.size()-1;i++){
                    result += "날짜 : "+ datelist.get(i)+"\n";
                    result += "확진자수 : "+ (decidelist.get(i)-decidelist.get(i+1))+"\n\n";

                }
                Log.d("result",result);
            } catch (UnsupportedEncodingException | ParserConfigurationException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (SAXException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }

            return result;

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            txt.setText(s);
        }
    }


}