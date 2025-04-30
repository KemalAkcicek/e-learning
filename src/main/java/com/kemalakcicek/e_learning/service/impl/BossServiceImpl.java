package com.kemalakcicek.e_learning.service.impl;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kemalakcicek.e_learning.dto.DtoUser;
import com.kemalakcicek.e_learning.enums.RoleType;
import com.kemalakcicek.e_learning.exception.BaseException;
import com.kemalakcicek.e_learning.exception.ErrorMessage;
import com.kemalakcicek.e_learning.exception.MessageType;
import com.kemalakcicek.e_learning.model.User;
import com.kemalakcicek.e_learning.repository.UserRepository;
import com.kemalakcicek.e_learning.service.IBossServiceImpl;

@Service
public class BossServiceImpl implements IBossServiceImpl {

	@Autowired
	private UserRepository userRepository;

	@Override
	public DtoUser updateRole(Long id) {

		Optional<User> optional = userRepository.findById(id);

		if (optional.isEmpty()) {

			throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXİSTS, id.toString()));
		}

		User user = optional.get();

		user.setRoleType(RoleType.ADMIN);

		userRepository.save(user);

		DtoUser dtoUser = new DtoUser();

		BeanUtils.copyProperties(user, dtoUser);

		return dtoUser;
	}

}
