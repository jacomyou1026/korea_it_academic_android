package com.jyj.ex_0222;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;

public class IntentMainActivity extends AppCompatActivity {
    Button btn_link,btn_call,btn_sms,btn_camera,btn_gallery,btn_next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_main);

        btn_link=findViewById(R.id.btn_link);
        btn_call=findViewById(R.id.btn_call);
        btn_sms=findViewById(R.id.btn_sms);
        btn_camera=findViewById(R.id.btn_camera);
        btn_gallery=findViewById(R.id.btn_gallery);
        btn_next=findViewById(R.id.btn_next);

        //웹페이지 액티비티로 전환
        btn_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent클래스는 액티비티간의 화면 전환을 위한 클래스다
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse("http://www.naver.com"));
                startActivity(i);//화면전환을 위한 메서드
            }
        });


        //전화연결
        btn_call.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                //다이얼 화면
//                Intent i = new Intent(Intent.ACTION_DIAL);
//                i.setData(Uri.parse("tel:01011112222"));
                //tel:,http:...이것들로 tel인지 웹페이지 이동인지 파악
//                startActivity(i);

                //전화를 즉시 걸어주는 기능
                //오류 : 강제전화걸어주는 거는 사용자권한이 있어야함
                //그래서 AndroidManifest.xml에서 사용자 권한등록해주어야함 --그래도 오류 사용자가 수락을 안했기에
                //so emulator에서 설정 - > Ex_0222->권한 수락해주어야함

                //AndroidManifest.xml에 Call_phone권한(permission)이필요요
               Intent i = new Intent(Intent.ACTION_CALL);
                i.setData(Uri.parse("tel:0101112222"));
                startActivity(i);

            }
        });

        //문자전송화면
        btn_sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_SENDTO);
                i.setData(Uri.parse("smsto:01011031111"));
                i.putExtra("sms_body","이걸 전송할테야");
                startActivity(i);
            }
        });

        //카메라 연결
        btn_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //카메라연결
//                Intent i  =new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                startActivity(i);

                //동영상 연결
                Intent i = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                startActivity(i);

            }
        });

        //갤러리 화면
        btn_gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_GET_CONTENT);
                i.setType("image/*"); //-- 모든 타입의 이미지 확인 /동영상 못봄
                //i.setType("*/*"); //모든 타입 이미지,영상 확인
                startActivity(i);
            }
        });

        //사용자의 액티비티간 화면전환
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(IntentMainActivity.this, //현재 액티비티
                        IntentSubActivity.class); // 화면 전화할 액티비티
                startActivity(i);
                //액티비티들이 겹쳐지면서 쌓임
            }
        });





    }
}