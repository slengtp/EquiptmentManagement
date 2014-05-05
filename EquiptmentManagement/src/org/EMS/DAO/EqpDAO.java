package org.EMS.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.EM.Hander.DatabaseHander;
import org.EMS.Model.EqpModel;

public class EqpDAO {

	private static String TABLE_NAME = "t_equiptment";

	//检查此设备是否存在
	public static Boolean Exist(String EqpRFID) {

		String query = "select * from " + TABLE_NAME + " where EqpRFID=?";

		ResultSet rs = DatabaseHander.ExecuteQuery(query, EqpRFID);

		try {
			if (rs.next()) {

				return true;
			} else {

				return false;
			}
		} catch (SQLException e) {

			e.printStackTrace();
			return false;
		}
	}

	//通过RFID获得设备信息
	public static EqpModel GetModelByRFID(String EqpRFID){
		
		if (EqpRFID == null){
			
			return null;
		}
		
		String query = "select * from " + TABLE_NAME + " where EqpRFID=?";
		
		ResultSet rs = DatabaseHander.ExecuteQuery(query, EqpRFID);
		
		try{
			if(rs.next()){
			
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
				
				return eqp;
			
			}
		}catch(SQLException e){
			
			e.printStackTrace();
			return null;
			
		}
		return null;
	}
	

	
	//通过ID获得信息
	public static EqpModel GetModelByID(String EqpID){

			if (EqpID == null){
				
				return null;
			}
			
			String query = "select * from " + TABLE_NAME + " where EqpID=?";
			
			ResultSet rs = DatabaseHander.ExecuteQuery(query, EqpID);
			
			try{
				if(rs.next()){
				
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
					
					return eqp;
				
				}
			}catch(SQLException e){
				
				e.printStackTrace();
				return null;
				
			}
			return null;
		
	}
	
	
	//插入设备信息
	public static int Insert(EqpModel eqp){
		
		String query = "Insert into "+ TABLE_NAME + "(EqpRFID,EqpName,EqpID,EqpModel,EqpManufacturer,EqpStartUsingDate,EqpAvailableTime,EqpDepartmentID,EqpUserID) values (?,?,?,?,?,?,?,?,?)";
		
		return DatabaseHander.ExecuteNonQuery(query, eqp.getEqpRFID(),eqp.getEqpName(),eqp.getEqpID(),eqp.getEqpModel(),eqp.getEqpManufacturer(),eqp.getEqpStartUsingDate(),eqp.getEqpAvailableTime(),eqp.getEqpDepartmentID(),eqp.getEqpUserID());
		
	}
	
	//更新设备信息
	public static int Update(EqpModel eqp){
		
		String query = "Update "+ TABLE_NAME + " set EqpName=?,EqpID=?,EqpModel=?,EqpManufacturer=?,EqpStartUsingDate=?,EqpAvailableTime=?,EqpDepartmentID=?,EqpUserID=? where EqpRFID=?";
		
		return DatabaseHander.ExecuteNonQuery(query, eqp.getEqpName(),eqp.getEqpID(),eqp.getEqpModel(),eqp.getEqpManufacturer(),eqp.getEqpStartUsingDate(),eqp.getEqpAvailableTime(),eqp.getEqpDepartmentID(),eqp.getEqpUserID(),eqp.getEqpRFID());
		
	}
	
	
	//删除设备信息
	public static int Delete(EqpModel eqp){
		
		String query = "delete from " + TABLE_NAME + " where EqpRFID=?";
		
		return DatabaseHander.ExecuteNonQuery(query, eqp.getEqpRFID());
	}
	
	
	//获得所有设备信息
	public static ResultSet GetAllList(String whereStr, Object... args){
		
		String query = "select * from " + TABLE_NAME;
		
		if(whereStr != ""){
			
			query += " where " + whereStr;
			
		}
		
		return DatabaseHander.ExecuteQuery(query, args);
		
	}


}
