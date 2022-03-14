package com.jyj.ex_0223;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HandlerActivity extends AppCompatActivity {

    TextView txt;
    Button btn_start,btn_stop;
    int num =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);

        txt = findViewById(R.id.txt);
        btn_start = findViewById(R.id.btn_start);
        btn_stop = findViewById(R.id.btn_stop);

        btn_start.setOnClickListener(click);
        btn_stop.setOnClickListener(click);
    } // onCreate

    View.OnClickListener click = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.btn_start:
                    //핸들러 동작(핸들러의 handleMessage()가 호출됨)
                    myHander.sendEmptyMessage(0); //특정한 handler를 동작하게 만드는 메서드
                    btn_start.setEnabled(false);//시작버튼 비활성화
                    btn_stop.setEnabled(true);//정지버튼 활성화


                    break;

                case R.id.btn_stop:
                    //핸들러 정지
                    myHander.removeMessages(0);
                    btn_start.setEnabled(true);//시작버튼 활성화

                    //없어도 됨
                    //btn_stop.setEnabled(false);//정지버튼 비활성화

                    break;
            }
        }
    };

    //Alt+enter -> split into ->dieable
    //핸들러 :  액티비티의 순환주기와는 별개로 독립적인 작업을 수행하기 위한영역
    //onDestory()메서드를 만나면 그 순간 함께 종료

    Handler myHander =new Handler(){
        @Override
        public void handleMessage(Message msg) {
            //super.handleMessage(msg);
            //1초간격으로 핸들러가 자기 자신을 반복수행
            //start누를 시, 계속 빨라짐
            myHander.sendEmptyMessageDelayed(0,1000);

            num++;
            txt.setText(""+num);

        }
    };

}