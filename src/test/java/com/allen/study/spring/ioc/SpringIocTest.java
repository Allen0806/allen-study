package com.allen.study.spring.ioc;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SpringIocTest {

	@Test
	public void testGetBean() {
		MessageForwardingFactory2.getService(ForwardingWay.HTTP).forward();
		MessageForwardingFactory.getService(ForwardingWay.KAFKA).forward();
	}
}
