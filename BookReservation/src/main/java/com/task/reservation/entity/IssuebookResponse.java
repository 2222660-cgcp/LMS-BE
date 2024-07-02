package com.task.reservation.entity;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

public class IssuebookResponse {
	
	private String username;
    private int bookId;
    private String bookName;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate issuedDate;
    private LocalDate dueDate;
    private double fine;
    
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public double getFine() {
		return fine;
	}
	public void setFine(double fine) {
		this.fine = fine;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	
	public LocalDate getIssuedDate() {
		return issuedDate;
	}
	public void setIssuedDate(LocalDate issuedDate) {
		this.issuedDate = issuedDate;
	}
	public LocalDate getDueDate() {
		return dueDate;
	}
	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}
    
    

}
