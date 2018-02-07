
package com.hurricane.learn.jdk.reflect;

import com.hurricane.learn.jdk.reflect.annotation.Ann;

@Ann
public class Person {
	private String privatePerson;
	String defaultPerson;
	protected String protectedPerson;
	public String publicPerson;
	
	
	private String getPrivatePerson() {
		return privatePerson;
	}
	private void setPrivatePerson(String privatePerson) {
		this.privatePerson = privatePerson;
	}
	String getDefaultPerson() {
		return defaultPerson;
	}
	void setDefaultPerson(String defaultPerson) {
		this.defaultPerson = defaultPerson;
	}
	protected String getProtectedPerson() {
		return protectedPerson;
	}
	protected void setProtectedPerson(String protectedPerson) {
		this.protectedPerson = protectedPerson;
	}
	public String getPublicPerson() {
		return publicPerson;
	}
	public void setPublicPerson(String publicPerson) {
		this.publicPerson = publicPerson;
	}
	
	
}
