package com.kemalakcicek.e_learning.controller;

import com.kemalakcicek.e_learning.dto.DtoUser;

public interface IBossController {

	public RootEntity<DtoUser> updateRole(Long id);

}
