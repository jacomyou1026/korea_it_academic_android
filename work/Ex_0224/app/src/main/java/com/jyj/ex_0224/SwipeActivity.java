package com.jyj.ex_0224;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public class SwipeActivity extends AppCompatActivity {
    SwipeRefreshLayout refreshLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe);

        refreshLayout = findViewById(R.id.swipe);

        //디스크의 배경색을 변경하는 속성
        refreshLayout.setProgressBackgroundColorSchemeColor(Color.parseColor("#aaaaff"));

        //디스크의 사이즈를 변경하는 메서드
        refreshLayout.setSize(SwipeRefreshLayout.LARGE); //LARGE / DEFAULT

        //디스크가 당겨지는 좌표값을 변경
        //scale- 당길때 점점 크기가 커짐
        refreshLayout.setProgressViewEndTarget(true,300);

        //디스크 안에있는 progress바가 바뀜
        //색깔 바꾸기 기능
        refreshLayout.setColorSchemeResources(R.color.c1,R.color.c2,R.color.c3,R.color.c4);

        //로딩이 시작되는 시점을 알 수 있도록 감지하는 이벤트 감지자
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //새로고침을 위해 당겼던 손을 놓는 순간 호출되는 메서드

                //3초 뒤 h핸들러 호출
                h.sendEmptyMessageDelayed(0,300);
            }
        });




    }//onCreate;

    Handler h = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            //로딩이 끝난것으로 가정
            refreshLayout.setRefreshing(false);//디스크 제거
        }
    };
}