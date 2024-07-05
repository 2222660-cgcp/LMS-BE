package com.task.author.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.task.author.model.Author;

//-------------------ANAGHA.S.R-----------------------

public interface AuthorRepository extends JpaRepository<Author, Integer>{
	
	@Query("select a from Author a where a.authorName = :authorName")
	Author viewAuthorByName(@Param("authorName") String authorName);

}
