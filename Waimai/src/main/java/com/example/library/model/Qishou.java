package com.example.library.model;

public class Qishou {
    private int id;
    private String qsbh;
    private String qsxm;
    private String rzrq;
    private String qssf;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQsbh() {
        return qsbh;
    }

    public void setQsbh(String qsbh) {
        this.qsbh = qsbh;
    }

    public String getQsxm() {
        return qsxm;
    }

    public void setQsxm(String qsxm) {
        this.qsxm = qsxm;
    }

    public String getRzrq() {
        return rzrq;
    }

    public void setRzrq(String rzrq) {
        this.rzrq = rzrq;
    }

    public String getQssf() {
        return qssf;
    }

    public void setQssf(String qssf) {
        this.qssf = qssf;
    }

    @Override
    public String toString() {
        return "Qishou{" +
                "id=" + id +
                ", qsbh='" + qsbh + '\'' +
                ", qsxm='" + qsxm + '\'' +
                ", rzrq='" + rzrq + '\'' +
                ", qssf='" + qssf + '\'' +
                '}';
    }
}
