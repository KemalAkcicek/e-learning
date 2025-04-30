package com.kemalakcicek.e_learning.controller;

import com.kemalakcicek.e_learning.dto.DtoCourse;
import com.kemalakcicek.e_learning.dto.DtoCourseIU;
import com.kemalakcicek.e_learning.dto.DtoUser;

public interface IRestCourseController {

	public RootEntity<DtoCourse> saveCourse(DtoCourseIU dtoCourseIU);

	public RootEntity<DtoUser> getUserFromCourse(Long id);

}
