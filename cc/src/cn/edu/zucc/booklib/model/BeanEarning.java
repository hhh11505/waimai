package cn.edu.zucc.booklib.model;
import java.util.*;
public class BeanEarning {
	private int dno;
	private int ordno;
	private Date dtime;
	private String ucomment;
	private float earn;
	public int getDno() {
		return dno;
	}
	public void setDno(int dno) {
		this.dno = dno;
	}
	public int getOrdno() {
		return ordno;
	}
	public void setOrdno(int ordno) {
		this.ordno = ordno;
	}
	public Date getDtime() {
		return dtime;
	}
	public void setDtime(Date dtime) {
		this.dtime = dtime;
	}
	public String getUcomment() {
		return ucomment;
	}
	public void setUcomment(String ucomment) {
		this.ucomment = ucomment;
	}
	public float getEarn() {
		return earn;
	}
	public void setEarn(float earn) {
		this.earn = earn;
	}
}
