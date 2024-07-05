package com.task.reservation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.task.reservation.entity.Reservation;

//------------------------ANAGHA.S.R----------

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

	@Query("select r from Reservation r where r.isIssued = true order by r.issuedDate desc")
	List<Reservation> viewIssuedBooks();
	
	@Query("select r from Reservation r order by r.reservedAt desc")
	List<Reservation> viewReservations();
	
//	-----------------------------IBRAHIM BADSHAH------------------------------------
	
	@Query("select r from Reservation r where r.book_Id = :book_Id")
	Reservation getReservationByBookId(@Param("book_Id")int book_Id);

	@Query("select r from Reservation r where r.username = :username")
	List<Reservation> viewReservationsByUsername(@Param("username")String username);

}
