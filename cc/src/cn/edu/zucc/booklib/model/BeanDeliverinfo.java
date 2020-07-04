package cn.edu.zucc.booklib.model;
import java.util.*;
public class BeanDeliverinfo {
	private int dno;
	private String dname;
	private Date workdate;
	private String dstate;
	public int getDno() {
		return dno;
	}
	public void setDno(int dno) {
		this.dno = dno;
	}
	public String getDname() {
		return dname;
	}
	public void setDname(String dname) {
		this.dname = dname;
	}
	public Date getWorkdate() {
		return workdate;
	}
	public void setWorkdate(Date workdate) {
		this.workdate = workdate;
	}
	public String getDstate() {
		return dstate;
	}
	public void setDstate(String dstate) {
		this.dstate = dstate;
	}
}
