package com.task.reservation.entity;

import java.time.LocalDate;
import java.util.Objects;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class Reservation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int reservationId;

	private String username;
    private int book_Id;
    private String book_name;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate reservedAt;
    
    private int book_price;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dueDate;
    private double fine;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate issuedDate;
    
    public LocalDate getIssuedDate() {
		return issuedDate;
	}

	public void setIssuedDate(LocalDate issuedDate) {
		this.issuedDate = issuedDate;
	}

	private Boolean isIssued;
    
    
    public Boolean getIsIssued() {
		return isIssued;
	}

	public void setIsIssued(Boolean isIssued) {
		this.isIssued = isIssued;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
    public String getBook_name() {
		return book_name;
	}

	public void setBook_name(String book_name) {
		this.book_name = book_name;
	}

    public int getBook_Id() {
		return book_Id;
	}

	public void setBook_Id(int book_Id) {
		this.book_Id = book_Id;
	}

	public int getBook_price() {
		return book_price;
	}

	public void setBook_price(int book_price) {
		this.book_price = book_price;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}

    public double getFine() {
		return fine;
	}

	public void setFine(double fine) {
		this.fine = fine;
	}

	public int getReservationId() {
        return reservationId;
    }
 
    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }
 

    public LocalDate getReservedAt() {
        return reservedAt;
    }
 
    public void setReservedAt(LocalDate localDate) {
        this.reservedAt = localDate;
    }

	public Reservation(int reservationId, String username, int book_Id, String book_name, LocalDate reservedAt,
			int book_price, LocalDate dueDate, double fine, LocalDate issuedDate, Boolean isIssued) {
		super();
		this.reservationId = reservationId;
		this.username = username;
		this.book_Id = book_Id;
		this.book_name = book_name;
		this.reservedAt = reservedAt;
		this.book_price = book_price;
		this.dueDate = dueDate;
		this.fine = fine;
		this.issuedDate = issuedDate;
		this.isIssued = isIssued;
	}

	public Reservation() {
		super();
	}

	@Override
	public String toString() {
		return "Reservation [reservationId=" + reservationId + ", username=" + username + ", book_Id=" + book_Id
				+ ", book_name=" + book_name + ", reservedAt=" + reservedAt + ", book_price=" + book_price
				+ ", dueDate=" + dueDate + ", fine=" + fine + ", issuedDate=" + issuedDate + ", isIssued=" + isIssued
				+ "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(book_Id, book_name, book_price, dueDate, fine, isIssued, issuedDate, reservationId,
				reservedAt, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reservation other = (Reservation) obj;
		return book_Id == other.book_Id && Objects.equals(book_name, other.book_name) && book_price == other.book_price
				&& Objects.equals(dueDate, other.dueDate)
				&& Double.doubleToLongBits(fine) == Double.doubleToLongBits(other.fine)
				&& Objects.equals(isIssued, other.isIssued) && Objects.equals(issuedDate, other.issuedDate)
				&& reservationId == other.reservationId && Objects.equals(reservedAt, other.reservedAt)
				&& Objects.equals(username, other.username);
	}
    
}