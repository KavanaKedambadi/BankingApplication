package com.nagarro.javatest.BankingApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.rest.RepositoryRestMvcAutoConfiguration;
import org.springframework.jdbc.core.JdbcTemplate;



@SpringBootApplication
public class BankingApplication  {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	private static Logger logger=LoggerFactory.getLogger(BankingApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(BankingApplication.class, args);
		
		logger.info("Banking application is up and running:");
	}

//	@Override
//	public void run(String... args) throws Exception {
//		System.out.println(jdbcTemplate.queryForList("select * from account where ID=1"));
//		
//	}

}
