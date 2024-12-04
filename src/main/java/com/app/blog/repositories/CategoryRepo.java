package com.app.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.blog.entities.CategoryEntity;

public interface CategoryRepo extends JpaRepository<CategoryEntity, Integer>{

}
