package com.jyj.listviewquest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //edt_text데이터 입력 후 추가 버튼(btn_add)을 클릭 아래 리스트뷰(list)에 입력내용을
    //아이템 항목으로 추가 추가한 후 edt_text에 입력내용 초기화
    Button btnAdd;
    EditText editText;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd = findViewById(R.id.btn_add);
        editText = (EditText)findViewById(R.id.edit_text);
        listView = findViewById(R.id.list);

        ArrayList<String> list = new ArrayList<>();

        //adapter
        //데이터1 - 어뎁터 -> 리스트 아이템1
        //데이터를 화면에 연결

        ArrayAdapter adapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1,list);
        listView.setAdapter(adapter);



        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text =  editText.getText().toString();
                Log.i("text",text);


                if(text.equals("")){
                    Toast.makeText(MainActivity.this,"값이 없습니다.",Toast.LENGTH_SHORT).show();
                }else{
                    //입력한 데이터 추출
                    list.add(text);
                    Toast.makeText(MainActivity.this,text+"를 추가합니다.",Toast.LENGTH_SHORT).show();

                    adapter.notifyDataSetChanged(); //데이터 갱신
                    editText.setText(""); //입력된 데이터 초기화
                }
            }
        });

        //삭제
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this,list.get(i)+"를 삭제합니다.",Toast.LENGTH_SHORT).show();
                list.remove(i);

                adapter.notifyDataSetChanged(); //데이터 갱신
                //ListView에서 적용된 어뎁터는 adapterView를 이용해서 꺼낼 수 있음
                //adapterView.getAdapter(); --> adapter랑 동일

            }
        });

    }
}