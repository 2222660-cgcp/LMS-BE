package com.task.author.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.task.author.exceptionhandling.AuthorNotFoundException;
import com.task.author.model.Author;

public class AuthorServiceTest {

	@Mock
	private AuthorService authorService;
	
	private Author author1;
	private Author author2;
	int authorId = 1;
	String authorName = "author one";
	private String token = "sampleToken";
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		author1 = new Author();
		author1.setAuthorName("author one");
		author2 = new Author();
		author2.setAuthorName("author two");
	}
	
	@Test
	void testViewAllAuthors() {
		List<Author> authors = Arrays.asList(author1, author2);
		when(authorService.viewAllAuthors(token)).thenReturn(authors);
		List<Author> result = authorService.viewAllAuthors(token);
		
		assertNotNull(result);
		assertEquals("author one", result.get(0).getAuthorName());
		assertEquals("author two", result.get(1).getAuthorName());
		
		verify(authorService).viewAllAuthors(token);
	}
	
	@Test
	void testViewAuthorById_Sucess() {
		when(authorService.viewAuthorbyId(authorId, token)).thenReturn(author1);
		Author result = authorService.viewAuthorbyId(authorId, token);
		
		assertNotNull(result);
		assertEquals("author one", author1.getAuthorName());
		
		verify(authorService).viewAuthorbyId(authorId, token);
	}
	
	@Test
	void testViewAuthorById_NotFound() {
		when(authorService.viewAuthorbyId(authorId, token)).thenThrow(new AuthorNotFoundException("Author not found"));
		
		assertThrows(AuthorNotFoundException.class, ()->{
			authorService.viewAuthorbyId(authorId, token);
		});
	}
	
	@Test
	void testViewAuthorByName_Success() {
		when(authorService.viewAuthorByName(authorName, token)).thenReturn(author1);
		Author result = authorService.viewAuthorByName(authorName, token);
		
		assertNotNull(result);
		assertEquals("author one", result.getAuthorName());
		
		verify(authorService).viewAuthorByName(authorName, token);
	}
	
	@Test
	void testViewAuthorByName_NotFound() {
		when(authorService.viewAuthorByName(authorName, token)).thenThrow(new AuthorNotFoundException("Author not found"));
		
		assertThrows(AuthorNotFoundException.class, ()->{
			authorService.viewAuthorByName(authorName, token);
		});
	}
	
	@Test 
	void testAddAuthor() {
		when(authorService.addAuthor(author1, token)).thenReturn(author1);
		Author result = authorService.addAuthor(author1, token);
		
		assertNotNull(result);
		assertEquals("author one", author1.getAuthorName());
		
		verify(authorService).addAuthor(author1, token);
	}
				
	@Test
	void testEditAuthor_Success() {
		Author updatedAuthor = new Author();
		updatedAuthor.setAuthorName("updated author");
		when(authorService.editAuthor(authorId, updatedAuthor, token)).thenReturn(updatedAuthor);
		Author result = authorService.editAuthor(authorId, updatedAuthor, token);
		
		assertNotNull(updatedAuthor);
		assertEquals("updated author",result.getAuthorName());
		
		verify(authorService).editAuthor(authorId, updatedAuthor, token);
	}
	
	@Test
	void testEditAuthor_NotFound() {
		Author updatedAuthor = new Author();
		updatedAuthor.setAuthorName("updated author");
		when(authorService.editAuthor(authorId, updatedAuthor, token)).thenThrow(new AuthorNotFoundException("Author not found"));
		
		assertThrows(AuthorNotFoundException.class, ()-> {
			authorService.editAuthor(authorId, updatedAuthor, token);
		});
	}
	
	@Test
	void testDeleteAuthor_Success() {
		when(authorService.deleteAuthor(authorId, token)).thenReturn("Author deleted");
		String result = authorService.deleteAuthor(authorId, token);
		
		assertEquals("Author deleted", result);
		
		verify(authorService).deleteAuthor(authorId, token);
	}
	
	@Test
	void testDeleteAuthor_NotFound() {
		when(authorService.deleteAuthor(authorId, token)).thenThrow(new AuthorNotFoundException("Author not found"));
		
		assertThrows(AuthorNotFoundException.class, ()-> {
			authorService.deleteAuthor(authorId,token);
		});
	}
}
