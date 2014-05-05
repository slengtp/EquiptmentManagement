package org.EMS.BLL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.EMS.DAO.DepartmentDAO;
import org.EMS.Model.DepartmentModel;


public class DepartmentBLL {

	//Exist
	public static Boolean Exist(Integer DepartmentID){
		
		return DepartmentDAO.Exist(DepartmentID);
		
	}
	
	
	//GetModel
	public static DepartmentModel GetModel(Integer DepartmentID){
		
		return DepartmentDAO.GetModel(DepartmentID);
		
	}
	
	//Insert
	public static int Insert(DepartmentModel dpmt){
		
		return DepartmentDAO.Insert(dpmt);
		
	}
	
	//Delete
	public static int Delete(DepartmentModel dpmt){
		
		return DepartmentDAO.Delete(dpmt);
		
	}
	
	//Update
	public static int Update(DepartmentModel dpmt){
		
		return DepartmentDAO.Update(dpmt);
		
	}
	
	//Get ResultSet
	public static ResultSet GetAllList(){
		
		return DepartmentDAO.GetAllList("");
		
	}
	
	//Get ResultSet with condition
	public static ResultSet GetAllList(String condition, Object... args){
		
		return DepartmentDAO.GetAllList(condition, args);
		
	}
	
	//Get ResultSet into Model
		public static ArrayList<DepartmentModel> GetAllArrayList(String condition, Object... args){
			
			ResultSet rs = GetAllList(condition, args);
			ArrayList<DepartmentModel> list = new ArrayList<DepartmentModel>();
			try {
				while(rs.next()){
					
					DepartmentModel dpmt = new DepartmentModel();
					dpmt.setDepartmentID(rs.getInt("DepartmentID"));
					dpmt.setDepartmentName(rs.getString("DepartmentName"));
					
					list.add(dpmt);
					
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return list;
		}
		
		
		public static ArrayList<DepartmentModel> GetAllArrayList(){
			
			return GetAllArrayList("");
			
		
		}
		
	}
