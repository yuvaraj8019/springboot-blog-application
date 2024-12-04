package com.app.blog.service;

import java.util.Set;

import com.app.blog.models.CommentDto;

public interface CommentService {
	
	CommentDto createComment(CommentDto commentDto, Integer postId);
	
	void deleteComment(Integer commentId);
	
	Set<CommentDto> getAllCommentsForPost(Integer postId);

}
