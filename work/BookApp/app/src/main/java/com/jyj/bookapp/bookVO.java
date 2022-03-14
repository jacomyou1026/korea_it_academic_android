package com.jyj.bookapp;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import android.widget.TextView;

public class bookVO {
    private String txt_title;
    private String txt_info;
    private String imageView;
    private String link;

    public bookVO(String txt_title, String txt_info, String imageView, String link) {
        this.txt_title = txt_title;
        this.txt_info = txt_info;
        this.imageView = imageView;
        this.link = link;
    }

    public String getImageView() {
        return imageView;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getLink() {
        return link;
    }

    public void setImageView(String imageView) {
        this.imageView = imageView;
    }

    public String getTxt_title() {
        return txt_title;
    }

    public void setTxt_title(String txt_title) {
        this.txt_title = txt_title;
    }

    public String getTxt_info() {
        return txt_info;
    }

    public void setTxt_info(String txt_info) {
        this.txt_info = txt_info;
    }

    @Override
    public String toString() {
        return "bookVO{" +
                "imageView='" + imageView + '\'' +
                ", txt_title='" + txt_title + '\'' +
                ", txt_info='" + txt_info + '\'' +
                '}';
    }
}
