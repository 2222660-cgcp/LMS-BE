package com.task.category.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.task.category.exceptionhandling.CategoryNotFoundException;
import com.task.category.model.Category;
import com.task.category.repository.CategoryRepository;


public class CategoryServiceImplTest {
	
	@Mock
	private CategoryRepository categoryRepository;
	
	@InjectMocks
	private CategoryServiceImpl categoryServiceImpl;
	
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
	void testViewAllCategory_Success() {
		List<Category> categories = Arrays.asList(category1, category2);
		when(categoryRepository.findAll()).thenReturn(categories);
		List<Category> result = categoryServiceImpl.viewAllCategory(token);
		
		assertNotNull(result);
		assertEquals(2, result.size());
		assertEquals("category one", result.get(0).getCategoryName());
		assertEquals("category two", result.get(1).getCategoryName());
		
		verify(categoryRepository).findAll();
	}
	
	@Test
	void testViewCategoryById_Success() {
		when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(category1));
		Category result = categoryServiceImpl.viewCategorybyId(categoryId, token);
		
		assertNotNull(result);
		assertEquals("category one",result.getCategoryName());
		
		verify(categoryRepository).findById(categoryId);
	}
	
	@Test
	void testViewCategoryById_NotFound() {
		when(categoryRepository.findById(categoryId)).thenReturn(Optional.empty());
		
		assertThrows(CategoryNotFoundException.class, ()->{
			categoryServiceImpl.viewCategorybyId(categoryId, token);
		});
	}
	
	@Test
	void testViewCategoryByName_Success() {
		when(categoryRepository.viewCategoryByName(categoryName)).thenReturn(category1);
		Category result = categoryServiceImpl.viewCategoryByName(categoryName, token);
		
		assertNotNull(result);
		assertEquals("category one",result.getCategoryName());
		
		verify(categoryRepository).viewCategoryByName(categoryName);
	}
	
	@Test
	void testViewCategoryByName_NotFound() {
		when(categoryRepository.viewCategoryByName(categoryName)).thenReturn(null);
		
		assertThrows(CategoryNotFoundException.class, ()->{
			categoryServiceImpl.viewCategoryByName(categoryName, token);
		});
	}
	
	@Test
	void testAddCategory_Success() {
		when(categoryRepository.save(category1)).thenReturn(category1);
		Category result = categoryServiceImpl.addCategory(category1, token);
		
		assertNotNull(result);
		assertEquals("category one",result.getCategoryName());
		
		verify(categoryRepository).save(category1);
	}
	
	@Test
	void testEditCategory_Success() {
		Category updatedCategory = new Category();
		updatedCategory.setCategoryName("updatedCategoryName");
		
		when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(category1));	
		Category result = categoryServiceImpl.editCategory(categoryId, updatedCategory, token);
		
		assertNotNull(result);
		assertEquals("updatedCategoryName",result.getCategoryName());
		
		verify(categoryRepository).save(category1);
	}
	
	@Test
	void testEditCategory_NotFound() {
		Category updatedCategory = new Category();
		updatedCategory.setCategoryName("updatedCategoryName");
		
		when(categoryRepository.findById(categoryId)).thenReturn(Optional.empty());	
		
		assertThrows(CategoryNotFoundException.class, ()->{
			categoryServiceImpl.editCategory(categoryId, updatedCategory, token);
		});	
	}
	
	@Test
	void testDeleteCategory_Success() {
		when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(category1));	
		String result = categoryServiceImpl.deleteCategory(categoryId, token);
		
		verify(categoryRepository).findById(categoryId);
		verify(categoryRepository).deleteById(categoryId);
		assertEquals("Category deleted", result);	
	}
	
	@Test
	void testDeleteCategory_NotFound() {
		Exception exception = assertThrows(CategoryNotFoundException.class, ()->{
			categoryServiceImpl.deleteCategory(categoryId, token);
		});
		
		verify(categoryRepository).findById(categoryId);
		verify(categoryRepository, never()).deleteById(categoryId);
		assertEquals("Category not found", exception.getMessage());
	}
}
