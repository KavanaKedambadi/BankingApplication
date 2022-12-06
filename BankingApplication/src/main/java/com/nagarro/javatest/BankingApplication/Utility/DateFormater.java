package com.nagarro.javatest.BankingApplication.Utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class DateFormater {
	
	public LocalDate convertStringToDate(String dateFromDb) throws ParseException {
		
		String newString=dateFromDb.replace(".", "-");
		   
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate localDate = LocalDate.parse(newString, formatter);
		//System.out.println(localDate); 
		 return localDate;
		
	}
	
	public LocalDate getThreeMonthDateFromCurrent() {
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		 Date date = new Date();
       // System.out.println("Current Date " + dateFormat.format(date));

        // Convert Date to Calendar
        Calendar c = Calendar.getInstance();
        c.setTime(date);

        // Perform addition/subtraction
        c.add(Calendar.YEAR, 0);
        c.add(Calendar.MONTH, -3);
        c.add(Calendar.DATE, 0);



        // Convert calendar back to LocalDate
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate localDate1 = LocalDateTime.ofInstant(c.toInstant(), c.getTimeZone().toZoneId()).toLocalDate();
       //System.out.println("Updated Date " + localDate1.format(formatter));
        
        return localDate1;

	}
	
	

}
