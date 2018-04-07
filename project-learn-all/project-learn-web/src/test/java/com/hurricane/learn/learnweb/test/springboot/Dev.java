package com.hurricane.learn.learnweb.test.springboot;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("dev")
public class Dev implements Inter{

	@Override
	public void show() {
		System.out.println("this is dev");
	}

}
