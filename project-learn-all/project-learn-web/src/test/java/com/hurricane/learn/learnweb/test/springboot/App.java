package com.hurricane.learn.learnweb.test.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
/**
 * 获取配置文件的两种方法：
 * 默认的配置文件名为application.properties
 * 默认的位置为编译路径（classpath）下
 * 默认的文件名可以用--spring.config.name来指定，只是指定名字（不能带文件扩展名）--spring.config.name=myconf 
 * 指定带路径的配置文件可以使用--spring.config.location=conf/myconf/myconfig.properties
 * 多个配置文件用逗号隔开，同一个配置后面的覆盖前面的：--spring.config.location=classpath:/myconf.properties,conf/myconf/myconfig.properties
 * 程序参数配置优先于@PropertySource注解
 * 注解@PropertySource可以连续配置多个，同一配置后面的覆盖前面的，优先使用application.properties的配置
 * 使用注解@PropertySources也可指定多个
 * 支持注入数组、list，在properties文件中使用name[index]=value的格式即可
 * --spring.profiles.active=dep可以用来指定激活哪一套配置文件，与哪一套bean被spring管理，可以指定多个值，用逗号分隔
 * @author Hurricane
 *
 */
//@PropertySource("myconf.properties")
//@PropertySource("file:\\E:\\Profession\\myconfig.properties")
//@PropertySources({@PropertySource("myconf.properties"),@PropertySource("file:\\E:\\Profession\\myconfig.properties")})
@ComponentScan
@EnableConfigurationProperties
//@SpringBootApplication
public class App {
	
//	@Bean
//	private Book createBook() {
//		return new Book();
//	}
	
	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(App.class, args);
		Book bean = context.getBean(Book.class);
		System.out.println(bean);
		Cart bean2 = context.getBean(Cart.class);
		System.out.println(bean2);
		context.getBean(Inter.class).show();
		context.close();
	}
}
