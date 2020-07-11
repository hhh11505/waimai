package com.example.library.model;

public class Qishouruzhang {
    private Integer id;
    private String qid;
    private String sjddbh;

    private String sj;
    private String yhpj;
    private String dbsr;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getQid() {
        return qid;
    }

    public void setQid(String qid) {
        this.qid = qid;
    }

    public String getSjddbh() {
        return sjddbh;
    }

    public void setSjddbh(String sjddbh) {
        this.sjddbh = sjddbh;
    }

    public String getSj() {
        return sj;
    }

    public void setSj(String sj) {
        this.sj = sj;
    }

    public String getYhpj() {
        return yhpj;
    }

    public void setYhpj(String yhpj) {
        this.yhpj = yhpj;
    }

    public String getDbsr() {
        return dbsr;
    }

    public void setDbsr(String dbsr) {
        this.dbsr = dbsr;
    }

    @Override
    public String toString() {
        return "Qishouruzhang{" +
                "id=" + id +
                ", qid='" + qid + '\'' +
                ", sjddbh='" + sjddbh + '\'' +
                ", sj='" + sj + '\'' +
                ", yhpj='" + yhpj + '\'' +
                ", dbsr='" + dbsr + '\'' +
                '}';
    }
}
