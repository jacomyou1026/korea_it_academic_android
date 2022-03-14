package com.jyj.ex_0221;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;

public class WorkActivity extends AppCompatActivity {
    Button btn, archor;
    ImageView rabbit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work);

        btn = findViewById(R.id.btn);
        rabbit = findViewById(R.id.rabbit);
        archor = findViewById(R.id.archor);

        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                PopupMenu popupMenu = new PopupMenu(WorkActivity.this, archor);

                getMenuInflater().inflate(R.menu.menu_work, popupMenu.getMenu());


                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {


                        if (rabbit.getVisibility() == View.VISIBLE) {
                            AlertDialog.Builder dialog = new AlertDialog.Builder(WorkActivity.this);

                            dialog.setTitle("앱제목");
                            dialog.setPositiveButton("invisible", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    rabbit.setVisibility(View.INVISIBLE);


                                }
                            });
                            menuItem.setTitle("Visible");

                            dialog.show();

                        } else {
                            rabbit.setVisibility(View.VISIBLE);
                            menuItem.setTitle("inVisible");

                        }


                        return true;
                    }
                });

                popupMenu.show();
            }
        });


    }
}