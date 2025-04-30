package com.kemalakcicek.e_learning.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.kemalakcicek.e_learning.enums.Roles;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DtoUserToken {

	private String username;

	private String password;

	private Long id;

	private Date createDate;

	private Roles roles;

}
