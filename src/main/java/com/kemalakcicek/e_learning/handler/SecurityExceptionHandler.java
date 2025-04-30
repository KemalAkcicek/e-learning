package com.kemalakcicek.e_learning.handler;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.util.Date;

import org.springframework.http.HttpStatus;

import com.kemalakcicek.e_learning.exception.BaseException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SecurityExceptionHandler {

	public static <E> ApiError<E> createError(E message, HttpServletRequest httpServletRequest) {

		ApiError<E> apiError = new ApiError<>();

		apiError.setStatus(HttpStatus.BAD_REQUEST.value());

		Exception<E> exception = new Exception<>();
		exception.setCreateTime(new Date());
		exception.setHost(getHostName());
		exception.setMessage(message);
		exception.setPath(httpServletRequest.getRequestURI().toString());

		apiError.setException(exception);

		return apiError;
	}

	private static String getHostName() {

		try {
			return Inet4Address.getLocalHost().getHostName();
		} catch (UnknownHostException e) {
			System.out.println("Hata oluştu" + e.getMessage());

		}
		return null;

	}

	// Biz burada şu olayı yapıyoruz normalde exception handler tarafında
	// yakaldığımız hatalar sadece mvc katmanlarındaki hataları yakalıyor filter
	// katmanındaki hatayı yakalamıyor bizde burada kendimiz filter katmanındaki
	// hatayı yakalyıp kendimi hata fırlatıyoruz
	public static void handleException(HttpServletResponse response, BaseException baseException,
			HttpServletRequest request) throws IOException {

		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		ApiError<String> error = createError(baseException.getMessage(), request);

		String jsonResponse = error.toString();

		response.getWriter().write(jsonResponse);

	}

}
