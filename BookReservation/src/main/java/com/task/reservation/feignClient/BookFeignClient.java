package com.task.reservation.feignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import com.task.book.model.Book;


@FeignClient(name = "Book", url = "http://localhost:8081/book")
public interface BookFeignClient {
	
	@PutMapping("/reserve_book/{book_id}")
	public Book reserveBook(@PathVariable int book_id, @RequestHeader("Authorization") final String token);
	
	@GetMapping("/{book_id}")
	public Book viewBookById(@PathVariable int book_id, @RequestHeader("Authorization") final String token);
	
	@PutMapping("/return_book/{book_id}")
	public void returnBook(@PathVariable int book_id, @RequestHeader("Authorization") final String token);
	
}
