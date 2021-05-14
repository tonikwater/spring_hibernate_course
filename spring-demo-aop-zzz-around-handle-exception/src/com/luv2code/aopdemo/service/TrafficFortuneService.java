package com.luv2code.aopdemo.service;

import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Component;

@Component
public class TrafficFortuneService {

	
	public String getFortune() {
		
		// simulate a delay
		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// return
		return "Except heavy traffic this morning";
	}

	public String getFortune(boolean tripwire) {
		if(tripwire) {
			throw new RuntimeException("Major accident! Highway is closed!");
		}
		return getFortune();
	}
}
