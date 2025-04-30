package com.kemalakcicek.e_learning.service.impl;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kemalakcicek.e_learning.dto.DtoCategory;
import com.kemalakcicek.e_learning.dto.DtoCourse;
import com.kemalakcicek.e_learning.dto.DtoCourseIU;
import com.kemalakcicek.e_learning.dto.DtoUser;
import com.kemalakcicek.e_learning.enums.RoleType;
import com.kemalakcicek.e_learning.exception.BaseException;
import com.kemalakcicek.e_learning.exception.ErrorMessage;
import com.kemalakcicek.e_learning.exception.MessageType;
import com.kemalakcicek.e_learning.model.Categories;
import com.kemalakcicek.e_learning.model.Course;
import com.kemalakcicek.e_learning.model.User;
import com.kemalakcicek.e_learning.repository.CategoryRepository;
import com.kemalakcicek.e_learning.repository.CourseRepository;
import com.kemalakcicek.e_learning.repository.UserRepository;
import com.kemalakcicek.e_learning.service.ICourseService;
import com.kemalakcicek.e_learning.service.IUserService;

@Service
public class CourseServiceImpl implements ICourseService {

	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private IUserService iUserService;

	private Course createCourse(DtoCourseIU dtoCourseIU) {

		Course course = new Course();

		BeanUtils.copyProperties(dtoCourseIU, course);

		Optional<User> optional = userRepository.findById(dtoCourseIU.getUserId());

		if (optional.isEmpty()) {

			throw new BaseException(new ErrorMessage(MessageType.NO_USER_RECORD, dtoCourseIU.getUserId().toString()));
		}

		if (!optional.get().getRoleType().equals(RoleType.INSTRUCTOR)) {

			throw new BaseException(
					new ErrorMessage(MessageType.NO_USER_INSTRUCTOR, dtoCourseIU.getUserId().toString()));

		}

		course.setUser(optional.get());

		Optional<Categories> optional2 = categoryRepository.findById(dtoCourseIU.getCategoryId());

		if (optional2.isEmpty()) {

			throw new BaseException(
					new ErrorMessage(MessageType.NO_CATEGORY_RECORD, dtoCourseIU.getCategoryId().toString()));
		}

		course.setCategories(optional2.get());

		course.setCreateDate(new Date());
		course.setUpdateDate(new Date());

		return course;
	}

	@Override
	public DtoCourse saveCourse(DtoCourseIU dtoCourseIU) {

		Course course = createCourse(dtoCourseIU);

		courseRepository.save(course);

		DtoCourse dtoCourse = new DtoCourse();

		DtoUser dtoUser = new DtoUser();

		DtoCategory dtoCategory = new DtoCategory();

		BeanUtils.copyProperties(course, dtoCourse);
		BeanUtils.copyProperties(course.getUser(), dtoUser);
		BeanUtils.copyProperties(course.getCategories(), dtoCategory);

		dtoCourse.setDtoUser(dtoUser);
		dtoCourse.setDtoCategory(dtoCategory);

		return dtoCourse;
	}

	@Override
	public DtoUser getUserFromCourse(Long id) {

		Optional<Course> optional = courseRepository.findById(id);

		if (optional.isEmpty()) {

			throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXİSTS, id.toString()));
		}

		return iUserService.getUser(id);
	}

}
