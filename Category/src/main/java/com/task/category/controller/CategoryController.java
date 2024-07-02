package com.task.category.controller;

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

import com.task.category.model.Category;
import com.task.category.service.CategoryService;

@CrossOrigin
@RestController
@RequestMapping("/category")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping
	public ResponseEntity<List<Category>> viewAllCategory(@RequestHeader("Authorization") final String token) {
		List<Category> categories = categoryService.viewAllCategory(token);
		return new ResponseEntity<List<Category>> (categories, HttpStatus.OK);
	}
	
	@GetMapping("/{category_id}") 
	public ResponseEntity<Category> viewCategorybyId(@PathVariable int category_id, @RequestHeader("Authorization") final String token) {
		Category category = categoryService.viewCategorybyId(category_id, token);
		return new ResponseEntity<Category> (category, HttpStatus.OK);
	}
	
	@GetMapping("/view-category/{category_name}") 
	public ResponseEntity<Category> viewCategoryByName(@PathVariable String category_name, @RequestHeader("Authorization") final String token) {
		Category category = categoryService.viewCategoryByName(category_name, token);
		return new ResponseEntity<Category> (category, HttpStatus.OK);
	}
		
	@PostMapping()
	public ResponseEntity<Category> addCategory(@RequestBody Category category, @RequestHeader("Authorization") final String token) {
		Category categoryObject = categoryService.addCategory(category, token);
		return new ResponseEntity<Category> (categoryObject, HttpStatus.CREATED);
	}
	
	@PutMapping("/{category_id}")
	public ResponseEntity<Category> editCategory(@PathVariable int category_id, @RequestBody Category category, @RequestHeader("Authorization") final String token) {
		Category updatedCategory = categoryService.editCategory(category_id, category, token);
		return new ResponseEntity<Category> (updatedCategory,HttpStatus.OK);
	}
	
	@DeleteMapping("/{category_id}")
	public ResponseEntity<String> deleteCategory(@PathVariable int category_id, @RequestHeader("Authorization") final String token) {
		String response = categoryService.deleteCategory(category_id, token);
		return new ResponseEntity<String> (response,HttpStatus.OK);
	}
}
