package com.jyj.spinner;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Spinner spinner =findViewById(R.id.spinner);
        TextView textView = findViewById(R.id.textView);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //spinner.getSelectedItem().toString()
                textView.setText(spinner.getSelectedItem().toString());
                //adapterView.getAdapter();로 해도 됨
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });




    }


}