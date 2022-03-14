package com.jyj.ex_0216;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class VisibleActivity extends AppCompatActivity {
    Button b1,b2;
    ImageView back1,back2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visible);
        b1 = findViewById(R.id.b1);
        b2 = findViewById(R.id.b2);
        back1 = findViewById(R.id.back1_img);
        back2 = findViewById(R.id.back2_img);

        b1.setOnClickListener(click);
        b2.setOnClickListener(click);
    }
    //이미지 같은 drawable에 넣을 경우 소문자로 넣어야함
    View.OnClickListener click = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.b1:
                    back1.setVisibility(View.VISIBLE);
                    back2.setVisibility(View.INVISIBLE);

                    break;
                case R.id.b2:
                    back2.setVisibility(View.VISIBLE);
                    back1.setVisibility(View.INVISIBLE);
                    break;
            }

        }

    };
}