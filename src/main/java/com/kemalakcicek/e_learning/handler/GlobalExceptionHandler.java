package com.kemalakcicek.e_learning.handler;

import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.kemalakcicek.e_learning.enums.RoleType;
import com.kemalakcicek.e_learning.exception.BaseException;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(value = BaseException.class)
	public ResponseEntity<ApiError<String>> handlerBaseException(BaseException ex, WebRequest request) {

		return ResponseEntity.badRequest().body(createApiError(ex.getMessage(), request));
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<ApiError<Map<String, List<String>>>> handlerValidEnum(HttpMessageNotReadableException ex,
			WebRequest webRequest) {

		return ResponseEntity.badRequest().body(createApiError(getRoleTypes(), webRequest));
	}

	@ExceptionHandler(value = { MethodArgumentNotValidException.class })
	public ResponseEntity<ApiError<Map<String, List<String>>>> handlerMethotArgument(MethodArgumentNotValidException ex,
			WebRequest webRequest) {

		Map<String, List<String>> map = new HashMap<>();

		for (ObjectError objError : ex.getBindingResult().getAllErrors()) {

			String fieldName = ((FieldError) objError).getField();

			if (map.containsKey(fieldName)) {

				map.put(fieldName, addNewValue(map.get(fieldName), objError.getDefaultMessage()));

			} else {

				map.put(fieldName, addNewValue(new ArrayList<>(), objError.getDefaultMessage()));

			}

		}

		return ResponseEntity.badRequest().body(createApiError(map, webRequest));

	}

	public String getHostName() {

		try {
			return Inet4Address.getLocalHost().getHostName();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return "";
	}

	public <E> ApiError<E> createApiError(E message, WebRequest webRequest) {

		ApiError<E> apiError = new ApiError<>();

		apiError.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());

		Exception<E> exception = new Exception<>();
		exception.setCreateTime(new Date());
		exception.setMessage(message);
		exception.setPath(webRequest.getDescription(false).substring(4));
		exception.setHost(getHostName());

		apiError.setException(exception);

		return apiError;
	}

	public Map<String, List<String>> getRoleTypes() {

		RoleType[] values = RoleType.values();

		List<String> roleTypes = new ArrayList<>();

		Map<String, List<String>> map = new HashMap<>();

		String key = "Verebileceğiniz değerler";

		for (RoleType temp : values) {

			roleTypes.add(temp.name());

		}
		map.put(key, roleTypes);

		return map;

	}

	public List<String> addNewValue(List<String> list, String newValue) {

		list.add(newValue);

		return list;

	}

}
