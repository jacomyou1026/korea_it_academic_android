package com.jyj.customlistview;

import android.graphics.drawable.Drawable;

public class ListItem {
    private Drawable icon;
    private String name;

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //Alt+insert
    public ListItem(Drawable icon, String name) {
        this.icon = icon;
        this.name = name;
    }
    //생성자
    public ListItem( String name) {
        this.name = name;
    }


}
