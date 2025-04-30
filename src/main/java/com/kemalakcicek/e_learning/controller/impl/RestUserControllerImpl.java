package com.kemalakcicek.e_learning.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kemalakcicek.e_learning.controller.IRestUserController;
import com.kemalakcicek.e_learning.controller.RootEntity;
import com.kemalakcicek.e_learning.dto.AuthRequest;
import com.kemalakcicek.e_learning.dto.DtoUser;
import com.kemalakcicek.e_learning.dto.UserPasswordControl;
import com.kemalakcicek.e_learning.service.IUserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/api/student")
public class RestUserControllerImpl extends RestBaseController implements IRestUserController {

	@Autowired
	private IUserService iUserService;

	@Override
	@GetMapping("/{id}")
	public RootEntity<DtoUser> getUser(@PathVariable(name = "id") Long id) {

		return ok(iUserService.getUser(id));
	}

	@Override
	@PutMapping(path = "/update-password")
	public RootEntity<AuthRequest> updatePassword(@Valid @RequestBody UserPasswordControl passwordControl) {

		return ok(iUserService.updatePassword(passwordControl));
	}

}
