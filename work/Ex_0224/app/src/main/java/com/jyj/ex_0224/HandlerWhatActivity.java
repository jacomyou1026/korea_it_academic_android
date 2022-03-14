package com.jyj.ex_0224;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class HandlerWhatActivity extends AppCompatActivity {

    Button btn0,btn1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler_what);

        btn0= findViewById(R.id.btn0);
        btn1= findViewById(R.id.btn1);

        btn0.setOnClickListener(click);
        btn1.setOnClickListener(click);

    }//onCreate()

    View.OnClickListener click = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.btn0:
                    //what으로 0을 보낸다.
                    handler.sendEmptyMessage(0);
                    break;
                case R.id.btn1:
                    //what으로 1을 보낸다.
                    handler.sendEmptyMessage(1);
                    break;
            }
        }
    };

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            //super.handleMessage(msg);
            //'what' : 핸들러는 하나인데 요청하는 객체가 여러개일 때 구별
            switch (msg.what){
                case 0:
                    Toast.makeText(HandlerWhatActivity.this, "0번 누름", Toast.LENGTH_SHORT).show();
                    break;
                case 1:
                    Toast.makeText(HandlerWhatActivity.this, "1번 누름", Toast.LENGTH_SHORT).show();

                    break;
            }
        }
    };
}