package com.nagarro.javatest.BankingApplication.Entity;

public class CustomException extends Exception {
	
	private String errorMessage;
	public CustomException(String errorMessage) {
		
		this.errorMessage=errorMessage;
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
