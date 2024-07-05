package com.task.author.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
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
import com.task.author.model.Author;
import com.task.author.service.AuthorService;

//------------------ANAGHA.S.R------------------------------------------

@WebMvcTest(AuthorController.class)
class AuthorControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private AuthorService authorService;
	
	private List<Author> authors;
	
	private Author author;
	
	private ObjectMapper objectMapper = new ObjectMapper();
	
	@BeforeEach
	public void setUp() {
		Author a1 = new Author(1,"author1");
		Author a2 = new Author(2,"author2");
		authors = Arrays.asList(a1,a2);
		author = a1;
		
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void viewAllAuthorsTest() throws Exception {
		when(authorService.viewAllAuthors(anyString())).thenReturn(authors);
		
		mockMvc.perform(get("/author").header("Authorization","Bearer token"))
			   .andExpect(status().isOk())
			   .andExpect(content().contentType(MediaType.APPLICATION_JSON))
			   .andExpect(jsonPath("$[0].authorId").value(1))
			   .andExpect(jsonPath("$[0].authorName").value("author1"))
			   .andExpect(jsonPath("$[1].authorId").value(2))
			   .andExpect(jsonPath("$[1].authorName").value("author2"));
		
		verify(authorService, times(1)).viewAllAuthors(anyString());
	}
	
	@Test
	public void viewAuthorybyIdTest() throws Exception {
		when(authorService.viewAuthorbyId(anyInt(),anyString())).thenReturn(author);
		
		mockMvc.perform(get("/author/{author_id}","1").header("Authorization","Bearer token"))
			   .andExpect(status().isOk())
			   .andExpect(content().contentType(MediaType.APPLICATION_JSON))
			   .andExpect(jsonPath("$.authorId").value(1))
			   .andExpect(jsonPath("$.authorName").value("author1"));
		
		verify(authorService, times(1)).viewAuthorbyId(anyInt(),anyString());
	}
	
	@Test
	public void viewAuthorByNameTest() throws Exception {
		when(authorService.viewAuthorByName(anyString(), anyString())).thenReturn(author);
		
		mockMvc.perform(get("/author/view-author/{author_name}","author1").header("Authorization","Bearer token"))
			   .andExpect(status().isOk())
			   .andExpect(content().contentType(MediaType.APPLICATION_JSON))
			   .andExpect(jsonPath("$.authorId").value(1))
			   .andExpect(jsonPath("$.authorName").value("author1"));
		
		verify(authorService, times(1)).viewAuthorByName(anyString(), anyString());
	}
	
	@Test
	public void addAuthorTest() throws Exception {
		when(authorService.addAuthor(any(Author.class), anyString())).thenReturn(author);
		
		mockMvc.perform(post("/author").header("Authorization","Bearer token")
										 .contentType(MediaType.APPLICATION_JSON)
										 .content(objectMapper.writeValueAsString(author)))
			   .andExpect(status().isCreated())
			   .andExpect(content().contentType(MediaType.APPLICATION_JSON))
			   .andExpect(jsonPath("$.authorId").value(1))
			   .andExpect(jsonPath("$.authorName").value("author1"));
		
		verify(authorService, times(1)).addAuthor(any(Author.class), anyString());
	}
	
	@Test
	public void editAuthorTest() throws Exception {
		when(authorService.editAuthor(anyInt(), any(Author.class), anyString())).thenReturn(author);
		
		mockMvc.perform(put("/author/{author_id}","1").header("Authorization","Bearer token")
														  .contentType(MediaType.APPLICATION_JSON)
														  .content(objectMapper.writeValueAsString(author)))
		       .andExpect(status().isOk())
		       .andExpect(content().contentType(MediaType.APPLICATION_JSON))
		       .andExpect(jsonPath("$.authorId").value(1))
			   .andExpect(jsonPath("$.authorName").value("author1"));
		
		verify(authorService, times(1)).editAuthor(anyInt(), any(Author.class), anyString());
		
	}
	
	@Test
	public void deleteAuthorTest() throws Exception {
		when(authorService.deleteAuthor(anyInt(), anyString())).thenReturn("Author deleted");
		
		mockMvc.perform(delete("/author/{author_id}","1").header("Authorization","Bearer token")
														     .contentType(MediaType.APPLICATION_JSON)
														     .content(objectMapper.writeValueAsString(author)))
		       .andExpect(status().isOk())
		       .andExpect(content().string("Author deleted"));
		
		verify(authorService, times(1)).deleteAuthor(anyInt(),anyString());
	}
}
