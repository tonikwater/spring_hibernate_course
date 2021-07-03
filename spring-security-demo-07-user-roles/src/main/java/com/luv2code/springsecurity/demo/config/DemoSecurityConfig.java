package com.luv2code.springsecurity.demo.config;

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

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		// add users for in memory authentication
		
		UserBuilder users = User.withDefaultPasswordEncoder();
		
		auth.inMemoryAuthentication()
		.withUser(users.username("john").password("test123").roles("EMPLOYEE"))
		.withUser(users.username("mary").password("test123").roles("EMPLOYEE", "MANAGER"))
		.withUser(users.username("susan").password("test123").roles("EMPLOYEE", "ADMIN"));
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
