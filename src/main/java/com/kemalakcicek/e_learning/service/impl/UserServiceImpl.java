package com.kemalakcicek.e_learning.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.kemalakcicek.e_learning.dto.AuthRequest;
import com.kemalakcicek.e_learning.dto.DtoCourseStudent;
import com.kemalakcicek.e_learning.dto.DtoUser;
import com.kemalakcicek.e_learning.dto.UserPasswordControl;
import com.kemalakcicek.e_learning.exception.BaseException;
import com.kemalakcicek.e_learning.exception.ErrorMessage;
import com.kemalakcicek.e_learning.exception.MessageType;
import com.kemalakcicek.e_learning.model.Course;
import com.kemalakcicek.e_learning.model.User;
import com.kemalakcicek.e_learning.repository.UserRepository;
import com.kemalakcicek.e_learning.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public DtoUser createDtoUser(User user) {

		DtoUser dtoUser = new DtoUser();

		BeanUtils.copyProperties(user, dtoUser);

		List<DtoCourseStudent> dtoCourses = new ArrayList<>();

		for (Course temp : user.getCourses()) {

			DtoCourseStudent dtoCourse = new DtoCourseStudent();

			BeanUtils.copyProperties(temp, dtoCourse);

			dtoCourse.setCourseCategory(temp.getCategories().getName());

			dtoCourses.add(dtoCourse);

		}

		dtoUser.setDtoListCourses(dtoCourses);

		return dtoUser;
	}

	@Override
	public DtoUser getUser(Long id) {

		Optional<User> optional = userRepository.findById(id);

		if (optional.isEmpty()) {

			throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXİSTS, id.toString()));
		}

		return createDtoUser(optional.get());
	}

	@Override
	public AuthRequest updatePassword(UserPasswordControl passwordControl) {

		if (!(passwordControl.getPassword().equals(passwordControl.getAgainPassword()))) {

			throw new BaseException(new ErrorMessage(MessageType.NO_PASSWORD_MATCH, passwordControl.getPassword()));
		}

		Optional<User> byUsername = userRepository.findByUsername(passwordControl.getUsername());

		if (byUsername.isEmpty()) {

			throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXİSTS, passwordControl.getUsername()));

		}

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

		String hashPassword = byUsername.get().getPassword();
		String rawPassword = passwordControl.getPassword();

		boolean matches = encoder.matches(rawPassword, hashPassword);

		if (!matches) {

			throw new BaseException(new ErrorMessage(MessageType.PASSWORD_WRONG, passwordControl.getUsername()));

		}

		User user = byUsername.get();
		user.setPassword(passwordEncoder.encode(passwordControl.getNewPassword()));

		userRepository.save(user);

		AuthRequest authRequest = new AuthRequest();
		authRequest.setUsername(user.getUsername());
		authRequest.setPassword(user.getPassword());

		return authRequest;
	}

}
