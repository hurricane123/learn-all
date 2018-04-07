package com.hurricane.learn.learnweb.test.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;
@Component
public class Monitor implements BeanFactoryPostProcessor,BeanPostProcessor{
	private static final Logger LOGGER = LoggerFactory.getLogger(Monitor.class);
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		LOGGER.debug("========postProcessBeforeInitialization========="+bean.getClass().getName()+", "+beanName);
		if (bean instanceof Cat) {
			CatAop catAop = new CatAop(null);
			catAop.setCat((Cat) bean);
			return catAop;
		}
		return bean;
	}

	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		LOGGER.debug("========postProcessAfterInitialization========="+bean.getClass().getName()+", "+beanName);
//		if (bean instanceof Cat) {
//			CatAop catAop = new CatAop(null);
//			catAop.setCat((Cat) bean);
//			return catAop;
//		}
		return bean;
	}

	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		LOGGER.debug("========postProcessBeanFactory========="+beanFactory.getBeanDefinitionCount());
	}

}
