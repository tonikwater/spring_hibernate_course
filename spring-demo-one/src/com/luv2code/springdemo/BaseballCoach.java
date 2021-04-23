package com.luv2code.springdemo;

public class BaseballCoach implements Coach {
	
	// define a private field for the dependency
	private FortuneService fortuneService;
	
	public BaseballCoach() {}
	
	// define a constructor for dependency injection
	public BaseballCoach(FortuneService theFortuneService) {
		fortuneService = theFortuneService;
	}

    @Override
    public String getDailyWorkout() {
        return "Do push ups";
    }

	@Override
	public String getDailyFortune() {
		// use my fortuneService to get a fortune
		return String.format("Base: %s", fortuneService.getFortune());
	}
}
