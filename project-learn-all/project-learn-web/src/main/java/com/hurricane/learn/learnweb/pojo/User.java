package com.hurricane.learn.learnweb.pojo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class User {
	private int id;
	private String name;
	private int age;
	private Date record;
	@JsonProperty("userId")//可以发挥作用
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
//	若是不加JsonFormat注解，返回前端的数据类似1517677711630
//	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm")//添加JsonFormat注解后显示为"2018-02-04 01:07"
	public Date getRecord() {
		return record;
	}
	public void setRecord(Date record) {
		this.record = record;
	}
	
	
}
