package com.allen.study.spring.ioc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringIocConfigTest {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SpringIocConfigTest.class);
	private static ApplicationContext ctx;
	

	public static void main(String[] args) {
		ctx = new AnnotationConfigApplicationContext(AppConfig.class);
		User user = ctx.getBean(User.class);
		LOGGER.info(user.toString());
		user = (User)ctx.getBean("initUser");
		LOGGER.info(user.toString());
	}

}
