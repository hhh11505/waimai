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
				throw new BusinessException("��½�˺ű�����1-20����");
			}
			if(user.getUsername()==null || "".equals(user.getUsername()) || user.getUsername().length()>50){
				throw new BusinessException("�˺����Ʊ�����1-50����");
			}
			if(!"����Ա".equals(user.getUsertype()) && "����Ա".equals(user.getUsertype())){
				throw new BusinessException("�û���� �����ǽ���Ա�����Ա");
			}
			
			
			Connection conn=null;
			try {
				conn=DBUtil.getConnection();
				String sql="select * from BeanSystemUser where userid=?";
				java.sql.PreparedStatement pst=conn.prepareStatement(sql);
				pst.setString(1,user.getUserid());
				java.sql.ResultSet rs=pst.executeQuery();
				if(rs.next()) throw new BusinessException("��½�˺��Ѿ�����");
				rs.close();
				pst.close();
				sql="insert into BeanSystemUser(userid,username,pwd,usertype,createDate) values(?,?,?,?,?)";
				pst=conn.prepareStatement(sql);
				pst.setString(1, user.getUserid());
				pst.setString(2, user.getUsername());
				user.setPwd(user.getUserid());//Ĭ������Ϊ�˺�
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
			if(oldPwd==null) throw new BusinessException("ԭʼ���벻��Ϊ��");
			if(newPwd==null || "".equals(newPwd) || newPwd.length()>16) throw new BusinessException("����Ϊ1-16���ַ�");
			Connection conn=null;
			try {
				conn=DBUtil.getConnection();
				String sql="select pwd from BeanSystemUser where userid=?";
				java.sql.PreparedStatement pst=conn.prepareStatement(sql);
				pst.setString(1,userid);
				java.sql.ResultSet rs=pst.executeQuery();
				if(!rs.next()) throw new BusinessException("��½�˺Ų� ����");
				if(!oldPwd.equals(rs.getString(1))) throw new BusinessException("ԭʼ�������");
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
				if(!rs.next()) throw new BusinessException("��½�˺Ų� ����");
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
				if(!rs.next()) throw new BusinessException("��½�˺Ų� ����");
				if(rs.getDate(1)!=null) throw new BusinessException("���˺��Ѿ���ɾ��");
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
					throw new BusinessException("���˺����н�����Ϣ������ɾ��");
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
				if(!rs.next()) throw new BusinessException("��½�˺Ų� ����");
				BeanManagerinfo u=new BeanManagerinfo();
				u.setUserid(rs.getString(1));
				u.setUsername(rs.getString(2));
				u.setPwd(rs.getString(3));
				u.setUsertype(rs.getString(4));
				u.setCreateDate(rs.getDate(5));
				u.setRemoveDate(rs.getDate(6));
				if(u.getRemoveDate()!=null) throw new BusinessException("���˺��Ѿ���ɾ��");
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
			user.setUsername("ϵͳ����Ա");
			user.setUsertype("����Ա");
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
		}*/
	}
}