package com.allen.study.spring.ioc;

import org.springframework.stereotype.Service;

/**
 * 通过HTTP转发消息实现类
 * 
 * @author Allen
 * @date 2020年12月1日
 * @since 1.0.0
 */
@Service("messageForwardingByHttp")
public class MessageForwardingByHttp implements MessageForwarding {

	@Override
	public void forward() {
		System.out.println("This is MessageForwardingByHttp");
	}
}
