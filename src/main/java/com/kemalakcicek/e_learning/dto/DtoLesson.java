package com.kemalakcicek.e_learning.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoLesson extends DtoBase {

	private String title;

	private String description;

	private String videoUrl;

	private double duration;

	private int lessonOrder;

	private DtoCourse course;

}
