package org.EMS.Model;

import java.util.Date;

public class EqpModel {
	

	public String getEqpRFID() {
		return EqpRFID;
	}
	public void setEqpRFID(String eqpRFID) {
		EqpRFID = eqpRFID;
	}
	public String getEqpName() {
		return EqpName;
	}
	public void setEqpName(String eqpName) {
		EqpName = eqpName;
	}
	public long getEqpID() {
		return EqpID;
	}
	public void setEqpID(long eqpID) {
		EqpID = eqpID;
	}
	public String getEqpModel() {
		return EqpModel;
	}
	public void setEqpModel(String eqpModel) {
		EqpModel = eqpModel;
	}
	public String getEqpManufacturer() {
		return EqpManufacturer;
	}
	public void setEqpManufacturer(String eqpManufacturer) {
		EqpManufacturer = eqpManufacturer;
	}
	public Date getEqpStartUsingDate() {
		return EqpStartUsingDate;
	}
	public void setEqpStartUsingDate(Date eqpStartUsingDate) {
		EqpStartUsingDate = eqpStartUsingDate;
	}
	public Date getEqpAvailableTime() {
		return EqpAvailableTime;
	}
	public void setEqpAvailableTime(Date eqpAvailableTime) {
		EqpAvailableTime = eqpAvailableTime;
	}
	public int getEqpDepartmentID() {
		return EqpDepartmentID;
	}
	public void setEqpDepartmentID(int eqpDepartmentID) {
		EqpDepartmentID = eqpDepartmentID;
	}
	public long getEqpUserID() {
		return EqpUserID;
	}
	public void setEqpUserID(long eqpUserID) {
		EqpUserID = eqpUserID;
	}
	
	private String EqpRFID;
	private String EqpName;
	private long  EqpID;
	private String EqpModel;
	private String EqpManufacturer;
	private Date EqpStartUsingDate;
	private Date EqpAvailableTime;
	private int EqpDepartmentID;
	private long EqpUserID;
	

}
