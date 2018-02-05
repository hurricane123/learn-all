package com.hurricane.learn.learnweb.pojo;

import java.util.List;

public class Result {
	private String name;
	private String url;
	//这里List中的泛型使用了？，可以匹配data不是Result的情况
	private List<?> data; 
	
}
