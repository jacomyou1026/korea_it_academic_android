package com.libra.vaccine.CustomAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.libra.vaccine.R;
import com.libra.vaccine.VO.VaccinVO;

import java.util.ArrayList;

public class Vaccine_CustomAdapter extends BaseAdapter {
     ArrayList<VaccinVO> list;

    public Vaccine_CustomAdapter() {
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

    public void addItem(VaccinVO item){
        list.add(item);
    }

    public void clear(){
        list.clear();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.vaccine_list_custom,viewGroup,false);

        VaccinVO vo = list.get(i);

        ImageView image = view.findViewById(R.id.image);
        Glide.with(view).load(vo.getImageView()).placeholder(R.drawable.ic_launcher_background).into(image);


        TextView title = view.findViewById(R.id.txt_title);
        title.setText(vo.getTxt_title());
        TextView txtinfo = view.findViewById(R.id.txt_info);
        txtinfo.setText(vo.getTxt_info());

        return view;
    }
}
