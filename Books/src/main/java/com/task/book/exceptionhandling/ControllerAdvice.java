package com.task.book.exceptionhandling;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//-----------------------ANAGHA.S.R------------------------------------
@RestControllerAdvice
public class ControllerAdvice {

	@ExceptionHandler(BookNotFoundException.class) 
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<String> handleBookNotFound(BookNotFoundException bookNotFoundException) {
		ResponseEntity<String> res = new ResponseEntity<>(bookNotFoundException.getMessage(),HttpStatus.NOT_FOUND);
		return res;
	}
	
	@ExceptionHandler(BookAlreadyReservedException.class) 
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<String> handleBookAlreadyReserved(BookAlreadyReservedException bookAlreadyReservedException) {
		ResponseEntity<String> res = new ResponseEntity<>(bookAlreadyReservedException.getMessage(),HttpStatus.NOT_ACCEPTABLE);
		return res;
	}

}
