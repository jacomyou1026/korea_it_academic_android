package com.libra.vaccine.CustomAdapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.libra.vaccine.R;
import com.libra.vaccine.VO.RegionConfirmedList;

import java.util.ArrayList;

public class GridListAdapter extends BaseAdapter {

    ArrayList<RegionConfirmedList> list;

    public GridListAdapter() {
        list = new ArrayList<>();
    }

    //주소로을 넣어짐
    //문제
    public void addItem(RegionConfirmedList item) {
        Log.d("what",item.getRegionName()+","+item.getReg_defCnt()+","+item.getReg_death());
        list.add(item);
    }


    public void clear() {
        list.clear();
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

        Context context = viewGroup.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.list_item, viewGroup, false);

        RegionConfirmedList vo = list.get(i);

        // xml에 TextView를 참조
        //지역
        TextView regionName = view.findViewById(R.id.regionName);
        regionName.setText(vo.getRegionName());


        //확진자
        TextView reg_defCnt = view.findViewById(R.id.reg_defCnt);
        int def = Integer.parseInt(vo.getReg_defCnt());
        String defCnt = String.format("%,d",def);
        reg_defCnt.setText(defCnt);

        //전일
        TextView reg_incDec = view.findViewById(R.id.reg_incDec);
        int dec = Integer.parseInt(vo.getReg_incDec());
        String incDec = String.format("%,d",dec);

        reg_incDec.setText("+"+incDec);

        //사망
        TextView reg_death = view.findViewById(R.id.reg_death);
        int deth = Integer.parseInt(vo.getReg_death());

        String death = String.format("%,d",deth);
        reg_death.setText(death);

        return view;
    }


}
