package com.kemalakcicek.e_learning.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kemalakcicek.e_learning.controller.IAdminController;
import com.kemalakcicek.e_learning.controller.RootEntity;
import com.kemalakcicek.e_learning.dto.DtoUser;
import com.kemalakcicek.e_learning.service.IAdminService;

@RestController
@RequestMapping(path = "api/admin")
public class AdminControllerImpl extends RestBaseController implements IAdminController {

	@Autowired
	private IAdminService iAdminService;

	@Override
	@GetMapping(path = "/all")
	public RootEntity<List<DtoUser>> getUserList() {

		return ok(iAdminService.getUserList());
	}

	@Override
	@DeleteMapping(path = "/delete/{id}")
	public boolean deleteUser(@PathVariable Long id) {

		return iAdminService.deleteUser(id);
	}

}
