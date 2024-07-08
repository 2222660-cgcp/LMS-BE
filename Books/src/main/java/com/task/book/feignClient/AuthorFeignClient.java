package com.task.book.feignClient;

import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import com.task.author.model.Author;

//------------------ANAGHA.S.R--------------------------------

@FeignClient(name = "Author", url = "http://localhost:8083/author")
public interface AuthorFeignClient {
	
	@GetMapping("/{author_id}") 
	public Author viewAuthorbyId(@PathVariable int author_id, @RequestHeader("Authorization") final String token);

}
