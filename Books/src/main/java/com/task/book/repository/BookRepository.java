package com.task.book.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.task.book.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer>{

	@Query("select b from Book b where b.bookName = :bookName")
	Book viewBookByName(@Param("bookName") String bookName);

}