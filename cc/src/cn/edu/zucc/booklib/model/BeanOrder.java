package cn.edu.zucc.booklib.model;
import java.util.*;
public class BeanOrder {
	private int ordno;
	private int sno;
	private int uno;
	private int dno;
	private float orgprice;
	private float fnlprice;
	private int cutno;
	private int cno;
	private Date ordtime;
	private Date arrtime;
	private int addno;
	private String ordstate;
	public int getOrdno() {
		return ordno;
	}
	public void setOrdno(int ordno) {
		this.ordno = ordno;
	}
	public int getSno() {
		return sno;
	}
	public void setSno(int sno) {
		this.sno = sno;
	}
	public int getUno() {
		return uno;
	}
	public void setUno(int uno) {
		this.uno = uno;
	}
	public int getDno() {
		return dno;
	}
	public void setDno(int dno) {
		this.dno = dno;
	}
	public float getOrgprice() {
		return orgprice;
	}
	public void setOrgprice(float orgprice) {
		this.orgprice = orgprice;
	}
	public float getFnlprice() {
		return fnlprice;
	}
	public void setFnlprice(float fnlprice) {
		this.fnlprice = fnlprice;
	}
	public int getCutno() {
		return cutno;
	}
	public void setCutno(int cutno) {
		this.cutno = cutno;
	}
	public int getCno() {
		return cno;
	}
	public void setCno(int cno) {
		this.cno = cno;
	}
	public Date getOrdtime() {
		return ordtime;
	}
	public void setOrdtime(Date ordtime) {
		this.ordtime = ordtime;
	}
	public Date getArrtime() {
		return arrtime;
	}
	public void setArrtime(Date arrtime) {
		this.arrtime = arrtime;
	}
	public int getAddno() {
		return addno;
	}
	public void setAddno(int addno) {
		this.addno = addno;
	}
	public String getOrdstate() {
		return ordstate;
	}
	public void setOrdstate(String ordstate) {
		this.ordstate = ordstate;
	}
}
