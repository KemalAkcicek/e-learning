package com.kemalakcicek.e_learning.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RefreshTokenRequest {

	@NotEmpty(message = "refresh token cannot be empty ")
	private String refreshToken;

}
