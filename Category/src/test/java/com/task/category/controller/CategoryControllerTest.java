package com.task.category.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.task.category.model.Category;
import com.task.category.service.CategoryService;

@WebMvcTest(CategoryController.class)
class CategoryControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private CategoryService categoryService;
	
	private List<Category> categories;
	
	private Category category;
	
	private ObjectMapper objectMapper = new ObjectMapper();
	
	@BeforeEach
	public void setUp() {
		Category c1 = new Category(1,"category1");
		Category c2 = new Category(2,"category2");
		categories = Arrays.asList(c1,c2);
		category = c1;
		
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void viewAllCategoryTest() throws Exception {
		when(categoryService.viewAllCategory(anyString())).thenReturn(categories);
		
		mockMvc.perform(get("/category").header("Authorization","Bearer token"))
			   .andExpect(status().isOk())
			   .andExpect(content().contentType(MediaType.APPLICATION_JSON))
			   .andExpect(jsonPath("$[0].categoryId").value(1))
			   .andExpect(jsonPath("$[0].categoryName").value("category1"))
			   .andExpect(jsonPath("$[1].categoryId").value(2))
			   .andExpect(jsonPath("$[1].categoryName").value("category2"));
		
		verify(categoryService, times(1)).viewAllCategory(anyString());
	}
	
	@Test
	public void viewCategorybyIdTest() throws Exception {
		when(categoryService.viewCategorybyId(anyInt(),anyString())).thenReturn(category);
		
		mockMvc.perform(get("/category/{category_id}","1").header("Authorization","Bearer token"))
			   .andExpect(status().isOk())
			   .andExpect(content().contentType(MediaType.APPLICATION_JSON))
			   .andExpect(jsonPath("$.categoryId").value(1))
			   .andExpect(jsonPath("$.categoryName").value("category1"));
		
		verify(categoryService, times(1)).viewCategorybyId(anyInt(),anyString());
	}
	
	@Test
	public void viewCategoryByNameTest() throws Exception {
		when(categoryService.viewCategoryByName(anyString(), anyString())).thenReturn(category);
		
		mockMvc.perform(get("/category/view-category/{category_name}","category1").header("Authorization","Bearer token"))
			   .andExpect(status().isOk())
			   .andExpect(content().contentType(MediaType.APPLICATION_JSON))
			   .andExpect(jsonPath("$.categoryId").value(1))
			   .andExpect(jsonPath("$.categoryName").value("category1"));
		
		verify(categoryService, times(1)).viewCategoryByName(anyString(), anyString());
	}

	@Test
	public void addCategoryTest() throws Exception {
		when(categoryService.addCategory(any(Category.class), anyString())).thenReturn(category);
		
		mockMvc.perform(post("/category").header("Authorization","Bearer token")
										 .contentType(MediaType.APPLICATION_JSON)
										 .content(objectMapper.writeValueAsString(category)))
			   .andExpect(status().isCreated())
			   .andExpect(content().contentType(MediaType.APPLICATION_JSON))
			   .andExpect(jsonPath("$.categoryId").value(1))
			   .andExpect(jsonPath("$.categoryName").value("category1"));
		
		verify(categoryService, times(1)).addCategory(any(Category.class), anyString());
	}
	
	@Test
	public void editCategoryTest() throws Exception {
		when(categoryService.editCategory(anyInt(), any(Category.class), anyString())).thenReturn(category);
		
		mockMvc.perform(put("/category/{category_id}","1").header("Authorization","Bearer token")
														  .contentType(MediaType.APPLICATION_JSON)
														  .content(objectMapper.writeValueAsString(category)))
		       .andExpect(status().isOk())
		       .andExpect(content().contentType(MediaType.APPLICATION_JSON))
		       .andExpect(jsonPath("$.categoryId").value(1))
			   .andExpect(jsonPath("$.categoryName").value("category1"));
		
		verify(categoryService, times(1)).editCategory(anyInt(), any(Category.class), anyString());	
	}
	
	@Test
	public void deleteCategoryTest() throws Exception {
		when(categoryService.deleteCategory(anyInt(), anyString())).thenReturn("Category deleted");
		
		mockMvc.perform(delete("/category/{category_id}","1").header("Authorization","Bearer token")
														     .contentType(MediaType.APPLICATION_JSON)
														     .content(objectMapper.writeValueAsString(category)))
		       .andExpect(status().isOk())
		       .andExpect(content().string("Category deleted"));
		
		verify(categoryService, times(1)).deleteCategory(anyInt(),anyString());
	}
}
