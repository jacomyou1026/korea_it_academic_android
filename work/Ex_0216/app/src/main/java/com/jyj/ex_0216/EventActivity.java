package com.jyj.ex_0216;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class EventActivity extends AppCompatActivity {
    Button btn1,btn2,btn3;
    TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        txt = findViewById(R.id.txt);

        btn1.setOnClickListener(click);
        btn2.setOnClickListener(click);
        btn3.setOnClickListener(click);



    }
    View.OnClickListener click =new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(view == btn1){
                txt.setText("1번 누름");
            }else if(view == btn2){
                txt.setText("2번 누름");
            }else if(view == btn3){
                txt.setText("3번 누름");
            }
        }
    };

//    View.OnClickListener click = new View.OnClickListener() {
//        @Override
//        public void onClick(View view) {
//            switch (view.getId()){
//                case R.id.btn1:
//                    txt.setText("1번 누름");
//                    break;
//                case R.id.btn2:
//                    txt.setText("2번 누름");
//                    break;
//                case R.id.btn3:
//                    txt.setText("3번 누름");
//                    break;
//
//            }//switch
//        }
//    };




}