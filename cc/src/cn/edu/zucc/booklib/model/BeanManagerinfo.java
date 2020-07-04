package cn.edu.zucc.booklib.model;

import java.util.Date;


public class BeanManagerinfo {
	public static BeanManagerinfo currentLoginMa=null;
	private int mno;
	private String mname;
	private String mpwd;
	public int getMno() {
		return mno;
	}
	public void setMno(int mno) {
		this.mno = mno;
	}
	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}
	public String getMpwd() {
		return mpwd;
	}
	public void setMpwd(String mpwd) {
		this.mpwd = mpwd;
	}
	
}
