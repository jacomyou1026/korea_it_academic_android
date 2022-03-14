package com.jyj.papapgoapp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

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

public class MainActivity extends AppCompatActivity {
    EditText txt_input, txt_result;
    Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt_input = findViewById(R.id.txt_input);
        txt_result = findViewById(R.id.txt_result);
        button = findViewById(R.id.button);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = txt_input.getText().toString();
                PaPago papago = new PaPago();
                papago.execute(text);

            }
        });
    }

    public class PaPago extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... strings) {
            Log.d("PAPAGO", "ddoInBackground : " + strings[0]);
            String result = null;
            String ClientId = "MWT2ZigEC3jso2N7Q4Uf";
            String ClientSecret = "GuVS0hLBld";
            String apiUrl = "https://openapi.naver.com/v1/papago/n2mt";
            HttpURLConnection con = null;//연결
            DataOutputStream dos = null;
            BufferedReader br = null; //받을때

            try {
                String text = URLEncoder.encode(strings[0], "UTF-8");
                URL url = new URL(apiUrl);
                con = (HttpURLConnection) url.openConnection();
                //header설정
                con.setRequestMethod("POST");
                con.setRequestProperty("X-Naver-Client-Id", ClientId);
                con.setRequestProperty("X-Naver-Client-Secret", ClientSecret);
                con.setDoOutput(true);

                dos = new DataOutputStream(con.getOutputStream());
                String postParams = "source=ko&target=en&text=" + text;//쿼리스트링
                dos.writeBytes(postParams);//보내기
                dos.flush();//전송
                dos.close();


                int responseCode = con.getResponseCode(); //응답 코드 받음
                if (responseCode == HttpURLConnection.HTTP_OK)//200
                {
                    br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                } else {//에러일떄
                    br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
                }
                result = new String();
                while (true) {
                    String str = br.readLine();
                    if (str == null) break;
                    result += str;
                }

                JSONObject json = new JSONObject(result);
                result = json.getJSONObject("message").getJSONObject("result").getString("translatedText");




                Log.d("result",result);


            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            txt_result.setText(s);
        }
    }

}