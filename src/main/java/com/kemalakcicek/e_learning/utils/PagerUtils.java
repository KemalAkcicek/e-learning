package com.kemalakcicek.e_learning.utils;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import lombok.experimental.UtilityClass;

@UtilityClass
public class PagerUtils {

	public boolean isNullOrEmpty(String value) {

		return value == null || value.trim().length() == 0;

	}

	public Pageable toPageable(RestPageableRequest pageableRequest) {

		// Burada ilk olarak columname değeri varmı onu kontrol ettik varsa columname
		// göre sıralama yapıyoruz

		if (!isNullOrEmpty(pageableRequest.getColumnName())) {

			Sort sortBy = pageableRequest.isAsc() ? Sort.by(Direction.ASC, pageableRequest.getColumnName())
					: Sort.by(Direction.DESC, pageableRequest.getColumnName());

			return PageRequest.of(pageableRequest.getPageNumber(), pageableRequest.getPageSize(), sortBy);

		}

		return PageRequest.of(pageableRequest.getPageNumber(), pageableRequest.getPageSize());

	}

	public <T> RestPageableEntity<T> toPageableEntity(Page<?> page, List<T> content) {

		RestPageableEntity<T> restPageableEntity = new RestPageableEntity<T>();

		restPageableEntity.setContent(content);
		restPageableEntity.setPageNumber(page.getPageable().getPageNumber());
		restPageableEntity.setPageSize(page.getPageable().getPageSize());
		restPageableEntity.setTotalElements(page.getTotalElements());

		return restPageableEntity;
	}

}
