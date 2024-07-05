package com.task.author.controller;

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

import com.task.author.model.Author;
import com.task.author.service.AuthorService;

//-----------------------ANAGHA.S.R------------------------------------

@CrossOrigin
@RestController
@RequestMapping("/author")
public class AuthorController {
	
	@Autowired
	private AuthorService authorService;
	
	@GetMapping
	public ResponseEntity<List<Author>> viewAllAuthors(@RequestHeader("Authorization") final String token) {
		List<Author> authors = authorService.viewAllAuthors(token);
		return new ResponseEntity<List<Author>> (authors, HttpStatus.OK);
	}
	
	@GetMapping("/{author_id}") 
	public ResponseEntity<Author> viewAuthorbyId(@PathVariable int author_id, @RequestHeader("Authorization") final String token) {
		Author author = authorService.viewAuthorbyId(author_id, token);
		return new ResponseEntity<Author> (author, HttpStatus.OK);
	}
	
	@GetMapping("/view-author/{author_name}") 
	public ResponseEntity<Author> viewAuthorByName(@PathVariable String author_name, @RequestHeader("Authorization") final String token) {
		Author author = authorService.viewAuthorByName(author_name, token);
		return new ResponseEntity<Author> (author, HttpStatus.OK);
	}

	@PostMapping()
	public ResponseEntity<Author> addAuthor(@RequestBody Author author, @RequestHeader("Authorization") final String token) {
		Author authorObject = authorService.addAuthor(author, token);
		return new ResponseEntity<Author> (authorObject, HttpStatus.CREATED);
	}
	
	@PutMapping("/{author_id}")
	public ResponseEntity<Author> editAuthor(@PathVariable int author_id, @RequestBody Author author, @RequestHeader("Authorization") final String token) {
		Author updatedAuthor = authorService.editAuthor(author_id, author, token);
		return new ResponseEntity<Author> (updatedAuthor,HttpStatus.OK);
	}
	
	@DeleteMapping("/{author_id}")
	public ResponseEntity<String> deleteAuthor(@PathVariable int author_id, @RequestHeader("Authorization") final String token) {
		String response = authorService.deleteAuthor(author_id, token);
		return new ResponseEntity<String> (response,HttpStatus.OK);
	}
}
