package com.roy;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.roy.pojo.User;
import com.roy.utils.RedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
public  class TestSpringbootRedis1 {

	@Autowired
	@Qualifier("redisTemplate")
	RedisTemplate redisTemplate;


	@Autowired
	RedisUtil redisUtil;

	/**
	 * 一般业务中都采用json来传递对象,
	 * user对象序列化以后就可以传输了，在企业中，一般我们所有的pojo都会序列化
	 * @throws JsonProcessingException
	 */
	@Test
	void testSerialize() throws JsonProcessingException {
		User user = new User("roy", 32);
		String str = new ObjectMapper().writeValueAsString(user);
		redisTemplate.opsForValue().set("user", user);
		System.out.println(redisTemplate.opsForValue().get("user"));

	}

	@Test
	void testRedis()  {
		redisUtil.set("ding", "yawu");
		System.out.println(redisUtil.get("ding"));

	}






	

}
