package com.jyj.customspinner;

import android.graphics.drawable.Drawable;

public class SpinnerItem {
    private Drawable icon;
    private String nation;

    public SpinnerItem(Drawable icon, String nation) {
        this.icon = icon;
        this.nation = nation;
    }

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }
}
