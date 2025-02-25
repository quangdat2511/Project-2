package com.javaweb.controlleradvice;

import java.util.Arrays;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.javaweb.dto.response.ErrorDetailResponse;
import com.javaweb.myexception.ValidateDataException;

@RestControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler{
	@ExceptionHandler(ValidateDataException.class)
	public ResponseEntity<Object> handleValidateDataBuildingException(ValidateDataException ex){
		ErrorDetailResponse errorDetailResponse = new ErrorDetailResponse();
		errorDetailResponse.setError(ex.getMessage());
		errorDetailResponse.setDetail(Arrays.asList("so nguyen thi lam sao chi het cho 0 duoc"));
		return new ResponseEntity<Object>(errorDetailResponse, HttpStatus.BAD_REQUEST );
	}
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleException(Exception ex){
		ErrorDetailResponse errorDetailResponse = new ErrorDetailResponse();
		errorDetailResponse.setError(ex.getMessage());
		errorDetailResponse.setDetail(Arrays.asList("so nguyen thi lam sao chi het cho 0 duoc"));
		return new ResponseEntity<Object>(errorDetailResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
