package com.hurricane.learn.util.json.pojo;

public class Product {
	private long id;
	private String name;
	private float price;
	public Product(int i, String string, float d) {
		id = i;
		name = string;
		price = d;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	
	
}
