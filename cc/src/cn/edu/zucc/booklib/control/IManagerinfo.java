package cn.edu.zucc.booklib.control;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.edu.zucc.booklib.model.*;
import cn.edu.zucc.booklib.util.BaseException;
import cn.edu.zucc.booklib.util.BusinessException;
import cn.edu.zucc.booklib.util.DBUtil;
import cn.edu.zucc.booklib.util.DbException;

public class IManagerinfo {
	public class SystemUserManager {
		public static BeanManagerinfo currentUser=null;
		public List<BeanManagerinfo> loadAllUsers(boolean withDeletedUser)throws BaseException{
			List<BeanManagerinfo> result=new ArrayList<BeanManagerinfo>();
			Connection conn=null;
			try {
				conn=DBUtil.getConnection();
				String sql="select userid,username,usertype,createDate from BeanSystemUser";
				if(!withDeletedUser)
					sql+=" where removeDate is null ";
				sql+=" order by userid";
				java.sql.Statement st=conn.createStatement();
				java.sql.ResultSet rs=st.executeQuery(sql);
				while(rs.next()){
					BeanManagerinfo u=new BeanManagerinfo();
					u.setUserid(rs.getString(1));
					u.setUsername(rs.getString(2));
					u.setUsertype(rs.getString(3));
					u.setCreateDate(rs.getDate(4));
					result.add(u);
				}
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
			return result;
		}
		public void createUser(BeanManagerinfo user)throws BaseException{
			if(user.getUserid()==null || "".equals(user.getUserid()) || user.getUserid().length()>20){
				throw new BusinessException("登陆账号必须是1-20个字");
			}
			if(user.getUsername()==null || "".equals(user.getUsername()) || user.getUsername().length()>50){
				throw new BusinessException("账号名称必须是1-50个字");
			}
			if(!"管理员".equals(user.getUsertype()) && "借阅员".equals(user.getUsertype())){
				throw new BusinessException("用户类别 必须是借阅员或管理员");
			}
			
			
			Connection conn=null;
			try {
				conn=DBUtil.getConnection();
				String sql="select * from BeanSystemUser where userid=?";
				java.sql.PreparedStatement pst=conn.prepareStatement(sql);
				pst.setString(1,user.getUserid());
				java.sql.ResultSet rs=pst.executeQuery();
				if(rs.next()) throw new BusinessException("登陆账号已经存在");
				rs.close();
				pst.close();
				sql="insert into BeanSystemUser(userid,username,pwd,usertype,createDate) values(?,?,?,?,?)";
				pst=conn.prepareStatement(sql);
				pst.setString(1, user.getUserid());
				pst.setString(2, user.getUsername());
				user.setPwd(user.getUserid());//默认密码为账号
				pst.setString(3,user.getPwd());
				pst.setString(4, user.getUsertype());
				pst.setTimestamp(5,new java.sql.Timestamp(System.currentTimeMillis()));
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
		public void changeUserPwd(String userid,String oldPwd,String newPwd)throws BaseException{
			if(oldPwd==null) throw new BusinessException("原始密码不能为空");
			if(newPwd==null || "".equals(newPwd) || newPwd.length()>16) throw new BusinessException("必须为1-16个字符");
			Connection conn=null;
			try {
				conn=DBUtil.getConnection();
				String sql="select pwd from BeanSystemUser where userid=?";
				java.sql.PreparedStatement pst=conn.prepareStatement(sql);
				pst.setString(1,userid);
				java.sql.ResultSet rs=pst.executeQuery();
				if(!rs.next()) throw new BusinessException("登陆账号不 存在");
				if(!oldPwd.equals(rs.getString(1))) throw new BusinessException("原始密码错误");
				rs.close();
				pst.close();
				sql="update BeanSystemUser set pwd=? where userid=?";
				pst=conn.prepareStatement(sql);
				pst.setString(1, newPwd);
				pst.setString(2, userid);
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
		public void resetUserPwd(String userid)throws BaseException{
			Connection conn=null;
			try {
				conn=DBUtil.getConnection();
				String sql="select * from BeanSystemUser where userid=?";
				java.sql.PreparedStatement pst=conn.prepareStatement(sql);
				pst.setString(1,userid);
				java.sql.ResultSet rs=pst.executeQuery();
				if(!rs.next()) throw new BusinessException("登陆账号不 存在");
				rs.close();
				pst.close();
				sql="update BeanSystemUser set pwd=? where userid=?";
				pst=conn.prepareStatement(sql);
				pst.setString(1, userid);
				pst.setString(2, userid);
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
		public void deleteUser(String userid)throws BaseException{
			Connection conn=null;
			try {
				conn=DBUtil.getConnection();
				String sql="select removeDate from BeanSystemUser where userid=?";
				java.sql.PreparedStatement pst=conn.prepareStatement(sql);
				pst.setString(1,userid);
				java.sql.ResultSet rs=pst.executeQuery();
				if(!rs.next()) throw new BusinessException("登陆账号不 存在");
				if(rs.getDate(1)!=null) throw new BusinessException("该账号已经被删除");
				rs.close();
				pst.close();
				sql="select * from BeanBookLendRecord where lendOperUserid=?";
				pst=conn.prepareStatement(sql);
				pst.setString(1, userid);
				rs=pst.executeQuery();
				if(!rs.next()) {
					sql="delete BeanSystemUser where userID=?";
					pst=conn.prepareStatement(sql);
					pst.setString(1, userid);
					pst.execute();
					pst.close();
				}else {
					throw new BusinessException("该账号已有借阅信息，不可删除");
				}
				/*sql="update BeanSystemUser set removeDate=? where userid=?";
				pst=conn.prepareStatement(sql);
				pst.setTimestamp(1, new java.sql.Timestamp(System.currentTimeMillis()));*/
				
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
		public BeanManagerinfo loadUser(String userid)throws BaseException{
			Connection conn=null;
			try {
				conn=DBUtil.getConnection();
				String sql="select userid,username,pwd,usertype,createDate,removeDate from BeanSystemUser where userid=?";
				java.sql.PreparedStatement pst=conn.prepareStatement(sql);
				pst.setString(1,userid);
				java.sql.ResultSet rs=pst.executeQuery();
				if(!rs.next()) throw new BusinessException("登陆账号不 存在");
				BeanManagerinfo u=new BeanManagerinfo();
				u.setUserid(rs.getString(1));
				u.setUsername(rs.getString(2));
				u.setPwd(rs.getString(3));
				u.setUsertype(rs.getString(4));
				u.setCreateDate(rs.getDate(5));
				u.setRemoveDate(rs.getDate(6));
				if(u.getRemoveDate()!=null) throw new BusinessException("该账号已经被删除");
				rs.close();
				pst.close();
				return u;
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
		public boolean modifyUserName(String username) throws BaseException{
			Connection conn=null;
			try {
				conn=DBUtil.getConnection();
				String sql="update beansystemuser set username= '"+username+"' where userid='admin'";
				java.sql.Statement st=conn.createStatement();
				boolean rs=st.execute(sql);
				if(rs==true)
					return true;
				else
					return false;
			}catch(SQLException e) {
				e.printStackTrace();
				throw new DbException(e);
			}finally {
				if(conn!=null) {
					try {
						conn.close();
					}catch(SQLException e) {
						e.printStackTrace();
					}
				}
			}
		}
		/*public static void main(String[] args){
			BeanSystemUser user=new BeanSystemUser();
			user.setUserid("admin");
			user.setUsername("系统管理员");
			user.setUsertype("管理员");
			try {
				new SystemUserManager().createUser(user);
			} catch (BaseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}*/
	/*	public static void main(String[] args){
			BeanSystemUser user=new BeanSystemUser();
			user.setUserid("admin");
			user.setUsername("系统管理员");
			user.setUsertype("管理员");
			try {
				new SystemUserManager().modifyUserName("超级管理员");
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
		}*/
	}
}
