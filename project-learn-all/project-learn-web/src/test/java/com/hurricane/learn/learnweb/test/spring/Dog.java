package com.hurricane.learn.learnweb.test.spring;

import org.springframework.stereotype.Component;

@Component
public class Dog {
	private String name;
	
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "Dog [name=" + name + "]";
	}
	
	
}
