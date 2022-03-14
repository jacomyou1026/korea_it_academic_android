package com.libra.vaccine.ButtonClickListeners;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.libra.vaccine.CustomAdapters.Vaccine_CustomAdapter;
import com.libra.vaccine.Vaccine_MainActivity;
import com.libra.vaccine.R;
import com.libra.vaccine.VO.VaccinVO;

import org.eazegraph.lib.charts.BarChart;

public class BtnClickLs {
    Context context;

    BarChart barChart,barChart2,barChart3;
    Button vcFirst_btn,vcSecond_btn,vcThird_btn;
    ListView vcUrlList;
    Vaccine_CustomAdapter adapter;
    //유진님
    Button Pfizer, Moderna, Janssen, AZ, Novavax;
    TextView contry, platform, cnt, time, storage, delivery, corona_cnt;


    public BtnClickLs(Context context) {
        this.context = context;
        //객체들 등록
        barChart = ((Vaccine_MainActivity) context ).findViewById(R.id.barChart);
        barChart2 = ((Vaccine_MainActivity) context ).findViewById(R.id.barChart2);
        barChart3 = ((Vaccine_MainActivity) context ).findViewById(R.id.barChart3);

        vcFirst_btn = ((Vaccine_MainActivity) context ).findViewById(R.id.vcFirst_btn);
        vcSecond_btn = ((Vaccine_MainActivity) context ).findViewById(R.id.vcSecond_btn);
        vcThird_btn = ((Vaccine_MainActivity) context ).findViewById(R.id.vcThird_btn);

        vcFirst_btn.setOnClickListener(barChartClick);
        vcSecond_btn.setOnClickListener(barChartClick);
        vcThird_btn.setOnClickListener(barChartClick);

        //유진님 객체
        Pfizer = ((Vaccine_MainActivity) context ).findViewById(R.id.Pfizer);
        Moderna = ((Vaccine_MainActivity) context ).findViewById(R.id.Moderna);
        Janssen = ((Vaccine_MainActivity) context ).findViewById(R.id.Janssen);
        AZ = ((Vaccine_MainActivity) context ).findViewById(R.id.AZ);
        Novavax = ((Vaccine_MainActivity) context ).findViewById(R.id.Novavax);

        contry = ((Vaccine_MainActivity) context ).findViewById(R.id.contry);
        platform = ((Vaccine_MainActivity) context ).findViewById(R.id.platform);
        cnt = ((Vaccine_MainActivity) context ).findViewById(R.id.cnt);
        time =((Vaccine_MainActivity) context ).findViewById(R.id.time);
        storage = ((Vaccine_MainActivity) context ).findViewById(R.id.storage);
        delivery = ((Vaccine_MainActivity) context ).findViewById(R.id.delivery);

        Pfizer.setOnClickListener(infoClick);
        Moderna.setOnClickListener(infoClick);
        Janssen.setOnClickListener(infoClick);
        AZ.setOnClickListener(infoClick);
        Novavax.setOnClickListener(infoClick);

        //url리스트
        vcUrlList = ((Vaccine_MainActivity) context ).findViewById(R.id.vc_url_list);
        adapter = new Vaccine_CustomAdapter();
        adapter.addItem(new VaccinVO("코로나 19예방접종", "사전예약", "https://ncv.kdca.go.kr/kor/img/main/main_top_ban_01.png"));
        adapter.addItem(new VaccinVO("코로나 19 예방접종 후", "건강상태 확인하기", "https://ncv.kdca.go.kr/kor/img/main/main_top_ban_02.png"));
        adapter.addItem(new VaccinVO("코로나 19", "전자예방접종증명", "https://ncv.kdca.go.kr/kor/img/main/main_top_ban_03.png"));
        adapter.addItem(new VaccinVO("코로나 19", "예방접종 후 이상반응", "https://ncv.kdca.go.kr/kor/img/main/main_top_ban_09.png"));
        vcUrlList.setAdapter(adapter);
        vcUrlList.setOnItemClickListener(UrlClick);

    }//기본생성자


    //버튼클릭 리스너들========================================================================================================

    //바차트 버튼 클릭
    View.OnClickListener barChartClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            vcFirst_btn.setEnabled(true);
            vcSecond_btn.setEnabled(true);
            vcThird_btn.setEnabled(true);

            vcFirst_btn.setBackgroundResource(R.drawable.btn_click);
            vcSecond_btn.setBackgroundResource(R.drawable.btn_click);
            vcThird_btn.setBackgroundResource(R.drawable.btn_click);

            vcFirst_btn.setTextColor(Color.parseColor("#D3D3D3"));
            vcSecond_btn.setTextColor(Color.parseColor("#D3D3D3"));
            vcThird_btn.setTextColor(Color.parseColor("#D3D3D3"));

