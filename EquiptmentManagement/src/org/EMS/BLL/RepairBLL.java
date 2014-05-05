package org.EMS.BLL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.EMS.DAO.RepairDAO;
import org.EMS.Model.RepairModel;


public class RepairBLL {

	//Exist
	public static Boolean Exist(long RepairID){
		
		return RepairDAO.Exist(RepairID);
		
	}
	
	
	//GetModel
	public static RepairModel GetModel(Long RepairID){
		
		return RepairDAO.GetModel(RepairID);
		
	}
	
	//Insert
	public static int Insert(RepairModel repair){
		
		return RepairDAO.Insert(repair);
		
	}
	
	//Delete
	public static int Delete(RepairModel repair){
		
		return RepairDAO.Delete(repair);
		
	}
	
	//Update
	public static int Update(RepairModel repair){
		
		return RepairDAO.Update(repair);
		
	}
	
	//Get ResultSet
	public static ResultSet GetAllList(){
		
		return RepairDAO.GetAllList("");
		
	}
	
	//Get ResultSet with condition
	public static ResultSet GetAllList(String condition, Object... args){
		
		return RepairDAO.GetAllList(condition, args);
		
	}
	
	//Get ResultSet into Model
	public static ArrayList<RepairModel> GetAllArrayList(String condition, Object... args){
		
		ResultSet rs = GetAllList(condition, args);
		ArrayList<RepairModel> list = new ArrayList<RepairModel>();
		try {
			while(rs.next()){
				
				RepairModel repair = new RepairModel();
				repair.setRpID(rs.getLong("RpID"));
				repair.setRpEqpRFID(rs.getString("RpEqpRFID"));
				repair.setRpDate(rs.getDate("RpDate"));
				repair.setRpPeople(rs.getLong("RpPeople"));
				repair.setRpDescribe(rs.getString("RpDescribe"));
				repair.setRpCondition(rs.getInt("RpCondition"));
				
				list.add(repair);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	
	public static ArrayList<RepairModel> GetAllArrayList(){
		
		return GetAllArrayList("");
		
	
	}
	
}
