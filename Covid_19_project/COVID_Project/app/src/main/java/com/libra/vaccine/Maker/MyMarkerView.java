package com.libra.vaccine.Maker;

import android.content.Context;
import android.widget.TextView;

import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.CandleEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointF;
import com.libra.vaccine.R;
import com.libra.vaccine.VO.SecondVO;

import java.util.ArrayList;

public class MyMarkerView extends MarkerView {

        private TextView tvContent;
        private  ArrayList<SecondVO> list;

        public MyMarkerView(Context context, int layoutResource, ArrayList<SecondVO> list) {
            super(context, layoutResource);
            tvContent = (TextView) findViewById(R.id.tvContentHead);
            this.list = list;
        }

        // callbacks everytime the MarkerView is redrawn, can be used to update the
        // content (user-interface)
        @Override
        public void refreshContent(Entry e, Highlight highlight) {

            if (e instanceof CandleEntry) {
                CandleEntry ce = (CandleEntry) e;
                tvContent.setText(String.format("%,d",ce.getHigh())+ "명");
            } else {
                tvContent.setText(list.get((int) e.getX()).getXdate()+"\n"+String.format("%,d",(int)e.getY()) + "명");
            }

            super.refreshContent(e, highlight);
        }

        @Override
        public MPPointF getOffset() {
            return new MPPointF(-(getWidth() / 2), -getHeight());
        }

    }

