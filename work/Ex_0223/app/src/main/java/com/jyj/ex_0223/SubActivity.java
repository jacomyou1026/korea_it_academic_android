package com.jyj.ex_0223;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;


public class SubActivity extends AppCompatActivity {

    TextView txt_name,txt_age,txt_tell,txt_birth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        //getIntent()를 통해서 MainActivity에서 보내준 intent정보를
        //SubActivity에서도 가져다가 사용할 수 있다.

        Intent i = getIntent();

        txt_name=findViewById(R.id.txt_name);
        txt_age=findViewById(R.id.txt_age);
        txt_tell=findViewById(R.id.txt_tell);
        txt_birth=findViewById(R.id.txt_birth);

        //Main에서 보낸준 데이터 추출
        String name = i.getStringExtra("m_name");

        //"m_age"라는 key로 읽어올 데이터가 없을떄 기본값으로 defaultvalues를 사용
        int age = i.getIntExtra("m_age",0);

        String tell = i.getStringExtra("m_tell");

        String birth = i.getStringExtra("m_birth");

        txt_name.setText(name);
        //setText()메서드는 정수값만 파라미터로 들어가면 오류가 나기 때문에
        //문자열 구조로 변경하여 세팅해야한다.
        txt_age.setText(""+age);
        txt_tell.setText(tell);
        txt_birth.setText(birth);


    }
}