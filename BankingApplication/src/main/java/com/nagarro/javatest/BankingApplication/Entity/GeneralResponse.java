package com.nagarro.javatest.BankingApplication.Entity;

import org.springframework.stereotype.Component;

@Component
public class GeneralResponse {
	
	private String message;

	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
	@Override
	public String toString() {
		return "GeneralResponse [message=" + message + "]";
	}


}
