package com.nagarro.javatest.BankingApplication.Repository;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.nagarro.javatest.BankingApplication.Entity.Account;
import com.nagarro.javatest.BankingApplication.Entity.CustomException;

@Component
public class BankRepository {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	private String ACCOUNT_QUERY="select * from account where ID=?";
	private String STATEMENT_QUERY="select * from statement where account_id=?";
	

	
	public Map<String, Object> getAccount(long id) throws CustomException,Exception{
		 String ACCOUNT_Count="select count(*) from account where ID="+id;
		int row=jdbcTemplate.queryForObject(ACCOUNT_Count,Integer.class);
		System.out.println(row);
		if(row<1) {
			throw new CustomException("Please enter valid account id");
		}
		else {
		
		 return jdbcTemplate.queryForMap(ACCOUNT_QUERY,id);
		}
	}
	
	public List<Map<String, Object>> getStatement(long accountId) throws CustomException,Exception{
		String STATEMENT_Count="select count(*) from statement where account_id="+accountId;
		int row1=jdbcTemplate.queryForObject(STATEMENT_Count,Integer.class);
		
		if(row1<1) {
			throw new CustomException("Please enter valid account id");
		}
		else {
		return jdbcTemplate.queryForList(STATEMENT_QUERY,accountId);
		}
	}
	

}
