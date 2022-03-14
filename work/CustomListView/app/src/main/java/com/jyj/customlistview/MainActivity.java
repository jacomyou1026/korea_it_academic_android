package com.jyj.customlistview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView list = findViewById(R.id.list);
        CustomAdapter adapter = new CustomAdapter();
        adapter.addItem(new ListItem(getDrawable(R.drawable.a_alarm),"테스트-1"));
        adapter.addItem(new ListItem(getDrawable(R.drawable.a_alarm),"테스트-2"));
        adapter.addItem(new ListItem(getDrawable(R.drawable.a_alarm),"테스트-3"));
        adapter.addItem(new ListItem(getDrawable(R.drawable.a_alarm),"테스트-4"));
        adapter.addItem(new ListItem(getDrawable(R.drawable.a_alarm),"테스트-5"));

        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ListItem item =(ListItem)adapter.getItem(i);
                Toast.makeText(MainActivity.this, item.getName(), Toast.LENGTH_SHORT).show();
            }
        });





    }
}