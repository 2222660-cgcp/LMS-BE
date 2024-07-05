package com.task.category.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.task.category.exceptionhandling.CategoryNotFoundException;
import com.task.category.model.Category;

//-----------------------ANAGHA.S.R------------------------------------

class CategoryServiceTest {

	@Mock
	private CategoryService categoryService;
	
	private Category category1;
	private Category category2;
	int categoryId = 1;
	String categoryName = "category one";
	private String token = "sampleToken";
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		category1 = new Category();
		category1.setCategoryName("category one");
		category2 = new Category();
		category2.setCategoryName("category two");
	}
	
	@Test
	void testViewAllCategory() {
		List<Category> categories = Arrays.asList(category1, category2);
		when(categoryService.viewAllCategory(token)).thenReturn(categories);
		List<Category> result = categoryService.viewAllCategory(token);
		
		assertNotNull(result);
		assertEquals("category one", result.get(0).getCategoryName());
		assertEquals("category two", result.get(1).getCategoryName());
		
		verify(categoryService).viewAllCategory(token);
	}

	@Test
	void testViewCategoryById_Sucess() {
		when(categoryService.viewCategorybyId(categoryId, token)).thenReturn(category1);
		Category result = categoryService.viewCategorybyId(categoryId, token);
		
		assertNotNull(result);
		assertEquals("category one", category1.getCategoryName());
		
		verify(categoryService).viewCategorybyId(categoryId, token);
	}
	
	@Test
	void testViewCategoryById_NotFound() {
		when(categoryService.viewCategorybyId(categoryId, token)).thenThrow(new CategoryNotFoundException("Category not found"));
		
		assertThrows(CategoryNotFoundException.class, ()->{
			categoryService.viewCategorybyId(categoryId, token);
		});
	}
	
	@Test
	void testViewCategoryByName_Success() {
		when(categoryService.viewCategoryByName(categoryName, token)).thenReturn(category1);
		Category result = categoryService.viewCategoryByName(categoryName, token);
		
		assertNotNull(result);
		assertEquals("category one", result.getCategoryName());
		
		verify(categoryService).viewCategoryByName(categoryName, token);
	}
	
	@Test
	void testViewCategoryByName_NotFound() {
		when(categoryService.viewCategoryByName(categoryName, token)).thenThrow(new CategoryNotFoundException("Category not found"));
		
		assertThrows(CategoryNotFoundException.class, ()->{
			categoryService.viewCategoryByName(categoryName, token);
		});
	}
	
	@Test 
	void testAddCategory() {
		when(categoryService.addCategory(category1, token)).thenReturn(category1);
		Category result = categoryService.addCategory(category1, token);
		
		assertNotNull(result);
		assertEquals("category one", category1.getCategoryName());
		
		verify(categoryService).addCategory(category1, token);
	}
	
	@Test
	void testEditCategory_Success() {
		Category updateCategory = new Category();
		updateCategory.setCategoryName("updated category");
		when(categoryService.editCategory(categoryId, updateCategory, token)).thenReturn(updateCategory);
		Category result = categoryService.editCategory(categoryId, updateCategory, token);
		
		assertNotNull(updateCategory);
		assertEquals("updated category",result.getCategoryName());
		
		verify(categoryService).editCategory(categoryId, updateCategory, token);
	}
	
	@Test
	void testEditCategory_NotFound() {
		Category updateCategory = new Category();
		updateCategory.setCategoryName("updated author");
		when(categoryService.editCategory(categoryId, updateCategory, token)).thenThrow(new CategoryNotFoundException("Category not found"));
		
		assertThrows(CategoryNotFoundException.class, ()-> {
			categoryService.editCategory(categoryId, updateCategory, token);
		});
	}
	
	@Test
	void testDeleteCategory_Success() {
		when(categoryService.deleteCategory(categoryId, token)).thenReturn("Category deleted");
		String result = categoryService.deleteCategory(categoryId, token);
		
		assertEquals("Category deleted", result);
		
		verify(categoryService).deleteCategory(categoryId, token);
	}
	
	@Test
	void testDeleteCategory_NotFound() {
		when(categoryService.deleteCategory(categoryId, token)).thenThrow(new CategoryNotFoundException("Category not found"));
		
		assertThrows(CategoryNotFoundException.class, ()-> {
			categoryService.deleteCategory(categoryId,token);
		});
	}
}
