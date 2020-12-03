package com.allen.study.spring.ioc;

import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 转发服务工厂
 * 
 * @author Allen
 * @date 2020年12月1日
 * @since 1.0.0
 */
@Component
public class MessageForwardingFactory {

	@Autowired
	private Map<String, MessageForwarding> serviceMap;

	private static Map<String, MessageForwarding> services;

	@PostConstruct
	public void init() {
		MessageForwardingFactory.services = serviceMap;
	}

	/**
	 * 根据转发方式获取对应的转发服务，增加新的转发方式时扩展此方法
	 * 
	 * @param forwardingWay 转发方式
	 * @return 转发服务
	 */
	public static MessageForwarding getService(ForwardingWay forwardingWay) {
		switch (forwardingWay) {
		case HTTP:
			return services.get("messageForwardingByHttp");
		case KAFKA:
			return services.get("messageForwardingByKafka");
		default:
			return null;
		}
	}
}
