package com.roy;


import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@SpringBootTest
public  class TestSpringbootRedis {

	@Autowired
	RedisTemplate redisTemplate;

	@Test
	void testKey() {
		//opsForValue()----操作字符串的，类似string字符串操作
		redisTemplate.opsForValue().set("myname", "roy");
		System.out.println(redisTemplate.opsForValue().get("myname"));
	}



}
