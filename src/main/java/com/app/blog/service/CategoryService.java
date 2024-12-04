package com.app.blog.service;

import java.util.List;

import com.app.blog.models.CategoryDto;

public interface CategoryService {
	
	CategoryDto createCategory(CategoryDto category);
	
	CategoryDto updateCategory(CategoryDto category, int categoryId);
	
	void deleteCategory(int categoryId);
	
	CategoryDto getCategory(int categoryId);
	
	List<CategoryDto> getAllCategories();

}
