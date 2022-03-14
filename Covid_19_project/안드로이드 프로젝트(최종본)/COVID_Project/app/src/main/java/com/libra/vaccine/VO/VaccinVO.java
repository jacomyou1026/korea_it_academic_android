package com.libra.vaccine.VO;

public class VaccinVO {

    private String txt_title;
    private String txt_info;
    private String imageView;

    public VaccinVO(String txt_title, String txt_info, String imageView) {
        this.txt_title = txt_title;
        this.txt_info = txt_info;
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

    public String getImageView() {
        return imageView;
    }

    public void setImageView(String imageView) {
        this.imageView = imageView;
    }

    @Override
    public String toString() {
        return "VaccinVO{" +
                "txt_title='" + txt_title + '\'' +
                ", txt_info='" + txt_info + '\'' +
                ", imageView='" + imageView + '\'' +
                '}';
    }
}
