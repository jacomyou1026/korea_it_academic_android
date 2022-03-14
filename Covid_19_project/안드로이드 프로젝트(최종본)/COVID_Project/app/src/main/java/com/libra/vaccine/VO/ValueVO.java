package com.libra.vaccine.VO;

public class ValueVO {

    private  String hj,death,care,recover;

    public ValueVO(String hj, String death, String care, String recover) {
        this.hj = String.format("%,d",Integer.parseInt(hj));
        this.death = String.format("%,d",Integer.parseInt(death));
        this.care = String.format("%,d",Integer.parseInt(care));
        this.recover = String.format("%,d",Integer.parseInt(recover));
    }

    public String getHj() {
        return hj;
    }

    public void setHj(String hj) {
        this.hj = hj;
    }

    public String getDeath() {
        return death;
    }

    public void setDeath(String death) {
        this.death = death;
    }

    public String getCare() {
        return care;
    }

    public void setCare(String care) {
        this.care = care;
    }

    public String getRecover() {
        return recover;
    }

    public void setRecover(String recover) {
        this.recover = recover;
    }
}
