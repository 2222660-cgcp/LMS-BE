package com.task.feedback.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="feedbacks")
public class Feedback {  
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;  
	private String comment;
	private String username;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Feedback() {
		super();
	}
	public Feedback(Long id, String comment, String username) {
		super();
		this.id = id;
		this.comment = comment;
		this.username = username;
	}
	@Override
	public String toString() {
		return "Feedback [id=" + id + ", comment=" + comment + ", username=" + username + "]";
	}
		
}