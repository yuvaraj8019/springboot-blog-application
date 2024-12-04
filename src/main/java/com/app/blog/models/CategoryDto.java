package com.app.blog.models;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDto {
	
	private int categoryId;
	
	@NotEmpty
	@Size(min=4, max=20, message="Title should be atleast 4 and maximum of 20 characters")
	private String categoryTitle;
	
	@NotEmpty
	@Size(min=10, message="Category should be atleast 10 characters")
	private String categoryDescription;

	
}
