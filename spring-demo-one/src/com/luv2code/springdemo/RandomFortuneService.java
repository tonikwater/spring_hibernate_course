package com.luv2code.springdemo;

import java.util.Random;

public class RandomFortuneService implements FortuneService {

	private String[] fortunes = {
			"Today u be ballin like the lakurrs!",
			"Struggled today, but learned a lot!",
			"Today was a good day!"
			};
	
	Random random = new Random();
	
	@Override
	public String getFortune() {
		return fortunes[random.nextInt(fortunes.length)];
	}

}
