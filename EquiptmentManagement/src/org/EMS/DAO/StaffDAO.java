package org.EMS.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.EM.Hander.DatabaseHander;
import org.EMS.Model.AdminModel;
import org.EMS.Model.StaffModel;

public class StaffDAO {

	private static String TABLE_NAME = "t_staff";
	
	//判断员工是否存在
	public static Boolean Exist(long staffID){
		
		String query = "select * from " + TABLE_NAME + " where staffID=?";
		
		//执行有返回值的SQL语句
		ResultSet rs = DatabaseHander.ExecuteQuery(query, staffID);
		try {
			if(rs.next()){				
				//存在
				return true;
			}
			else{
				
				return false;			
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}		
	}
	
	//获取员工信息
	public static StaffModel GetModel(Long staffID){
		
		if(staffID == null){
			
			//staffID为空，出错
			return null;
		}
		
		//SQL语句
		String query = "select * from " + TABLE_NAME + " where staffID=?";
		
		//执行SQL语句，并且得到返回值
		ResultSet rs = DatabaseHander.ExecuteQuery(query, staffID);
		try {
			if(rs.next()){
				
				StaffModel staff = new StaffModel();
				staff.setStaffId(rs.getLong("StaffID"));
				staff.setStaffDepartmentID(rs.getInt("StaffDepartmentID"));
				staff.setStaffGender(rs.getBoolean("StaffGender"));
				staff.setStaffLimit(rs.getInt("StaffLimit"));
				staff.setStaffName(rs.getString("StaffName"));
				staff.setStaffPassword(rs.getString("StaffPassword"));
				staff.setStaffProfession(rs.getString("StaffProfession"));
				staff.setStaffTitle(rs.getString("StaffTitle"));
				
				return staff;
			}
			else{
				
				return null;				
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	//插入员工信息
	public static int Insert(StaffModel staff){
		
		//SQL语句
		String query = "Insert into " + TABLE_NAME + " (StaffID, staffname, staffgender, staffdepartmentid, staffProfession, staffTitle, stafflimit, staffpassword) values (?,?,?,?,?,?,?,?)";
		
		//执行插入过程
		return DatabaseHander.ExecuteNonQuery(query, staff.getStaffId(), staff.getStaffName(), staff.getStaffGender(), staff.getStaffDepartmentID(), staff.getStaffProfession(), staff.getStaffTitle(), staff.getStaffLimit(), staff.getStaffPassword());
		
	}
	
	//更新员工信息
	public static int Update(StaffModel staff){
		
		//根据ID来进行员工的更新
		String query = "Update " + TABLE_NAME + " set staffName=?,staffgender=?,staffdepartmentid=?,staffprofession=?,stafftitle=?,stafflimit=?,staffpassword=? where staffid=?";
		
		return DatabaseHander.ExecuteNonQuery(query, staff.getStaffName(), staff.getStaffGender(), staff.getStaffDepartmentID(), staff.getStaffProfession(), staff.getStaffTitle(), staff.getStaffLimit(), staff.getStaffPassword(), staff.getStaffId());
		
	}
	
	//删除员工信息
	
	
	
	public static int Delete(Long staffId){
		
		//根据员工的ID删除员工
		String query = "delete from " + TABLE_NAME + " where staffId=?";
		
		return DatabaseHander.ExecuteNonQuery(query, staffId);
		
	}
	
	public static int Delete(StaffModel staff){
		
		return Delete(staff.getStaffId());
		
	}
	
	//获得所有的员工数据
	public static ResultSet GetAllList(String whereStr, Object... args){
		
		String query = "select * from " + TABLE_NAME;
		
		if(whereStr != ""){
			
			query += " where " + whereStr;
			
		}
		
		return DatabaseHander.ExecuteQuery(query, args);
		
	}


	
}
