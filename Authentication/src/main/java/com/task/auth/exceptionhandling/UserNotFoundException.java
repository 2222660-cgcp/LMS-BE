package com.task.auth.exceptionhandling;

//-----------------------ANAGHA.S.R------------------------------------

public class UserNotFoundException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	public UserNotFoundException() 
	{
		super();	
	}

	public UserNotFoundException(final String message) 
	{	
		super(message);
	}
}
