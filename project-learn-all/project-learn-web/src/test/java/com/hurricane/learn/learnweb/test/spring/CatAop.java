package com.hurricane.learn.learnweb.test.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CatAop extends Cat{
	
	public CatAop(User user) {
		super(user);
	}

	private static final Logger LOGGER = LoggerFactory.getLogger(CatAop.class);
	private Cat cat;
	
	public void setCat(Cat cat) {
		this.cat = cat;
	}
	
	public void init() {
		LOGGER.debug("cataop init before cat init");
		cat.init();
		LOGGER.debug("cataop init after cat init");
	}
	
	public void show() {
		LOGGER.debug("cataop show before cat show");
		cat.show();
		LOGGER.debug("cataop show after cat show");
	}
}
