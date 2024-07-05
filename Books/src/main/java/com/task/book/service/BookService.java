package com.task.book.service;
import java.util.List;

import com.task.book.model.Book;
import com.task.book.model.UserAvailableBooks;

//-----------------------ANAGHA.S.R-------------------------------------------

public interface BookService {
	
	List<Book> viewAllBooks(String token);
	Book viewBookById(int book_id, String token);
	Book viewBookByName(String book_name, String token);
	Book addBook(Book book, String token);
	Book editBook(int book_id, Book book, String token);
	String deleteBook(int book_id, String token);
	
//	---------------------IBRAHIM BADSHAH-----------------------------------------------------------------------------------------
	
	Book reserveBook(int book_id, String token);
	List<UserAvailableBooks> availableBooksForUser(String token);	
	void returnBook(int book_id, String token);
	UserAvailableBooks searchBooksForUser(String book_name, String token);
	List<UserAvailableBooks> searchBooksByPartialName(String name, String token);
	
}
