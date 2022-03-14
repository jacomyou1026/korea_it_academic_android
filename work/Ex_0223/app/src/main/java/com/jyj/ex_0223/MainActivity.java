package com.jyj.ex_0223;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    EditText edit_name,edit_age,edit_tell,edit_birth;
    Button btn_date,btn_send;
    Dialog dialog; //달력 팝업을 위한 다이얼로그



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edit_name = findViewById(R.id.edit_name);
        edit_age = findViewById(R.id.edit_age);
        edit_tell = findViewById(R.id.edit_tell);
        edit_birth = findViewById(R.id.edit_birth);

        btn_date = findViewById(R.id.btn_date);
        btn_send = findViewById(R.id.btn_send);

        btn_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //캘린더 클래스를 사용하여 오늘 날짜 구해온다.
                Calendar now = Calendar.getInstance();
                //오늘 년도로(나오게 하기위해서)
                int y = now.get(Calendar.YEAR);//년
                int m= now.get(Calendar.MONTH);//월
                int d = now.get(Calendar.DAY_OF_MONTH);// 몇 일

                //달력 선택을 위한 다이얼로그 생성
                dialog = new DatePickerDialog(MainActivity.this // context
                        ,dateListner, // 날짜 선택 감지자
                         y,m,d);//년, 월, 일

                dialog.show();
            }
        });

        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //이름,나이,전화번호,생일 SubActivity로 전달
                String name  = edit_name.getText().toString();
                int  age  =Integer.parseInt(edit_age.getText().toString());
                String tell  = edit_tell.getText().toString();
                String birth  = edit_birth.getText().toString();

                //전송 버튼을 누르면 위의 name,age,tel정보를 SubActivity로 전달되어야 한다.
                //화면을 전환하면서 값을 전달하는 개념이므로 반드시 Intent클래스가 필요
                Intent i = new Intent(MainActivity.this,SubActivity.class);

                //화면전환에 사용되는 i에 정보를 저장
                //String/int이든 상관없음
                i.putExtra("m_name",name); //String
                i.putExtra("m_age",age); //int
                i.putExtra("m_tell",tell);
                i.putExtra("m_birth",birth);

                startActivity(i);


            }
        });

    }

    DatePickerDialog.OnDateSetListener dateListner = new DatePickerDialog.OnDateSetListener() {
        //처음에 y,m,d가 year ,month,day로 변경
        //추후 다른 날 선택시 year ,month,day가가바뀌
        @Override
        public void onDateSet(DatePicker datePicker, int year, int month, int day) {

            //인자 중 month는 1월->0 , 2월 - > 1,
            //날짜 형식: 2022-02-23 , 2022/02/24
            String str= String.format("%d-%02d-%02d",year,month+1,day);

            //얻어온 생일을 editText에 넣는다.
            edit_birth.setText(str);

        }
    };


}