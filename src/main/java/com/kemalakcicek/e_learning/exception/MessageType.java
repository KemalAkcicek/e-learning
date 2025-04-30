package com.kemalakcicek.e_learning.exception;

import lombok.Getter;

@Getter
public enum MessageType {

	NO_RECORD_EXİSTS("1001", "Kayıt bulunamadı"), GENERAL_EXCEPTİON("2002", "Genel bir hata oluştu"),
	TOKEN_IS_EXPİRED("3003", "Token süresi geçmiş"), NO_RECORD_INVALID("4004", "Kullanıcı adi veya password hatalı"),
	NO_REFRESH_TOKEN_INVALID("5005", "Refresh token geçerli değil"), PASSWORD_WRONG("8008", "Parola yanlış"),
	REFRESH_TOKEN_EXPIRED("6006", "Refresh Token süresi dolmuş"), NO_PASSWORD_MATCH("7007", "Passwordler ayni değil"),
	NO_USER_RECORD("8006", "User bulunamadı"), NO_CATEGORY_RECORD("8006", "Category kayıdı bulunamadı"),
	NO_USER_INSTRUCTOR("1234", "Course eklemek için sadece INSTRUCTOR olabilir diğerleri olamaz");

	private String code;

	private String message;

	private MessageType(String code, String message) {
		this.code = code;
		this.message = message;
	}

}
