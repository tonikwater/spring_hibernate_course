package com.luv2code.aopdemo;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.luv2code.aopdemo.dao.AccountDAO;
import com.luv2code.aopdemo.dao.MembershipDAO;

public class AfterThrowingDemoApp {

	public static void main(String[] args) {
		
		// read spring config java class
		AnnotationConfigApplicationContext context = 
				new AnnotationConfigApplicationContext(DemoConfig.class);
		
		// get the bean from spring container
		AccountDAO theAccountDAO = context.getBean("accountDAO", AccountDAO.class);
		MembershipDAO theMembershipDAO = context.getBean("membershipDAO", MembershipDAO.class);
		
		// call business methods
		
		List<Account> theAccounts = null;
		
		try {
			boolean tripwire = true;
			theAccounts = theAccountDAO.findAccounts(tripwire);
		}catch(Exception exc) {
			System.out.println("\n\nMain Program ... caught exception: " + exc);
		}
					
		System.out.println("\n\n---\nMain Program: AfterThrowingDemoApp");
		System.out.println("---");
		
		System.out.println(theAccounts);
		System.out.println("at: " +  System.identityHashCode(theAccounts));
		System.out.println("\n");
		
		// close context
		context.close();
	}

}
