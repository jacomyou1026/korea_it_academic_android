package com.jyj.ex_0215;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class WorkActivity extends AppCompatActivity {
    EditText et;
    Button btn;
    TextView txt ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work);
        et = findViewById(R.id.editTxt);
        btn = findViewById(R.id.editbtn);
        txt = findViewById(R.id.txtviewresult);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = et.getText().toString();
                 int center = s.length()/2;
                 String res= s.substring(0,center);
                 String change = s.substring(center+1); // 배우기 ->기우배
                String text = "";
                for (int i = change.length()-1; i>=0;i-- ){
                    text += change.charAt(i);
                    Log.i("work1",text);
                }
                String result = "";
                if(res.equals(text)){
                    result = "true";
                }else{
                    result="false";
                }

                txt.setText(result);
            }
        });


    }
}