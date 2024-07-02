package com.task.category.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.task.category.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer>{
	
	@Query("select c from Category c where c.categoryName = :categoryName")
	Category viewCategoryByName(@Param("categoryName") String categoryName);

}
