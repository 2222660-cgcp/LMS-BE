package com.task.author.service;

import java.util.List;

import com.task.author.model.Author;

//------------------ANAGHA.S.R--------------------

public interface AuthorService {
	
	Author addAuthor(Author author, String token);
	List<Author> viewAllAuthors(String token);
	Author viewAuthorbyId(int author_id, String token);
	Author editAuthor(int author_id, Author author, String token);
	String deleteAuthor(int author_id, String token);
	Author viewAuthorByName(String author_name, String token);

}
