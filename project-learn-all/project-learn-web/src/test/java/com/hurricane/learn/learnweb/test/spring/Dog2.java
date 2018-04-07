package com.hurricane.learn.learnweb.test.spring;

import org.springframework.stereotype.Component;

/**
 * 这个类的一个实例被手动添加到了spring容器中
 * @see AddOther
 * @author Hurricane
 *
 */
public class Dog2 {
	private String name;
	
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "Dog [name=" + name + "]";
	}
	
	
}
