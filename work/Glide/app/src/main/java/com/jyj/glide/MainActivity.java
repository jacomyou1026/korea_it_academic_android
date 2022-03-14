package com.jyj.glide;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //인터넷 연결 필요
        // AndroidManifest.xml에 <uses-permission android:name="android.permission.INTERNET "/>
        String[] url = {
          "https://cdn.pixabay.com/photo/2022/02/09/17/22/cat-7003849_960_720.jpg",
                "https://cdn.pixabay.com/photo/2017/06/22/20/23/dandelion-2432381_960_720.jpg",
                "https://cdn.pixabay.com/photo/2022/02/04/10/31/cow-6992475_960_720.jpg",
                "https://cdn.pixabay.com/photo/2020/05/08/16/06/dog-5146351_960_720.jpg"
        };

        ImageView imageView = findViewById(R.id.imageView);
        ImageView imageView2 = findViewById(R.id.imageView2);
        ImageView imageView3 = findViewById(R.id.imageView3);
        ImageView imageView4 = findViewById(R.id.imageView4);

        //with : Cotext정보
        //load : 불러올 이미지, url,경로,바이트...
        //placeholder : Glide로 이미지 로딩시 로딩전에 보여줄 이미지
        //into : 이미지를 보여줄 view를 지정
        Glide.with(this).load( "https://cdn.pixabay.com/photo/2022/02/09/17/22/cat-7003849_960_720.jpg").placeholder(R.drawable.ic_launcher_background).into(imageView);
        Glide.with(this).load(url[1]).placeholder(R.drawable.ic_launcher_background).into(imageView2);
        Glide.with(this).load(url[2]).placeholder(R.drawable.ic_launcher_background).into(imageView3);
        Glide.with(this).load(url[3]).placeholder(R.drawable.ic_launcher_background).into(imageView4);

    }
}