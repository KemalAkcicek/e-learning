package com.kemalakcicek.e_learning.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorMessage {

	private MessageType messageType;

	private String ofStatic;

	public String preparedMessage() {

		StringBuilder builder = new StringBuilder();

		builder.append(messageType.getMessage());

		if (ofStatic != null) {

			builder.append(" :" + ofStatic);
		}

		return builder.toString();
	}

}
