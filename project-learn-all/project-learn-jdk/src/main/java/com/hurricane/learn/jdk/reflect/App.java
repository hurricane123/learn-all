package com.hurricane.learn.jdk.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * Class是一个类，一个描述类的类（也就是描述类本身），
 * 封装了描述方法的Method，
 * 描述字段的Filed，
 * 描述构造器的Constructor等属性
 * 参考网址：https://www.cnblogs.com/bojuetech/p/5896551.html
 * @author Hurricane
 *
 */
public class App {
	private static Logger logger = LoggerFactory.getLogger(App.class);
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		Class cls;
//		cls = App.class; //获取class的方式一
//		App app = new App();
//		cls = app.getClass(); //获取class的方式二
		cls = Class.forName("com.hurricane.learn.jdk.reflect.App");//获取class的方式三
		Method[] methods = cls.getMethods();
		Field[] fields = cls.getFields();
		Constructor[] constructors = cls.getConstructors();
		logger.info("===========method===========");
		for (Method method : methods) {
			logger.info(method.toString());
		}
		logger.info("===========fields===========");
		for (Field field : fields) {
			logger.info(field.toString());
		}
		logger.info("===========constructor===========");
		for (Constructor constructor : constructors) {
			logger.info(constructor.toString());
		}
		
		//使用Class类的newInstance()方法创建类的一个对象 
		Object newInstance = cls.newInstance();
		logger.info("============newInstance 创建新对象==========");
		logger.info(newInstance.toString());
	}
}	
