package com.task.category.service;

import java.util.List;

import com.task.category.model.Category;

//-----------------------ANAGHA.S.R------------------------------------

public interface CategoryService {
	
	List<Category> viewAllCategory(String token);
	Category viewCategorybyId(int category_id, String token);
	Category addCategory(Category category, String token);
	Category editCategory(int category_id, Category category, String token);
	String deleteCategory(int category_id, String token);
	Category viewCategoryByName(String category_name, String token);

}
