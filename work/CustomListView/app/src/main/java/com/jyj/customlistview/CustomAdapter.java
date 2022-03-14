package com.jyj.customlistview;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends BaseAdapter {
    //리스트 뷰 아이템 항목에 대한 데이터를 저장하는 리스트
    private ArrayList<ListItem> list;

    public CustomAdapter() {
        list = new ArrayList<ListItem>();
    }

    //리스트 데이터 추가가
    public void addItem(ListItem item) {
        list.add(item);
    }

    //리스트 데이터 삭제
    public void removeItem(int i) {
        list.remove(i);
        notifyDataSetChanged();
    }

    public void removeItme(int i){
        list.remove(i);

    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    //현재 선택된 아이템
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        //context : 화면제어권자 : 내가 이걸 어떤 화면에 뿌려줄 것인디
        //ViewGroup : listview와 같은 레이아웃 ;view들이 모인그룹
        //View : 화면에 표시되거나 입력하기 위한 기본 객체

        //LayoutInflater
        //-xml로 정의한 layout 틀을 실제 메모리에 올려주는 역할
        //만들어놓은 xml을 실제 메모리에 올려준다.
        //  -LayoutInflater는 xml에 정의된 Resource를 View객체로 반환해주는 역할을 함
        //  -ViweGroup에서 getSystemService로 호출하여 inflater를 만들어주고 이 inflater를 이용해서 레이아웃 셋팅  해서 뷰에 리턴

        final Context context = viewGroup.getContext(); // 정보를 꺼낸다.
        LayoutInflater  inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);//안드로이드에서 제공하는 system읽어옴
        view = inflater.inflate(R.layout.list_view_item, viewGroup, false);//레이아웃 잡아주기
        //inflater :사전에 미리 선언했뒀던 R.layout.list_view_item이라는 레이아웃에 작성했던 xml의 메모리객체가 삽입
        //전에 만든 xml,현재


        //리스트뷰 아이템 한건에 대한 디자인 설정
        ImageView icon = view.findViewById(R.id.icon);
        TextView text = view.findViewById(R.id.txt_name);


        icon.setImageDrawable(list.get(i).getIcon());
        text.setText(list.get(i).getName());

        return view;

    }
}
