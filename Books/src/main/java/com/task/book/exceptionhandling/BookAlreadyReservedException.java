package com.task.book.exceptionhandling;

//-----------------------ANAGHA.S.R------------------------------------

public class BookAlreadyReservedException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public BookAlreadyReservedException() {
		super();
	}

	public BookAlreadyReservedException(String message) {
		super(message);
	}
	
	

}
