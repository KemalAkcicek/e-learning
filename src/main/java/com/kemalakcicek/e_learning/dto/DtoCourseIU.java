package com.kemalakcicek.e_learning.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoCourseIU extends DtoBase {

	@Size(min = 5, max = 40)
	@NotBlank(message = "title cannot be null")
	private String title;

	@Size(min = 2, max = 400)
	@NotBlank(message = "description cannot be null")
	private String description;

	@Max(500)
	private BigDecimal price;

	private Long userId;

	private Long categoryId;

}
