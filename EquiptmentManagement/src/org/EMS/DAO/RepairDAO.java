package org.EMS.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.EM.Hander.DatabaseHander;
import org.EMS.Model.RepairModel;

//判断维修记录是否存在
public class RepairDAO {
	
	private static String TABLE_NAME = "t_repair";
	
	public static Boolean Exist(long RpID){
		
		String query = "select * form " + TABLE_NAME + " where RpID=?";
		
		ResultSet rs =  DatabaseHander.ExecuteQuery(query, RpID);
		
		try{
			if(rs.next()){
				return true;
			}
			else{
				return false;
			}
		}catch (SQLException e) {
			e.printStackTrace();
			return false;
		}		
	}
	
	//获取维修信息
	public static RepairModel GetModel(Long RpID){
		
		if(RpID==null){
			return null;
		}
		
		String query = "select * from " + TABLE_NAME + " where RpID=?";
		
		ResultSet rs =  DatabaseHander.ExecuteQuery(query, RpID);
		try{
			
			if(rs.next()){
				
				RepairModel repair = new RepairModel();
				repair.setRpID(rs.getLong("RpID"));
				repair.setRpEqpRFID(rs.getString("RpEqpRFID"));
				repair.setRpDate(rs.getDate("RpDate"));
				repair.setRpPeople(rs.getLong("RpPeople"));
				repair.setRpDescribe(rs.getString("RpDescribe"));
				repair.setRpCondition(rs.getInt("RpCondition"));
				
				return repair;
			}
			else{
				return null;
			}		

		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}
	}
	
	//插入维修信息
	public static int Insert(RepairModel repair){
		
		String query = "insert into " + TABLE_NAME + " (RpID,RpEqpRFID,RpDate,RpPeople,RpDescribe,RpCondition) values (?,?,?,?,?,?)";
		
		return DatabaseHander.ExecuteNonQuery(query, repair.getRpID(),repair.getRpEqpRFID(),repair.getRpDate(),repair.getRpPeople(),repair.getRpDescribe(),repair.getRpCondition());
		
	}
	
	//跟新维修信息
	public static int Update(RepairModel repair){
		
		String query = "Update " + TABLE_NAME + " set RpEqpRFID=?,RpDate=?,RpPeople=?,RpDescribe=?,RpCondition=? where RpID=?";
		
		return DatabaseHander.ExecuteNonQuery(query, repair.getRpEqpRFID(),repair.getRpDate(),repair.getRpPeople(),repair.getRpDescribe(),repair.getRpCondition(),repair.getRpID());
		
	}
	
	//删除维修信息
	public static  int Delete(RepairModel repair){
		 
		String query = "Delete from " + TABLE_NAME + " where RpID=?";
		
		return DatabaseHander.ExecuteNonQuery(query, repair.getRpID());
				
	}
	
	//获得所有的维修数据
	public static ResultSet GetAllList(String whereStr, Object... args){
		
		String query = "select * from " + TABLE_NAME;
		
		if(whereStr != ""){
			
			query += " where " + whereStr;
			
		}
		
		return DatabaseHander.ExecuteQuery(query, args);
		
	}
	
}
