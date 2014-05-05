package org.EMS.Model;

public class DepartmentModel {

	public int getDepartmentID() {
		return DepartmentID;
	}
	public void setDepartmentID(int departmentID) {
		DepartmentID = departmentID;
	}
	public String getDepartmentName() {
		return DepartmentName;
	}
	public void setDepartmentName(String departmentName) {
		DepartmentName = departmentName;
	}
	
	public int getDepartmentParentID() {
		return DepartmentParentID;
	}
	public void setDepartmentParentID(int departmentParentID) {
		DepartmentParentID = departmentParentID;
	}

	private int DepartmentID;
	private String DepartmentName;
	private int DepartmentParentID;

}
