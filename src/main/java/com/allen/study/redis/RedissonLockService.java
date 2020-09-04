package com.allen.study.redis;

import java.util.concurrent.TimeUnit;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedissonLockService {

	private static final Logger LOGGER = LoggerFactory.getLogger(RedissonLockService.class);

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Autowired
	private RedissonClient redissonClient;

	private static final String PRODUCT = "MoonCake";

	private static final String STOCK = "stock";

	public void setStock(int stock) {
		stringRedisTemplate.opsForValue().set(STOCK, String.valueOf(stock));
	}

	public void submitOrder() {
		RLock lock = redissonClient.getLock(PRODUCT);
		lock.lock();// 阻塞
		try {
			int stock = Integer.parseInt(stringRedisTemplate.opsForValue().get(STOCK));
			if (stock > 0) {
				// 下单
				stock--;
				stringRedisTemplate.opsForValue().set(STOCK, String.valueOf(stock));
				LOGGER.info(Thread.currentThread().getName() + "-扣减成功，库存stock：" + stock);
			} else {
				// 没库存
				LOGGER.info(Thread.currentThread().getName() + "-扣减失败，库存不足");
			}
		} finally {
			lock.unlock();// 释放锁
		}
	}

	public void submitOrderTryLock() {
		RLock lock = redissonClient.getLock(PRODUCT);
//		lock.lock();// 阻塞
		boolean b = false;
		try {
			b = lock.tryLock(1, TimeUnit.MILLISECONDS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // 非阻塞
		if (b) {
			try {
				int stock = Integer.parseInt(stringRedisTemplate.opsForValue().get(STOCK));
				if (stock > 0) {
					// 下单
					stock--;
					stringRedisTemplate.opsForValue().set(STOCK, String.valueOf(stock));
					LOGGER.info(Thread.currentThread().getName() + "-扣减成功，库存stock：" + stock);
				} else {
					// 没库存
					LOGGER.info(Thread.currentThread().getName() + "-扣减失败，库存不足");
				}
			} finally {
				lock.unlock();// 释放锁
			}
		} else {
			LOGGER.info(Thread.currentThread().getName() + "-未获取到锁");
		}

	}

}
