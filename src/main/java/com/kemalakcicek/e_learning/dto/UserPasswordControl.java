package com.kemalakcicek.e_learning.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserPasswordControl {

	@Email(message = "username cannot be empty")
	private String username;

	@NotEmpty(message = "password cannot be empty")
	private String password;

	@NotEmpty(message = "password  again cannot be empty")
	private String againPassword;

	@NotEmpty(message = "new password   cannot be empty")
	private String newPassword;

}
