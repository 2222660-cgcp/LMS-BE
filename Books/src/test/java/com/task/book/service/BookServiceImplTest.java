package com.task.book.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.task.author.model.Author;
import com.task.book.exceptionhandling.BookAlreadyReservedException;
import com.task.book.exceptionhandling.BookNotFoundException;
import com.task.book.feignClient.AuthorFeignClient;
import com.task.book.feignClient.CategoryFeignClient;
import com.task.book.model.Book;
import com.task.book.repository.BookRepository;
import com.task.category.model.Category;

@SpringBootTest
public class BookServiceImplTest {
	
	@Mock
	private BookRepository bookRepository;
	
	@Mock
	private AuthorFeignClient authorFeignClient;

	@Mock
	private CategoryFeignClient categoryFeignClient;
	
	@InjectMocks
	private BookServiceImpl bookServiceImpl;
	
	private Book book1;
	private Book book2;
	private Book updateBook;
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
		
		updateBook = new Book();
		updateBook.setBookName("book updated");
		updateBook.setAuthorId(666);
		updateBook.setBookNo(66);
		updateBook.setBookPrice(600);
		updateBook.setCategoryId(6);
	}
	
	@Test
	void testViewAllBooks_Success() {
		List<Book> books = Arrays.asList(book1, book2);
		when(bookRepository.findAll()).thenReturn(books);
		List<Book> result = bookServiceImpl.viewAllBooks(token);
		
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
		
		verify(bookRepository).findAll();
	}
	
	@Test
	void testViewBookById_Success() {
		when(bookRepository.findById(bookId)).thenReturn(Optional.of(book1));
		Book result = bookServiceImpl.viewBookById(bookId, token);
		
		assertNotNull(result);
		assertEquals("book one", book1.getBookName());
		assertEquals(123, result.getAuthorId());
		assertEquals(456, result.getBookNo());
		assertEquals(100, result.getBookPrice());
		assertEquals(879, result.getCategoryId());
		assertFalse(result.isReserved());
		
		verify(bookRepository).findById(bookId);
	}
	
	@Test
	void testViewBookById_NotFound() {
		when(bookRepository.findById(bookId)).thenReturn(Optional.empty());
		
		assertThrows(BookNotFoundException.class, ()->{
			bookServiceImpl.viewBookById(bookId, token);
		});
	}
	
	@Test
	void testViewBookByName_Success() {
		when(bookRepository.viewBookByName(bookName)).thenReturn(book1);
		Book result = bookServiceImpl.viewBookByName(bookName, token);
		
		assertNotNull(result);
		assertEquals("book one", book1.getBookName());
		assertEquals(123, result.getAuthorId());
		assertEquals(456, result.getBookNo());
		assertEquals(100, result.getBookPrice());
		assertEquals(879, result.getCategoryId());
		assertFalse(result.isReserved());
		
		verify(bookRepository).viewBookByName(bookName);
	}
	
	@Test
	void testViewBookByName_NotFound() {
		when(bookRepository.viewBookByName(bookName)).thenReturn(null);
		
		assertThrows(BookNotFoundException.class, ()->{
			bookServiceImpl.viewBookByName(bookName, token);
		});
	}
	
//	@Test
//	void testAddBook_Success() {
//		Author author = new Author();
//		Category category = new Category();
//		when(authorFeignClient.viewAuthorbyId(book1.getAuthorId(), token)).thenReturn(author);
//		when(categoryFeignClient.viewCategorybyId(book1.getCategoryId(), token)).thenReturn(category);
//		when(bookRepository.save(any(Book.class))).thenReturn(book1);
//		Book result = bookServiceImpl.addBook(book1, token);
//		
//		assertNotNull(result);
//		assertEquals("book one", book1.getBookName());
//		assertEquals(123, result.getAuthorId());
//		assertEquals(456, result.getBookNo());
//		assertEquals(100, result.getBookPrice());
//		assertEquals(879, result.getCategoryId());
//		assertFalse(result.isReserved());
//		
//		verify(bookRepository, times(1)).save(any(Book.class));
//	}
	
