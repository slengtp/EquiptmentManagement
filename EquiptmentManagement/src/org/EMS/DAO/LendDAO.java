package org.EMS.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.EM.Hander.DatabaseHander;
import org.EMS.Model.LendModel;

public class LendDAO {
	
	private static String TABLE_NAME = "t_lend";
	
	//判断此借用信息是否存在
	public static Boolean Exist(long LendID){
		
		String query = "select * from " + TABLE_NAME + " where LendID=?";
		
		ResultSet rs = DatabaseHander.ExecuteQuery(query, LendID);
		
		try{
			if(rs.next()){
				return true;
			}
			else{
				return false;
			}
		}catch(SQLException e){
			
			e.printStackTrace();
			return false;
			
		}
	}
	
	//获取借用信息
	public static LendModel GetModel(Long lendID){
		
		if(lendID == null){
			
			//staffID为空，出错
			return null;
		}
		
		//SQL语句
		String query = "select * from " + TABLE_NAME + " where lendID=?";
		
		//执行SQL语句，并且得到返回值
		ResultSet rs = DatabaseHander.ExecuteQuery(query, lendID);
		try {
			if(rs.next()){
				
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
				
				return lend;

			}
			else{
				
				return null;
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	//插入借用信息
	public static int Insert(LendModel lend){
		
		String query = "Insert into " + TABLE_NAME + " (LendID,LendEqpRFID,LendDepartmentID,LendDate,LendTime,LendDeadline,LendToPeople,LendFromCharge,LendFlag) values (?,?,?,?,?,?,?,?,?)";
	
		return DatabaseHander.ExecuteNonQuery(query, lend.getLendID(),lend.getLendEqpRFID(),lend.getLendDepartmentID(),lend.getLendDate(),lend.getLendTime(),lend.getLendDeadline(),lend.getLendToPeople(),lend.getLendFromCharge(),lend.getLendFlag());
			
	}
	
	//更新借用信息
	public static int Update(LendModel lend){
		
		//根据ID来进行员工的更新
		String query = "Update " + TABLE_NAME + " set LendEqpRFID=?,LendDepartmentID=?,LendDate=?,LendTime=?,LendDeadline=?,LendToPeople=?,LendFromCharge=?,LendFlag=? where LendID=?";
	
		return DatabaseHander.ExecuteNonQuery(query, lend.getLendEqpRFID(),lend.getLendDepartmentID(),lend.getLendDate(),lend.getLendTime(),lend.getLendDeadline(),lend.getLendToPeople(),lend.getLendFromCharge(),lend.getLendFlag(),lend.getLendID());
		
	}
	
	//删除借用信息
	public static int Delete(LendModel lend){
		
		String query = "delete from " + TABLE_NAME + " where lendID=?";
		
		return DatabaseHander.ExecuteNonQuery(query, lend.getLendID());
		
	}
	
	//获得所有的借用数据
	public static ResultSet GetAllList(String whereStr, Object... args){
		
		String query = "select * from " + TABLE_NAME;
		
		if(whereStr != ""){
			
			query += " where " + whereStr;
			
		}
		
		return DatabaseHander.ExecuteQuery(query, args);
		
	}
	
}
