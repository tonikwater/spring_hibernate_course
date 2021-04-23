package com.luv2code.springdemo;

import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class RandomFortuneService implements FortuneService {

	private String[] data = {
			"Today u be ballin like the lakurrs!",
			"Struggled today, but learned a lot!",
			"Today was a good day!"
	};
	
	private Random random = new Random();
	
	@Override
	public String getFortune() {
		return data[random.nextInt(data.length)];
	}

}
