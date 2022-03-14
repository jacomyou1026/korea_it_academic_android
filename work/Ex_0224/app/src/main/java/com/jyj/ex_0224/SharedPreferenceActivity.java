package com.jyj.ex_0224;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SharedPreferenceActivity extends AppCompatActivity {
    TextView value;
    Button value_up,value_down,btn_reset;

    int n = 0;

    //DB를 사용하기에는 너무 간략한 정보여서 DB가 오히려 낭비라고 생각되는 경우가 있다.
    //이런 경우에 기본 자료형 형태의 데이터들을 간단한 구조로 저장할 수 있도록 해 주는 클래스
    SharedPreferences pref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_preference);


        //저장을 위한 객체
        pref = getSharedPreferences("SHARE",MODE_PRIVATE); //MODE_PRIVATE  :외부 접근 불가하도록
        //onPause보다 onCreate가 먼저 호출되기에 기본값이 필요하기에 n을 넣음
        n = pref.getInt("my_n",
                n);//오류나지 않도록하기위한 기본값


        value = findViewById(R.id.value);
        value_up = findViewById(R.id.btn_value_up);
        value_down = findViewById(R.id.btn_value_down);
        btn_reset = findViewById(R.id.btn_reset);

        value.setText(""+n);

        value_up.setOnClickListener(click);
        value_down.setOnClickListener(click);
        btn_reset.setOnClickListener(click);
    }//onCreate()



    View.OnClickListener click = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.btn_value_up:
                    ++n;
                    break;

                case R.id.btn_value_down:
                    --n;
                    break;

                case R.id.btn_reset:
                    n = 0;
                    break;

            }
            value.setText(""+n);
        }
    };

    @Override
    protected void onPause() {
        super.onPause();
        //애플리케이션이 일시정지 상태가 됐을때 현재n값을 저장
        SharedPreferences.Editor edit = pref.edit();
        edit.putInt("my_n",n);
        edit.commit();//커밋을 해야 n값이 물리적으로 저장된다.

    }

}