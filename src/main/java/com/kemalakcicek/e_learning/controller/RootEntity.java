package com.kemalakcicek.e_learning.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(value = Include.NON_NULL)
public class RootEntity<T> {

	private int status;

	private String errorMessage;

	private T payload;

	public static <T> RootEntity<T> ok(T payload) {

		RootEntity<T> rootEntity = new RootEntity<>();

		rootEntity.setStatus(200);
		rootEntity.setPayload(payload);
		rootEntity.setErrorMessage(null);

		return rootEntity;

	}

	public static <T> RootEntity<T> error(String errorMessage) {

		RootEntity<T> rootEntity = new RootEntity<>();

		rootEntity.setStatus(400);
		rootEntity.setPayload(null);
		rootEntity.setErrorMessage(errorMessage);

		return rootEntity;

	}

}
