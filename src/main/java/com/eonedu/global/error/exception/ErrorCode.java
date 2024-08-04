package com.eonedu.global.error.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
	// Common
	METHOD_ARGUMENT_INVALID(HttpStatus.BAD_REQUEST, "유효하지 않은 method 인자 입니다."),
	METHOD_NOT_SUPPORTED(HttpStatus.METHOD_NOT_ALLOWED, "지원하지 않는 HTTP method 입니다."),
	INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버에 문제가 발생했습니다. 관리자에게 문의해주세요."),

	// Reservation

	// User

	// Notice

	// Register

	// Branch

	// Image

	// Verification


	SAMPLE_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "샘플 에러입니다."),
	;

	private final HttpStatus httpStatus;
	private final String message;
}
