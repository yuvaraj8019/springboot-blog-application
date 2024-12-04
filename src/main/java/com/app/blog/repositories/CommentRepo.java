package com.app.blog.repositories;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.blog.entities.CommentEntity;
import com.app.blog.entities.PostEntity;

public interface CommentRepo extends JpaRepository<CommentEntity, Integer> {

	Set<CommentEntity> findByPost(PostEntity post);
}
