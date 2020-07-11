package com.example.library.model;

public class Youhuiquanxinxi {
    private Integer id;
    private Integer yhqbh;
    private Double yhje;
    private Integer jdyqs;
    private String qsrq;
    private String jsrq;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getYhqbh() {
        return yhqbh;
    }

    public void setYhqbh(Integer yhqbh) {
        this.yhqbh = yhqbh;
    }

    public Double getYhje() {
        return yhje;
    }

    public void setYhje(Double yhje) {
        this.yhje = yhje;
    }

    public Integer getJdyqs() {
        return jdyqs;
    }

    public void setJdyqs(Integer jdyqs) {
        this.jdyqs = jdyqs;
    }

    public String getQsrq() {
        return qsrq;
    }

    public void setQsrq(String qsrq) {
        this.qsrq = qsrq;
    }

    public String getJsrq() {
        return jsrq;
    }

    public void setJsrq(String jsrq) {
        this.jsrq = jsrq;
    }

    @Override
    public String toString() {
        return "Youhuiquanxinxi{" +
                "id=" + id +
                ", yhqbh=" + yhqbh +
                ", yhje=" + yhje +
                ", jdyqs=" + jdyqs +
                ", qsrq='" + qsrq + '\'' +
                ", jsrq='" + jsrq + '\'' +
                '}';
    }
}
