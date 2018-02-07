package com.hurricane.learn.jdk.reflect.annotation;

import java.lang.annotation.Annotation;

import com.hurricane.learn.jdk.reflect.Person;
import com.hurricane.learn.jdk.reflect.Student;

public class App {
	
	public static void main(String[] args) {
		Person person = new Person();
		Class class1 = person.getClass();
		Annotation[] annotations = class1.getAnnotations();
		for (Annotation annotation : annotations) {
			Ann ann = (Ann) annotation;
			System.out.println(ann.name());
		}
		Student student = new Student();
		Class class2 = student.getClass();
		Annotation[] annotations2 = class2.getAnnotations();
		for (Annotation annotation : annotations2) {
			Ann ann = (Ann) annotation;
			System.out.println(ann.name());
		}
	}
	
}
