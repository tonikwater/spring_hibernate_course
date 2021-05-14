package com.luv2code.aopdemo;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.luv2code.aopdemo.dao.AccountDAO;
import com.luv2code.aopdemo.dao.MembershipDAO;

public class AfterReturningDemoApp {

	public static void main(String[] args) {
		
		// read spring config java class
		AnnotationConfigApplicationContext context = 
				new AnnotationConfigApplicationContext(DemoConfig.class);
		
		// get the bean from spring container
		AccountDAO theAccountDAO = context.getBean("accountDAO", AccountDAO.class);
		MembershipDAO theMembershipDAO = context.getBean("membershipDAO", MembershipDAO.class);
		
		// call business methods
		
		List<Account> theAccounts = theAccountDAO.findAccounts();
		
		System.out.println("\n\n---\nMain Program: AfterReturningDemoApp");
		System.out.println("---");
		
		System.out.println(theAccounts);
		System.out.println("at: " +  System.identityHashCode(theAccounts));
		System.out.println("\n");
		
		// close context
		context.close();
	}

}
