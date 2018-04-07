package com.hurricane.learn.learnweb.test.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.ConstructorArgumentValues;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class AddOther implements BeanDefinitionRegistryPostProcessor{
	private static final Logger LOGGER = LoggerFactory.getLogger(AddOther.class);
	
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		LOGGER.debug("postProcessBeanFactory被执行了");
	}
	/**
	 * 这种方式的依赖注入，属性必须要有setter方法
	 * 若是这里指定bean的名称为dog，会使com.hurricane.learn.learnweb.test.Dog无法初始化，即没有Dog的实例
	 */
	public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
		LOGGER.debug("postProcessBeanDefinitionRegistry被执行了");
		BeanDefinitionBuilder bdb = BeanDefinitionBuilder.rootBeanDefinition(Dog2.class);
		bdb.addPropertyValue("name", "dooog");
		registry.registerBeanDefinition("dog2", bdb.getBeanDefinition());
	}

}
