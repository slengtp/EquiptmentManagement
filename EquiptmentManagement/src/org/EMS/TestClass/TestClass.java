package org.EMS.TestClass;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Date;

import junit.framework.Assert;

import org.EMS.DAO.AdminDAO;
import org.EMS.DAO.BreakDAO;
import org.EMS.DAO.DepartmentDAO;
import org.EMS.DAO.EqpDAO;
import org.EMS.DAO.LendDAO;
import org.EMS.DAO.RepairDAO;
import org.EMS.DAO.StaffDAO;
import org.EMS.DAO.TransDAO;
import org.EMS.Model.AdminModel;
import org.EMS.Model.BreakModel;
import org.EMS.Model.DepartmentModel;
import org.EMS.Model.EqpModel;
import org.EMS.Model.LendModel;
import org.EMS.Model.RepairModel;
import org.EMS.Model.StaffModel;
import org.EMS.Model.TransModel;
import org.junit.Test;

public class TestClass {


	
	
	@SuppressWarnings("deprecation")
	@Test
	public void TestDAO_Insert() throws ParseException{
		

		
/*		BreakModel brk = new BreakModel();
		brk.setBreakID(3);
		brk.setBreakEqpID(3001);
		brk.setBreakEqpRFID("3001");
		brk.setBreakDescribe("发动机不转");
		brk.setBreakFixman(3);
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		//Date类型转换成String类型
		String sdate=sdf.format(new Date());
		//String类型转换成Date类型
		Date ddate= sdf.parse("2005-05-05");
		brk.setBreakReportDate(ddate);*/
		
		AdminModel admin = new AdminModel();
		admin.setAdminID("zw111");
		admin.setAdminName("庄玮");
		admin.setAdminPassword("111");
		admin.setAdminIsSuper(true);
		
		Assert.assertEquals(AdminDAO.Insert(admin), 1);
	}
	
	
	@Test
	public void TestDAO_Delete() throws ParseException{
		
		AdminModel admin = new AdminModel();
		admin.setAdminID("zw111");
		admin.setAdminName("庄玮");
		admin.setAdminPassword("1212");
		admin.setAdminIsSuper(true);
		
		Assert.assertEquals(AdminDAO.Delete(admin), 1);
		
	}
	
	
	@Test
	public void TestUpdate() throws ParseException{
		
		AdminModel admin = new AdminModel();
		admin.setAdminID("zw111");
		admin.setAdminName("庄玮");
		admin.setAdminPassword("1212");
		admin.setAdminIsSuper(true);
		
		Assert.assertEquals(AdminDAO.Update(admin), 1);

	}
	
	@Test
	public void TestGetList() throws SQLException{
		
		ResultSet rs = BreakDAO.GetAllList("breakId=? or breakId = ?",1,2);

		int count = 0;
		
		while(rs.next()){
			count ++;
		}
		Assert.assertEquals(count, 2);
	}
}
