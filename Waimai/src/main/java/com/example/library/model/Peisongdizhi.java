package com.example.library.model;

public class Peisongdizhi {

    private int id;
    private int dzbh;
    private String sheng;
    private String shi;
    private String qu;

    private String dz;
    private String lxr;
    private String dh;

    private int yhid;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDzbh() {
        return dzbh;
    }

    public void setDzbh(int dzbh) {
        this.dzbh = dzbh;
    }

    public String getSheng() {
        return sheng;
    }

    public void setSheng(String sheng) {
        this.sheng = sheng;
    }

    public String getShi() {
        return shi;
    }

    public void setShi(String shi) {
        this.shi = shi;
    }

    public String getQu() {
        return qu;
    }

    public void setQu(String qu) {
        this.qu = qu;
    }

    public String getDz() {
        return dz;
    }

    public void setDz(String dz) {
        this.dz = dz;
    }

    public String getLxr() {
        return lxr;
    }

    public void setLxr(String lxr) {
        this.lxr = lxr;
    }

    public String getDh() {
        return dh;
    }

    public void setDh(String dh) {
        this.dh = dh;
    }

    public int getYhid() {
        return yhid;
    }

    public void setYhid(int yhid) {
        this.yhid = yhid;
    }

    @Override
    public String toString() {
        return "Peisongdizhi{" +
                "id=" + id +
                ", dzbh=" + dzbh +
                ", sheng='" + sheng + '\'' +
                ", shi='" + shi + '\'' +
                ", qu='" + qu + '\'' +
                ", dz='" + dz + '\'' +
                ", lxr='" + lxr + '\'' +
                ", dh='" + dh + '\'' +
                ", yhid=" + yhid +
                '}';
    }
}
