package com.hurricane.learn.learnweb.test.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class Book {
	@Value("${springboot.name}")
	private String name;
	@Value("${springboot.port}")
	private Integer port;
	@Value("${springboot.secondName}")
	private String secondName;
	@Autowired
	private Environment env;
	
	@Override
	public String toString() {
		System.out.println(env.getProperty("springboot.secondName"));
		return "Book [name=" + name + ", port=" + port + ", secondName=" + secondName + "]";
	}
	
	
}
