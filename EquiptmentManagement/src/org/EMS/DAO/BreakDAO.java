package org.EMS.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.EM.Hander.DatabaseHander;
import org.EMS.Model.BreakModel;

public class BreakDAO {
	
	private static String TABLE_NAME = "t_break";
	
	public static boolean Exist(long BreakID){
		
		String query = "select * from " + TABLE_NAME + " where BreakID=?";
		
		ResultSet rs = DatabaseHander.ExecuteQuery(query, BreakID);
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
	
	//获取故障信息
	public static BreakModel GetModel(Long BreakID){
		
		if(BreakID == null){
			
			return null;
		}
		String query = "select * from " + TABLE_NAME + " where BreakID=?";
		ResultSet rs = DatabaseHander.ExecuteQuery(query, BreakID);
		try{
			if(rs.next()){
				
				BreakModel brk = new BreakModel();
				brk.setBreakID(rs.getLong("BreakID"));
				brk.setBreakEqpID(rs.getLong("BreakEqpID"));
				brk.setBreakEqpRFID(rs.getString("BreakEqpRFID"));
				brk.setBreakDescribe(rs.getString("BreakDescribe"));
				brk.setBreakFixman(rs.getLong("BreakFixman"));
				brk.setBreakReportDate(rs.getDate("BreakReportDate"));
				
				return brk;
			}
			else{
				return null;
			}
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	//插入故障信息
	public static int Insert(BreakModel brk){
		
		String query = "Insert into " + TABLE_NAME + " (BreakEqpID,BreakEqpRFID,BreakDescribe,BreakFixman,BreakReportDate) values (?,?,?,?,?)";
		
		return DatabaseHander.ExecuteNonQuery(query, brk.getBreakEqpID(),brk.getBreakEqpRFID(),brk.getBreakDescribe(),brk.getBreakFixman(),brk.getBreakReportDate());
	}

	//跟新故障信息
	public static int Update(BreakModel brk){
		
		String query = "Update " + TABLE_NAME + " set BreakEqpID=?,BreakEqpRFID=?,BreakDescribe=?,BreakFixman=?,BreakReportDate=? where BreakID=? ";
		
		return DatabaseHander.ExecuteNonQuery(query, brk.getBreakEqpID(),brk.getBreakEqpRFID(),brk.getBreakDescribe(),brk.getBreakFixman(),brk.getBreakReportDate(),brk.getBreakID());
	}
	
	//删除故障信息
	public static int Delete(BreakModel brk){
		
		String query = "delete from " + TABLE_NAME + " where breakID=?";
		
		return DatabaseHander.ExecuteNonQuery(query, brk.getBreakID());
		
	}
	
	//获得所有的故障数据
	public static ResultSet GetAllList(String whereStr, Object... args){
		
		String query = "select * from " + TABLE_NAME;
		
		if(whereStr != ""){
			
			query += " where " + whereStr;
			
		}
		
		return DatabaseHander.ExecuteQuery(query, args);
		
	}
	
}
