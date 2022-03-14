package com.jyj.ex_0215;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class EventActivity extends AppCompatActivity {

    Button red,green,blue;
    TextView txt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        //레이아웃에 있는 id를 기반으로 각 객체들을 검색
        red = findViewById(R.id.btn_red);
        green = findViewById(R.id.btn_green);
        blue = findViewById(R.id.btn_blue);
        txt = findViewById(R.id.txt);

        //버튼에 이벤트감지자 등록
        red.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //red버튼이 클릭되었을 때 실행되는 메서드
                txt.setText("빨강");

                //parseColor()메서드에서 rgb값을 세자리로 줄여서 쓸 수 없다.
                txt.setBackgroundColor(Color.parseColor("#ff0000"));

            }
        });

        green.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt.setText("초록");
                txt.setBackgroundColor(Color.parseColor("#228B22"));
            }
        });
        blue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt.setText("블루");
                txt.setBackgroundColor(Color.parseColor("#0000FF"));
            }
        });


    }
}