package com.libra.vaccine;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.libra.vaccine.AsyncTasks.VaccineAPI;
import com.libra.vaccine.AsyncTasks.VaccineSetChart;
import com.libra.vaccine.ButtonClickListeners.BtnClickLs;

public class Vaccine_MainActivity extends AppCompatActivity {

    Button covid_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vaccine_activity_main);

        //1차 2차 3차 접종률 + 유진님차트(전체접종률,60세 이상 접종률)
        new VaccineAPI(Vaccine_MainActivity.this).execute();

        //1,2,4차 Bar,Pie Chart 세팅
        new VaccineSetChart(this).execute();

        //객체 생성 및 버튼 클릭 리스너
        new BtnClickLs(this);

        //밑 버튼
        covid_btn = findViewById(R.id.covid_btn2);
        covid_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Vaccine_MainActivity.this, Covid_MainActivity.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
                finish();
            }
        });

    }//onCreate




}
