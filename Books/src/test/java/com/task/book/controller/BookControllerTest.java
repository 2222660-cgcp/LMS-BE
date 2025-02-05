package com.task.book.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.task.book.model.Book;
import com.task.book.model.UserAvailableBooks;
import com.task.book.service.BookService;

//-----------------------ANAGHA.S.R------------------------------------

@WebMvcTest(BookController.class)
class BookControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private BookService bookService;
	
	private List<Book> books;
	
	private Book book;
	
	private ObjectMapper objectMapper = new ObjectMapper();
	
	@BeforeEach
	public void setUp() {
		Book b1 = new Book(1,"book1",1,3,123,250,false);
		Book b2 = new Book(2,"book2",4,7,139,300,true);
		books = Arrays.asList(b1,b2);
		book = b1;
		
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	public void viewAllBooksTest() throws Exception {
		when(bookService.viewAllBooks(anyString())).thenReturn(books);
		
		mockMvc.perform(get("/book").header("Authorization","Bearer token"))
			   .andExpect(status().isOk())
			   .andExpect(content().contentType(MediaType.APPLICATION_JSON))
			   .andExpect(jsonPath("$[0].bookId").value(1))
			   .andExpect(jsonPath("$[0].bookName").value("book1"))
			   .andExpect(jsonPath("$[0].authorId").value(1))
			   .andExpect(jsonPath("$[0].categoryId").value(3))
			   .andExpect(jsonPath("$[0].bookNo").value(123))
			   .andExpect(jsonPath("$[0].bookPrice").value(250))
			   .andExpect(jsonPath("$[0].reserved").value(false))
			   .andExpect(jsonPath("$[1].bookId").value(2))
			   .andExpect(jsonPath("$[1].bookName").value("book2"))
			   .andExpect(jsonPath("$[1].authorId").value(4))
			   .andExpect(jsonPath("$[1].categoryId").value(7))
			   .andExpect(jsonPath("$[1].bookNo").value(139))
			   .andExpect(jsonPath("$[1].bookPrice").value(300))
			   .andExpect(jsonPath("$[1].reserved").value(true));
		
		verify(bookService, times(1)).viewAllBooks(anyString());
	}
	
	@Test
	public void viewBookByIdTest() throws Exception {
		when(bookService.viewBookById(anyInt(),anyString())).thenReturn(book);
		
		mockMvc.perform(get("/book/{book_id}","1").header("Authorization","Bearer token"))
			   .andExpect(status().isOk())
			   .andExpect(content().contentType(MediaType.APPLICATION_JSON))
			   .andExpect(jsonPath("$.bookId").value(1))
			   .andExpect(jsonPath("$.bookName").value("book1"))
			   .andExpect(jsonPath("$.authorId").value(1))
			   .andExpect(jsonPath("$.categoryId").value(3))
			   .andExpect(jsonPath("$.bookNo").value(123))
			   .andExpect(jsonPath("$.bookPrice").value(250))
			   .andExpect(jsonPath("$.reserved").value(false));
		
		verify(bookService, times(1)).viewBookById(anyInt(),anyString());
	}
	
	@Test
	public void viewBookByNameTest() throws Exception {
		when(bookService.viewBookByName(anyString(), anyString())).thenReturn(book);
		
		mockMvc.perform(get("/book/viewBook/{book_name}","book1").header("Authorization","Bearer token"))
			   .andExpect(status().isOk())
			   .andExpect(content().contentType(MediaType.APPLICATION_JSON))
			   .andExpect(jsonPath("$.bookId").value(1))
			   .andExpect(jsonPath("$.bookName").value("book1"))
			   .andExpect(jsonPath("$.authorId").value(1))
			   .andExpect(jsonPath("$.categoryId").value(3))
			   .andExpect(jsonPath("$.bookNo").value(123))
			   .andExpect(jsonPath("$.bookPrice").value(250))
			   .andExpect(jsonPath("$.reserved").value(false));
		
		verify(bookService, times(1)).viewBookByName(anyString(), anyString());
	}
	
	@Test
	public void addBookTest() throws Exception {
		when(bookService.addBook(any(Book.class), anyString())).thenReturn(book);
		
		mockMvc.perform(post("/book").header("Authorization","Bearer token")
										 .contentType(MediaType.APPLICATION_JSON)
										 .content(objectMapper.writeValueAsString(book)))
			   .andExpect(status().isCreated())
			   .andExpect(content().contentType(MediaType.APPLICATION_JSON))
			   .andExpect(jsonPath("$.bookId").value(1))
			   .andExpect(jsonPath("$.bookName").value("book1"))
			   .andExpect(jsonPath("$.authorId").value(1))
			   .andExpect(jsonPath("$.categoryId").value(3))
			   .andExpect(jsonPath("$.bookNo").value(123))
			   .andExpect(jsonPath("$.bookPrice").value(250))
			   .andExpect(jsonPath("$.reserved").value(false));
		
		verify(bookService, times(1)).addBook(any(Book.class), anyString());
	}
	
	@Test
	public void editBookTest() throws Exception {
		when(bookService.editBook(anyInt(), any(Book.class), anyString())).thenReturn(book);
		
		mockMvc.perform(put("/book/{book_id}","1").header("Authorization","Bearer token")
														  .contentType(MediaType.APPLICATION_JSON)
														  .content(objectMapper.writeValueAsString(book)))
		       .andExpect(status().isOk())
		       .andExpect(content().contentType(MediaType.APPLICATION_JSON))
		       .andExpect(jsonPath("$.bookId").value(1))
			   .andExpect(jsonPath("$.bookName").value("book1"))
			   .andExpect(jsonPath("$.authorId").value(1))
			   .andExpect(jsonPath("$.categoryId").value(3))
			   .andExpect(jsonPath("$.bookNo").value(123))
			   .andExpect(jsonPath("$.bookPrice").value(250))
			   .andExpect(jsonPath("$.reserved").value(false));
		
		verify(bookService, times(1)).editBook(anyInt(), any(Book.class), anyString());
		
	}
	
	@Test
	public void deleteBookTest() throws Exception {
		when(bookService.deleteBook(anyInt(), anyString())).thenReturn("Book deleted");
		
		mockMvc.perform(delete("/book/{book_id}","1").header("Authorization","Bearer token")
														     .contentType(MediaType.APPLICATION_JSON)
														     .content(objectMapper.writeValueAsString(book)))
		       .andExpect(status().isOk())
		       .andExpect(content().string("Book deleted"));
		
		verify(bookService, times(1)).deleteBook(anyInt(),anyString());
	}
	
	@Test
	public void reserveBookTest() throws Exception {
		when(bookService.reserveBook(anyInt(), anyString())).thenReturn(book);
		
		mockMvc.perform(put("/book/reserve_book/{book_id}","1").header("Authorization","Bearer token")
															   .contentType(MediaType.APPLICATION_JSON)
															   .content(objectMapper.writeValueAsString(book)))
			   .andExpect(status().isOk())
			   .andExpect(content().contentType(MediaType.APPLICATION_JSON))
			   .andExpect(jsonPath("$.bookId").value(1))
			   .andExpect(jsonPath("$.bookName").value("book1"))
			   .andExpect(jsonPath("$.authorId").value(1))
			   .andExpect(jsonPath("$.categoryId").value(3))
			   .andExpect(jsonPath("$.bookNo").value(123))
			   .andExpect(jsonPath("$.bookPrice").value(250))
			   .andExpect(jsonPath("$.reserved").value(false));
		
		verify(bookService, times(1)).reserveBook(anyInt(), anyString());
	}
	
//	----------------------IBRAHIM BADSHAH-------------------------------------------------------------------------------
	
	@Test
    public void testReturnBook() throws Exception {
        doNothing().when(bookService).returnBook(anyInt(), anyString());
 
        mockMvc.perform(put("/book/return_book/{book_id}", 1)
                .header("Authorization", "Bearer token"))
                .andExpect(status().isOk());
    }
	
	@Test
    public void testAvailableBooksForUser() throws Exception {
        List<UserAvailableBooks> userAvailableBooks = Arrays.asList(
                new UserAvailableBooks(1, "Book 1", 101, "Author 1", "Category 1", false),
                new UserAvailableBooks(2, "Book 2", 102, "Author 2", "Category 2", true)
        );
        when(bookService.availableBooksForUser(anyString())).thenReturn(userAvailableBooks);
 
        mockMvc.perform(get("/book/available-books")
                .header("Authorization", "Bearer token"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].bookId").value(1))
                .andExpect(jsonPath("$[0].bookName").value("Book 1"))
                .andExpect(jsonPath("$[0].bookNo").value(101))
                .andExpect(jsonPath("$[0].authorName").value("Author 1"))
                .andExpect(jsonPath("$[0].categoryName").value("Category 1"))
                .andExpect(jsonPath("$[0].reserved").value(false))
                .andExpect(jsonPath("$[1].bookId").value(2))
                .andExpect(jsonPath("$[1].bookName").value("Book 2"))
                .andExpect(jsonPath("$[1].bookNo").value(102))
                .andExpect(jsonPath("$[1].authorName").value("Author 2"))
                .andExpect(jsonPath("$[1].categoryName").value("Category 2"))
                .andExpect(jsonPath("$[1].reserved").value(true));
    }
	
	@Test
    public void testSearchBooksByPartialName() throws Exception {
        List<UserAvailableBooks> books = Arrays.asList(
                new UserAvailableBooks(3, "Partial Book 1", 103, "Author 3", "Category 3", false),
                new UserAvailableBooks(4, "Partial Book 2", 104, "Author 4", "Category 4", true)
        );
        when(bookService.searchBooksByPartialName(anyString(), anyString())).thenReturn(books);
 
        mockMvc.perform(get("/book/search/{name}", "Partial Book")
                .header("Authorization", "Bearer token"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].bookId").value(3))
                .andExpect(jsonPath("$[0].bookName").value("Partial Book 1"))
                .andExpect(jsonPath("$[0].bookNo").value(103))
                .andExpect(jsonPath("$[0].authorName").value("Author 3"))
                .andExpect(jsonPath("$[0].categoryName").value("Category 3"))
                .andExpect(jsonPath("$[0].reserved").value(false))
                .andExpect(jsonPath("$[1].bookId").value(4))
                .andExpect(jsonPath("$[1].bookName").value("Partial Book 2"))
                .andExpect(jsonPath("$[1].bookNo").value(104))
                .andExpect(jsonPath("$[1].authorName").value("Author 4"))
                .andExpect(jsonPath("$[1].categoryName").value("Category 4"))
                .andExpect(jsonPath("$[1].reserved").value(true));
    }

}
