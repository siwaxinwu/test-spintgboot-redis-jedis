package com.roy.filter;

import org.redisson.Redisson;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

/**
 * @description： 测试bloomFilter，结合Redisson的客户端
 * @author： dingyawu
 * @date： created in 14:28 2020-12-31
 * @history:
 */
public class RedissonBloomFilterDemo {

	public static void main(String[] args) {

		//创建配置
		Config config = new Config();
		//指定使用单节点部署方式
		config.useSingleServer().setAddress("redis://192.168.159.131:6379").setPassword("123456");
		//创建客户端(发现创建RedissonClient非常耗时，基本在2秒-4秒左右)
		RedissonClient redisson = Redisson.create(config);
		RBloomFilter<String> bloomFilter = redisson.getBloomFilter("user");
		// 初始化布隆过滤器，预计统计元素数量为55000000，期望误差率为0.03
		bloomFilter.tryInit(55000000L, 0.03);
		bloomFilter.add("Tom");
		bloomFilter.add("Jack");
		System.out.println(bloomFilter.count());
		System.out.println(bloomFilter.contains("Tom"));
		System.out.println(bloomFilter.contains("Linda"));
	}
}
