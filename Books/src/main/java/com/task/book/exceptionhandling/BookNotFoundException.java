package com.task.book.exceptionhandling;

//-----------------------ANAGHA.S.R------------------------------------
public class BookNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public BookNotFoundException() {
		super();
		
	}

	public BookNotFoundException(String message) {
		super(message);
		
	}
	
	

}
