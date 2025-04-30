package com.kemalakcicek.e_learning.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kemalakcicek.e_learning.dto.DtoCourseStudent;
import com.kemalakcicek.e_learning.dto.DtoUser;
import com.kemalakcicek.e_learning.exception.BaseException;
import com.kemalakcicek.e_learning.exception.ErrorMessage;
import com.kemalakcicek.e_learning.exception.MessageType;
import com.kemalakcicek.e_learning.model.Course;
import com.kemalakcicek.e_learning.model.User;
import com.kemalakcicek.e_learning.repository.UserRepository;
import com.kemalakcicek.e_learning.service.IAdminService;

@Service
public class AdminServiceImpl implements IAdminService {

	@Autowired
	private UserRepository userRepository;

	public List<DtoUser> createUserList(List<User> userList) {

		List<DtoUser> listdtoUsers = new ArrayList<>();

		for (User temp : userList) {

			DtoUser dtoUser = new DtoUser();

			List<DtoCourseStudent> dtoCourseStudents = new ArrayList<>();

			BeanUtils.copyProperties(temp, dtoUser);

			listdtoUsers.add(dtoUser);

			for (Course tempCourse : temp.getCourses()) {

				DtoCourseStudent dtoCourseStudent = new DtoCourseStudent();

				BeanUtils.copyProperties(tempCourse, dtoCourseStudent);

				dtoCourseStudents.add(dtoCourseStudent);

				dtoCourseStudent.setCourseCategory(tempCourse.getCategories().getName());
			}

			dtoUser.setDtoListCourses(dtoCourseStudents);

		}

		return listdtoUsers;
	}

	@Override
	public List<DtoUser> getUserList() {

		return createUserList(userRepository.findAll());
	}

	@Override
	public boolean deleteUser(Long id) {

		Optional<User> optional = userRepository.findById(id);

		if (optional.isEmpty()) {

			throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXİSTS, id.toString()));

		}

		userRepository.delete(optional.get());

		return true;
	}

}
