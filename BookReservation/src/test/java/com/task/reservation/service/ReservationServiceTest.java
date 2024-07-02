package com.task.reservation.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.task.reservation.entity.Reservation;

@SpringBootTest
class ReservationServiceTest {

	@InjectMocks
	private ReservationserviceImpl reservationserviceImpl;
	
	String token = "token";
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}
	
//	@Test
//	void testViewReservations() {
//		List<Reservation> reservations = Arrays.asList(new Reservation(1,"user1",123,"book1", LocalDate.of(2024,06,10),300,LocalDate.of(2024,07,10),0.00, LocalDate.of(2024,06,11),false),
//				new Reservation(2,"user2",124,"book2", LocalDate.of(2024,06,10),5500,LocalDate.of(2024,07,10),0.00, LocalDate.of(2024,06,11),true));
//		when(reservationserviceImpl.viewReservations(token)).thenReturn(reservations);
//		List<Reservation> result = reservationserviceImpl.viewReservations(token);
//		
//		assertNotNull(result);
//		assertEquals(2,result.size());
//		assertEquals(1,result.get(0).getReservationId());
//		assertEquals("user1",result.get(0).getUsername());
//		assertFalse(result.get(0).getIsIssued());
//		assertEquals(2,result.get(1).getReservationId());
//		assertEquals("user2",result.get(1).getUsername());
//		assertTrue(result.get(1).getIsIssued());
//	}

}
