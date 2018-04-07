package com.hurricane.learn.learnweb.test.springboot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;
/**
 * 注解@ConfigurationProperties的使用需要springboot的注解@EnableConfigurationProperties的支持，
 * 直接使用@SpringBootApplication也可以
 * @author Hurricane
 *
 */
@ConfigurationProperties(prefix="cart.springboot")
@Component
public class Cart {
	private String name;
	private Integer port;
	private String secondName;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	public String getSecondName() {
		return secondName;
	}

	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}

	@Override
	public String toString() {
		return "Cart [name=" + name + ", port=" + port + ", secondName=" + secondName + "]";
	}
	
	
}
