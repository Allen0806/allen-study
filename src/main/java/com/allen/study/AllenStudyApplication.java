package com.allen.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 启动类
 * 
 * @author Allen
 *
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.allen")
public class AllenStudyApplication {

	public static void main(String[] args) {
		SpringApplication.run(AllenStudyApplication.class, args);
	}

}
