package com.kemalakcicek.e_learning.service;

import com.kemalakcicek.e_learning.dto.AuthRequest;
import com.kemalakcicek.e_learning.dto.DtoUser;
import com.kemalakcicek.e_learning.dto.UserPasswordControl;

public interface IUserService {

	public DtoUser getUser(Long id);

	public AuthRequest updatePassword(UserPasswordControl passwordControl);

}
