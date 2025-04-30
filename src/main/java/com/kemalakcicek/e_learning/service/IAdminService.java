package com.kemalakcicek.e_learning.service;

import java.util.List;

import com.kemalakcicek.e_learning.dto.DtoUser;

public interface IAdminService {

	public List<DtoUser> getUserList();

	public boolean deleteUser(Long id);

}
