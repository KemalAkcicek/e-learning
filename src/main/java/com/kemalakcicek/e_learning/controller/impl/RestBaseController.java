package com.kemalakcicek.e_learning.controller.impl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.kemalakcicek.e_learning.controller.RootEntity;
import com.kemalakcicek.e_learning.utils.PagerUtils;
import com.kemalakcicek.e_learning.utils.RestPageableEntity;
import com.kemalakcicek.e_learning.utils.RestPageableRequest;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestBaseController {

	public <T> RootEntity<T> ok(T payload) {

		return RootEntity.ok(payload);
	}

	public <T> RootEntity<T> error(String message) {
		return RootEntity.error(message);
	}

	public Pageable toPageable(RestPageableRequest pageableRequest) {

		return PagerUtils.toPageable(pageableRequest);
	}

	public <T> RestPageableEntity<T> toPageableResponse(Page<?> page, List<T> content) {

		return PagerUtils.toPageableEntity(page, content);

	}

}
