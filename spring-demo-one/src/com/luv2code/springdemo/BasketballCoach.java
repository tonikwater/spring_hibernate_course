package com.luv2code.springdemo;

public class BasketballCoach implements Coach {
	
	// define a private field for the dependency
	private FortuneService fortuneService;
	
	// define a constructor for dependency injection
	public BasketballCoach(FortuneService theFortuneService) {
		fortuneService = theFortuneService;
	}

	@Override
	public String getDailyWorkout() {
		return "Ball a bit with Mikey Williams.";
	}

	@Override
	public String getDailyFortune() {
		// use my fortuneService to get a fortune
		return String.format("Ball: %s", fortuneService.getFortune());
	}

}
