package com.nagarro.javatest.BankingApplication.Entity;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class AccountSummary {
	
	Account account;
	
	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	List<Map<String,Object>> statement;

	public List<Map<String, Object>> getStatement() {
		return statement;
	}

	public void setStatement(List<Map<String, Object>> newStatementList) {
		this.statement = (List<Map<String, Object>>) newStatementList;
	}

	@Override
	public String toString() {
		return "AccountSummary [account=" + account + ", statement=" + statement + "]";
	}
	
	
	
	

}
