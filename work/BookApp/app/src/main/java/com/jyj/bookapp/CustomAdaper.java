package com.jyj.bookapp;

import android.content.Context;
import android.icu.text.CaseMap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class CustomAdaper extends BaseAdapter {

    ArrayList<bookVO> list;

    public CustomAdaper() {
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

    @Override
    public long getItemId(int i) {
        return i;
    }

    public void addItem(bookVO item){
        list.add(item);
    }

    public void clear(){
        list.clear();
    }


    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.list_custom,viewGroup,false);

        bookVO vo = list.get(i);

        ImageView thumnail = view.findViewById(R.id.image_tumnaim);
        Glide.with(view).load(vo.getImageView()).placeholder(R.drawable.img).into(thumnail);

        TextView title = view.findViewById(R.id.txt_title);
        title.setText(vo.getTxt_title());
        TextView txtinfo = view.findViewById(R.id.txt_info);
        txtinfo.setText(vo.getTxt_info());

        return view;
    }
}
