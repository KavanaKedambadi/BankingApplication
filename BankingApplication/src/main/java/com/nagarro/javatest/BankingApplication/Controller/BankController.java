package com.nagarro.javatest.BankingApplication.Controller; 

import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.nagarro.javatest.BankingApplication.Entity.AccountSummary;
import com.nagarro.javatest.BankingApplication.Entity.CustomException;
import com.nagarro.javatest.BankingApplication.Entity.GeneralResponse;
import com.nagarro.javatest.BankingApplication.Entity.UserInput;
import com.nagarro.javatest.BankingApplication.Service.BankService;

@RestController
public class BankController {
	
	@Autowired
	BankService bankService;
	
	@Autowired
	UserInput input;
	
	@Autowired
	AccountSummary accountSummary;
	
	@Autowired
	GeneralResponse generalResponse;
	 
	private static Logger logger=LoggerFactory.getLogger(BankController.class);
	
	
 @GetMapping("/userlogin")
	 public ResponseEntity<?> getCurrentSessionUser(){
	 Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		generalResponse.setMessage(currentPrincipalName);
		 return new ResponseEntity<GeneralResponse>(generalResponse,HttpStatus.OK);
	 }
 

	
	@PostMapping("/getAccStatement")
	public ResponseEntity<?> getAccountStatementForRange(@RequestBody UserInput userInput){
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		
		
		
		
		
		try {
			if(currentPrincipalName.contains("admin")) {
				
			if((userInput.getFromAmount()==0.00 && userInput.getFromAmount()==0.00 && userInput.getFromDate().isEmpty() && userInput.getToDate().isEmpty()))
			{
				long id= userInput.getId();
				AccountSummary summary=bankService.getAccountStatement(id);
				return ResponseEntity.status(HttpStatus.OK).body(summary);
			}else  {
				
				AccountSummary summary =bankService.getAccountStatement(userInput);
			return ResponseEntity.status(HttpStatus.OK).body(summary);
			
			}
			
			
			
			}
			else if(currentPrincipalName.contains("user"))
			{
				
				if((userInput.getFromAmount()==0.00 && userInput.getFromAmount()==0.00 && userInput.getFromDate().isEmpty() && userInput.getToDate().isEmpty()))
				{
					long id= userInput.getId();
					
					AccountSummary summary=bankService.getAccountStatement(id);
					
					return ResponseEntity.status(HttpStatus.OK).body(summary);
					
				}else {
					 
			
					generalResponse.setMessage("Only Admin can get statement based on date and amount range");
					return new ResponseEntity<GeneralResponse>(generalResponse,HttpStatus.UNAUTHORIZED);
				}
				
			}
				else {
					generalResponse.setMessage("Unauthorized user");
					return new ResponseEntity<GeneralResponse>(generalResponse,HttpStatus.UNAUTHORIZED);
				}				
			
		} catch (CustomException e) {
			System.out.println("In Custom Exception"+e.getMessage());
			e.printStackTrace();
			logger.debug("Exception is:" + e);
			logger.info(e.getErrorMessage());
			generalResponse.setMessage(e.getErrorMessage());
			
			return new ResponseEntity<GeneralResponse>(generalResponse,HttpStatus.NOT_FOUND);
			
		}catch (Exception e) {
           System.out.println("In Exception"+e.getMessage());
			
           generalResponse.setMessage("Invalid Data - Exception occured. Please check log file for more information");
			logger.info("error occured : "+ e.getStackTrace());
			return new ResponseEntity<GeneralResponse>(generalResponse ,HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
		
	}

}
