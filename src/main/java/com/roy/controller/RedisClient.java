package com.roy.controller;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.Socket;

/**
 * @description：
 * @author： dingyawu
 * @date： created in 14:57 2021-01-16
 * @history:
 */
@RestController
public class RedisClient implements BeanFactoryAware {
  private Socket socket;

  private BeanFactory beanFactory;

  public RedisClient() {
    try {
      socket = new Socket("192.168.159.131", 6379);
      System.out.println("redis连接成功");
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("redis连接失败");
    }
  }

  /**
   * *3 $3 set $4 ding $4 yawu
   *
   * @throws IOException ioexception
   * @return {@link String}
   */
  @RequestMapping("/get1")
  public String execute() throws IOException {
    RedisClient redisClient = beanFactory.getBean(RedisClient.class);
    String result = redisClient.set("ding", "yawu");
    System.out.println(result);
    return result;
  }

  /**
   * 执行redis中的set命令
   *
   * @param key 关键
   * @param value 价值
   * @return {@link String}
   */
  public String set(String key, String value) throws IOException {
    StringBuilder builder = new StringBuilder();
    builder
        .append("*3")
        .append("\r\n")
        .append("$")
        .append("set".length())
        .append("\r\n")
        .append("set")
        .append("\r\n")
        .append("$")
        .append(key.getBytes().length)
        .append("\r\n")
        .append(key)
        .append("\r\n")
        .append("$")
        .append(value.getBytes().length)
        .append("\r\n")
        .append(value)
        .append("\r\n");
    System.out.println(builder.toString());
    socket.getOutputStream().write(builder.toString().getBytes());
    byte[] bytes = new byte[1024];
    socket.getInputStream().read(bytes);
    return new String(bytes);
  }

  public String get(String key) throws IOException {
    StringBuilder builder = new StringBuilder();
    builder
        .append("*2")
        .append("\r\n")
        .append("$")
        .append("get".length())
        .append("\r\n")
        .append("get")
        .append("\r\n")
        .append("$")
        .append(key.getBytes().length)
        .append("\r\n")
        .append(key)
        .append("\r\n");
    System.out.println(builder.toString());
    socket.getOutputStream().write(builder.toString().getBytes("utf-8"));
    byte[] bytes = new byte[1024];
    socket.getInputStream().read(bytes);
    return new String(bytes);
  }

  @Override
  public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
    this.beanFactory = beanFactory;
  }
}
