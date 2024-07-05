package com.task.reservation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.web.client.RestTemplate;

import com.task.book.model.Book;
import com.task.reservation.entity.IssuebookResponse;
import com.task.reservation.entity.Reservation;
import com.task.reservation.exceptionhandling.ReservationDataNotFoundException;
import com.task.reservation.feignClient.BookFeignClient;
import com.task.reservation.repository.ReservationRepository;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;

import org.springframework.http.ResponseEntity;
 
//-------------------------IBRAHIM BADSHAH------------------------------------------------------


@Service
public class ReservationserviceImpl implements ReservationService {
    
    @Autowired
    private RestTemplate restTemplate;
    
    @Autowired
    private ReservationRepository reservationRepository;
    
    private final String AUTH_MICROSERVICE_URL = "http://localhost:8080";
    
    private final BookFeignClient bookFeignClient;
	
	public ReservationserviceImpl(BookFeignClient bookFeignClient) {
        this.bookFeignClient = bookFeignClient;
    }
	
	@Override
    public Reservation reserveBook(int book_Id,String authToken) {
    	String username = getUsernameFromAuthToken(authToken);
        if (username == null) {
            return null;
        }
        Book book = bookFeignClient.reserveBook(book_Id, authToken);
		Reservation reservation = new Reservation();
	    if((book.getBookId() == book_Id) && book.isReserved()) {
	        reservation.setReservedAt(LocalDate.now());
	        reservation.setBook_Id(book_Id);
	        reservation.setUsername(username);
	        reservation.setBook_name(book.getBookName());
	        reservation.setBook_price(book.getBookPrice());
	        reservation.setIssuedDate(null);
	        reservation.setDueDate(null);    
	        reservation.setIsIssued(false);
	    } else {
	    	throw new RuntimeException("Book is already reserved");
	    }
	    return reservationRepository.save(reservation);
    }
    
    public String getUsernameFromAuthToken(String authToken) {
        String username = null;
 
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + authToken);
        ResponseEntity<String> response = restTemplate.exchange(
                AUTH_MICROSERVICE_URL + "/validateToken",
                HttpMethod.GET,
                //null,
                new HttpEntity<>(headers),
                String.class);
        if (response.getStatusCode().is2xxSuccessful()) {
            username = response.getBody();
        }
 
        return username;
    }
 
    @Override
    public void updateFines() {    	
    	List<Reservation> reservation = reservationRepository.findAll();
    	LocalDate today = LocalDate.now();
    	for(Reservation r : reservation) {
    		if(r.getDueDate().isBefore(today)) {
    			long daysOverDue  = ChronoUnit.DAYS.between(r.getDueDate(), today);
    			r.setFine((int)daysOverDue*5);
    			reservationRepository.save(r);
    		}
    	}
    }
       
    @Override
    public Reservation renewBook(int book_Id, String token) {
        Reservation r = reservationRepository.getReservationByBookId(book_Id);
        Reservation obj = reservationRepository.findById(r.getReservationId()).orElseThrow(() -> new RuntimeException("Reservation not found"));

        LocalDate today = LocalDate.now();
        LocalDate dueDate = obj.getDueDate();

        if(obj.getIsIssued() && (today.plusDays(2).equals(dueDate) || today.plusDays(1).equals(dueDate) || today.equals(dueDate) || today.isAfter(dueDate) )) {
            obj.setDueDate(dueDate.plusWeeks(2));
        } else {
            throw new RuntimeException("Book can only be renewed 2 day before the due date");
        }
        return reservationRepository.save(obj);
    }
  
    public void deleteReturnedBooks(int reservation_Id) {
    	reservationRepository.deleteById(reservation_Id);
    }
    
    @Override
	public Reservation returnBook(int book_Id, String token) {
    	bookFeignClient.returnBook(book_Id, token);
		Reservation r = reservationRepository.getReservationByBookId(book_Id);
		Reservation obj = reservationRepository.findById(r.getReservationId()).orElseThrow(() -> new RuntimeException("Reservation not found"));
		if(obj.getIsIssued()) {
			deleteReturnedBooks(r.getReservationId());
		} else {
			throw new RuntimeException("Book is not issued");
		}
		return obj;
	}
     
    @Override
	public List<Reservation> viewReservationsByUsername(String username, String token) {
		
		return reservationRepository.viewReservationsByUsername(username);
	}
    
//    --------------------------ANAGHA.S.R---------------------------------------------------------------------------------------
    
    @Override
    public List<Reservation> viewReservations(String token) {
    	return reservationRepository.viewReservations();     
    }
    
	@Override
	public IssuebookResponse issueBook(int revervationId, String token) {
		Reservation reservation = reservationRepository.findById(revervationId).orElseThrow(() -> new ReservationDataNotFoundException("Reservation id doesn't exist"));
		reservation.setDueDate(LocalDate.now().plusWeeks(2));
		reservation.setIsIssued(true);
		reservation.setIssuedDate(LocalDate.now());
		reservationRepository.save(reservation);
		
		IssuebookResponse issueBookResponse = new IssuebookResponse();
		issueBookResponse.setBookId(reservation.getBook_Id());
		issueBookResponse.setBookName(reservation.getBook_name());
		issueBookResponse.setDueDate(LocalDate.now().plusWeeks(2));
		issueBookResponse.setIssuedDate(LocalDate.now());
		issueBookResponse.setUsername(reservation.getUsername());
		return issueBookResponse;
	}
	
	@Override
	public List<Reservation> viewIssuedBooks(String token) {
		return reservationRepository.viewIssuedBooks();
	}	
}
