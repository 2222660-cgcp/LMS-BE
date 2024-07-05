package com.task.book.feignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import com.task.category.model.Category;

//-----------------------IBRAHIM BADSHAH---------------------------------

@FeignClient(name = "Category", url = "http://localhost:8082/category")
public interface CategoryFeignClient {

	@GetMapping("/{category_id}") 
	public Category viewCategorybyId(@PathVariable int category_id, @RequestHeader("Authorization") final String token);

}
