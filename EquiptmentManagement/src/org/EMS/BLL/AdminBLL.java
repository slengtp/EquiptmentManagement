package org.EMS.BLL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.EMS.DAO.AdminDAO;
import org.EMS.Model.AdminModel;


public class AdminBLL {

	//Exist
	public static Boolean Exist(String AdminID){
		
		return AdminDAO.Exist(AdminID);
		
	}
	
	
	//GetModel
	public static AdminModel GetModel(String AdminID){
		
		return AdminDAO.GetModel(AdminID);
		
	}
	
	//Insert
	public static int Insert(AdminModel admin){
		
		return AdminDAO.Insert(admin);
		
	}
	
	//Delete
	public static int Delete(AdminModel admin){
		
		return AdminDAO.Delete(admin);
		
	}
	
	public static int Delete(String adminId){
		
		return AdminDAO.Delete(adminId);
		
	}
	
	//Update
	public static int Update(AdminModel admin){
		
		return AdminDAO.Update(admin);
		
	}
	
	//Get ResultSet
	public static ResultSet GetAllList(){
		
		return AdminDAO.GetAllList("");
		
	}
	
	//Get ResultSet with condition
	public static ResultSet GetAllList(String condition, Object... args){
		
		return AdminDAO.GetAllList(condition, args);
		
	}
	
	//Get ResultSet into Model
	public static ArrayList<AdminModel> GetAllArrayList(String condition, Object... args){
		
		ResultSet rs = GetAllList(condition, args);
		ArrayList<AdminModel> list = new ArrayList<AdminModel>();
		try {
			while(rs.next()){
				
				AdminModel admin = new AdminModel();
				admin.setAdminID(rs.getString("AdminID"));
				admin.setAdminName(rs.getString("AdminName"));
				admin.setAdminPassword(rs.getString("AdminPassword"));
				admin.setAdminIsSuper(rs.getBoolean("AdminIsSuper"));
				
				list.add(admin);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	
	public static ArrayList<AdminModel> GetAllArrayList(){
		
		return GetAllArrayList("");
		
	
	}
	
}
