package com.jyj.ex_0216;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class Work_randomActivity extends AppCompatActivity {
    Button b1,b2,b3,b4,restart;
    TextView txt_result;
    int num; //난수 발생용 변수
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_random);



            b1 = (Button)findViewById(R.id.b1);
            b2 = (Button)findViewById(R.id.b2);
            b3 = (Button)findViewById(R.id.b3);
            b4 = (Button)findViewById(R.id.b4);
            restart = (Button)findViewById(R.id.restart);
            txt_result = (TextView)findViewById(R.id.txt_result);

            b1.setOnClickListener(click);
            b2.setOnClickListener(click);
            b3.setOnClickListener(click);
            b4.setOnClickListener(click);

            restart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    num = new Random().nextInt(4)+1;
                    restart.setText("result");
                }
            });
        num = new Random().nextInt(4)+1;

        }

    View.OnClickListener click = new View.OnClickListener() {

        @Override
        public void onClick(View view) {
            //view가 상위 클래스이기에 buttom으로 강제 캐스팅해야한다.
            int myNum= Integer.parseInt(((Button)view).getText().toString());

            
            if(num == myNum){
                txt_result.setText("당첨!!");
            }else{
                txt_result.setText("꽝");
            }
        }
    };

}