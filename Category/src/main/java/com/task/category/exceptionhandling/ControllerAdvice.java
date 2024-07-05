package com.task.category.exceptionhandling;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//-----------------------ANAGHA.S.R------------------------------------

@RestControllerAdvice
public class ControllerAdvice {

	@ExceptionHandler(CategoryNotFoundException.class) 
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<String> handleCategoryNotFound(CategoryNotFoundException categoryNotFoundException) {
		ResponseEntity<String> res = new ResponseEntity<>(categoryNotFoundException.getMessage(),HttpStatus.NOT_FOUND);
		return res;
	}

}
