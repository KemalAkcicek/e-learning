package com.kemalakcicek.e_learning.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthRequest {

	@Email(message = "username cannot be empty")
	private String username;

	@NotEmpty(message = "password cannot be empty")
	private String password;

}
