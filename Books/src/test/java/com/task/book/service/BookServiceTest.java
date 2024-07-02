package com.task.book.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.task.book.exceptionhandling.BookNotFoundException;
import com.task.book.model.Book;

class BookServiceTest {

	@Mock
	private BookService bookService;
	
	private Book book1;
	private Book book2;
	int bookId = 1;
	String bookName = "book one";
	private String token = "sampleToken";
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		book1 = new Book();
		book1.setBookName("book one");
		book1.setAuthorId(123);
		book1.setBookNo(456);
		book1.setBookPrice(100);
		book1.setCategoryId(879);
		book1.setReserved(false);
		book2 = new Book();
		book2.setBookName("book two");
		book2.setAuthorId(124);
		book2.setBookNo(357);
		book2.setBookPrice(200);
		book2.setCategoryId(346);
		book2.setReserved(true);
	}
	
	@Test
	void testViewAllBook() {
		List<Book> books = Arrays.asList(book1, book2);
		when(bookService.viewAllBooks(token)).thenReturn(books);
		List<Book> result = bookService.viewAllBooks(token);
		
		assertNotNull(result);
		assertEquals("book one", result.get(0).getBookName());
		assertEquals(123, result.get(0).getAuthorId());
		assertEquals(456, result.get(0).getBookNo());
		assertEquals(100, result.get(0).getBookPrice());
		assertEquals(879, result.get(0).getCategoryId());
		assertFalse(result.get(0).isReserved());
		assertEquals("book two", result.get(1).getBookName());
		assertEquals(124, result.get(1).getAuthorId());
		assertEquals(357, result.get(1).getBookNo());
		assertEquals(200, result.get(1).getBookPrice());
		assertEquals(346, result.get(1).getCategoryId());
		assertTrue(result.get(1).isReserved());
		
		verify(bookService).viewAllBooks(token);
	}

	@Test
	void testViewBookById_Sucess() {
		when(bookService.viewBookById(bookId, token)).thenReturn(book1);
		Book result = bookService.viewBookById(bookId, token);
		
		assertNotNull(result);
		assertEquals("book one", book1.getBookName());
		assertEquals(123, result.getAuthorId());
		assertEquals(456, result.getBookNo());
		assertEquals(100, result.getBookPrice());
		assertEquals(879, result.getCategoryId());
		assertFalse(result.isReserved());
		
		verify(bookService).viewBookById(bookId, token);
	}
	
	@Test
	void testViewBookById_NotFound() {
		when(bookService.viewBookById(bookId, token)).thenThrow(new BookNotFoundException("Book not found"));
		
		assertThrows(BookNotFoundException.class, ()->{
			bookService.viewBookById(bookId, token);
		});
	}
	
	@Test
	void testViewBookByName_Success() {
		when(bookService.viewBookByName(bookName, token)).thenReturn(book1);
		Book result = bookService.viewBookByName(bookName, token);
		
		assertNotNull(result);
		assertEquals("book one", book1.getBookName());
		assertEquals(123, result.getAuthorId());
		assertEquals(456, result.getBookNo());
		assertEquals(100, result.getBookPrice());
		assertEquals(879, result.getCategoryId());
		assertFalse(result.isReserved());
		
		verify(bookService).viewBookByName(bookName, token);
	}
	
	@Test
	void testViewBookByName_NotFound() {
		when(bookService.viewBookByName(bookName, token)).thenThrow(new BookNotFoundException("Book not found"));
		
		assertThrows(BookNotFoundException.class, ()->{
			bookService.viewBookByName(bookName, token);
		});
	}
	
	@Test 
	void testAddBook() {
		when(bookService.addBook(book1, token)).thenReturn(book1);
		Book result = bookService.addBook(book1, token);
		
		assertNotNull(result);
		assertEquals("book one", book1.getBookName());
		assertEquals(123, result.getAuthorId());
		assertEquals(456, result.getBookNo());
		assertEquals(100, result.getBookPrice());
		assertEquals(879, result.getCategoryId());
		assertFalse(result.isReserved());
		
		verify(bookService).addBook(book1, token);
	}
	
	@Test
	void testEditBook_Success() {
		Book updateBook = new Book();
		updateBook.setBookName("updated book");
		updateBook.setReserved(true);
		when(bookService.editBook(bookId, updateBook, token)).thenReturn(updateBook);
		Book result = bookService.editBook(bookId, updateBook, token);
		
		assertNotNull(updateBook);
		assertEquals("updated book",result.getBookName());
		assertTrue(result.isReserved());
		
		verify(bookService).editBook(bookId, updateBook, token);
	}
	
	@Test
	void testEditBook_NotFound() {
		Book updateBook = new Book();
		updateBook.setBookName("updated book");
		when(bookService.editBook(bookId, updateBook, token)).thenThrow(new BookNotFoundException("Book not found"));
		
		assertThrows(BookNotFoundException.class, ()-> {
			bookService.editBook(bookId, updateBook, token);
		});
	}
	
	@Test
	void testDeleteBook_Success() {
		when(bookService.deleteBook(bookId, token)).thenReturn("Book deleted");
		String result = bookService.deleteBook(bookId, token);
		
		assertEquals("Book deleted", result);
		
		verify(bookService).deleteBook(bookId, token);
	}
	
	@Test
	void testDeleteBook_NotFound() {
		when(bookService.deleteBook(bookId, token)).thenThrow(new BookNotFoundException("Book not found"));
		
		assertThrows(BookNotFoundException.class, ()-> {
			bookService.deleteBook(bookId,token);
		});
	}
	
	@Test
	void testReserveBook_Success() {
		when(bookService.reserveBook(bookId, token)).thenReturn(book1);
		Book result = bookService.reserveBook(bookId, token);
		
		assertNotNull(result);
		
		verify(bookService).reserveBook(bookId, token);
	}
}
