package com.allen.study.spring.ioc;

import com.allen.tool.bean.SpringBeanUtil;

/**
 * 转发服务工厂
 * 
 * @author Allen
 * @date 2020年12月1日
 * @since 1.0.0
 */
public class MessageForwardingFactory2 {

	/**
	 * 根据转发方式获取对应的转发服务，增加新的转发方式时扩展此方法
	 * 
	 * @param forwardingWay 转发方式
	 * @return 转发服务
	 */
	public static MessageForwarding getService(ForwardingWay forwardingWay) {
		switch (forwardingWay) {
		case HTTP:
			return SpringBeanUtil.getBean("messageForwardingByHttp", MessageForwarding.class);
		case KAFKA:
			return SpringBeanUtil.getBean("messageForwardingByKafka", MessageForwarding.class);
		default:
			return null;
		}
	}
	
	public static void main(String[] args) {
		getService(ForwardingWay.HTTP).forward();
	}
}
