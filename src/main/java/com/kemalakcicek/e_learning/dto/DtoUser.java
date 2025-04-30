package com.kemalakcicek.e_learning.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.kemalakcicek.e_learning.enums.RoleType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class DtoUser extends DtoBase {

	private String firstName;

	private String lastName;

	private String username;

	private RoleType roleType;

	private List<DtoCourseStudent> dtoListCourses;

}
