package com.jyj.customspinner;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {
    ArrayList<SpinnerItem> list;

    public CustomAdapter() {
        list = new ArrayList<SpinnerItem>();
    }

    //아이템 추가
    public void addItem(SpinnerItem item){
        list.add(item);
    }

    //아이템 삭제
    public void removeItem(SpinnerItem item){
        list.remove(item);
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

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final Context context = viewGroup.getContext();
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.custom_spinner_item,viewGroup,false);

        SpinnerItem item = list.get(i);
        ImageView icon = view.findViewById(R.id.icon);
        icon.setImageDrawable(item.getIcon());
        return view;
    }
}
