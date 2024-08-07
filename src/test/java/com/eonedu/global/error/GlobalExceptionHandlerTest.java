package com.eonedu.global.error;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.request.WebRequest;

import com.eonedu.global.common.response.GlobalResponse;
import com.eonedu.global.error.exception.CustomException;
import com.eonedu.global.error.exception.ErrorCode;

@DisplayName("GlobalExceptionHandler 테스트")
class GlobalExceptionHandlerTest {

	@InjectMocks
	private GlobalExceptionHandler globalExceptionHandler;

	@Mock
	private WebRequest webRequest;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	@DisplayName("MethodArgumentNotValid 예외 처리 테스트")
	void handleMethodArgumentNotValid() {
		// given
		MethodArgumentNotValidException ex = mock(MethodArgumentNotValidException.class);
		HttpHeaders headers = new HttpHeaders();

		// when
		ResponseEntity<Object> response = globalExceptionHandler.handleMethodArgumentNotValid(ex, headers, HttpStatus.BAD_REQUEST, webRequest);

		// then
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		assertInstanceOf(GlobalResponse.class, response.getBody());
		GlobalResponse globalResponse = (GlobalResponse) response.getBody();
		assertInstanceOf(ErrorResponse.class, globalResponse.data());
		ErrorResponse errorResponse = (ErrorResponse) globalResponse.data();
		assertEquals(ex.getClass().getSimpleName(), errorResponse.className());
		assertEquals(ErrorCode.METHOD_ARGUMENT_INVALID.getMessage(), errorResponse.message());
	}

	@Test
	@DisplayName("HttpRequestMethodNotSupported 예외 처리 테스트")
	void handleHttpRequestMethodNotSupported() {
		// given
		HttpRequestMethodNotSupportedException ex = mock(HttpRequestMethodNotSupportedException.class);
		HttpHeaders headers = new HttpHeaders();

		// when
		ResponseEntity<Object> response = globalExceptionHandler.handleHttpRequestMethodNotSupported(ex, headers, HttpStatus.METHOD_NOT_ALLOWED, webRequest);

		// then
		assertEquals(HttpStatus.METHOD_NOT_ALLOWED, response.getStatusCode());
		assertInstanceOf(GlobalResponse.class, response.getBody());
		GlobalResponse globalResponse = (GlobalResponse) response.getBody();
		assertInstanceOf(ErrorResponse.class, globalResponse.data());
		ErrorResponse errorResponse = (ErrorResponse) globalResponse.data();
		assertEquals(ex.getClass().getSimpleName(), errorResponse.className());
		assertEquals(ErrorCode.METHOD_NOT_SUPPORTED.getMessage(), errorResponse.message());
	}

	@Test
	@DisplayName("ExceptionInternal 예외 처리 테스트")
	void handleExceptionInternal() {
		// given
		Exception ex = new Exception("Test exception");
		HttpHeaders headers = new HttpHeaders();

		// when
		ResponseEntity<Object> response = globalExceptionHandler.handleExceptionInternal(ex, null, headers, HttpStatus.INTERNAL_SERVER_ERROR, webRequest);

		// then
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
		assertInstanceOf(GlobalResponse.class, response.getBody());
		GlobalResponse globalResponse = (GlobalResponse) response.getBody();
		assertInstanceOf(ErrorResponse.class, globalResponse.data());
		ErrorResponse errorResponse = (ErrorResponse) globalResponse.data();
		assertEquals(ex.getClass().getSimpleName(), errorResponse.className());
		assertEquals(ErrorCode.INTERNAL_SERVER_ERROR.getMessage(), errorResponse.message());
	}

	@Test
	@DisplayName("CustomException 예외 처리 테스트")
	void handleCustomException() {
		// given
		CustomException ex = new CustomException(ErrorCode.SAMPLE_ERROR);

		// when
		ResponseEntity<Object> response = globalExceptionHandler.handleCustomException(ex);

		// then
		assertNotNull(response);
		assertEquals(ex.getErrorCode().getHttpStatus(), response.getStatusCode());
		assertInstanceOf(GlobalResponse.class, response.getBody());
		GlobalResponse globalResponse = (GlobalResponse) response.getBody();
		assertInstanceOf(ErrorResponse.class, globalResponse.data());
		ErrorResponse errorResponse = (ErrorResponse) globalResponse.data();
		assertEquals(ex.getClass().getSimpleName(), errorResponse.className());
		assertEquals(ErrorCode.SAMPLE_ERROR.getMessage(), errorResponse.message());
	}

	@Test
	@DisplayName("일반 Exception 예외 처리 테스트")
	void handleException() {
		// given
		Exception ex = new RuntimeException("Test exception");

		// when
		ResponseEntity<Object> response = globalExceptionHandler.handleException(ex);

		// then
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
		assertInstanceOf(GlobalResponse.class, response.getBody());
		GlobalResponse globalResponse = (GlobalResponse) response.getBody();
		assertInstanceOf(ErrorResponse.class, globalResponse.data());
		ErrorResponse errorResponse = (ErrorResponse) globalResponse.data();
		assertEquals(ex.getClass().getSimpleName(), errorResponse.className());
		assertEquals(ErrorCode.INTERNAL_SERVER_ERROR.getMessage(), errorResponse.message());
	}
}