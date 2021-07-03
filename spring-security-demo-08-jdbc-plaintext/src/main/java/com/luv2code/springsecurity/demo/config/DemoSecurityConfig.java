package com.luv2code.springsecurity.demo.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;

@Configuration
@EnableWebSecurity
public class DemoSecurityConfig extends WebSecurityConfigurerAdapter {
	
	
	@Autowired
	private DataSource securityDataSource;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		// use jdbc authentication
		
		auth.jdbcAuthentication().dataSource(securityDataSource);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
	
		http.authorizeRequests() // restrict access
			.antMatchers("/").hasRole("EMPLOYEE") // GIVE ANYONE WITH "EMPLOYEE" Role access to /
			.antMatchers("/leaders/**").hasRole("MANAGER") // GIVE ANYONE WITH "MANAGER" Role acces to /leaders/**
			.antMatchers("/systems/**").hasRole("ADMIN") // ...
			.and()
			.formLogin() // login process config
				.loginPage("/showMyLoginPage") // path of login form
				.loginProcessingUrl("/authenticateTheUser") // path for authenticating submitted user data
				.permitAll()
			.and()
			.logout() // exposes "/login" POST route
			.permitAll()
			.and()
			.exceptionHandling().accessDeniedPage("/my-access-denied"); // request path if access is denied for a page
	}
}
