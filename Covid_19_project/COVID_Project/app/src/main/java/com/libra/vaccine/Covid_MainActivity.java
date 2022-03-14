package com.libra.vaccine;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import com.libra.vaccine.AsyncTasks.Covid19Task;
import com.libra.vaccine.AsyncTasks.FirstAPI;
import com.libra.vaccine.CustomAdapters.GridListAdapter;
import com.libra.vaccine.AsyncTasks.SecondAPI;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Covid_MainActivity extends AppCompatActivity {
    Button vaccine_btn;
    //하은님
    Date currentTime = Calendar.getInstance().getTime();
    static GridListAdapter adapter;
    TextView txt_date;
    GridView gridView_region;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    String current = sdf.format(currentTime);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_covid_main);
        setTitle("COVID_19");
        //첫번째 api
        new FirstAPI(this).execute();
        //두번째 api
        new SecondAPI(this).execute();

        //하은님
        gridView_region = findViewById(R.id.gridView_region);

        adapter = new GridListAdapter();

        new Covid19Task(adapter).execute();

        gridView_region.setAdapter(adapter);

        txt_date = findViewById(R.id.txt_date);
        txt_date.setText(current+" 기준");

        //버튼
        vaccine_btn = findViewById(R.id.vaccine_btn);
        vaccine_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Covid_MainActivity.this,Vaccine_MainActivity.class);
                startActivity(i);
                overridePendingTransition(0, 0);
                finish();
            }
        });


    }//onCreat()
}