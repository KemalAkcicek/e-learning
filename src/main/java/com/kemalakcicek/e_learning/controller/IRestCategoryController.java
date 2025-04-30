package com.kemalakcicek.e_learning.controller;

import com.kemalakcicek.e_learning.dto.DtoCategory;
import com.kemalakcicek.e_learning.dto.DtoCategoryIU;
import com.kemalakcicek.e_learning.dto.DtoUser;
import com.kemalakcicek.e_learning.utils.RestPageableEntity;
import com.kemalakcicek.e_learning.utils.RestPageableRequest;

public interface IRestCategoryController {

	public RootEntity<DtoCategory> updateCategory(DtoCategoryIU dtoCategoryIU, Long id);

	public RootEntity<DtoCategory> findCategory(Long id);

	public RootEntity<RestPageableEntity<DtoUser>> findAllUserPageable(RestPageableRequest pageableRequest);

}
