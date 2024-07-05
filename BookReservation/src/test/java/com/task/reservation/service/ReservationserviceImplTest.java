package com.task.reservation.service;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.task.reservation.entity.Reservation;
import com.task.reservation.repository.ReservationRepository;

//----------------------IBRAHIM BADSHAH-----------------------------

@SpringBootTest
public class ReservationserviceImplTest {
	
	@Mock
	private ReservationRepository reservationRepository;
	
	@InjectMocks 
	private ReservationserviceImpl reservationserviceImpl;
	
	
	String token = "token";
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		
	}
	
	@Test
    public void testRenewBook_Success() {
       
        int bookId = 123;
        String token = "sampleToken";
        LocalDate today = LocalDate.now();
        LocalDate dueDate = today.plusDays(1); 
 
        Reservation mockReservation = new Reservation();
        mockReservation.setReservationId(1);
        mockReservation.setBook_Id(bookId);
        mockReservation.setIsIssued(true);
        mockReservation.setDueDate(dueDate);
 
        when(reservationRepository.getReservationByBookId(bookId)).thenReturn(mockReservation);
        when(reservationRepository.findById(mockReservation.getReservationId())).thenReturn(Optional.of(mockReservation));
        when(reservationRepository.save(any(Reservation.class))).thenAnswer(invocation -> invocation.getArgument(0));
 
        Reservation renewedReservation = reservationserviceImpl.renewBook(bookId, token);
 
        assertNotNull(renewedReservation);
        assertEquals(dueDate.plusWeeks(2), renewedReservation.getDueDate());
        verify(reservationRepository).save(mockReservation);
    }
 
    @Test
    public void testRenewBook_BookCannotBeRenewed() {
    
        int bookId = 123;
        String token = "sampleToken";
        LocalDate today = LocalDate.now();
        LocalDate dueDate = today.plusDays(3); 
 
        Reservation mockReservation = new Reservation();
        mockReservation.setReservationId(1);
        mockReservation.setBook_Id(bookId);
        mockReservation.setIsIssued(true);
        mockReservation.setDueDate(dueDate);
 
        when(reservationRepository.getReservationByBookId(bookId)).thenReturn(mockReservation);
        when(reservationRepository.findById(mockReservation.getReservationId())).thenReturn(Optional.of(mockReservation));
 
        RuntimeException exception = assertThrows(RuntimeException.class, () -> reservationserviceImpl.renewBook(bookId, token));
 
        assertEquals("Book can only be renewed 2 day before the due date", exception.getMessage());
    }
	
	//----------------------------ANAGHA.S.R------------------------------------
	
	@Test
	void testViewReservations() {
		List<Reservation> reservations = Arrays.asList(new Reservation(1,"user1",123,"book1", LocalDate.of(2024,06,10),300,LocalDate.of(2024,07,10),0.00, LocalDate.of(2024,06,11),false),
				new Reservation(2,"user2",124,"book2", LocalDate.of(2024,06,10),5500,LocalDate.of(2024,07,10),0.00, LocalDate.of(2024,06,11),true));
		when(reservationRepository.viewReservations()).thenReturn(reservations);
		List<Reservation> result = reservationserviceImpl.viewReservations(token);
                                                     
		assertNotNull(result);
		assertEquals(2,result.size());
		assertEquals(1,result.get(0).getReservationId());
		assertEquals("user1",result.get(0).getUsername());
		assertFalse(result.get(0).getIsIssued());
		assertEquals(2,result.get(1).getReservationId());
		assertEquals("user2",result.get(1).getUsername());
		assertTrue(result.get(1).getIsIssued());
		
		verify(reservationRepository, times(1)).viewReservations();
	}
	
	@Test
	void testViewIssuedBooks() {
		
		List<Reservation> issuedBooks = Arrays.asList(new Reservation(2,"user2",124,"book2", LocalDate.of(2024,06,10),5500,LocalDate.of(2024,07,10),0.00, LocalDate.of(2024,06,11),true));
		when(reservationRepository.viewIssuedBooks()).thenReturn(issuedBooks);
		List<Reservation> result = reservationserviceImpl.viewIssuedBooks(token);
		
		assertNotNull(result);
		assertEquals(1,result.size());
		assertEquals(2,result.get(0).getReservationId());
		assertEquals("user2",result.get(0).getUsername());
		assertTrue(result.get(0).getIsIssued());
		
		verify(reservationRepository, times(1)).viewIssuedBooks();
	}
	
	
	
	
}
