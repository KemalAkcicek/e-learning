package com.kemalakcicek.e_learning.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kemalakcicek.e_learning.controller.IRestCategoryController;
import com.kemalakcicek.e_learning.controller.RootEntity;
import com.kemalakcicek.e_learning.dto.DtoCategory;
import com.kemalakcicek.e_learning.dto.DtoCategoryIU;
import com.kemalakcicek.e_learning.dto.DtoUser;
import com.kemalakcicek.e_learning.model.User;
import com.kemalakcicek.e_learning.service.ICategoryService;
import com.kemalakcicek.e_learning.utils.RestPageableEntity;
import com.kemalakcicek.e_learning.utils.RestPageableRequest;

import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/api/admin")
public class CategoryControllerImpl extends RestBaseController implements IRestCategoryController {

	@Autowired
	private ICategoryService iCategoryService;

	@Override
	@PostMapping(path = "/update/{id}")
	public RootEntity<DtoCategory> updateCategory(@Valid @RequestBody DtoCategoryIU dtoCategoryIU,
			@PathVariable Long id) {

		return ok(iCategoryService.updateCategory(dtoCategoryIU, id));
	}

	@Override
	@GetMapping(path = "/get/{id}")
	public RootEntity<DtoCategory> findCategory(@PathVariable Long id) {

		return ok(iCategoryService.findCategory(id));
	}

	// Modelatribute
	// Metotun parametresini şişirmemiş olduk

	@GetMapping(path = "/list/pageable")
	@Override
	public RootEntity<RestPageableEntity<DtoUser>>  findAllUserPageable(RestPageableRequest pageableRequest) {

		Page<User> page = iCategoryService.findAllUserPageable(toPageable(pageableRequest));

		RestPageableEntity<DtoUser> restPageableEntity = toPageableResponse(page, iCategoryService.toDtoList(page.getContent()));

		return ok(restPageableEntity);
	}

}
