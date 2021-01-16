package com.roy.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * @description：
 * @author： dingyawu
 * @date： created in 19:45 2020-12-24
 * @history:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User implements Serializable {
	private String name;

	private Integer age;



}
