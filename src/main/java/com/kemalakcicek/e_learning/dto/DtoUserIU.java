package com.kemalakcicek.e_learning.dto;

import com.kemalakcicek.e_learning.enums.RoleType;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoUserIU extends DtoBase {

	@NotEmpty(message = "firstName cannot be empty")
	private String firstName;

	@NotEmpty(message = "lastName cannot be empty")
	private String lastName;

	@Email(message = "username cannot be empty")
	private String username;

	@NotEmpty(message = "password cannot be empty")
	private String password;

	private RoleType roleType;

}
