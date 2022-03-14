package com.jyj.ex_0215;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class LifeCycleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life_cycle);

        //예외의 위치를 확인한거나 컴파일 시 데이터 값 등을 확인하기 위해 사용하는 클래스
        Log.i("My","--onCreate--");
    }//onCreate()

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("My","--destory--");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("My","--onPause--");
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("My","--onRestart--");
        //makeText(Context: 화면제어권자, 출력할 메세지, 노출시간)
        Toast.makeText(LifeCycleActivity.this,"리스타트 호출됨",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("My","--onResume--");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("My","--onStart--");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("My","--onStop--");
    }
    /*
        앱이 실행했을 때
        onCreate() - 앱을 맨 처음 실행했을때 딱 한번만 호출
        onStart()
        onResume()

        홈 버튼으로 앱을 바져나갔을때
        onPause()
        onStop()

        다시 앱으로 복귀했을 때
        onRestart()
        onStart()
        onResume()

        뒤로가기 버튼을 통해 앱을 완전히 종료
        onPause()
        onStop()
        onDestory() --> 완전히 종료될 때 딱 한번 호출
    */
}