package com.app.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.app.exception.StudentNotFoundException;

@RestControllerAdvice
public class MyExceptionHandler {

	@ExceptionHandler(StudentNotFoundException.class)
	public ResponseEntity<String> showMyException(StudentNotFoundException snfe){
		return new ResponseEntity<String>(snfe.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
