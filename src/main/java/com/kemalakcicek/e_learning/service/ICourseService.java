package com.kemalakcicek.e_learning.service;

import com.kemalakcicek.e_learning.dto.DtoCourse;
import com.kemalakcicek.e_learning.dto.DtoCourseIU;
import com.kemalakcicek.e_learning.dto.DtoUser;

public interface ICourseService {

	public DtoCourse saveCourse(DtoCourseIU dtoCourseIU);

	public DtoUser getUserFromCourse(Long id);

}
