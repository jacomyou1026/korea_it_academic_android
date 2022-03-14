package com.jyj.address;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button btnRegister;
    EditText edtSearch;
    ImageButton btnSearch;
    DBHelper dbHelper;
    ListView listAddress;
    CustomList adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper =new DBHelper(this);

        listAddress = findViewById(R.id.list_address);
        btnRegister = findViewById(R.id.main_btn_registor);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,AddressRegister.class);
                startActivity(intent);
            }
        });

        //adapter연결
        adapter=new CustomList();
        listAddress.setAdapter(adapter);


        listAddress.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                AddressVO item = (AddressVO) adapter.getItem(i);
                Intent change = new Intent(MainActivity.this,AddressUpdateActivity.class);
                change.putExtra("name",item.getName());
                change.putExtra("tel",item.getTel());
                change.putExtra("id",item.getId());
                startActivity(change);
            }
        });

        edtSearch = findViewById(R.id.main_edit_serarch);
        btnSearch = findViewById(R.id.main_btn_serarch);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<AddressVO> list =  dbHelper.selectAddressVO(edtSearch.getText().toString());
                adapter.clearList();
                for(int i=0;i<list.size();i++){
                    adapter.addItem(list.get(i));
                }
                adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        dbHelper =  new DBHelper(this);
        Log.d("Address","onResume");
        List<AddressVO> list = dbHelper.selectAllAdressVO();
        Log.d("Address","onResume"+list.toString());
        adapter.clearList();
        for (int i=0;i<list.size();i++){
            adapter.addItem(list.get(i));
        }
        adapter.notifyDataSetChanged();



    }
}