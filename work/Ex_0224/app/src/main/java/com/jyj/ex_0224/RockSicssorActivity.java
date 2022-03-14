package com.jyj.ex_0224;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class RockSicssorActivity extends AppCompatActivity {
    SwipeRefreshLayout refreshLayout;
    TextView wins,loses,draws;
    ImageView com[] = new ImageView[3];
    ImageView user[] = new ImageView[3];
    int count = 0; //그림을 움직이기 위한 변수
    int rot =  0;  // 그림을  움직이기  위한  변수
    int comRand = 0; //컴퓨터 난수발생

    Button start, exit,btn_Scissor,btn_rock,btn_paper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rocksisor);


        refreshLayout = findViewById(R.id.swipe);

        wins = findViewById(R.id.win);
        loses = findViewById(R.id.lose);
         draws= findViewById(R.id.draw);

        com[0]= (ImageView) findViewById(R.id.cr);
        com[1]= (ImageView) findViewById(R.id.cs);
        com[2]= (ImageView) findViewById(R.id.cp);

        user[0]= (ImageView) findViewById(R.id.ur);
        user[1]= (ImageView) findViewById(R.id.us);
        user[2]= (ImageView) findViewById(R.id.up);

        btn_rock = (Button)findViewById(R.id.btn_rock);
        btn_Scissor = (Button)findViewById(R.id.btn_Scissor);
        btn_paper = (Button)findViewById(R.id.btn_paper);

        start = findViewById(R.id.btn_start);
        exit = findViewById(R.id.btn_exit);

        //버튼 클릭 시
        start.setOnClickListener(btn_click);
        exit.setOnClickListener(btn_click);

        btn_rock.setOnClickListener(selectButton);
        btn_paper.setOnClickListener(selectButton);
        btn_Scissor.setOnClickListener(selectButton);




        //swipe변경
        //디스크의 사이즈 변경
        refreshLayout.setSize(SwipeRefreshLayout.LARGE);
        refreshLayout.setProgressViewEndTarget(true, 300);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //새로고침을 위해 당겼던 손을 놓는 순간
                h.sendEmptyMessageDelayed(0, 300);

            }
        });




    }

    View.OnClickListener btn_click = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.btn_start:
                        handler.sendEmptyMessage(0);

                    break;
                case R.id.btn_exit:
                    finish();
                    break;
            }
        }
    };
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            moving();
            handler.sendEmptyMessageDelayed(0, 50);


        }
    };

    void moving(){
        count++;
        rot = count%3;
        //1,2,0
        //0 : btn_rock
        //1 :btn_Scissor
        //2 :btn_paper

        visible(rot,rot);
        if(count == 3){
            count = 0;
        }
    }

    int win,lose,draw;

    View.OnClickListener selectButton = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
                comRand = new Random().nextInt(3);  //컴퓨터 가위,바위,보
                handler.removeMessages(0); //돌아가는걸 멈춤

                //유저의 결과
                int uResult =0;

                if(view == btn_rock){
                    uResult = 0;
                }
                else if(view == btn_Scissor){
                    uResult =1;
                }
                else{
                    uResult =2;
                }

                if(uResult-comRand==0||uResult-comRand== -2) {
                    Toast.makeText(RockSicssorActivity.this, "이겼습니다.", Toast.LENGTH_SHORT).show();
                    win++;
                    wins.setText(""+win);
                }else if(uResult - comRand == 0){
                    Toast.makeText(RockSicssorActivity.this, "비겼습니다.", Toast.LENGTH_SHORT).show();
                    draw++;
                    draws.setText(""+draw);
                }else{
                    Toast.makeText(RockSicssorActivity.this, "졌습니다.", Toast.LENGTH_SHORT).show();
                    lose++;
                    loses.setText(""+lose);
                }


                visible(comRand,uResult);


        }
    };

    //유저와 컴퓨터 이미지의 숨김처리를 하는 메서드
    void visible(int c, int u){
        com[c].setVisibility(View.VISIBLE);
        user[u].setVisibility(View.VISIBLE);



        for (int i=0; i<com.length;i++){
            if(i!=c){
                com[i].setVisibility(View.INVISIBLE);
            }
            if(i!=u){
                user[i].setVisibility(View.INVISIBLE);
            }
        }

    }





    Handler h = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            //super.handleMessage(msg);
            refreshLayout.setRefreshing(false);
        }
    };


}