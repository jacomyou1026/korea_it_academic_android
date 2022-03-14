package com.jyj.ex_0221;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

public class PopupMenuActivity extends AppCompatActivity {
    Button btn_show_menu,btn_anchor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup_menu);

        btn_show_menu = findViewById(R.id.btn_show_menu);
        btn_anchor = findViewById(R.id.btn_anchor);

        btn_show_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //버튼 클릭 시 팝업메뉴 생성
                PopupMenu popupMenu = new PopupMenu(PopupMenuActivity.this //팝업메뉴를 띄어줄 화면
                        ,btn_anchor ); //anchor : 팝업창을 띄울 기준


                //팝업메뉴가 보여줄 메뉴 참조파일을 등록
                getMenuInflater().inflate(R.menu.my_menu,popupMenu.getMenu());

                //팝업메뉴에 이벤트 감지자 등록
                popupMenu.setOnMenuItemClickListener(popupListner);
                //팝업메뉴 호출
                popupMenu.show(); //show()메소드는 가장 마지막에 호출하자!!
            }
        });
    }//onCreate()

    PopupMenu.OnMenuItemClickListener  popupListner= new PopupMenu.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()){
                case R.id.add:

                    break;

                case R.id.edit:
                    break;

                case R.id.fin:
                    finish();
                    break;
            }
            return true;
        }
    };

    @Override
    public void onBackPressed() {
        //휴대폰의 뒤로가기 버튼을 눌렀을 때 호출되는 메서드
        //super.onBackPressed(); //무조건 종료시킴
        Toast.makeText(this, "뒤로가기 누름", Toast.LENGTH_SHORT).show();
    }
}