package com.nagarro.javatest.BankingApplication.Entity;

import org.springframework.stereotype.Component;

@Component
public class UserInput {
	
	private long id;
	private String fromDate;
	private String toDate;
	private double fromAmount;
	private double toAmount;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	public double getFromAmount() {
		return fromAmount;
	}
	public void setFromAmount(double fromAmount) {
		this.fromAmount = fromAmount;
	}
	public double getToAmount() {
		return toAmount;
	}
	public void setToAmount(double toAmount) {
		this.toAmount = toAmount;
	}
	
	@Override
	public String toString() {
		return "userInput [id=" + id + ", fromDate=" + fromDate + ", toDate=" + toDate + ", fromAmount=" + fromAmount
				+ ", toAmount=" + toAmount + "]";
	}
	
	
	

}
