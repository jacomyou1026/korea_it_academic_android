package com.jyj.address;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AddressUpdateActivity extends AppCompatActivity {

    TextView update_edt_name,update_edt_tell;
    Button  update_edt_add,update_cancel,update_btn_delete;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_address_update);


        update_edt_name = findViewById(R.id.update_edt_name);
        update_edt_tell =  findViewById(R.id.update_tell);
        update_cancel = findViewById(R.id.update_cancel);
        update_edt_add = findViewById(R.id.update_edt_add);
        update_btn_delete = findViewById(R.id.update_btn_delete);


        //화면전환시 값 가져오기
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String tel = intent.getStringExtra("tel");
        int id = intent.getIntExtra("id",0);

        update_edt_name.setText(name);
        update_edt_tell.setText(tel);

        
        //삭제
        update_btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper dbHelper = new DBHelper(getApplicationContext());
                dbHelper.delete(id);
                finish();

            }
        });
        //수정
        update_edt_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper dbHelper = new DBHelper(getApplicationContext());
                AddressVO vo = new AddressVO(id,update_edt_name.getText().toString(),update_edt_tell.getText().toString());
                dbHelper.update(vo);
                finish();
            }
        });
        
        //취소
        update_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });




    }

}