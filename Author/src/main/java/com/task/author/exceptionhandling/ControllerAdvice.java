package com.task.author.exceptionhandling;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class ControllerAdvice {

	@ExceptionHandler(AuthorNotFoundException.class) 
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<String> handleAuthorNotFound(AuthorNotFoundException authorNotFoundException) {
		ResponseEntity<String> res = new ResponseEntity<>(authorNotFoundException.getMessage(),HttpStatus.NOT_FOUND);
		return res;
	}
}
