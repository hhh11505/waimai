package cn.edu.zucc.booklib.model;
import java.util.*;

public class BeanCoupon {
	private int cno;
	private float jine;
	private int jidanshu;
	private Date begin;
	private Date end;
	public int getCno() {
		return cno;
	}
	public void setCno(int cno) {
		this.cno = cno;
	}
	public float getJine() {
		return jine;
	}
	public void setJine(float jine) {
		this.jine = jine;
	}
	public int getJidanshu() {
		return jidanshu;
	}
	public void setJidanshu(int jidanshu) {
		this.jidanshu = jidanshu;
	}
	public Date getBegin() {
		return begin;
	}
	public void setBegin(Date begin) {
		this.begin = begin;
	}
	public Date getEnd() {
		return end;
	}
	public void setEnd(Date end) {
		this.end = end;
	}
}
