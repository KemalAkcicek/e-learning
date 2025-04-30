package com.kemalakcicek.e_learning.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoLessonIU  extends DtoBase{
	
	@Size(min = 5, max = 40)
	@NotBlank(message = "title cannot be null")
	private String title;

	@Size(min = 20, max = 400)
	@NotBlank(message = "description cannot be null")
	private String description;

	@Size(min = 30, max = 40)
	@NotBlank(message = "video-url cannot be null")
	private String videoUrl;

	@Max(150)
	private double duration;

	@Max(100)
	private int lessonOrder;
	

}
