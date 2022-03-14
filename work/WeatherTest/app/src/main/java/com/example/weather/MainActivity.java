package com.example.weather;

import androidx.appcompat.app.AppCompatActivity;

import android.icu.util.Calendar;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    DBHelper dbHelper;
    Spinner city;
    Spinner gu;
    ArrayList<String> city_list;
    TextView detail;
    ImageView skyImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        skyImage = findViewById(R.id.imageView2);
        dbHelper = new DBHelper(this);
        detail = findViewById(R.id.detail);
        city = findViewById(R.id.city);
        city_list =  dbHelper.selectCity();
        ArrayAdapter<String> cityAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,city_list);
        city.setAdapter(cityAdapter);

        city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ArrayList<String> city_list = dbHelper.selectGu(city.getSelectedItem().toString());
                ArrayAdapter<String> guAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1,city_list);
                gu.setAdapter(guAdapter);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        gu = findViewById(R.id.gu);
        ArrayList<String> gu_list = dbHelper.selectGu("서울특별시");
        ArrayAdapter<String> guAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,gu_list);
        gu.setAdapter(guAdapter);
        gu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Weather weather = new Weather();
                weather.execute(dbHelper.selectPosition(city.getSelectedItem().toString(),gu.getSelectedItem().toString()));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        gu.setSelection(1);

    }

    @Override
    protected void onResume() {
        super.onResume();
        Weather weather = new Weather();
        weather.execute(dbHelper.selectPosition(city.getSelectedItem().toString(),gu.getSelectedItem().toString()));
    }

    class Weather extends AsyncTask<String,Void,String> {

        int timeList[] = new int[]{2,5,8,11,14,17,20,23};
        public String getTime(){
            int time = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);

            int i;
            for(i=0;i<timeList.length;i++){
                if(timeList[i] >= time)
                    break;
            }
            if(i<3 && i != 0)
                return "0" + timeList[i-1] + "00";
            return timeList[i-1] + "00";
        }
        @Override
        protected String doInBackground(String... strings) {
            String apiURL = "http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst";
            String nx, ny, baseTime, serviceKey, numOfRows,dataType, baseDate;
            serviceKey = "hpOVfNem4MVro1QdBZTMTq%2FMZs%2B8yylSvxNQlqPiEQec%2Bo99WRRbIvrVqLltto5W0TmluoxR7uQHpHFNZ146qg%3D%3D";

            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            baseDate = sdf.format(Calendar.getInstance().getTime());
            dataType = "json";
            baseTime = getTime();
            Log.d("Weather", "getTime: "+baseTime);
            numOfRows = "14";
            nx = strings[0];
            ny=strings[1];
            apiURL += "?serviceKey="+serviceKey;
            apiURL += "&base_date="+baseDate;
            apiURL += "&base_time="+baseTime;
            apiURL += "&dataType="+dataType;
            apiURL += "&nx="+nx;
            apiURL += "&ny="+ny;
            apiURL += "&numOfRows="+numOfRows;
            Log.d("Weather", "doInBackground: "+apiURL);
            URL url = null;
            JSONObject r = new JSONObject();
            try {
                url = new URL(apiURL);

                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(),"UTF-8"));

                String line;
                String result = new String();
                while((line = br.readLine())!= null)
                    result += line;

                Log.d("wheater",result);

                JSONObject json = new JSONObject(result);
                JSONArray arr = json.getJSONObject("response").getJSONObject("body").getJSONObject("items").getJSONArray("item");

                for(int i=0;i<arr.length();i++) {
                    JSONObject obj = (JSONObject) arr.get(i);

                    if (obj.getString("category").equals("TMP")) {
                        r.put(obj.getString("category"), obj.getString("fcstValue"));
                    }else if(obj.getString("category").equals("PTY")) {
                        r.put(obj.getString("category"), obj.getString("fcstValue"));
                    }else if(obj.getString("category").equals("REH")) {
                        r.put(obj.getString("category"), obj.getString("fcstValue"));
                    }else if(obj.getString("category").equals("SKY")) {
                        r.put(obj.getString("category"), obj.getString("fcstValue"));
                    }
                }
            } catch (ProtocolException protocolException) {
                protocolException.printStackTrace();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            } catch (JSONException jsonException) {
                jsonException.printStackTrace();
            }
            return r.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                JSONObject json = new JSONObject(s);
                String result = "온도 -"+json.getString("TMP") +"\n" +
                        "강수형태 -"+json.getString("PTY") +"\n" +
                        "습도 -"+json.getString("REH") +"\n";
                Log.d("Weather", "onPostExecute: "+s);
                detail.setText(result);
                if(json.getString("PTY") !=null && !json.getString("PTY").equals("0") ){
                    if(json.getString("PTY").equals("1")){
                        skyImage.setImageResource(R.drawable.rain);
                    }else if(json.getString("PTY").equals("2")){
                        skyImage.setImageResource(R.drawable.snow);
                    }else if(json.getString("PTY").equals("3")){
                        skyImage.setImageResource(R.drawable.snow);
                    }else if(json.getString("PTY").equals("0")){
                        skyImage.setImageResource(R.drawable.cloud);
                    }
                }else{
                    if(json.getString("SKY").equals("1")){
                        skyImage.setImageResource(R.drawable.sun);
                    }else if(json.getString("SKY").equals("3")){
                        skyImage.setImageResource(R.drawable.cloud);
                    }else if(json.getString("SKY").equals("4")){
                        skyImage.setImageResource(R.drawable.cloud);
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

}