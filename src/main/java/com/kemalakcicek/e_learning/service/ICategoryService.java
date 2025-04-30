package com.kemalakcicek.e_learning.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.kemalakcicek.e_learning.dto.DtoCategory;
import com.kemalakcicek.e_learning.dto.DtoCategoryIU;
import com.kemalakcicek.e_learning.dto.DtoUser;
import com.kemalakcicek.e_learning.model.User;

public interface ICategoryService {

	public DtoCategory updateCategory(DtoCategoryIU dtoCategoryIU, Long id);

	public DtoCategory findCategory(Long id);

	public Page<User> findAllUserPageable(Pageable page);
	
	public List<DtoUser> toDtoList(List<User> userList);

}
