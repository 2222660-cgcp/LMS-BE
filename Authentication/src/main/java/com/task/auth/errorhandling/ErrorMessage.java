package com.task.auth.errorhandling;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

//---------------------ANAGHA.S.R--------------------------

public class ErrorMessage 
{
    private HttpStatus status;
    private LocalDateTime timestamp;
    private String message;
	public HttpStatus getStatus() {
		return status;
	}
	public void setStatus(HttpStatus status) {
		this.status = status;
	}
	public LocalDateTime getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public ErrorMessage() {
		super();
	}
	public ErrorMessage(HttpStatus status, LocalDateTime timestamp, String message) {
		super();
		this.status = status;
		this.timestamp = timestamp;
		this.message = message;
	}
       
}
