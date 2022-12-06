package com.nagarro.javatest.BankingApplication.Entity;

import org.springframework.stereotype.Component;

@Component
public class Account {
	

  
	private String id;
	private String accountType;
	private String accountNumber;
	
	public String getId() {
		return id;
	}
	public void setId(Object object) {
		this.id = (String) object;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	
	@Override
	public String toString() {
		return "Account [id=" + id + ", accountType=" + accountType + ", accountNumber=" + accountNumber + "]";
	}
	
	
	

}
