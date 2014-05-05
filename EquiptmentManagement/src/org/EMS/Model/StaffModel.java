package org.EMS.Model;

public class StaffModel {
	

	public long getStaffId() {
		return StaffId;
	}
	public void setStaffId(long staffId) {
		StaffId = staffId;
	}
	public String getStaffName() {
		return StaffName;
	}
	public void setStaffName(String staffName) {
		StaffName = staffName;
	}
	public Boolean getStaffGender() {
		return StaffGender;
	}
	public void setStaffGender(Boolean staffGender) {
		StaffGender = staffGender;
	}
	public int getStaffDepartmentID() {
		return StaffDepartmentID;
	}
	public void setStaffDepartmentID(int staffDepartmentID) {
		StaffDepartmentID = staffDepartmentID;
	}
	public String getStaffTitle() {
		return StaffTitle;
	}
	public void setStaffTitle(String staffTitle) {
		StaffTitle = staffTitle;
	}
	public String getStaffPassword() {
		return StaffPassword;
	}
	public void setStaffPassword(String staffPassword) {
		StaffPassword = staffPassword;
	}
	public String getStaffProfession() {
		return StaffProfession;
	}
	public void setStaffProfession(String staffProfession) {
		StaffProfession = staffProfession;
	}
	public int getStaffLimit() {
		return StaffLimit;
	}
	public void setStaffLimit(int staffLimit) {
		StaffLimit = staffLimit;
	}
	
	private long StaffId;
	private String StaffName;
	private Boolean StaffGender;
	private int StaffDepartmentID;
	private String StaffTitle;
	private String StaffPassword;
	private String StaffProfession;
	private int StaffLimit;
	
}
