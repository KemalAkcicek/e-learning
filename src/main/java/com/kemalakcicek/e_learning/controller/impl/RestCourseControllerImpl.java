package com.kemalakcicek.e_learning.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kemalakcicek.e_learning.controller.IRestCourseController;
import com.kemalakcicek.e_learning.controller.RootEntity;
import com.kemalakcicek.e_learning.dto.DtoCourse;
import com.kemalakcicek.e_learning.dto.DtoCourseIU;
import com.kemalakcicek.e_learning.dto.DtoUser;
import com.kemalakcicek.e_learning.service.ICourseService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "api/instructor/")
public class RestCourseControllerImpl extends RestBaseController implements IRestCourseController {

	@Autowired
	private ICourseService iCourseService;

	@Override
	@PostMapping(path = "/save")
	public RootEntity<DtoCourse> saveCourse(@Valid @RequestBody DtoCourseIU dtoCourseIU) {

		return ok(iCourseService.saveCourse(dtoCourseIU));
	}

	@Override
	@GetMapping(path = "/get/{id}")
	public RootEntity<DtoUser> getUserFromCourse(@PathVariable Long id) {

		return ok(iCourseService.getUserFromCourse(id));
	}

}
