package com.kemalakcicek.e_learning.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kemalakcicek.e_learning.controller.IBossController;
import com.kemalakcicek.e_learning.controller.RootEntity;
import com.kemalakcicek.e_learning.dto.DtoUser;
import com.kemalakcicek.e_learning.service.IBossServiceImpl;

@RestController
@RequestMapping(path = "/api/boss/")
public class BossControllerImpl extends RestBaseController implements IBossController {

	@Autowired
	private IBossServiceImpl iBossServiceImpl;

	@Override
	@GetMapping(path = "/update-role/{id}")
	public RootEntity<DtoUser> updateRole(@PathVariable Long id) {

		return ok(iBossServiceImpl.updateRole(id));
	}

}
