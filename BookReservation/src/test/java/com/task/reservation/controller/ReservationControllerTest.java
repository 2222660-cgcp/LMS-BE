package com.task.reservation.controller;
 
import com.task.reservation.entity.IssuebookResponse;
import com.task.reservation.entity.Reservation;
import com.task.reservation.service.ReservationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.time.LocalDate;
import java.util.Collections;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//--------------------------IBRAHIM BADSHAH-------------------

@WebMvcTest(ReservationController.class)
public class ReservationControllerTest {
	
    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private ReservationService reservationService;
    
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        Reservation reservation = new Reservation();
        reservation.setReservationId(1);
        reservation.setUsername("testuser");
        reservation.setBook_Id(1);
        reservation.setBook_name("Test Book");
        reservation.setReservedAt(LocalDate.now());
        reservation.setBook_price(100);
        reservation.setDueDate(LocalDate.now().plusDays(14));
        reservation.setFine(0.0);
        reservation.setIssuedDate(LocalDate.now());
        reservation.setIsIssued(true);
        IssuebookResponse issuebookResponse = new IssuebookResponse();
        issuebookResponse.setUsername("testuser");
        issuebookResponse.setBookId(1);
        issuebookResponse.setBookName("Test Book");
        issuebookResponse.setIssuedDate(LocalDate.now());
        issuebookResponse.setDueDate(LocalDate.now().plusDays(14));
        issuebookResponse.setFine(0.0);
        when(reservationService.reserveBook(1, "Bearer token")).thenReturn(reservation);
        when(reservationService.renewBook(1, "Bearer token")).thenReturn(reservation);
        when(reservationService.returnBook(1, "Bearer token")).thenReturn(reservation);
        when(reservationService.viewReservationsByUsername("testuser", "Bearer token"))
                .thenReturn(Collections.singletonList(reservation));
        when(reservationService.viewReservations("Bearer token"))
                .thenReturn(Collections.singletonList(reservation));
        when(reservationService.issueBook(1, "Bearer token")).thenReturn(issuebookResponse);
        when(reservationService.viewIssuedBooks("Bearer token"))
                .thenReturn(Collections.singletonList(reservation));
    }
    
    @Test
    public void testReserveBook() throws Exception {
        mockMvc.perform(post("/books/reserve/1")
                .header("Authorization", "Bearer token")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.reservationId").value(1))
                .andExpect(jsonPath("$.username").value("testuser"))
                .andExpect(jsonPath("$.book_Id").value(1))
                .andExpect(jsonPath("$.book_name").value("Test Book"));
    }
    
    @Test
    public void testRenewBook() throws Exception {
        mockMvc.perform(put("/books/renew/1")
                .header("Authorization", "Bearer token")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.reservationId").value(1))
                .andExpect(jsonPath("$.username").value("testuser"))
                .andExpect(jsonPath("$.book_Id").value(1))
                .andExpect(jsonPath("$.book_name").value("Test Book"));
    }
    @Test
    public void testReturnBook() throws Exception {
        mockMvc.perform(put("/books/return/1")
                .header("Authorization", "Bearer token")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.reservationId").value(1))
                .andExpect(jsonPath("$.username").value("testuser"))
                .andExpect(jsonPath("$.book_Id").value(1))
                .andExpect(jsonPath("$.book_name").value("Test Book"));
    }
    
    @Test
    public void testViewReservationsByUsername() throws Exception {
        mockMvc.perform(get("/books/reservations/testuser")
                .header("Authorization", "Bearer token")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$[0].reservationId").value(1))
                .andExpect(jsonPath("$[0].username").value("testuser"))
                .andExpect(jsonPath("$[0].book_Id").value(1))
                .andExpect(jsonPath("$[0].book_name").value("Test Book"));
    }
    
//    -----------------------ANAGHA.S.R-------------------------------------------------------------------------------
    
    @Test
    public void testViewReservations() throws Exception {
        mockMvc.perform(get("/books/reservations")
                .header("Authorization", "Bearer token")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$[0].reservationId").value(1))
                .andExpect(jsonPath("$[0].username").value("testuser"))
                .andExpect(jsonPath("$[0].book_Id").value(1))
                .andExpect(jsonPath("$[0].book_name").value("Test Book"));
    }
    
    @Test
    public void testIssueBook() throws Exception {
        mockMvc.perform(post("/books/issue-book/1")
                .header("Authorization", "Bearer token")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.username").value("testuser"))
                .andExpect(jsonPath("$.bookId").value(1))
                .andExpect(jsonPath("$.bookName").value("Test Book"));
    }
    
    @Test
    public void testViewIssuedBooks() throws Exception {
        mockMvc.perform(get("/books/view-issued-books")
                .header("Authorization", "Bearer token")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$[0].reservationId").value(1))
                .andExpect(jsonPath("$[0].username").value("testuser"))
                .andExpect(jsonPath("$[0].book_Id").value(1))
                .andExpect(jsonPath("$[0].book_name").value("Test Book"));
    }
}