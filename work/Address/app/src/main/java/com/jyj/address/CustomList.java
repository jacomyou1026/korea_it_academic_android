package com.jyj.address;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomList extends BaseAdapter {
    private ArrayList<AddressVO> list;

    public CustomList() {
        list= new ArrayList<>();
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
    public void addItem(AddressVO vo){
        list.add(vo);
    }
    public void removeItem(int i){
        list.remove(i);
    }
    public void clearList(){
        list.clear();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final Context context = viewGroup.getContext();
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view= inflater.inflate(R.layout.customlist,viewGroup,false);

        TextView name=   view.findViewById(R.id.name);
        TextView tell= view.findViewById(R.id.tell);

        name.setText(list.get(i).getName());
        tell.setText(list.get(i).getTel());

        return view;
    }
}
