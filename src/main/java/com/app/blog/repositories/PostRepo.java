package com.app.blog.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.blog.entities.CategoryEntity;
import com.app.blog.entities.PostEntity;
import com.app.blog.entities.UserEntity;

public interface PostRepo extends JpaRepository<PostEntity, Integer> {
	
	List<PostEntity> findByUser(UserEntity user);
	List<PostEntity> findByCategory(CategoryEntity category);
	//List<PostEntity> findByUserCategory(UserEntity user, CategoryEntity category);
	Page<PostEntity> findByUser(Pageable p,UserEntity user);
	Page<PostEntity> findByCategory(Pageable p,CategoryEntity category);
	//Page<PostEntity> findByUserCategory(Pageable p,UserEntity user, CategoryEntity category);
	
	Page<PostEntity> findByTitleContainingIgnoreCase(Pageable p, String keyword);
	
	Page<PostEntity> findByTitle(Pageable p, String keyword);
	
	
	@Query("SELECT p FROM PostEntity p WHERE p.title LIKE %:keyword%")
	List<PostEntity> searchByTitle(@Param("keyword") String keyword);
	
}
