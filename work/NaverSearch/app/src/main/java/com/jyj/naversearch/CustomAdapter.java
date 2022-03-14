package com.jyj.naversearch;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {
    private ArrayList<ListItem> list;

    public CustomAdapter() {
        list = new ArrayList<>();
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    public Object getlink(int i) {
        return list.get(i).getLink();
    }

    @Override
    public long getItemId(int i) {
        return i;
    }



    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.custom_list,viewGroup,false);


        //vo변수와 같으면 좋은
        TextView title = view.findViewById(R.id.txt_txt);
        TextView descript = view.findViewById(R.id.txt_descript);
        //
        descript.setPaintFlags(descript.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        descript.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "경로 "+list.get(i).getLink(), Toast.LENGTH_SHORT).show();

                Intent change = new Intent(Intent.ACTION_VIEW);
                change.setData(Uri.parse(""+list.get(i).getLink()));
                context.startActivity(change);

            }
        });




        title.setText(list.get(i).getTitle());
        descript.setText(list.get(i).getDescript());


        return view;
    }

    public void addItem(ListItem vo){
        list.add(vo);
    }

    public void clear() {
        list.clear();
    }





}
