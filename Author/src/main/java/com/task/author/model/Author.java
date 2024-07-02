package com.task.author.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="authors")
public class Author {
	
	@Id
	@Column(name="author_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int authorId;
	
	@Column(name="author_name")
	private String authorName;

	public int getAuthorId() {
		return authorId;
	}

	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public Author() {
		super();
	}

	public Author(int authorId, String authorName) {
		super();
		this.authorId = authorId;
		this.authorName = authorName;
	}

	@Override
	public String toString() {
		return "Author [authorId=" + authorId + ", authorName=" + authorName + "]";
	}
	
	

}
