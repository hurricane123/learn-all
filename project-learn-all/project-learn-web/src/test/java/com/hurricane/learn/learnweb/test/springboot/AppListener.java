package com.hurricane.learn.learnweb.test.springboot;
/**
 * 1:自定义事件，一般继承ApplicationEvent抽象类
 * 2：自定义事件监听器，一般是实现ApplicationListener接口
 * 3：配置监听器，启动的时候，需要把监听器加入到spring容器中
 * 4：发送事件，使用ApplicationContext.publishEvent发布事件
 * 
 * 配置监听器
 * 1：SpringApplication.addListeners添加监听器
 * 2：把监听器纳入到spring容器中管理
 * 3：使用context.listener.classes配置项配置（详细内容参照DelegatingApplicationListener）
 * 4:使用@EventListener注解，在方法上面添加@EventListener注解，且该类加入spring容器，详细见EventListenerFactory、EventListenerMethodProcessor
 * @author Hurricane
 *
 */

import org.springframework.boot.context.config.DelegatingApplicationListener;
import org.springframework.context.event.EventListenerFactory;
import org.springframework.context.event.EventListenerMethodProcessor;

public class AppListener {
	

}
