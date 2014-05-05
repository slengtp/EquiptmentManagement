package org.EMS.BLL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.EMS.DAO.TransDAO;
import org.EMS.Model.TransModel;


public class TransBLL {

	//Exist
	public static Boolean Exist(long TransID){
		
		return TransDAO.Exist(TransID);
		
	}
	
	
	//GetModel
	public static TransModel GetModel(Long TransID){
		
		return TransDAO.GetModel(TransID);
		
	}
	
	//Insert
	public static int Insert(TransModel trans){
		
		return TransDAO.Insert(trans);
		
	}
	
	//Delete
	public static int Delete(TransModel trans){
		
		return TransDAO.Delete(trans);
		
	}
	
	//Update
	public static int Update(TransModel trans){
		
		return TransDAO.Update(trans);
		
	}
	
	//Get ResultSet
	public static ResultSet GetAllList(){
		
		return TransDAO.GetAllList("");
		
	}
	
	//Get ResultSet with condition
	public static ResultSet GetAllList(String condition, Object... args){
		
		return TransDAO.GetAllList(condition, args);
		
	}
	
	//Get ResultSet into Model
	public static ArrayList<TransModel> GetAllArrayList(String condition, Object... args){
		
		ResultSet rs = GetAllList(condition, args);
		ArrayList<TransModel> list = new ArrayList<TransModel>();
		try {
			while(rs.next()){
				
				TransModel trans = new TransModel();
				trans.setTranID(rs.getLong("TranID"));
				trans.setTranEqpRFID(rs.getString("TranEqpRFID"));
				trans.setTranPeople(rs.getString("TranPeople"));
				trans.setTranDate(rs.getDate("TranDate"));
				trans.setTranDepartmentID(rs.getInt("TranDepartmentID"));
				trans.setTranToDepartmentID(rs.getInt("TranToDepartmentID"));
				
				list.add(trans);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	
	public static ArrayList<TransModel> GetAllArrayList(){
		
		return GetAllArrayList("");
		
	
	}
	
}
