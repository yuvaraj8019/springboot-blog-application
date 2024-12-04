package com.app.blog.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.blog.entities.CategoryEntity;
import com.app.blog.exception.ResourceNotFoundException;
import com.app.blog.models.CategoryDto;
import com.app.blog.repositories.CategoryRepo;
import com.app.blog.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private CategoryRepo categoryRepo;

	@Override
	public CategoryDto createCategory(CategoryDto category) {
		CategoryEntity categoryEntity = modelMapper.map(category, CategoryEntity.class);
		CategoryEntity createdCategory = categoryRepo.save(categoryEntity);
		return modelMapper.map(createdCategory, CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto category, int categoryId) {
		
		CategoryEntity existingCategory = categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category Id is not found to update"));
		existingCategory.setCategoryTitle(category.getCategoryTitle());
		existingCategory.setCategoryDescription(category.getCategoryDescription());
		CategoryEntity updatedCategory = categoryRepo.save(existingCategory);
		return modelMapper.map(updatedCategory, CategoryDto.class);
	}

	@Override
	public void deleteCategory(int categoryId) {
		CategoryEntity existingCategory = categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category Id is not found to delete"));
		categoryRepo.delete(existingCategory);
	}

	@Override
	public CategoryDto getCategory(int categoryId) {
		CategoryEntity existingCategory = categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category Id is not found to fetch"));
		return modelMapper.map(existingCategory, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getAllCategories() {
		List<CategoryEntity> categoriesEntity = categoryRepo.findAll();
		List<CategoryDto> categories = categoriesEntity.stream().map(cat->modelMapper.map(cat, CategoryDto.class)).collect(Collectors.toList());
		return categories;
	}

}
