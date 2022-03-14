package com.jyj.ex_0222;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class IntentSubActivity extends AppCompatActivity {
    Button btn_prev;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_sub);
        btn_prev= findViewById(R.id.btn_prev);

        //btn_prev클릭 시
        // internetMainActivity.java로 화면 전환환
        btn_prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish(); //액티비티를 한장씩 사라지게 함

            }
        });


    }
}