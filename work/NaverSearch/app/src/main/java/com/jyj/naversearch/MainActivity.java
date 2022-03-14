package com.jyj.naversearch;

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
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
EditText edtSearch;
Button btnSearch;
ListView listResult;
CustomAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtSearch = findViewById(R.id.edit_search);
        btnSearch = findViewById(R.id.btn_search);
        listResult = findViewById(R.id.list_result);
        adapter =new CustomAdapter();
        listResult.setAdapter(adapter);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SearchTask task = new SearchTask();
                task.execute(edtSearch.getText().toString());
            }
        });
        
        //링크나오게 하기
//        listResult.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                ListItem vo = (ListItem)adapter.getItem(i);
//                Toast.makeText(MainActivity.this,vo.getLink() , Toast.LENGTH_SHORT).show();
//
//                Intent change = new Intent(Intent.ACTION_VIEW);
//                change.setData(Uri.parse(vo.getLink()));
//                startActivity(change);
//
//            }
//        });

    }

    public class SearchTask extends AsyncTask<String,String, ArrayList<ListItem>>{


        @Override
        protected ArrayList<ListItem> doInBackground(String... strings) {
            ArrayList<ListItem> list = new ArrayList<ListItem>();
            String result = null;

            //네이버 블로그 검색 결과를 받음
            String ClientId= "MWT2ZigEC3jso2N7Q4Uf";
            String ClientSecret= "GuVS0hLBld";
            String apiUrl="https://openapi.naver.com/v1/search/blog.json";
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

                String title =null;
                String description =null;
                String link =null;
                String titles =null;
                String descriptions =null;
                String links =null;
                for (int i = 0; i < arr.length(); i++) {
                    JSONObject obj = (JSONObject)arr.get(i);
                    title =obj.getString("title");
                    description = obj.getString("description");
                    link=obj.getString("link");


                    String tag = "<(/)?([a-zA-Z]*)(\\\\s[a-zA-Z]*=[^>]*)?(\\\\s)*(/)?>";
                    titles =title.replaceAll(tag,"");
                    descriptions  =description.replaceAll(tag,"");
                    links  =link.replaceAll(tag,"");

                    Log.d("title",titles);
                    Log.d("de",descriptions);

                    list.add(new ListItem(titles,descriptions,links));
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
        protected void onPostExecute(ArrayList<ListItem> list) {
            //결과값 받아서 ui처리 부분
            super.onPostExecute(list);
            //list내용 초기화 --이전에 검색했던 부분을 없애기 위해
            adapter.clear();
            for (int i =0;i<list.size();i++){
                adapter.addItem(list.get(i));
            }
            adapter.notifyDataSetChanged();
        }


    }

}