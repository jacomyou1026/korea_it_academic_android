package com.libra.vaccine.VO;

public class SecondVO {
    String date,value,xdate;
    float fvalue;


    public SecondVO(String date, String value) {
        this.xdate = date.substring(0,10);
        this.date = date.substring(5,10);
        this.value = value;
        fvalue = Float.parseFloat(value);
    }

    public float getFvalue() {
        return fvalue;
    }

    public void setFvalue(float fvalue) {
        this.fvalue = fvalue;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getXdate() {
        return xdate;
    }

    public void setXdate(String xdate) {
        this.xdate = xdate;
    }
}
