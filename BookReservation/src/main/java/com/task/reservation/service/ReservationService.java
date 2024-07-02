package com.task.reservation.service;

import java.util.List;

import com.task.reservation.entity.IssuebookResponse;
import com.task.reservation.entity.Reservation;


public interface ReservationService {  
   
    void updateFines();
	Reservation reserveBook(int book_Id, String token);
	Reservation renewBook(int book_Id, String token);
	Reservation returnBook(int book_Id, String token);
	List<Reservation> viewReservationsByUsername(String username, String token);
	
//	---------------------------------------------------------------------------------------------------------------
	
    List<Reservation> viewReservations(String token);    
	IssuebookResponse issueBook(int revervationId, String token);
	List<Reservation> viewIssuedBooks(String token);    
	
}