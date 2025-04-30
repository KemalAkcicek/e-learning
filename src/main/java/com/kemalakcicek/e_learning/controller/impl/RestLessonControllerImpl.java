package com.kemalakcicek.e_learning.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kemalakcicek.e_learning.controller.IRestLessonController;
import com.kemalakcicek.e_learning.controller.RootEntity;
import com.kemalakcicek.e_learning.dto.DtoLesson;
import com.kemalakcicek.e_learning.model.Lesson;
import com.kemalakcicek.e_learning.service.ILessonService;
import com.kemalakcicek.e_learning.utils.RestPageableEntity;
import com.kemalakcicek.e_learning.utils.RestPageableRequest;

@RestController
@RequestMapping(path = "/api/instructor")
public class RestLessonControllerImpl extends RestBaseController implements IRestLessonController {

	@Autowired
	private ILessonService iLessonService;

	@Override
	@GetMapping(path = "/get-all")
	public RootEntity<List<DtoLesson>> findAllLesson() {

		return ok(iLessonService.findAllLessons());
	}

	@Override
	@GetMapping(path = "/list-all")
	public RootEntity<RestPageableEntity<DtoLesson>> findAllPageable(RestPageableRequest restPageableRequest) {
		
		Page<Lesson> page = iLessonService.findAllLesson(toPageable(restPageableRequest));
		
		RestPageableEntity<DtoLesson> pageableResponse = toPageableResponse(page, iLessonService.toDtoList(page.getContent()));
		
		return ok(pageableResponse);
	}

}
