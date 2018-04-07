package com.hurricane.learn.learnweb.test.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.AbstractApplicationContext;

@Configuration
@ComponentScan("com.hurricane.learn.learnweb.test.spring")
public class AppCfg {
	private static final Logger LOGGER = LoggerFactory.getLogger(AppCfg.class);
//	扩展spring
//	1.实现接口ApplicationContextAware
//	2.添加ApplicationContext属性的AutoWired
	
//	spring初始化bean：（BeanFactoryPostProcessor）-》设置属性-》（BeanPostProcessor）—》调用init方法-》（BeanPostProcessor）
//	1.实现接口BeanPostProcessor，其中的两个方法在初始化bean时设置完属性后，分别在init方法调用前后执行,
//	该方法返回代理对象很有用（不修改原有逻辑的情况下，可实现AOP）
//	2.实现接口BeanFactoryPostProcessor，其中的方法postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory)
//	会在spring容器设置bean的属性之前回调
//	AbstractApplicationContext的refresh方法清晰的显示了调用顺序
//	BeanDefinitionRegistryPostProcessor
	@Bean(initMethod="init")
	public Cat createCat(User user) {
		LOGGER.debug("cat被注入的user是: "+user);
		return new Cat(user);
	}
	@Bean("user")
	public User createUser() {
		User user = new User();
		LOGGER.debug("user: "+user);
		return user;
	}
	@Bean
	public User createUser2() {
		User user = new User();
		LOGGER.debug("createUser2: "+user);
		return user;
	}

//	@Bean
//	public Dog createDog() {
//		return new Dog();
//	}
}
