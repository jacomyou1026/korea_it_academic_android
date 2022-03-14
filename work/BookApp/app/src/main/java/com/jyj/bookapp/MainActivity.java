package com.jyj.bookapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

public class
MainActivity extends AppCompatActivity {
CustomAdaper adapter;
    ListView list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText editText = findViewById(R.id.editTextTextPersonName2);
        Button button = findViewById(R.id.button2);
        list = findViewById(R.id.list);
        adapter = new CustomAdaper();
        list.setAdapter(adapter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txt = editText.getText().toString();
                SearchTask task = new SearchTask();
                task.execute(txt);
            }
        });
        
        
        //링크연결
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                bookVO vo = (bookVO)adapter.getItem(i);
                
                Intent change = new Intent(Intent.ACTION_VIEW,Uri.parse(vo.getLink()));
                startActivity(change);
            }
        });


    }

    public class SearchTask extends AsyncTask<String,String, ArrayList<bookVO>>{

        protected ArrayList<bookVO> doInBackground(String... strings) {
            ArrayList<bookVO> list = new ArrayList<>();
            String result = null;
            String ClientId= "MWT2ZigEC3jso2N7Q4Uf";
            String ClientSecret= "GuVS0hLBld";
            String apiUrl="https://openapi.naver.com/v1/search/book.json";
            HttpURLConnection con = null;//연결
            //DataOutputStream dos=null;
            BufferedReader br = null; //받을때
            String text = strings[0];


            try {
                text = URLEncoder.encode(text,"UTF-8");
                URL url = new URL(apiUrl+"?query=" + text);
                con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");
                con.setRequestProperty("X-Naver-Client-Id", ClientId);
                con.setRequestProperty("X-Naver-Client-Secret", ClientSecret);
                //con.setDoo(true) --post일때만 적용

                int response = con.getResponseCode();
                if(response == HttpURLConnection.HTTP_OK) {
                    br = new BufferedReader(new InputStreamReader(con.getInputStream()));

                }else {
                    br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
                }

                result = new String();
                while(true) {
                    String str = br.readLine();
                    if(str ==null) {
                        break;
                    }else {
                        result +=str;
                    }
                }
                JSONObject json =new JSONObject(result);
                JSONArray arr= json.getJSONArray("items");


                for (int i = 0; i < arr.length(); i++) {
                    JSONObject obj = (JSONObject)arr.get(i);
                    String title = obj.getString("title");
                    String author = obj.getString("author");
                    String publisher = obj.getString("publisher");
                    String thumnail = obj.getString("image");
                    String link = obj.getString("link");
                    //obj.getString("title").replace("<b>","").replace("</b>","");


                    String tag = "<(/)?([a-zA-Z]*)(\\\\s[a-zA-Z]*=[^>]*)?(\\\\s)*(/)?>";
                    title =title.replaceAll(tag,"");
                    author  =author.replaceAll(tag,"");
                    link  =link.replaceAll(tag,"");
                    publisher  =publisher.replaceAll(tag,"");
//                    Log.d("title",titles);
//                    Log.d("de",descriptions);
//
                    list.add(new bookVO(title,"저자 : "+author+",출판사 : "+publisher,thumnail,link));

                }

            } catch (UnsupportedEncodingException | MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return list;
        }


        @Override
        protected void onPostExecute(ArrayList<bookVO> list) {
            super.onPostExecute(list);
            adapter.clear();
            for (int i =0;i< list.size();i++){
                adapter.addItem(list.get(i));
                Log.d("m", String.valueOf(list.get(i)));

            }
            adapter.notifyDataSetChanged();
        }
    }


}