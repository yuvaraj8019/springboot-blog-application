package com.app.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.blog.models.BlogApiResponse;
import com.app.blog.models.CategoryDto;
import com.app.blog.service.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@PostMapping("/create")
	public ResponseEntity<CategoryDto> createCategory(@RequestBody @Valid CategoryDto category) {
		CategoryDto createdCategory = categoryService.createCategory(category);
		return new ResponseEntity<>(createdCategory, HttpStatus.CREATED);
	}

	@PutMapping("/update")
	public ResponseEntity<CategoryDto> updateCategory(@RequestBody @Valid CategoryDto category, @RequestParam Integer category_id) {
		CategoryDto updatedCategory = categoryService.updateCategory(category, category_id);
		return new ResponseEntity<>(updatedCategory, HttpStatus.OK);
	}

	@GetMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> getCategory(@PathVariable("categoryId") Integer category_id) {
		CategoryDto Category = categoryService.getCategory(category_id);
		return new ResponseEntity<>(Category, HttpStatus.OK);
	}

	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getAllCategorys() {
		List<CategoryDto> Categories = categoryService.getAllCategories();
		return new ResponseEntity<>(Categories, HttpStatus.OK);
	}

	@DeleteMapping("/{categoryId}")
	public ResponseEntity<BlogApiResponse> deleteCategory(@PathVariable("categoryId") Integer category_id) {
		categoryService.deleteCategory(category_id);
		return new ResponseEntity<>(new BlogApiResponse("Category Deleted Successfully", true), HttpStatus.OK);
	}
}
