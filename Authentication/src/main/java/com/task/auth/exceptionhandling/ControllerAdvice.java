package com.task.auth.exceptionhandling;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.task.auth.errorhandling.ErrorMessage;

//-----------------------ANAGHA.S.R------------------------------------

@RestControllerAdvice
public class ControllerAdvice {
	
	@ExceptionHandler(UsernameNotFoundException.class) 
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ErrorMessage userNotFoundException(UsernameNotFoundException userNotFoundException) 
	{
		return new ErrorMessage(HttpStatus.NOT_FOUND,LocalDateTime.now(),userNotFoundException.getMessage());
	}	
}
