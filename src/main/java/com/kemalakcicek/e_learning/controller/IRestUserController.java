package com.kemalakcicek.e_learning.controller;

import com.kemalakcicek.e_learning.dto.AuthRequest;
import com.kemalakcicek.e_learning.dto.DtoUser;
import com.kemalakcicek.e_learning.dto.UserPasswordControl;

public interface IRestUserController {

	public RootEntity<DtoUser> getUser(Long id);

	public RootEntity<AuthRequest> updatePassword(UserPasswordControl passwordControl);
}
