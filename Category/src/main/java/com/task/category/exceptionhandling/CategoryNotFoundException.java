package com.task.category.exceptionhandling;

//-----------------------ANAGHA.S.R------------------------------------

public class CategoryNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public CategoryNotFoundException() {
		super();
		
	}

	public CategoryNotFoundException(String message) {
		super(message);
		
	}

}
