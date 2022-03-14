package com.jyj.ex_0221;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    //menu폴더를 만들어주어야함
    //menu폴더 resource xml을 소문자와 언더바(_)만 가능

    //메뉴 호출을 위한 메서드 오버라이드
    //하나씩 썻음
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        //R.menu.my_menu이 -->  menu로
        getMenuInflater().inflate(R.menu.my_menu,menu);
        return true;

    }


    //메뉴 클릭을 감지하는 이벤트 감지자

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.add:
                Toast.makeText(this, "추가", Toast.LENGTH_SHORT).show();
                break;
            case R.id.edit:
                Toast.makeText(this, "수정", Toast.LENGTH_SHORT).show();
                break;
            case R.id.fin:
                finish();//현재 보여지고 있는 액티비티(MenuActivity)를 종료
                break;
        }

        return true;
    }
}