package com.task.book.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.task.book.model.Book;
import com.task.book.model.UserAvailableBooks;
import com.task.book.service.BookService;


//-----------------------ANAGHA.S.R-----------------------------------

@CrossOrigin()
@RestController
@RequestMapping("/book")
public class BookController {
	
	@Autowired
	private BookService bookService;
	
	@GetMapping
	public ResponseEntity<List<Book>> viewAllBooks(@RequestHeader("Authorization") final String token) {
		List<Book> books =  bookService.viewAllBooks(token);
		return new ResponseEntity<List<Book>> (books, HttpStatus.OK);
	}

	@GetMapping("/{book_id}")
	public ResponseEntity<Book> viewBookById(@PathVariable int book_id, @RequestHeader("Authorization") final String token) {
		Book book = bookService.viewBookById(book_id, token);
		return new ResponseEntity<Book> (book, HttpStatus.OK);
	}
	
	@GetMapping("/viewBook/{book_name}")
	public ResponseEntity<Book> viewBookByName(@PathVariable String book_name, @RequestHeader("Authorization") final String token) {
		Book book = bookService.viewBookByName(book_name, token);
		return new ResponseEntity<Book> (book, HttpStatus.OK);
	} 
	
	@PostMapping
	public ResponseEntity<Book> addBook(@RequestBody Book book, @RequestHeader("Authorization") final String token) {
		Book savedBook = bookService.addBook(book, token);
		return new ResponseEntity<Book> (savedBook, HttpStatus.CREATED);
	}
	
	@PutMapping("/{book_id}")
	public ResponseEntity<Book> editBook(@PathVariable int book_id, @RequestBody Book book, @RequestHeader("Authorization") final String token) {
		Book updatedBook = bookService.editBook(book_id, book, token);
		return new ResponseEntity<Book> (updatedBook,HttpStatus.OK);
	}
	
	@DeleteMapping("/{book_id}")
	public ResponseEntity<String> deleteBook(@PathVariable int book_id, @RequestHeader("Authorization") final String token) {
		 String response = bookService.deleteBook(book_id, token);
		 return new ResponseEntity<String> (response,HttpStatus.OK);
	}
	
	
//	---------------------------IBRAHIM BADSHAH----------------------------------------------------------------------------------------

	@PutMapping("/reserve_book/{book_id}")
	public ResponseEntity<Book> reserveBook(@PathVariable int book_id, @RequestHeader("Authorization") final String token) {
		Book book = bookService.reserveBook(book_id, token);
		return new ResponseEntity<Book> (book,HttpStatus.OK);
	}
	
	@PutMapping("/return_book/{book_id}")
	public void returnBook(@PathVariable int book_id, @RequestHeader("Authorization") final String token) {
		bookService.returnBook(book_id,token);
	}
	
	@GetMapping("/available-books")
	public ResponseEntity<List<UserAvailableBooks>> availableBooksForUser(@RequestHeader("Authorization") final String token) {
		List<UserAvailableBooks> userAvailableBooks =  bookService.availableBooksForUser(token);
		return new ResponseEntity<List<UserAvailableBooks>> (userAvailableBooks, HttpStatus.OK);
	}
	
	@GetMapping("/viewBookSearch/{book_name}")
	public ResponseEntity<UserAvailableBooks> searchBooksForUser(@PathVariable String book_name, @RequestHeader("Authorization") final String token) {
		UserAvailableBooks userAvailableBooks = bookService.searchBooksForUser(book_name, token);
		return new ResponseEntity<UserAvailableBooks> (userAvailableBooks, HttpStatus.OK);
	} 
	
	@GetMapping("/search/{name}")
    public ResponseEntity<List<UserAvailableBooks>> searchBooksByPartialName(@PathVariable String name, @RequestHeader("Authorization") final String token) {
        List<UserAvailableBooks> books = bookService.searchBooksByPartialName(name, token);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }
} 
