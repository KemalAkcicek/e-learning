package com.kemalakcicek.e_learning.dto;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoCourseStudent extends DtoBase {

	private String title;

	private String description;

	private BigDecimal price;

	private String courseCategory;

}
