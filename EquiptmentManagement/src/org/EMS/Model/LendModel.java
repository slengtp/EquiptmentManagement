package org.EMS.Model;

import java.util.Date;

public class LendModel {
	
	public long getLendID() {
		return LendID;
	}
	public void setLendID(long lendID) {
		LendID = lendID;
	}
	public String getLendEqpRFID() {
		return LendEqpRFID;
	}
	public void setLendEqpRFID(String lendEqpRFID) {
		LendEqpRFID = lendEqpRFID;
	}
	public int getLendDepartmentID() {
		return LendDepartmentID;
	}
	public void setLendDepartmentID(int lendDepartmentID) {
		LendDepartmentID = lendDepartmentID;
	}
	public Date getLendDate() {
		return LendDate;
	}
	public void setLendDate(Date lendDate) {
		LendDate = lendDate;
	}
	public Date getLendTime() {
		return LendTime;
	}
	public void setLendTime(Date lendTime) {
		LendTime = lendTime;
	}
	public Date getLendDeadline() {
		return LendDeadline;
	}
	public void setLendDeadline(Date lendDeadline) {
		LendDeadline = lendDeadline;
	}
	public long getLendToPeople() {
		return LendToPeople;
	}
	public void setLendToPeople(long lendToPeople) {
		LendToPeople = lendToPeople;
	}
	public long getLendFromCharge() {
		return LendFromCharge;
	}
	public void setLendFromCharge(long lendFromCharge) {
		LendFromCharge = lendFromCharge;
	}
	public boolean getLendFlag() {
		return LendFlag;
	}
	public void setLendFlag(boolean lendFlag) {
		LendFlag = lendFlag;
	}
	
	private long LendID;
	private String LendEqpRFID;
	private int LendDepartmentID;
	private Date LendDate;
	private Date LendTime;
	private Date LendDeadline;
	private long LendToPeople;
	private long LendFromCharge;
	private boolean LendFlag;

}
