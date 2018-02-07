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
	public static void main(String[] args) throws Exception {
		App app = new App();
//		app.testClassObject();
//		app.testClassLoader(); 
//		app.testInvokePublicMethod();
//		app.testInvokeAllMethod();
//		app.testAccessPublicField();
//		app.testAccessAllField();
		app.testInvokeConstructor();
	}
	/**
	 * 获取其他可见性的构造函数，需要使用forName.getDeclaredConstructor(parameterTypes)方法,<br/>
	 * 使用很简单，不再举例
	 * @throws Exception
	 */
	private void testInvokeConstructor() throws Exception{
		Class forName = Class.forName("com.hurricane.learn.jdk.reflect.Person");
		Constructor constructor = forName.getConstructor(null);
		Person newInstance = (Person) constructor.newInstance(null);
		newInstance.setPublicPerson("public");
		System.out.println(newInstance.getPublicPerson());
	}
	/**
	 * 访问public级别属性 
	 * @throws Exception
	 */
	private void testAccessPublicField() throws Exception {
		Class forName = Class.forName("com.hurricane.learn.jdk.reflect.Person");
		Field field = forName.getField("publicPerson");
		Person person = new Person();
		field.set(person, "publicValue");
		System.out.println(person.getPublicPerson());
	}
	/**
	 * 访问所有可见性属性
	 * @throws Exception
	 */
	private void testAccessAllField() throws Exception {
		Class forName = Class.forName("com.hurricane.learn.jdk.reflect.Person");
		Field field = forName.getDeclaredField("privatePerson");
		Person person = new Person();
		field.setAccessible(true);
		field.set(person, "privateValue");
		System.out.println(field.get(person));
		//可见性的设置会一直有效，应在调用之后将可见性修改回去
		Person person2 = new Person();
		field.set(person2, "privateValue2");
		System.out.println(field.get(person2));
		
	}

	/**
	 * 反射调用公共方法
	 * @throws Exception
	 */
	private void testInvokePublicMethod() throws Exception {
		Class forName = Class.forName("com.hurricane.learn.jdk.reflect.Person");
		Method method = forName.getMethod("setPublicPerson", String.class);
		Person person = new Person();
		method.invoke(person, "abc");
		Method method2 = forName.getMethod("getPublicPerson", null);
		Object invoke = method2.invoke(person, null);
		System.out.println(invoke);
	}
	/**
	 * 反射调用所有可见性方法
	 * @throws Exception
	 */
	private void testInvokeAllMethod() throws Exception {
		Class forName = Class.forName("com.hurricane.learn.jdk.reflect.Person");
		Method method = forName.getDeclaredMethod("setPrivatePerson", String.class);
		Person person = (Person) forName.newInstance();//获取对象实体方式一
//		Person person = new Person();//获取对象实体方式二
		method.setAccessible(true);
		method.invoke(person, "abcd");
		Method method2 = forName.getDeclaredMethod("getPrivatePerson", null);
		method2.setAccessible(true);
		Object invoke = method2.invoke(person, null);
		System.out.println(invoke);
		//修改了类的方法的可见性，会一直有效，应调用之后将可见性修改回原来的值
		Person person2 = new Person();
		method.invoke(person2, "abcd");
		Object invoke2 = method2.invoke(person, null);
		System.out.println(invoke2);
	}
	
	/**
	 * classLoader.getResourceAsStream("name")方法加载类路径下的资源文件
	 * @throws Exception
	 */
	private void testClassLoader() throws Exception {
		//1、获取一个系统的类加载器  
		ClassLoader classLoader = ClassLoader.getSystemClassLoader();  
		System.out.println("System系统的类加载器-->" + classLoader);  

		//2、获取系统类加载器的父类加载器(扩展类加载器（extensions classLoader）)  
		classLoader = classLoader.getParent();  
		System.out.println("Extension扩展类加载器-->" + classLoader);  

		//3、获取扩展类加载器的父类加载器  
		//输出为Null,无法被Java程序直接引用  
		classLoader = classLoader.getParent();  
		System.out.println("BootStrap引导类加载器-->" + classLoader);  


		//4、测试当前类由哪个类加载器进行加载 ,结果就是系统的类加载器  
		classLoader = Class.forName("com.hurricane.learn.jdk.reflect.App").getClassLoader();  
		System.out.println("当前类由哪个类加载器进行加载-->"+classLoader);  

		//5、测试JDK提供的Object类由哪个类加载器负责加载的  
		classLoader = Class.forName("java.lang.Object").getClassLoader();  
		System.out.println("JDK提供的Object类由哪个类加载器加载-->" + classLoader);  

	}



	/**
	 * getConstructors,getFields,getMethods获得的是<b>public</b>的方法（<b>自身定义与从父类继承</b>）
	 * Declared获取的是类<b>自身</b>定义的<b>所有可见级别</b>的
	 * @throws Exception
	 */
	private void testClassObject() throws Exception {
		Class cls;
		//		cls = App.class; //获取class的方式一
		//		App app = new App();
		//		cls = app.getClass(); //获取class的方式二
		cls = Class.forName("com.hurricane.learn.jdk.reflect.Student");//获取class的方式三

		logger.info("===========constructor===========");
		Constructor[] constructors = cls.getConstructors();
		print(constructors);
		constructors = cls.getDeclaredConstructors();
		logger.info("===========constructor Declared===========");
		print(constructors);
		
		
		logger.info("===========fields===========");
		Field[] fields = cls.getFields();
		print(fields);
		logger.info("===========fields Declared===========");
		fields = cls.getDeclaredFields();
		print(fields);

		logger.info("===========method===========");
		Method[] methods = cls.getMethods();
		print(methods);
		logger.info("===========method Declared===========");
		methods = cls.getDeclaredMethods();
		print(methods);

		//使用Class类的newInstance()方法创建类的一个对象 
		Object newInstance = cls.newInstance();
		logger.info("============newInstance 创建新对象==========");
		logger.info(newInstance.toString());
	}
	/**
	 * 打印方法
	 * @param objects
	 */
	private void print(Object[] objects) {
		for (Object object : objects) {
			logger.info(object.toString());
		}
	}
}	
