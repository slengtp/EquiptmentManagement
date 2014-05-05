package org.EMS.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.EM.Hander.DatabaseHander;
import org.EMS.Model.DepartmentModel;

public class DepartmentDAO {
	
	private static String TABLE_NAME = "t_department";

	//判断是否存在部门
	public static Boolean Exist(Integer DepartmentID){
		
		String query = "select * from " + TABLE_NAME + " where DepartmentID=?";
		
		ResultSet rs = DatabaseHander.ExecuteQuery(query, DepartmentID);
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
	
	//获取部门信息
	public static DepartmentModel GetModel(Integer DepartmentID){
		if(DepartmentID == null){
			
			return null;
		}		
		String query = "select * from " + TABLE_NAME + " where DepartmentID=?";
		
		ResultSet rs = DatabaseHander.ExecuteQuery(query, DepartmentID);
		
		try{
			if(rs.next()){
				
				DepartmentModel dpmt = new DepartmentModel();
				dpmt.setDepartmentID(rs.getInt("DepartmentID"));
				dpmt.setDepartmentName(rs.getString("DepartmentName"));
				dpmt.setDepartmentParentID(rs.getInt("DepartmentParentID"));
				
				return dpmt;
			}
			else{
				
				return null;				
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	//插入部门信息
	public static int Insert(DepartmentModel dpmt){
		
		String query = "insert into " + TABLE_NAME + "  (DepartmentName, DepartmentParentID) values (?,?)";
		return DatabaseHander.ExecuteNonQuery(query, dpmt.getDepartmentName(), dpmt.getDepartmentParentID());
	}
	
	//更新部门信息
	public static int Update(DepartmentModel dpmt){
		
		String query = "Update " + TABLE_NAME + " set DepartmentName=?, DepartmentParentID=? where DepartmentID=?";
		return DatabaseHander.ExecuteNonQuery(query, dpmt.getDepartmentName(), dpmt.getDepartmentParentID(), dpmt.getDepartmentID());
	}
	
	//删除部门信息
	public static int Delete(DepartmentModel dpmt){
		

		String query = "delete from " + TABLE_NAME + " where DepartmentID=?";
		
		return DatabaseHander.ExecuteNonQuery(query, dpmt.getDepartmentID());
		
	}
	
	//获得所有的部门数据
	public static ResultSet GetAllList(String whereStr, Object... args){
		
		String query = "select * from " + TABLE_NAME;
		
		if(whereStr != ""){
			
			query += " where " + whereStr;
			
		}
		
		return DatabaseHander.ExecuteQuery(query, args);
		
	}
	
}
