package com.task.reservation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.task.reservation.entity.IssuebookResponse;
import com.task.reservation.entity.Reservation;
import com.task.reservation.service.ReservationService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/books")
public class ReservationController {
 
    @Autowired
    private ReservationService reservationService;
   
    @PostMapping("/reserve/{book_Id}")
    public Reservation reserveBook(@PathVariable int book_Id,  @RequestHeader("Authorization") final String token) {
        return reservationService.reserveBook(book_Id, token);         
    }
    
    @PutMapping("/renew/{book_Id}")
    public ResponseEntity<?> renewBook(@PathVariable int book_Id, @RequestHeader("Authorization") final String token) {
        try {
            Reservation reservation = reservationService.renewBook(book_Id, token);
            return ResponseEntity.ok(reservation);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    
    @PutMapping("/return/{book_Id}")
    public Reservation returnBook(@PathVariable int book_Id, @RequestHeader("Authorization") final String token) {
    	return reservationService.returnBook(book_Id, token);
    }
    
    @GetMapping("/reservations/{username}")
    public ResponseEntity<List<Reservation>> viewReservationsByUsername(@PathVariable String username, @RequestHeader("Authorization") final String token) {            
    	List<Reservation> reservations = reservationService.viewReservationsByUsername(username, token);  
    	return new ResponseEntity<List<Reservation>> (reservations, HttpStatus.OK);
    }
    
    
//    ----------------------------------------------------------------------------------------------------------------
    
    @GetMapping("/reservations")
    public ResponseEntity<List<Reservation>> viewReservations(@RequestHeader("Authorization") final String token) {            
    	List<Reservation> reservations = reservationService.viewReservations(token);  
    	return new ResponseEntity<List<Reservation>> (reservations, HttpStatus.OK);
    }
    
    @PostMapping("/issue-book/{revervationId}")
    public ResponseEntity<IssuebookResponse> issueBook(@PathVariable int revervationId, @RequestHeader("Authorization") final String token) {
    	IssuebookResponse issuebookResponse = reservationService.issueBook(revervationId,token);
        return new ResponseEntity<IssuebookResponse> (issuebookResponse, HttpStatus.OK);	
    }
    
    @GetMapping("/view-issued-books")
    public ResponseEntity<List<Reservation>> viewIssuedBooks(@RequestHeader("Authorization") final String token) {   
    	List<Reservation> issuedBooks = reservationService.viewIssuedBooks(token);  
    	return new ResponseEntity<List<Reservation>> (issuedBooks, HttpStatus.OK);
    }  
}