package com.hurricane.learn.util.json.pojo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hurricane.learn.util.json.util.ReName;

public class User {
	private Integer id;
	private String name;
	@JsonProperty("uage")
	private int age;
	private List<Product> products;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@ReName(name="aaa")
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
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", age=" + age + "]";
	}

}
