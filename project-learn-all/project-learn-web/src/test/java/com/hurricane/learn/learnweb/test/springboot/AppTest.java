package com.hurricane.learn.learnweb.test.springboot;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

//@ComponentScan
//@SpringBootApplication
@RunWith(SpringRunner.class)
@SpringBootTest
public class AppTest {
	private static final Logger LOGGER = LoggerFactory.getLogger(AppTest.class);
//	public static void main(String[] args) {
//		ConfigurableApplicationContext context = SpringApplication.run(AppCfg.class,args);
////		Book bean = context.getBean(Book.class);
////		LOGGER.debug("book bean is {}",bean);
//		LOGGER.debug("book bean is {}","sss");
//		context.close();
//	}
	@Autowired
	Book book;
	@Test
	public void testSpringBoot() {
		Assert.assertNotNull(book);
	}
}
