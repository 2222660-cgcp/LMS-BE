package com.task.book.model;

public class UserAvailableBooks {
	
	private int bookId;
	private String bookName;
	private int bookNo;
	private String authorName;
	private String categoryName;
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
	public int getBookNo() {
		return bookNo;
	}
	public void setBookNo(int bookNo) {
		this.bookNo = bookNo;
	}
	public String getAuthorName() {
		return authorName;
	}
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	public UserAvailableBooks() {
		super();
	}
	public UserAvailableBooks(int bookId, String bookName, int bookNo, String authorName, String categoryName,
			boolean isReserved) {
		super();
		this.bookId = bookId;
		this.bookName = bookName;
		this.bookNo = bookNo;
		this.authorName = authorName;
		this.categoryName = categoryName;
		this.isReserved = isReserved;
	}
	@Override
	public String toString() {
		return "UserAvailableBooks [bookId=" + bookId + ", bookName=" + bookName + ", bookNo=" + bookNo
				+ ", authorName=" + authorName + ", categoryName=" + categoryName + ", isReserved=" + isReserved + "]";
	}
	
}
