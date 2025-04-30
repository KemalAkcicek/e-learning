package com.kemalakcicek.e_learning.controller;

import java.util.List;

import com.kemalakcicek.e_learning.dto.DtoLesson;
import com.kemalakcicek.e_learning.utils.RestPageableEntity;
import com.kemalakcicek.e_learning.utils.RestPageableRequest;

public interface IRestLessonController {

	public RootEntity<List<DtoLesson>> findAllLesson();

	public RootEntity<RestPageableEntity<DtoLesson>> findAllPageable(RestPageableRequest restPageableRequest);
}
