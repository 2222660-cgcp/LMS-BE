package com.task.reservation.service;

import java.util.List;

import com.task.reservation.entity.IssuebookResponse;
import com.task.reservation.entity.Reservation;

//-----------------------IBRAHIM BADSHAH--------------------------------------

public interface ReservationService {  
   
    void updateFines();
	Reservation reserveBook(int book_Id, String token);
	Reservation renewBook(int book_Id, String token);
	Reservation returnBook(int book_Id, String token);
	List<Reservation> viewReservationsByUsername(String username, String token);
	
//	-----------------------ANAGHA.S.R----------------------------------------------------------------------------------------
	
    List<Reservation> viewReservations(String token);    
	IssuebookResponse issueBook(int revervationId, String token);
	List<Reservation> viewIssuedBooks(String token);    
	
}