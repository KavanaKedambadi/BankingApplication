package com.nagarro.javatest.BankingApplication.Service;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.nagarro.javatest.BankingApplication.BankingApplication;
import com.nagarro.javatest.BankingApplication.Entity.Account;
import com.nagarro.javatest.BankingApplication.Entity.AccountSummary;
import com.nagarro.javatest.BankingApplication.Entity.CustomException;
import com.nagarro.javatest.BankingApplication.Entity.Statement;
import com.nagarro.javatest.BankingApplication.Entity.UserInput;
import com.nagarro.javatest.BankingApplication.Repository.BankRepository;
import com.nagarro.javatest.BankingApplication.Utility.DateFormater;

@Component
@Service
public class BankService  {
	
	@Autowired
	BankRepository bankRepository;
	
	@Autowired
	DateFormater dateFormater;
	
	@Autowired
	Account account;
	
	@Autowired
	Statement statement;
	
	@Autowired
	AccountSummary accountSummary;
	
	
	
	private static Logger logger=LoggerFactory.getLogger(BankService.class);
	
	public AccountSummary getAccountStatement(long id) throws ParseException,CustomException,Exception{
		List<Map<String, Object>> newStatementList= new ArrayList();
		logger.info("fetching bank statement for 3 months from current date");
		Map<String, Object> accountDetails=  bankRepository.getAccount(id);
		List<Map<String, Object>> statementDetails=bankRepository.getStatement(id);
		System.out.println(accountDetails);
		System.out.println(statementDetails);
		
		String hashedAccountNumber=(String) accountDetails.get("account_number");
        String strReplacement = "$$$$$$$$$";
        
        String newString = strReplacement + hashedAccountNumber.substring(9);
		account.setAccountNumber(newString);
		account.setAccountType((String) accountDetails.get("account_type"));
		
		
		
		for(Map<String, Object> item:statementDetails) {
			String dateField=(String) item.get("datefield");
			LocalDate date=dateFormater.convertStringToDate(dateField);
			//System.out.println(date);
			LocalDate threeMonthDate=dateFormater.getThreeMonthDateFromCurrent();
			//System.out.println(threeMonthDate);
			if( date.isBefore(LocalDate.now())){
				
				if(date.isAfter(threeMonthDate)) {
				
				newStatementList.add(item);
//				
				
				
				
			}
			}
			
		}
		
		if (newStatementList.isEmpty())
		{
			logger.info("No statement found for account id");
			throw new CustomException("No statement found");
		}
		else {
			
			accountSummary.setStatement(newStatementList);
			accountSummary.setAccount(account);
			
			return accountSummary;
		}
		
		
		
		
		
	}
	
	public AccountSummary getAccountStatement(UserInput input) throws ParseException, CustomException,Exception{
		//get the list from database
		Map<String, Object> accountDetails=  bankRepository.getAccount(input.getId());
		List<Map<String, Object>> newStatementList= new ArrayList();
		List<Map<String, Object>> newStatListWithDateAmountSort= new ArrayList();
		String hashedAccountNumber=(String) accountDetails.get("account_number");
        String strReplacement = "$$$$$$$$$";
        
        String newString = strReplacement + hashedAccountNumber.substring(9);
        
		account.setAccountNumber(newString);
		account.setAccountType((String) accountDetails.get("account_type"));
		account.setId(String.valueOf(accountDetails.get("ID")));
		
		
		List<Map<String, Object>> statementDetails=bankRepository.getStatement(input.getId());
		
		
		//check if time or date is given
		if(!((input.getFromDate().isEmpty()) && (input.getToDate().isEmpty()))) {
			
			
			logger.info("getting the bank statement based on the date range");
			LocalDate fromDate= dateFormater.convertStringToDate(input.getFromDate());
			LocalDate toDate=dateFormater.convertStringToDate(input.getToDate());
			double fromAmount=input.getFromAmount();
			double toAmount=input.getToAmount();
			
		if(fromAmount<=0.0 && toAmount<=0.0 ) {
				System.out.println("entered dateamountcheck");
			
				for(Map<String, Object> item:statementDetails) {
				String dateField=(String) item.get("datefield");
				LocalDate date=dateFormater.convertStringToDate(dateField);

					if((date.isAfter(fromDate) || date.isEqual(fromDate)) && (date.isBefore(toDate) || date.isEqual(toDate)))
					{
						
						newStatementList.add(item);
						

					}
					else
					{
					System.out.println("not satisfied satisfied condidtion");
					}
				}	
		} else {
			
			for(Map<String, Object> item:statementDetails) {
				String dateField=(String) item.get("datefield");
				LocalDate date=dateFormater.convertStringToDate(dateField);

					if((date.isAfter(fromDate) || date.isEqual(fromDate)) && (date.isBefore(toDate) || date.isEqual(toDate)))
					{
						System.out.println("satisfied condidtion");
						String amountField=(String) item.get("amount");
						double amount=Double. parseDouble(amountField);
						if(amount>=fromAmount) {
							if(amount<=toAmount) {
							newStatementList.add(item);
							
							}
						}
						

					}
					else
					{
					System.out.println("not satisfied satisfied condidtion");
					}
					
					
				}
			
				
			
			
			
			}
			
		} else{
			logger.info("getting the bank statement based on the amount range");
			
			double fromAmount=input.getFromAmount();
			double toAmount=input.getToAmount();
			
			for(Map<String, Object> item:statementDetails) {
				String amountField=(String) item.get("amount");
				double amount=Double. parseDouble(amountField);
				
				if(amount>=fromAmount) {
					if(amount<=toAmount) {
					newStatementList.add(item);
					}
				}
			}
			
		
			
		}
		
		
		
		if (newStatementList.isEmpty())
		{
			throw new CustomException("No statement found");
		}
		else {
			accountSummary.setStatement(newStatementList);
			accountSummary.setAccount(account);
			
			
			return accountSummary;
			
		}
		
		
		
		
	}

}
