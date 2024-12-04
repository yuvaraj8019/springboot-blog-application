package com.app.blog.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.blog.entities.UserEntity;

public interface UserRepo extends JpaRepository<UserEntity, Integer> {
	
	Optional<UserEntity> findByEmail(String email);

}
