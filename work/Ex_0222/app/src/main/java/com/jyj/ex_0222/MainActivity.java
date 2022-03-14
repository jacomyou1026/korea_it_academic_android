package com.jyj.ex_0222;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;

public class MainActivity extends AppCompatActivity {
    Button btn, anchor;
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.btn);
        anchor = findViewById(R.id.anchor);
        img = findViewById(R.id.img);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(MainActivity.this, anchor);
                getMenuInflater().inflate(R.menu.my_menu, popupMenu.getMenu());

                //팝업메뉴에 감지자 등록
                popupMenu.setOnMenuItemClickListener(click);
                popupMenu.show();
            }
        });


    }

    PopupMenu.OnMenuItemClickListener click = new PopupMenu.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.menu1:
                    //alertDialog생성
                    AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                    dialog.setTitle("앱 제목");
                    String img_visible = "visible";
                    if (img.getVisibility() == View.VISIBLE) {
                        img_visible = "invisible";
                    }
                    dialog.setPositiveButton(img_visible, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            if (img.getVisibility() == View.VISIBLE) {
                                img.setVisibility(View.INVISIBLE);
                            }else{
                                img.setVisibility(View.VISIBLE);

                            }
                        }
                    });
                    dialog.show();

                    break;
            }
            return true;
        }
    };

}