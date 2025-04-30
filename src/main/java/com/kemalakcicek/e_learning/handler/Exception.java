package com.kemalakcicek.e_learning.handler;

import java.util.Date;

import lombok.Data;

@Data
public class Exception<E> {

	private String path;

	private Date createTime;

	private String host;

	private E message;

}
