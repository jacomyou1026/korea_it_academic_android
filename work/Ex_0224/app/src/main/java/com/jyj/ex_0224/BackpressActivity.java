package com.jyj.ex_0224;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

public class BackpressActivity extends AppCompatActivity {

    //3초 뒤 종료
    int num = 3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_backpress);
    }//onCreate()

    @Override
    public void onBackPressed() {
        if(num !=3){
            finish();
        }else{
            Toast.makeText(this, "한 번 더 누르면 종료됩니다.", Toast.LENGTH_SHORT).show();

            //핸들러 호출
            backHandler.sendEmptyMessage(0);
            //what은 식별하기 위해서

        }
    }
    Handler backHandler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            //super.handleMessage(msg);
            backHandler.sendEmptyMessageDelayed(0,1000);
            if(num >0){
                --num;
            }else{
                num=3;
                backHandler.removeMessages(0);
            }


        }
    };
}