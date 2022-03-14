package com.jyj.ex_0221;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class AlertDialogActivity extends AppCompatActivity {

    //AlertDialog : 최대 3개의 버튼을가지고 메시지를 출력해 줄 수 있는 팝업창

    Button btn_show;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert_dialog);

        btn_show = findViewById(R.id.btn_show);

        btn_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //AlertDialog객체준비
                AlertDialog.Builder dialog = new AlertDialog.Builder(AlertDialogActivity.this);

                dialog.setTitle("App Title");
                dialog.setMessage("다이얼로그에 표시될 내용\n 클릭하시오");

                //alert dialog에서 사용 가능한 버튼1
                //예
                dialog.setPositiveButton("posi", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(AlertDialogActivity.this, "posi누름", Toast.LENGTH_SHORT).show();
                    }
                });
                //같은 감지자(setPositiveButton)는 밑에 있는것이 나옴
//                dialog.setPositiveButton("posi", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        Toast.makeText(AlertDialogActivity.this, "posi2누름", Toast.LENGTH_SHORT).show();
//                    }
//                });


                
                //alert dialog에서 사용 가능한 버튼2
                //아니오
                dialog.setNegativeButton("nega", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //감지자에 이벤트 처리를 따로 등록해두지 않으면 다이얼로그만 종료도니다.

                    }
                });

                //alert dialog에서 사용 가능한 버튼3
                //감지자 null로 지정하면 이벤트 처리 없이 다이얼로그만 종료
                //나중에
                dialog.setNeutralButton("neut", null);

                //다이얼로그 화면에 노출
                dialog.show();
                

            }
        });


    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        AlertDialog.Builder alert = new AlertDialog.Builder(AlertDialogActivity.this);
        alert.setTitle("Apptitle");
        alert.setMessage("앱을 종료하시겠습니까?");
        //setNegativeButton
        alert.setNegativeButton("네", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish(); //액티비티 종료
            }
        });
        alert.setPositiveButton("아니요",null);
        alert.show();

    }
}