package com.hurricane.learn.learnweb.test.spring;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AppTest {
	private static final Logger LOGGER = LoggerFactory.getLogger(AppTest.class);
	
//	@Test
	public void annonTest() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppCfg.class);
//		context.registerBeanDefinition("dog", BeanDefinitionBuilder.rootBeanDefinition(Dog.class).addPropertyValue("name", "doog").getBeanDefinition());
		Cat cat = context.getBean(Cat.class);
		cat.show();
		Dog2 dog2 = context.getBean(Dog2.class);
		LOGGER.debug(dog2.toString());
		context.close();
	}
}
