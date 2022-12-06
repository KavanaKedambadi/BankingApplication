package com.nagarro.javatest.BankingApplication.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration
public class webSecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.csrf().disable().authorizeRequests().antMatchers("/**")
		.hasAnyRole("admin","user","test").and().formLogin().and().logout().logoutUrl("/doLogout").and().sessionManagement().maximumSessions(2).expiredUrl("/login");
		
		  	
		
		
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
		auth.inMemoryAuthentication().withUser("user").password("{noop}user").roles("user").and()
		.withUser("admin").password("{noop}admin").roles("user","admin")
		.and().withUser("test").password("{noop}test").roles("user");
	}

	}


