package com.allen.study.redis;

import java.util.concurrent.ThreadPoolExecutor;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.allen.tool.thread.ThreadPoolExecutorUtil;

@SpringBootTest
class RedissonLockServiceTest {

	@Autowired
	private RedissonLockService redissonLockService;

	@Test
	void testSubmitOrder() {
		redissonLockService.setStock(10);
		ThreadPoolExecutor executor = ThreadPoolExecutorUtil.getExecutor("RedissonTest");
		for (int i = 0; i < 20; i++) {
			executor.execute(() -> redissonLockService.submitOrder());
		}
	}

}
