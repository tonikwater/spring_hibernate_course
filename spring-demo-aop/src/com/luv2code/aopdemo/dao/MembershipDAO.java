package com.luv2code.aopdemo.dao;

import org.springframework.stereotype.Component;

@Component
public class MembershipDAO {

	 public boolean addSillyMember() {
		 
		 System.out.println(getClass() + ": DOING STUFF - ADDING MEMBERSHIP ACC");
		 return true;
	 }
	 
	 public boolean goToSleep() {
		 
		 System.out.println(getClass() + ": I be sleeping on this stuff");
		 return true;
	 }
}
