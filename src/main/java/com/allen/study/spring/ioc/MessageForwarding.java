package com.allen.study.spring.ioc;

/**
 * 消息转发处理顶层接口，不通的转发方式需实现此接口
 * 
 * @author Allen
 * @date 2020年12月1日
 * @since 1.0.0
 */
public interface MessageForwarding {

	void forward();
}
