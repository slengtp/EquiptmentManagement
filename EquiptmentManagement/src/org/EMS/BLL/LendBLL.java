package org.EMS.BLL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.EMS.DAO.LendDAO;
import org.EMS.Model.LendModel;


public class LendBLL {

	//Exist
	public static Boolean Exist(long LendID){
		
		return LendDAO.Exist(LendID);
		
	}
	
	
	//GetModel
	public static LendModel GetModel(Long LendID){
		
		return LendDAO.GetModel(LendID);
		
	}
	
	//Insert
	public static int Insert(LendModel lend){
		
		return LendDAO.Insert(lend);
		
	}
	
	//Delete
	public static int Delete(LendModel lend){
		
		return LendDAO.Delete(lend);
		
	}
	
	//Update
	public static int Update(LendModel lend){
		
		return LendDAO.Update(lend);
		
	}
	
	//Get ResultSet
	public static ResultSet GetAllList(){
		
		return LendDAO.GetAllList("");
		
	}
	
	//Get ResultSet with condition
	public static ResultSet GetAllList(String condition, Object... args){
		
		return LendDAO.GetAllList(condition, args);
		
	}
	
	//Get ResultSet into Model
	public static ArrayList<LendModel> GetAllArrayList(String condition, Object... args){
		
		ResultSet rs = GetAllList(condition, args);
		ArrayList<LendModel> list = new ArrayList<LendModel>();
		try {
			while(rs.next()){
				
				LendModel lend = new LendModel();
				lend.setLendID(rs.getLong("LendID"));
				lend.setLendEqpRFID(rs.getString("LendEqpRFID"));
				lend.setLendDepartmentID(rs.getInt("LendDepartmentID"));
				lend.setLendDate(rs.getDate("LendDate"));
				lend.setLendTime(rs.getDate("LendTime"));
				lend.setLendDeadline(rs.getDate("LendDeadline"));
				lend.setLendToPeople(rs.getLong("LendToPeople"));
				lend.setLendFromCharge(rs.getLong("LendFromCharge"));
				lend.setLendFlag(rs.getBoolean("LendFlag"));
				
				list.add(lend);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	
	public static ArrayList<LendModel> GetAllArrayList(){
		
		return GetAllArrayList("");
		
	
	}
	
}
