package com.task.book.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="book_table")
public class Book {
	
	@Id
	@Column(name="book_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int bookId;
	
	@Column(name="book_name")
	private String bookName;
	
	@Column(name="author_id")
	private int authorId;
	
	@Column(name="category_id")
	private int categoryId;
	
	@Column(name="book_no")
	private int bookNo;
	
	@Column(name="book_price")
	private int bookPrice;
	

	@Column(name="is_reserved")
	private boolean isReserved;

	public boolean isReserved() {
		return isReserved;
	}

	public void setReserved(boolean isReserved) {
		this.isReserved = isReserved;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public int getAuthorId() {
		return authorId;
	}

	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public int getBookNo() {
		return bookNo;
	}

	public void setBookNo(int bookNo) {
		this.bookNo = bookNo;
	}

	public int getBookPrice() {
		return bookPrice;
	}

	public void setBookPrice(int bookPrice) {
		this.bookPrice = bookPrice;
	}

	public Book() {
		super();
	}

	public Book(int bookId, String bookName, int authorId, int categoryId, int bookNo, int bookPrice,
			boolean isReserved) {
		super();
		this.bookId = bookId;
		this.bookName = bookName;
		this.authorId = authorId;
		this.categoryId = categoryId;
		this.bookNo = bookNo;
		this.bookPrice = bookPrice;
		this.isReserved = isReserved;
	}

	@Override
	public String toString() {
		return "Book [bookId=" + bookId + ", bookName=" + bookName + ", authorId=" + authorId + ", categoryId="
				+ categoryId + ", bookNo=" + bookNo + ", bookPrice=" + bookPrice + ", isReserved=" + isReserved + "]";
	}
}
