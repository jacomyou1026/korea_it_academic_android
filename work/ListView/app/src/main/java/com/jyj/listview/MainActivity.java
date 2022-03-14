package com.jyj.listview;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView = findViewById(R.id.ListView);
        ArrayList<String> list = new ArrayList<>();
        list.add("java");
        list.add("HTML5/CSS");
        list.add("javascript");
        list.add("jQuery");
        list.add("JSP");
        list.add("Spring");
        list.add("Android");

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list);
        //android가 기본적으로 제공하는 layout으로 쓰기에 그냥 R.layout으로만 쓰면 안된다.
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //i -->클릭한 아이템 인덱스번호
                Toast.makeText(MainActivity.this, list.get(i), Toast.LENGTH_SHORT).show();
            }
        });

        //길게 누르면 삭제
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                //i -->클릭한 아이템 인덱스번호
                list.remove(i); //삭제
                adapter.notifyDataSetChanged(); //데이터 갱신
                return true;
            }
        });

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count++;
                list.add("추가된 데이터 -" + count);
        //데이터가 바뀌면 무조건  adapter.notifyDataSetChanged(); 해주어야 함
                adapter.notifyDataSetChanged();
            }
        });

    }
}