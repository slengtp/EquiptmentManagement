package org.EMS.BLL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.EMS.DAO.AdminDAO;
import org.EMS.DAO.StaffDAO;
import org.EMS.Model.StaffModel;


public class StaffBLL {

	//Exist
	public static Boolean Exist(long StaffID){
		
		return StaffDAO.Exist(StaffID);
		
	}
	
	
	//GetModel
	public static StaffModel GetModel(Long StaffID){
		
		return StaffDAO.GetModel(StaffID);
		
	}
	
	//Insert
	public static int Insert(StaffModel staff){
		
		return StaffDAO.Insert(staff);
		
	}
	
	//Delete
	public static int Delete(StaffModel staff){
		
		return StaffDAO.Delete(staff);
		
	}
	
	public static int Delete(Long staffId){
		
		return StaffDAO.Delete(staffId);
		
	}
	
	//Update
	public static int Update(StaffModel staff){
		
		return StaffDAO.Update(staff);
		
	}
	
	//Get ResultSet
	public static ResultSet GetAllList(){
		
		return StaffDAO.GetAllList("");
		
	}
	
	//Get ResultSet with condition
	public static ResultSet GetAllList(String condition, Object... args){
		
		return StaffDAO.GetAllList(condition, args);
		
	}
	
	//Get ResultSet into Model
	public static ArrayList<StaffModel> GetAllArrayList(String condition, Object... args){
		
		ResultSet rs = GetAllList(condition, args);
		ArrayList<StaffModel> list = new ArrayList<StaffModel>();
		try {
			while(rs.next()){
				
				StaffModel staff = new StaffModel();
				staff.setStaffId(rs.getLong("StaffID"));
				staff.setStaffDepartmentID(rs.getInt("StaffDepartmentID"));
				staff.setStaffGender(rs.getBoolean("StaffGender"));
				staff.setStaffLimit(rs.getInt("StaffLimit"));
				staff.setStaffName(rs.getString("StaffName"));
				staff.setStaffPassword(rs.getString("StaffPassword"));
				staff.setStaffProfession(rs.getString("StaffProfession"));
				staff.setStaffTitle(rs.getString("StaffTitle"));
							
				list.add(staff);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	
	public static ArrayList<StaffModel> GetAllArrayList(){
		
		return GetAllArrayList("");
		
	
	}
	
}
