package com.kemalakcicek.e_learning.handler;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApiError<E> {

	private Integer status;

	private Exception<E> exception;

	@Override
	public String toString() {
		return "{" + "\n  \"status\": " + this.status + "," + "\n  \"exception\": {" + "\n    \"hostname\": \""
				+ this.exception.getHost() + "\"," + "\n    \"path\": \"" + this.exception.getPath() + "\","
				+ "\n    \"createTime\": \"" + this.exception.getCreateTime() + "\"," + "\n    \"message\": \""
				+ this.exception.getMessage() + "\"" + "\n  }" + "\n}";
	}

}
