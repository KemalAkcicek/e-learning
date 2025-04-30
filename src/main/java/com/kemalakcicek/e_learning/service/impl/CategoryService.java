package com.kemalakcicek.e_learning.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.kemalakcicek.e_learning.dto.DtoCategory;
import com.kemalakcicek.e_learning.dto.DtoCategoryIU;
import com.kemalakcicek.e_learning.dto.DtoCourseStudent;
import com.kemalakcicek.e_learning.dto.DtoUser;
import com.kemalakcicek.e_learning.exception.BaseException;
import com.kemalakcicek.e_learning.exception.ErrorMessage;
import com.kemalakcicek.e_learning.exception.MessageType;
import com.kemalakcicek.e_learning.model.Categories;
import com.kemalakcicek.e_learning.model.Course;
import com.kemalakcicek.e_learning.model.User;
import com.kemalakcicek.e_learning.repository.CategoryRepository;
import com.kemalakcicek.e_learning.repository.UserRepository;
import com.kemalakcicek.e_learning.service.ICategoryService;

@Service
public class CategoryService implements ICategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private UserRepository userRepository;

	private DtoCategory categoryConvertFromCategories(Categories categories) {

		DtoCategory dtoCategory = new DtoCategory();

		BeanUtils.copyProperties(categories, dtoCategory);

		dtoCategory.setCreateDate(categories.getCreateDate());

		return dtoCategory;
	}

	@Override
	public DtoCategory updateCategory(DtoCategoryIU dtoCategoryIU, Long id) {

		Optional<Categories> optional = categoryRepository.findById(id);

		if (optional.isEmpty()) {

			throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXİSTS, id.toString()));
		}

		Categories categories = optional.get();

		Date createDate = categories.getCreateDate();

		BeanUtils.copyProperties(dtoCategoryIU, categories);

		categories.setCreateDate(createDate);

		categories.setUpdateDate(new Date());

		categoryRepository.save(categories);

		return categoryConvertFromCategories(categories);
	}

	@Override
	public DtoCategory findCategory(Long id) {

		Optional<Categories> optional = categoryRepository.findById(id);

		if (optional.isEmpty()) {

			throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXİSTS, id.toString()));
		}

		return categoryConvertFromCategories(optional.get());

	}

	@Override
	public Page<User> findAllUserPageable(Pageable page) {

		return userRepository.findAllUserPageable(page);
	}

	@Override
	public List<DtoUser> toDtoList(List<User> userList) {

		List<DtoUser> listDtoUsers = new ArrayList<>();

		for (User temp : userList) {

			DtoUser dtoUser = new DtoUser();

			BeanUtils.copyProperties(temp, dtoUser);

			dtoUser.setRoleType(temp.getRoleType());

			List<DtoCourseStudent> listDtoCourseStudents = new ArrayList<>();

			for (Course tempCourse : temp.getCourses()) {

				DtoCourseStudent courseStudent = new DtoCourseStudent();

				BeanUtils.copyProperties(tempCourse, courseStudent);

				listDtoCourseStudents.add(courseStudent);

			}

			dtoUser.setDtoListCourses(listDtoCourseStudents);

			listDtoUsers.add(dtoUser);

		}

		return listDtoUsers;
	}

}