//	@Test
//	void testAddBook_AuthorNotFound() {
//		when(authorFeignClient.viewAuthorbyId(book1.getAuthorId(), token)).thenReturn(null);
//		
//		Exception exception = assertThrows(AuthorNotFoundException.class, ()-> {
//			bookServiceImpl.addBook(book1, token);
//		});
//		
//		String expectedMsg = "Author NotFound";
//		String actualMsg = exception.getMessage();
//		
//		assertTrue(actualMsg.contains(expectedMsg));
//	}
		
	@Test
	void testEditBook_Success() {
	    when(bookRepository.findById(book1.getBookId())).thenReturn(Optional.of(book1));
		when(bookRepository.save(any(Book.class))).thenReturn(book1);
		
		Book result = bookServiceImpl.editBook(book1.getBookId(), updateBook, token);
		
		assertNotNull(updateBook);
		assertEquals(updateBook.getBookName(),result.getBookName());
		assertEquals(updateBook.getAuthorId(),result.getAuthorId());
		assertEquals(updateBook.getCategoryId(),result.getCategoryId());
		assertEquals(updateBook.getBookNo(),result.getBookNo());
		assertEquals(updateBook.getBookPrice(),result.getBookPrice());
		
		verify(bookRepository, times(1)).save(any(Book.class));
	}
	
	@Test
	void testEditBook_NotFound() {
		Book updateBook = new Book();
		updateBook.setBookName("updated book");
		
		when(bookRepository.findById(bookId)).thenReturn(Optional.empty());	
		
		assertThrows(BookNotFoundException.class, ()->{
			bookServiceImpl.editBook(bookId, updateBook, token);
		});	
	}
	
	@Test
	void testDeleteBook_Success() {
		when(bookRepository.findById(bookId)).thenReturn(Optional.of(book1));	
		String result = bookServiceImpl.deleteBook(bookId, token);
		
		verify(bookRepository).findById(bookId);
		verify(bookRepository).deleteById(bookId);
		assertEquals("Book deleted", result);	
	}

	@Test
	void testDeleteBook_NotFound() {
		Exception exception = assertThrows(BookNotFoundException.class, ()->{
			bookServiceImpl.deleteBook(bookId, token);
		});
		
		verify(bookRepository).findById(bookId);
		verify(bookRepository, never()).deleteById(bookId);
		assertEquals("Book not found", exception.getMessage());
	}
	
	@Test
	void testReserveBook_Success() {
		when(bookRepository.findById(book1.getBookId())).thenReturn(Optional.of(book1));
		Book result = bookServiceImpl.reserveBook(book1.getBookId(), token);
		
		assertNotNull(result);
		assertTrue(result.isReserved());
		
		verify(bookRepository, times(1)).save(book1);
	}
	
	@Test
	void testReserveBook_BookNotFound() {
		when(bookRepository.findById(bookId)).thenReturn(Optional.empty());
		
		Exception exception = assertThrows(BookNotFoundException.class, ()->{
			bookServiceImpl.reserveBook(bookId, token);
		});
		String expectedMsg = "Book not found";
		String actualMsg = exception.getMessage();
		
		assertTrue(actualMsg.contains(expectedMsg));
	}
	
	@Test
	void testReserveBook_AlreadyReserved() {
		book1.setReserved(true);
		
		when(bookRepository.findById(book1.getBookId())).thenReturn(Optional.of(book1));
		
		Exception exception = assertThrows(BookAlreadyReservedException.class, ()->{
			bookServiceImpl.reserveBook(book1.getBookId(), token);
		});
		String expectedMsg = "Book already reserved";
		String actualMsg = exception.getMessage();
		
		assertTrue(actualMsg.contains(expectedMsg));
	}
	
	@Test
	void testReturnBook_Success() {
		when(bookRepository.findById(book2.getBookId())).thenReturn(Optional.of(book2));
		bookServiceImpl.returnBook(book2.getBookId(), token);
		
		assertFalse(book2.isReserved());
		
		verify(bookRepository, times(1)).save(book2);	
	}
	
	@Test
	void testReturnBook_BookNotFound() {
		when(bookRepository.findById(book2.getBookId())).thenReturn(Optional.empty());
		
		Exception exception = assertThrows(BookNotFoundException.class, ()->{
			bookServiceImpl.returnBook(book2.getBookId(), token);
		});
		String expectedMsg = "Book not found";
		String actualMsg = exception.getMessage();
		
		assertTrue(actualMsg.contains(expectedMsg));
	}
	
	@Test
	void testReturnBook_BookNotReserved() {
		book2.setReserved(false);
		when(bookRepository.findById(book2.getBookId())).thenReturn(Optional.of(book2));
		
		Exception exception = assertThrows(RuntimeException.class, ()->{
			bookServiceImpl.returnBook(book2.getBookId(), token);
		});
		String expectedMsg = "Book is not reserved and cannot be returned";
		String actualMsg = exception.getMessage();
		
		assertTrue(actualMsg.contains(expectedMsg));
	}	
}
