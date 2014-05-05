package org.EMS.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.EM.Hander.DatabaseHander;
import org.EMS.Model.AdminModel;

public class AdminDAO {
	
	private static String TABLE_NAME = "t_admin";
	
	public static boolean Exist(String AdminID){
		
		String query = "select * from " + TABLE_NAME + " where AdminID=?";
		
		ResultSet rs = DatabaseHander.ExecuteQuery(query, AdminID);
		try{
			if(rs.next()){
				return true;
			}
			else{
				return false;
			}
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}		
	}
	
	//获取admin信息
	public static AdminModel GetModel(String adminID){
		
		if(adminID == null){
			
			return null;
		}
		String query = "select * from " + TABLE_NAME + " where AdminID=?";
		ResultSet rs = DatabaseHander.ExecuteQuery(query, adminID);
		try{
			if(rs.next()){
				
				AdminModel admin = new AdminModel();
				admin.setAdminID(rs.getString("AdminID"));
				admin.setAdminName(rs.getString("AdminName"));
				admin.setAdminPassword(rs.getString("AdminPassword"));
				admin.setAdminIsSuper(rs.getBoolean("AdminIsSuper"));

				
				return admin;
			}
			else{
				return null;
			}
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	//插入admin信息
	public static int Insert(AdminModel admin){
		
		String query = "Insert into " + TABLE_NAME + " (AdminID,AdminName,AdminPassword,AdminIsSuper) values (?,?,?,?)";
		
		return DatabaseHander.ExecuteNonQuery(query, admin.getAdminID(),admin.getAdminName(),admin.getAdminPassword(),admin.getAdminIsSuper());
	}

	//更新admin信息
	public static int Update(AdminModel admin){
		
		String query = "Update " + TABLE_NAME + " set AdminName=?,AdminPassword=?,AdminIsSuper=? where AdminID=? ";
		
		return DatabaseHander.ExecuteNonQuery(query,admin.getAdminName(),admin.getAdminPassword(),admin.getAdminIsSuper(), admin.getAdminID());
	}
	
	//删除admin信息
	public static int Delete(String adminId){
		
		String query = "delete from " + TABLE_NAME + " where AdminID=?";
		
		return DatabaseHander.ExecuteNonQuery(query, adminId);
		
	}
	
	public static int Delete(AdminModel admin){
		
		return Delete(admin.getAdminID());
		
	}
	
	//获得所有的admin数据
	public static ResultSet GetAllList(String whereStr, Object... args){
		
		String query = "select * from " + TABLE_NAME;
		
		if(whereStr != ""){
			
			query += " where " + whereStr;
			
		}
		
		return DatabaseHander.ExecuteQuery(query, args);
		
	}
	
}
