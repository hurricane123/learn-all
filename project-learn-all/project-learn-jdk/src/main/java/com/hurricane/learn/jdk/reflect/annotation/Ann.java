package com.hurricane.learn.jdk.reflect.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Inherited	//指定子类是否继承父类的本注解
public @interface Ann {
	String name() default "defaultName";
}
