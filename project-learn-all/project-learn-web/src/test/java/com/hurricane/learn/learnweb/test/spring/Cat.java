package com.hurricane.learn.learnweb.test.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

public class Cat {
	private static final Logger LOGGER = LoggerFactory.getLogger(Cat.class);
	private User user;
	private Dog dog;
	@Autowired
	public void setDog(Dog dog) {
		this.dog = dog;
		LOGGER.debug("cat的普通属性被设置了");
	}

	public Cat(User user) {
		this.user = user;
		LOGGER.debug("cat的构造属性被设置了");
	}
	
	public void show() {
		LOGGER.debug("user is "+user+", dog is "+dog);
//		LOGGER.debug("user is "+user);
	}
	
	public void init() {
		LOGGER.debug("cat的init方法执行了");
	}
}
