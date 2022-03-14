package com.example.weather;

public class WeatherVO {
    //하늘상태
    private String sky;
    //기온
    private String tmp;
    //강수형태
    private String pty;
    //현재습도
    private String reh;

    public WeatherVO() {
    }

    public String getSky() {
        return sky;
    }

    public void setSky(String sky) {
        this.sky = sky;
    }

    public String getTmp() {
        return tmp;
    }

    public void setTmp(String tmp) {
        this.tmp = tmp;
    }

    public String getPty() {
        return pty;
    }

    public void setPty(String pty) {
        this.pty = pty;
    }

    public String getReh() {
        return reh;
    }

    public void setReh(String reh) {
        this.reh = reh;
    }

    @Override
    public String toString() {
        String msg = "기상 정보 \n";
        msg += "\t하늘상태 : "+sky;
        msg += "\t온도 : "+tmp +"도";
        msg += "\t강수형태 : "+pty;
        return msg;
    }
}