            barChart.setVisibility(View.GONE);
            barChart2.setVisibility(View.GONE);
            barChart3.setVisibility(View.GONE);

            switch (view.getId()){
                case R.id.vcFirst_btn:
                    vcFirst_btn.setEnabled(false);
                    vcFirst_btn.setBackgroundResource(R.drawable.btn);
                    vcFirst_btn.setTextColor(Color.parseColor("#5673eb"));
                    barChart.setVisibility(View.VISIBLE);
                    barChart.startAnimation();
                    break;
                case R.id.vcSecond_btn:
                    vcSecond_btn.setEnabled(false);
                    vcSecond_btn.setBackgroundResource(R.drawable.btn);
                    vcSecond_btn.setTextColor(Color.parseColor("#5673eb"));
                    barChart2.setVisibility(View.VISIBLE);
                    barChart2.startAnimation();
                    break;
                case R.id.vcThird_btn:
                    vcThird_btn.setEnabled(false);
                    vcThird_btn.setBackgroundResource(R.drawable.btn);
                    vcThird_btn.setTextColor(Color.parseColor("#5673eb"));
                    barChart3.setVisibility(View.VISIBLE);
                    barChart3.startAnimation();
                    break;
            }

        }
    };


    //백신정보 클릭릭
    View.OnClickListener infoClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            Pfizer.setBackgroundResource(R.drawable.btn_click);
            Moderna.setBackgroundResource(R.drawable.btn_click);
            Janssen.setBackgroundResource(R.drawable.btn_click);
            AZ.setBackgroundResource(R.drawable.btn_click);
            Novavax.setBackgroundResource(R.drawable.btn_click);

            switch (view.getId()) {

                case R.id.Pfizer:

                    contry.setText("미국/독일");
                    platform.setText("mRNA 백신(핵산백신)");
                    cnt.setText("2회");
                    time.setText("21일");
                    storage.setText("-90°C ~ -60°C(6개월)");
                    delivery.setText("2~8°C(5일)");
                    Pfizer.setBackgroundResource(R.drawable.info_btn_click);
                    break;

                case R.id.Moderna:
                    contry.setText("미국");
                    platform.setText("mRNA 백신(핵산백신)");
                    cnt.setText("2회");
                    time.setText("28일");
                    storage.setText("-25°C ~ -15°C(7개월)");
                    delivery.setText("2~8°C(30일)");
                    Moderna.setBackgroundResource(R.drawable.info_btn_click);
                    break;

                case R.id.Janssen:
                    contry.setText("미국");
                    platform.setText("바이러스 벡터 백신");
                    cnt.setText("1회 * (임상결과에 따른 변경가능)");
                    time.setText("-");
                    storage.setText("-25°C ~ -15°C(24개월)");
                    delivery.setText("2~8°C(4.5개월)");
                    Janssen.setBackgroundResource(R.drawable.info_btn_click);
                    break;

                case R.id.AZ:
                    contry.setText("영국");
                    platform.setText("바이러스 벡터 백신");
                    cnt.setText("2회");
                    time.setText("8~12주");
                    storage.setText("2~8°C(6개월)");
                    delivery.setText("2~8°C(6개월)");
                    AZ.setBackgroundResource(R.drawable.info_btn_click);
                    break;

                case R.id.Novavax:
                    contry.setText("미국");
                    platform.setText("유전자 재조합 백신");
                    cnt.setText("2회");
                    time.setText("21일");
                    storage.setText("2~8°C(5개월)");
                    delivery.setText("2~8°C(5개월)");
                    Novavax.setBackgroundResource(R.drawable.info_btn_click);
                    break;
            }
        }
    };

    //UrlListClick
    AdapterView.OnItemClickListener UrlClick = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            VaccinVO item = (VaccinVO) adapter.getItem(i);
            Intent change;
            switch (i) {
                case 0:
                    change = new Intent(Intent.ACTION_VIEW, Uri.parse("https://ncvr2.kdca.go.kr/"));
                    ((Vaccine_MainActivity) context ).startActivity(change);
                    break;
                case 1:
                    change = new Intent(Intent.ACTION_VIEW, Uri.parse("https://nip.kdca.go.kr/irgd/covid.do?MnLv1=3"));
                    ((Vaccine_MainActivity) context ).startActivity(change);

                    break;
                case 2:
                    change = new Intent(Intent.ACTION_VIEW, Uri.parse("https://ncv.kdca.go.kr/coov"));
                    ((Vaccine_MainActivity) context ).startActivity(change);

                    break;
                case 3:
                    change = new Intent(Intent.ACTION_VIEW, Uri.parse("https://ncv.kdca.go.kr/menu.es?mid=a12601010000"));
                    ((Vaccine_MainActivity) context ).startActivity(change);

                    break;
            }
        }
    };


}
