package com.jyj.customspinner;

import androidx.appcompat.app.AppCompatActivity;

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

        CustomAdapter adapter = new CustomAdapter();
        adapter.addItem(new SpinnerItem(getDrawable(R.drawable.menu_img00),"첫번째"));
        adapter.addItem(new SpinnerItem(getDrawable(R.drawable.menu_img01),"두번째"));
        adapter.addItem(new SpinnerItem(getDrawable(R.drawable.menu_img02),"세번째"));
        adapter.addItem(new SpinnerItem(getDrawable(R.drawable.menu_img03),"네번째"));

        TextView textView = findViewById(R.id.textView);
        Spinner spinner = findViewById(R.id.spinner);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                SpinnerItem item = (SpinnerItem) adapter.getItem(i);
                textView.setText(item.getNation());

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }
}