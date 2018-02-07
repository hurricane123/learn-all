package com.hurricane.learn.jdk.reflect;

public class Student extends Person{
	private String privateStudent;
	String defaultStudent;
	protected String protectedStudent;
	public String publicStudent;
	private String getPrivateStudent() {
		return privateStudent;
	}
	private void setPrivateStudent(String privateStudent) {
		this.privateStudent = privateStudent;
	}
	String getDefaultStudent() {
		return defaultStudent;
	}
	void setDefaultStudent(String defaultStudent) {
		this.defaultStudent = defaultStudent;
	}
	protected String getProtectedStudent() {
		return protectedStudent;
	}
	protected void setProtectedStudent(String protectedStudent) {
		this.protectedStudent = protectedStudent;
	}
	public String getPublicStudent() {
		return publicStudent;
	}
	public void setPublicStudent(String publicStudent) {
		this.publicStudent = publicStudent;
	}
	
	
}
