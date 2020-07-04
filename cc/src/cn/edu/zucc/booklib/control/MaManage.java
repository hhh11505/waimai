package cn.edu.zucc.booklib.control;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import cn.edu.zucc.booklib.model.BeanManagerinfo;
import cn.edu.zucc.booklib.util.BaseException;
import cn.edu.zucc.booklib.util.BusinessException;
import cn.edu.zucc.booklib.util.DBUtil;
import cn.edu.zucc.booklib.util.DbException;


public class MaManage {
	//public static BeanManagerinfo currentUser=null;
	public BeanManagerinfo reg(String mname, String pwd,String pwd2)throws BaseException{
		if(mname==null||"".equals(mname)) throw new BusinessException("�û�������Ϊ��");
		if(pwd==null||"".equals(pwd)) throw new BusinessException("���벻��Ϊ��");
		if(!pwd.equals(pwd2)) throw new BusinessException("�������벻һ��");
		
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select count(*) from managerinfo where mname=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1,mname);
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next()) if(rs.getInt(1)>=1) throw new BusinessException("���˺��Ѵ���");
			pst.close();
			rs.close();
			sql="select max(mno) from managerinfo ";
			pst=conn.prepareStatement(sql);
			rs=pst.executeQuery();
			int x=0;
			while(rs.next()) {
				x=rs.getInt(1);
			}
			
			pst.close();
			String sql2="insert into managerinfo(mname,mpwd) values(?,?)";
			java.sql.PreparedStatement pst2=conn.prepareStatement(sql2);
			pst2.setString(1,mname);
			pst2.setString(2,pwd);
			pst2.execute();
			BeanManagerinfo b=new BeanManagerinfo();
			b.setMno(x+1);
			b.setMname(mname);
			b.setMpwd(pwd);
			rs.close();
			pst.close();
			pst2.close();
			return b;
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DbException(e);
		}
		finally{
			if(conn!=null)
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
	public void changePwd(BeanManagerinfo mm,String oldPwd, String newPwd,String newPwd2)throws BaseException{
		if(mm==null) throw new BusinessException("�˺Ų���Ϊ��");
		if(!newPwd.equals(newPwd2)) throw new BusinessException("�����������벻һ��");
		if(newPwd==null || "".equals(newPwd) || newPwd.length()>16) throw new BusinessException("����Ϊ1-16���ַ�");
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();	
			String sql="select mpwd from managerinfo where mname=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1,mm.getMname());
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) throw new BusinessException("�˺Ų�����");
			if(!oldPwd.equals(rs.getString(1))) throw new BusinessException("�������");
			rs.close();
			pst.close();
			sql="update managerinfo set mpwd=? where mname=?";
			pst=conn.prepareStatement(sql);
			pst.setString(1, newPwd);
			pst.setString(2, mm.getMname());
			pst.execute();
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DbException(e);
		}
		finally{
			if(conn!=null)
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}

	public  BeanManagerinfo loadUser(String mname,String mpwd)throws BaseException{
		if(mname==null||"".equals(mname))throw new BusinessException("�û�������Ϊ��");
		if(mpwd==null||"".equals(mpwd))throw new BusinessException("���벻��Ϊ��");
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select * from managerinfo where mname=? and mpwd=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1,mname);	
			pst.setString(2,mpwd);
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) throw new BusinessException("��½�˺Ų����ڻ��������");
			
			rs.close();
			pst.close();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DbException(e);
		}
		finally{
			if(conn!=null)
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		BeanManagerinfo b=new BeanManagerinfo();
		b.setMname(mname);
		b.setMpwd(mpwd);
		return b;
		
	}

	public static void main(String[] args){
		
		/*BeanSystemUser user=new BeanSystemUser();
		user.setUserid("admin");
		user.setUsername("ϵͳ����Ա");
		user.setUsertype("����Ա");
		try {
			new SystemUserManager().createUser(user);
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
	/*public static void main(String[] args){
		BeanSystemUser user=new BeanSystemUser();
		user.setUserid("admin");
		user.setUsername("ϵͳ����Ա");
		user.setUsertype("����Ա");
		try {
			new SystemUserManager().modifyUserName("��������Ա");
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void create() throws BaseException {
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="insert into BeanBookLendRecord_Backup select * from BeanBookLendRecord";
			java.sql.Statement st=conn.createStatement();
			java.sql.ResultSet rs=st.executeQuery(sql);
		}catch(SQLException e) {
			e.printStackTrace();
			throw new DbException(e);
		}finally{
			if(conn!=null) {
				try {
					conn.close();
				}catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
}*/
}
