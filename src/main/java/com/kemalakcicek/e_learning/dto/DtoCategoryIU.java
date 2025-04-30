package com.kemalakcicek.e_learning.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoCategoryIU extends DtoBase {

	@Size(min = 5, max = 40)
	@NotBlank(message = "name cannot be null")
	private String name;

	@Size(min = 20, max = 400)
	@NotBlank(message = "title cannot be null")
	private String description;

}
