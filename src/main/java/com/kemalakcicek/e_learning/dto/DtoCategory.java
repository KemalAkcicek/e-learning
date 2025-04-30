package com.kemalakcicek.e_learning.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoCategory extends DtoBase {

	private String name;

	private String description;

	private List<DtoCourse> listDtoCourses;

}
