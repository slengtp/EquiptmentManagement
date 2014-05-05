package org.EMS.Model;

public class AdminModel {

	private String AdminID;
	public String getAdminID() {
		return AdminID;
	}
	public void setAdminID(String adminID) {
		AdminID = adminID;
	}
	public String getAdminName() {
		return AdminName;
	}
	public void setAdminName(String adminName) {
		AdminName = adminName;
	}
	public String getAdminPassword() {
		return AdminPassword;
	}
	public void setAdminPassword(String adminPassword) {
		AdminPassword = adminPassword;
	}
	public Boolean getAdminIsSuper() {
		return AdminIsSuper;
	}
	public void setAdminIsSuper(Boolean adminIsSuper) {
		AdminIsSuper = adminIsSuper;
	}
	private String AdminName;
	private String AdminPassword;
	private Boolean AdminIsSuper;
	
	
}
