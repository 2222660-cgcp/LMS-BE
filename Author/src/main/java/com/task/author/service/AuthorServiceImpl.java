package com.task.author.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.task.author.exceptionhandling.AuthorNotFoundException;
import com.task.author.model.Author;
import com.task.author.repository.AuthorRepository;

//-----------------------ANAGHA.S.R------------------------

@Service
public class AuthorServiceImpl implements AuthorService{
	
	@Autowired
	private AuthorRepository authorRepository;
	
	@Override
	public List<Author> viewAllAuthors(String token) {
		return authorRepository.findAll();
	}

	@Override
	public Author viewAuthorbyId(int author_id, String token) {
		return authorRepository.findById(author_id).orElseThrow(()-> new AuthorNotFoundException("Author not found"));
	}
	
	@Override
	public Author viewAuthorByName(String author_name, String token) {
		Author author = authorRepository.viewAuthorByName(author_name);
		if(author == null ) {
			throw new AuthorNotFoundException("Author not found");
		}
		return author;
	}

	@Override
	public Author addAuthor(Author author, String token) {
		return authorRepository.save(author);
	}

	@Override
	public Author editAuthor(int authorId, Author author, String token) {
		Optional<Author> optionalAuthor = authorRepository.findById(authorId);
		if(!optionalAuthor.isPresent()) {
			throw new AuthorNotFoundException("Author not found with id : "+authorId);
		}
		
		Author authorObject = optionalAuthor.get();
		if(author.getAuthorName() != null) {
			authorObject.setAuthorName(author.getAuthorName());
		}
		
		authorRepository.save(authorObject);
		return authorObject;
	}

	@Override
	public String deleteAuthor(int author_id, String token) {
		Optional<Author> optionalAuthor = authorRepository.findById(author_id);
		if(!optionalAuthor.isPresent()) {
			throw new AuthorNotFoundException("Author not found");
		}
		
		authorRepository.deleteById(author_id);
		return "Author deleted";
	}
}
