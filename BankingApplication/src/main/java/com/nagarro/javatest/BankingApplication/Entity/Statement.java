package com.nagarro.javatest.BankingApplication.Entity;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class Statement {

	
	private String accountId;
	private String dateField;
	private String amount;
	
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	
	
	
	
	public String getDateField() {
		return dateField;
	}
	public void setDateField(String dateField) {
		this.dateField = dateField;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	@Override
	public String toString() {
		return "Statement [accountId=" + accountId + ", dateField=" + dateField + ", amount=" + amount
				+ "]";
	}
	
	
	
}
