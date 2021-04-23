package com.luv2code.springdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class TennisCoach implements Coach {
	
//	@Autowired
//	@Qualifier("randomFortuneService")
	private FortuneService fortuneService;
	
	@Value("${foo.email}")
	private String email;
	
	// define default constructor
	@Autowired
	public TennisCoach(@Qualifier("randomFortuneService") FortuneService fortuneService) {
		this.fortuneService = fortuneService;
		System.out.println(">> TennisCoach: inside default constructor");
	}
	
	/*
	 * @Autowired public TennisCoach(FortuneService theFortuneService) {
	 * fortuneService = theFortuneService; }
	 */

	/*
	 * @Autowired public void doSomeCrazyStuff(FortuneService theFortuneService) {
	 * System.out.println(">> TennisCoach: inside doSomeCrazyStuff() method");
	 * this.fortuneService = theFortuneService; }
	 */

	@Override
	public String getDailyWorkout() {
		
		return "Practice your backhand volley";
	}

	@Override
	public String getDailyFortune() {
		return fortuneService.getFortune();
	}

	public String getEmail() {
		return email;
	}
}
