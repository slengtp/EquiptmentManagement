package org.EMS.BLL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.EMS.DAO.BreakDAO;
import org.EMS.Model.BreakModel;


public class BreakBLL {

	//Exist
	public static Boolean Exist(long BreakID){
		
		return BreakDAO.Exist(BreakID);
		
	}
	
	
	//GetModel
	public static BreakModel GetModel(Long BreakID){
		
		return BreakDAO.GetModel(BreakID);
		
	}
	
	//Insert
	public static int Insert(BreakModel brk){
		
		return BreakDAO.Insert(brk);
		
	}
	
	//Delete
	public static int Delete(BreakModel brk){
		
		return BreakDAO.Delete(brk);
		
	}
	
	//Update
	public static int Update(BreakModel brk){
		
		return BreakDAO.Update(brk);
		
	}
	
	//Get ResultSet
	public static ResultSet GetAllList(){
		
		return BreakDAO.GetAllList("");
		
	}
	
	//Get ResultSet with condition
	public static ResultSet GetAllList(String condition, Object... args){
		
		return BreakDAO.GetAllList(condition, args);
		
	}
	
	//Get ResultSet into Model
	public static ArrayList<BreakModel> GetAllArrayList(String condition, Object... args){
		
		ResultSet rs = GetAllList(condition, args);
		ArrayList<BreakModel> list = new ArrayList<BreakModel>();
		try {
			while(rs.next()){
				
				BreakModel brk = new BreakModel();
				brk.setBreakID(rs.getLong("BreakID"));
				brk.setBreakEqpID(rs.getLong("BreakEqpID"));
				brk.setBreakEqpRFID(rs.getString("BreakEqpRFID"));
				brk.setBreakDescribe(rs.getString("BreakDescribe"));
				brk.setBreakFixman(rs.getLong("BreakFixman"));
				brk.setBreakReportDate(rs.getDate("BreakReportDate"));
				
				list.add(brk);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	
	public static ArrayList<BreakModel> GetAllArrayList(){
		
		return GetAllArrayList("");
		
	
	}
	
}
