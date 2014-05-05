package org.EMS.Model;

import java.util.Date;

public class TransModel {

	public long getTranID() {
		return TranID;
	}
	public void setTranID(long tranID) {
		TranID = tranID;
	}
	public String getTranEqpRFID() {
		return TranEqpRFID;
	}
	public void setTranEqpRFID(String tranEqpRFID) {
		TranEqpRFID = tranEqpRFID;
	}
	public String getTranPeople() {
		return TranPeople;
	}
	public void setTranPeople(String tranPeople) {
		TranPeople = tranPeople;
	}
	public Date getTranDate() {
		return TranDate;
	}
	public void setTranDate(Date tranDate) {
		TranDate = tranDate;
	}
	public int getTranDepartmentID() {
		return TranDepartmentID;
	}
	public void setTranDepartmentID(int tranDepartmentID) {
		TranDepartmentID = tranDepartmentID;
	}
	public int getTranToDepartmentID() {
		return TranToDepartmentID;
	}
	public void setTranToDepartmentID(int tranToDepartmentID) {
		TranToDepartmentID = tranToDepartmentID;
	}
	private  long TranID;
	private  String TranEqpRFID;
	private String TranPeople;
	private Date TranDate;
	private int TranDepartmentID;
	private int TranToDepartmentID;
}
