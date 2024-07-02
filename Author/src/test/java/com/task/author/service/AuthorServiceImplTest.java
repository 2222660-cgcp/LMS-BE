package com.task.author.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.never;
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

import com.task.author.exceptionhandling.AuthorNotFoundException;
import com.task.author.model.Author;
import com.task.author.repository.AuthorRepository;

public class AuthorServiceImplTest {
	
	@Mock
	private AuthorRepository authorRepository;
	
	@InjectMocks
	private AuthorServiceImpl authorServiceImpl;
	
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
	void testViewAllAuthors_Success() {
		List<Author> authors = Arrays.asList(author1, author2);
		when(authorRepository.findAll()).thenReturn(authors);
		List<Author> result = authorServiceImpl.viewAllAuthors(token);
		
		assertNotNull(result);assertEquals(2, result.size());
		assertEquals("author one", result.get(0).getAuthorName());
		assertEquals("author two", result.get(1).getAuthorName());
		
		verify(authorRepository).findAll();
	}
	
	@Test
	void testViewAuthorById_Success() {
		when(authorRepository.findById(authorId)).thenReturn(Optional.of(author1));
		Author result = authorServiceImpl.viewAuthorbyId(authorId, token);
		
		assertNotNull(result);
		assertEquals("author one",result.getAuthorName());
		
		verify(authorRepository).findById(authorId);
	}
	
	@Test
	void testViewAuthorById_NotFound() {
		when(authorRepository.findById(authorId)).thenReturn(Optional.empty());
		
		assertThrows(AuthorNotFoundException.class, ()->{
			authorServiceImpl.viewAuthorbyId(authorId, token);
		});
	}
	
	@Test
	void testViewAuthorByName_Success() {
		when(authorRepository.viewAuthorByName(authorName)).thenReturn(author1);
		Author result = authorServiceImpl.viewAuthorByName(authorName, token);
		
		assertNotNull(result);
		assertEquals("author one",result.getAuthorName());
		
		verify(authorRepository).viewAuthorByName(authorName);
	}
	
	@Test
	void testViewAuthorByName_NotFound() {
		when(authorRepository.viewAuthorByName(authorName)).thenReturn(null);
		
		assertThrows(AuthorNotFoundException.class, ()->{
			authorServiceImpl.viewAuthorByName(authorName, token);
		});
	}
	
	@Test
	void testAddAuthor_Success() {
		when(authorRepository.save(author1)).thenReturn(author1);
		Author result = authorServiceImpl.addAuthor(author1, token);
		
		assertNotNull(result);
		assertEquals("author one",result.getAuthorName());
		
		verify(authorRepository).save(author1);
	}
	
	@Test
	void testEditAuthor_Success() {
		Author updatedAuthor = new Author();
		updatedAuthor.setAuthorName("updatedAuthorName");
		
		when(authorRepository.findById(authorId)).thenReturn(Optional.of(author1));
		Author result = authorServiceImpl.editAuthor(authorId, updatedAuthor, token);
		
		assertNotNull(result);
		assertEquals("updatedAuthorName",result.getAuthorName());
		
		verify(authorRepository).save(author1);
	}
	
	@Test
	void testEditAuthor_NotFound() {
		Author updatedAuthor = new Author();
		updatedAuthor.setAuthorName("updatedAuthorName");
		
		when(authorRepository.findById(authorId)).thenReturn(Optional.empty());	
		
		assertThrows(AuthorNotFoundException.class, ()->{
			authorServiceImpl.editAuthor(authorId, updatedAuthor, token);
		});	
	}
	
	@Test
	void testDeleteAuthor_Success() {
		when(authorRepository.findById(authorId)).thenReturn(Optional.of(author1));	
		String result = authorServiceImpl.deleteAuthor(authorId, token);
		
		verify(authorRepository).findById(authorId);
		verify(authorRepository).deleteById(authorId);
		assertEquals("Author deleted", result);	
	}
	
	@Test
	void testDeleteAuthor_NotFound() {
		Exception exception = assertThrows(AuthorNotFoundException.class, ()->{
			authorServiceImpl.deleteAuthor(authorId, token);
		});
		
		verify(authorRepository).findById(authorId);
		verify(authorRepository, never()).deleteById(authorId);
		assertEquals("Author not found", exception.getMessage());
	}
}
