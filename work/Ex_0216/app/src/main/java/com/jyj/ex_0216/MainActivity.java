package com.jyj.ex_0216;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText et;
    Button btn_ok;
    TextView txt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //이벤트에 사용할 객체 검색
        et = findViewById(R.id.et);
        btn_ok = findViewById(R.id.btn_ok);
        txt = findViewById(R.id.txt_result);

        //ok버튼 클릭시 이벤트 처리 감지
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ori = et.getText().toString(); //EditText에 쓰여진 원본 문자열
                String rev ="";

                //ori데이터를 뒤집어서 rev변수에 저장
                for(int i = ori.length()-1;i>=0;i--){
                    rev +=ori.charAt(i);
                }

                if(ori.equals(rev)){
                    txt.setText("회문수입니다.");
                }else{
                    txt.setText("회문수가 아닙니다.");
                }


            }
        });
    }
}