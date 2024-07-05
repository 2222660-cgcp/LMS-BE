package com.task.category.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.task.category.exceptionhandling.CategoryNotFoundException;
import com.task.category.model.Category;
import com.task.category.repository.CategoryRepository;

//-----------------------ANAGHA.S.R------------------------------------

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public List<Category> viewAllCategory(String token) {
		return categoryRepository.findAll();
	}
	
	@Override
	public Category viewCategorybyId(int category_id, String token) {
		return categoryRepository.findById(category_id).orElseThrow(()-> new CategoryNotFoundException("Category not found"));
	}
	
	@Override
	public Category addCategory(Category category, String token) {
		return categoryRepository.save(category);
	}

	@Override
	public Category editCategory(int category_id, Category category, String token) {
		Optional<Category> optionalCategory = categoryRepository.findById(category_id);
		if(!optionalCategory.isPresent()) {
			throw new CategoryNotFoundException("Category not found");
		}
		
		Category categoryObject = optionalCategory.get();
		if(category.getCategoryName() != null) {
			categoryObject.setCategoryName(category.getCategoryName());
		}
		
		categoryRepository.save(categoryObject);
		return categoryObject;
	}

	@Override
	public String deleteCategory(int category_id, String token) {
		Optional<Category> optionalCategory = categoryRepository.findById(category_id);
		if(!optionalCategory.isPresent()) {
			throw new CategoryNotFoundException("Category not found");
		}
		
		categoryRepository.deleteById(category_id);
		return "Category deleted";
	}

	@Override
	public Category viewCategoryByName(String category_name, String token) {
		Category category = categoryRepository.viewCategoryByName(category_name);
		if(category==null) {
			throw new CategoryNotFoundException("Category not found");
		}
		return category;
	}

}
