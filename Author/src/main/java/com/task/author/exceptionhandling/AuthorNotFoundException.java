package com.task.author.exceptionhandling;

//-----------------------ANAGHA.S.R------------------------------------

public class AuthorNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public AuthorNotFoundException() {
		super();
	}

	public AuthorNotFoundException(String message) {
		super(message);
	}
	
	

}
