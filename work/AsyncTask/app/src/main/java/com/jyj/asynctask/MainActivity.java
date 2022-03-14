package com.jyj.asynctask;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    ProgressBar bar;
    Button btnStart, btnStop;
    progressTask task = new progressTask();

    int value = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bar = findViewById(R.id.progressBar);
        btnStart = findViewById(R.id.btn_start);
        btnStop = findViewById(R.id.btn_stop);


        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                ProgressThread thread  =new ProgressThread();
//                thread.start();

                //task.doInBackground(); 이렇게 직접 실행x -- Thread의 start처럼
                task = new progressTask();
                task.execute(); //--doInBackground()실행

            }
        });
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                task.cancel(true);
            }
        });
    }

    /*
    value 값을 1초마다 5씩 증가시키는 스레드 작성
    value 값이 100되면 스레드 종료
    스레드 클레스 명: ProgressThread
     */
//    public class ProgressThread extends Thread {
//        @Override
//        public void run() {
//            while (value < 100) {
//                value += 5;
//                bar.setProgress(value ,true);
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//
//            }
//
//        }
//    }
    //AsyncTask(,,): task실행 필요 데이터 타입, 일을 진행 데이터 타입, task끝나고 결과의 데이터 타엡
    //거의 통일
    public class progressTask extends AsyncTask<Integer,Integer,Integer>{
        //Alt+enter:오버라이딩

        @Override
        protected Integer doInBackground(Integer... integers) {
            //테스크 처리 부분(doInBackground == run(thread))
            while (!isCancelled()){//작업정리 눌렀나?
                value++;
                if(value>=100) break;
                else{
                    publishProgress(value);//일 진행했을때 진행 작업 업데이트하는 거
                    //값 여러개 보낼 수 있음(배열)
                }
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return value;
        }

        @Override
        //테스크 종료 신호를 받았을 때
        protected void onCancelled() {
            bar.setProgress(0);
            Toast.makeText(MainActivity.this, "테스크를 종료합니다.", Toast.LENGTH_SHORT).show();

        }

        //현재진행상황 표시
        @Override
        //... ->가변매개변수 만들떄 (데이터를 여러개 만들고 받을떄 )
        //개수 몇개든지 받고 values를 배열로 받겠다는것
        protected void onProgressUpdate(Integer... values) {
            //현재 작업 진행 상태 표시 --> doInBackground에서 publishProgress가 호출
            super.onProgressUpdate(values);
            bar.setProgress(values[0]);
            Toast.makeText(MainActivity.this, values[0]+"% 충전중", Toast.LENGTH_SHORT).show();
        }

        @Override
        //doInBackground에서 return 값
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            //결과값 받아서 ui처리 부분
            bar.setProgress(integer);
            Toast.makeText(MainActivity.this, integer+"% 충전완료", Toast.LENGTH_SHORT).show();
        }

        @Override
        //작업 전 실행(초기화영역)-작업 준비
        protected void onPreExecute() {
            super.onPreExecute();
            //백그라운드 작업 전에 실행할 초기화 영역
            value =0;
            bar.setProgress(0);
        }
    }

}