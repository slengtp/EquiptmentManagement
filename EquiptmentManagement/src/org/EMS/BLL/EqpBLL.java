package org.EMS.BLL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.EMS.DAO.EqpDAO;
import org.EMS.Model.EqpModel;


public class EqpBLL {

	//Exist
	public static Boolean Exist(String EqpID){
		
		return EqpDAO.Exist(EqpID);
		
	}
	
	
	//GetModel
	public static EqpModel GetModelByID(String EqpID){
		
		return EqpDAO.GetModelByID(EqpID);
		
	}
	
	//GetModel
	public static EqpModel GetModelByRFID(String EqpRFID){
		
		return EqpDAO.GetModelByRFID(EqpRFID);
		
	}
	
	//Insert
	public static int Insert(EqpModel eqp){
		
		return EqpDAO.Insert(eqp);
		
	}
	
	//Delete
	public static int Delete(EqpModel eqp){
		
		return EqpDAO.Delete(eqp);
		
	}
	
	//Update
	public static int Update(EqpModel eqp){
		
		return EqpDAO.Update(eqp);
		
	}
	
	//Get ResultSet
	public static ResultSet GetAllList(){
		
		return EqpDAO.GetAllList("");
		
	}
	
	//Get ResultSet with condition
	public static ResultSet GetAllList(String condition, Object... args){
		
		return EqpDAO.GetAllList(condition, args);
		
	}
	
	//Get ResultSet into Model
	public static ArrayList<EqpModel> GetAllArrayList(String condition, Object... args){
		
		ResultSet rs = GetAllList(condition, args);
		ArrayList<EqpModel> list = new ArrayList<EqpModel>();
		try {
			while(rs.next()){
				
				EqpModel eqp = new EqpModel();
				eqp.setEqpRFID(rs.getString("EqpRFID"));
				eqp.setEqpName(rs.getString("EqpName"));
				eqp.setEqpID(rs.getLong("EqpID"));
				eqp.setEqpModel(rs.getString("EqpModel"));
				eqp.setEqpManufacturer(rs.getString("EqpManufacturer"));
				eqp.setEqpStartUsingDate(rs.getDate("EqpStartUsingDate"));
				eqp.setEqpAvailableTime(rs.getDate("EqpAvailableTime"));
				eqp.setEqpDepartmentID(rs.getInt("EqpDepartmentID"));
				eqp.setEqpUserID(rs.getLong("EqpUserID"));
				
				list.add(eqp);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	
	public static ArrayList<EqpModel> GetAllArrayList(){
		
		return GetAllArrayList("");
		
	
	}
	
}
