package com.luv2code.aopdemo.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.luv2code.aopdemo.Account;

@Component
public class AccountDAO {
	
	
	private String name;
	private String serviceCode;
	
	// add a new method: findAccounts()
	
	public List<Account> findAccounts(){
		List<Account> myAccounts = new ArrayList<>();
		
		Account temp1 = new Account("John", "Silver");
		Account temp2 = new Account("Madhu", "Platinum");
		Account temp3 = new Account("Luca", "Gold");
		
		myAccounts.add(temp1);
		myAccounts.add(temp2);
		myAccounts.add(temp3);
		
		return myAccounts;
	}

	 public void addAccount(Account theAccount, boolean vipFlag) {
		 
		 System.out.println(getClass() + ": DOING MY DB WORK - ADDING AN ACC\n");
	 }
	 
	 public boolean doWork() {
		 System.out.println(getClass() + ": doing work\n");
		 return true;
	 }

	public String getName() {
		System.out.println(getClass() + ": in getName()\n");
		return name;
	}

	public void setName(String name) {
		System.out.println(getClass() + ": in setName()\n");
		this.name = name;
	}

	public String getServiceCode() {
		System.out.println(getClass() + ": in getService()\n");
		return serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		System.out.println(getClass() + ": in setService()\n");
		this.serviceCode = serviceCode;
	}
}
