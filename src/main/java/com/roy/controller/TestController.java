package com.roy.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

	@Autowired
	RedisTemplate redisTemplate;

	@RequestMapping("/get")
	public String getFilter(){
		
		return "ok";
	}


}
