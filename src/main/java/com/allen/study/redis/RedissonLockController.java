package com.allen.study.redis;

import java.util.concurrent.ThreadPoolExecutor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.allen.tool.thread.ThreadPoolExecutorUtil;

@RestController
@RequestMapping(path = "/redisson")
public class RedissonLockController {
	
	@Autowired
	private RedissonLockService redissonLockService;

	@RequestMapping("/testLock")
	public void testLock() {
		redissonLockService.setStock(10);
		ThreadPoolExecutor executor = ThreadPoolExecutorUtil.getExecutor("RedissonTest");
		for (int i = 0; i < 20; i++) {
			executor.execute(() -> redissonLockService.submitOrder());
		}
	}
	
	@RequestMapping("/testTryLock")
	public void testTryLock() {
		redissonLockService.setStock(10);
		ThreadPoolExecutor executor = ThreadPoolExecutorUtil.getExecutor("RedissonTest");
		for (int i = 0; i < 20; i++) {
			executor.execute(() -> redissonLockService.submitOrderTryLock());
		}
	}
}
