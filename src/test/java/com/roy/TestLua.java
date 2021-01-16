package com.roy;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestLua {

  @Test
  void testLua() {
    CacheProperties.Redis redis = new CacheProperties.Redis();
  }
}
