package com.task.reservation.exceptionhandling;

//-----------------------ANAGHA.S.R------------------------------------

public class ReservationDataNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public ReservationDataNotFoundException() {
		super();
	
	}

	public ReservationDataNotFoundException(String message) {
		super(message);
		
	}

}
