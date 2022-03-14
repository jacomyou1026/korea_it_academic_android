package com.libra.vaccine.VO;

public class RegionConfirmedList {

    private  String regionName;
    private  String reg_defCnt;
    private  String reg_incDec;
    private String reg_death;

    public RegionConfirmedList(String regionName, String reg_defCnt, String reg_incDec, String reg_death) {
        this.regionName = regionName;
        this.reg_defCnt = reg_defCnt;
        this.reg_incDec = reg_incDec;
        this.reg_death = reg_death;
    }

    public RegionConfirmedList() {
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getReg_defCnt() {
        return reg_defCnt;
    }

    public void setReg_defCnt(String reg_defCnt) {
        this.reg_defCnt = reg_defCnt;
    }

    public String getReg_incDec() {
        return reg_incDec;
    }

    public void setReg_incDec(String reg_incDec) {
        this.reg_incDec = reg_incDec;
    }

    public String getReg_death() {
        return reg_death;
    }

    public void setReg_death(String reg_death) {
        this.reg_death = reg_death;
    }
}
