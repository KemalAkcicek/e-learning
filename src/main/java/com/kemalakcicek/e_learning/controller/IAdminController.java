package com.kemalakcicek.e_learning.controller;

import java.util.List;

import com.kemalakcicek.e_learning.dto.DtoUser;

public interface IAdminController {

	public RootEntity<List<DtoUser>> getUserList();

	public boolean deleteUser(Long id);

}
