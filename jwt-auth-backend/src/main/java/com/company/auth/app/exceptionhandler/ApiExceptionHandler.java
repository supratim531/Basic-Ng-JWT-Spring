package com.company.auth.app.exceptionhandler;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {

	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MissingServletRequestParameterException.class)
	public Map<String, Object> handleMissingRequestParam(MissingServletRequestParameterException exception) {
		Map<String, Object> errorMap = new LinkedHashMap<>();

		errorMap.put("timeStamp", LocalDateTime.now());
		errorMap.put("status", HttpStatus.BAD_REQUEST);
		errorMap.put("statusCode", HttpStatus.BAD_REQUEST.value());
		errorMap.put("message", exception.getMessage());

		return errorMap;
	}

}
