package com.task.book.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.task.book.model.Book;

//------------------------ANAGHA.S.R---------------------------------------

@Repository
public interface BookRepository extends JpaRepository<Book, Integer>{

	@Query("select b from Book b where b.bookName = :bookName")
	Book viewBookByName(@Param("bookName") String bookName);
	
//	---------------------IBRAHIM BADSHAH----------------------------------
	
	@Query("SELECT b FROM Book b WHERE b.bookName LIKE %:name%")
    List<Book> findByBookNameContaining(@Param("name") String name);

}