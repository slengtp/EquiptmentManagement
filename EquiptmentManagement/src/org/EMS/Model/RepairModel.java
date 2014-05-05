package org.EMS.Model;

import java.util.Date;

public class RepairModel {

	public long getRpID() {
		return RpID;
	}
	public void setRpID(long rpID) {
		RpID = rpID;
	}
	public String getRpEqpRFID() {
		return RpEqpRFID;
	}
	public void setRpEqpRFID(String rpEqpRFID) {
		RpEqpRFID = rpEqpRFID;
	}
	public Date getRpDate() {
		return RpDate;
	}
	public void setRpDate(Date rpDate) {
		RpDate = rpDate;
	}
	public long getRpPeople() {
		return RpPeople;
	}
	public void setRpPeople(long rpPeople) {
		RpPeople = rpPeople;
	}
	public String getRpDescribe() {
		return RpDescribe;
	}
	public void setRpDescribe(String rpDescribe) {
		RpDescribe = rpDescribe;
	}
	public int getRpCondition() {
		return RpCondition;
	}
	public void setRpCondition(int rpCondition) {
		RpCondition = rpCondition;
	}
	
	private long RpID;
	private String RpEqpRFID;
	private Date RpDate;
	private long RpPeople;
	private String RpDescribe;
	private int RpCondition;
	
}
