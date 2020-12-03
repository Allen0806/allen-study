package com.allen.study.spring.ioc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

	@Bean
	public User initUser() {
		User user = new User();
		user.setId(1L);
		user.setUserName("John");
		user.setNote("spring test");
		return user;
	}
}
