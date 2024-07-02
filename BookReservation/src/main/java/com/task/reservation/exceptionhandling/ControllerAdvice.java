package com.task.reservation.exceptionhandling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class ControllerAdvice {
	
	@ExceptionHandler(ReservationDataNotFoundException.class) 
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<String> handleReservationDataNotFoundException(ReservationDataNotFoundException handleReservationDataNotFoundException) {
		ResponseEntity<String> res = new ResponseEntity<>(handleReservationDataNotFoundException.getMessage(),HttpStatus.NOT_FOUND);
		return res;
	}
}
