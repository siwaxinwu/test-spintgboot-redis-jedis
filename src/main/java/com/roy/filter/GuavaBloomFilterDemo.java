package com.roy.filter;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

import java.util.ArrayList;
import java.util.List;

/**
 * @description：  guava自带的布隆过滤器测试，以空间换时间
 * @author： dingyawu
 * @date： created in 13:46 2020-12-31
 * @history:
 */
public class GuavaBloomFilterDemo {

	private static int size = 100000;

	public static void main(String[] args) {
		//后边两个参数：预计包含的数据量，和允许的误差值
		BloomFilter<Integer> bloomFilter = BloomFilter.create(Funnels.integerFunnel(), size, 0.00001);
		for (int i = 0; i < size; i++) {
			bloomFilter.put(i);
		}

		for (int i = 0; i < size; i++) {
			if (!bloomFilter.mightContain(i)) {
				System.out.println("有坏人" + i + "逃脱了");
			}
		}

		//循环1W次，肯定不存在，但还是能命中
		List<Integer> list = new ArrayList<Integer>(1000);
		for (int i = size + 100000; i < size + 200000; i++) {
			if (bloomFilter.mightContain(i)) {
				list.add(i);
			}

		}
		System.out.println("有误伤的数量：" + list.size());

		System.out.println(bloomFilter.mightContain(1));
		System.out.println(bloomFilter.mightContain(2));
		System.out.println(bloomFilter.mightContain(3));
		System.out.println(bloomFilter.mightContain(100001));
	}

}