package org.EMS.Model;

import java.util.Date;

public class BreakModel {
	
	public long getBreakID() {
		return BreakID;
	}
	public void setBreakID(long breakID) {
		BreakID = breakID;
	}
	public long getBreakEqpID() {
		return BreakEqpID;
	}
	public void setBreakEqpID(long breakEqpID) {
		BreakEqpID = breakEqpID;
	}
	public String getBreakEqpRFID() {
		return BreakEqpRFID;
	}
	public void setBreakEqpRFID(String breakEqpRFID) {
		BreakEqpRFID = breakEqpRFID;
	}
	public String getBreakDescribe() {
		return BreakDescribe;
	}
	public void setBreakDescribe(String breakDescribe) {
		BreakDescribe = breakDescribe;
	}
	public long getBreakFixman() {
		return BreakFixman;
	}
	public void setBreakFixman(long breakFixman) {
		BreakFixman = breakFixman;
	}
	public Date getBreakReportDate() {
		return BreakReportDate;
	}
	public void setBreakReportDate(Date breakReportDate) {
		BreakReportDate = breakReportDate;
	}
	
	
	private long BreakID;
	private long BreakEqpID;
	private String BreakEqpRFID;
	private String BreakDescribe;
	private long BreakFixman;
	private Date BreakReportDate;
	
	
	
}
