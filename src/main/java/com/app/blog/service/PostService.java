package com.app.blog.service;

import java.util.List;

import com.app.blog.models.PostDto;

public interface PostService {
	
	PostDto createPost(PostDto post, Integer categoryId, Integer userId);
	
	PostDto updatePost(PostDto post, Integer postId);
	
	void deletePost(Integer postId);
	
	PostDto getPost(Integer postId);
	
	List<PostDto> getAllPosts(Integer pageNum, Integer pageSize);
	
	List<PostDto> getAllPostsByCategory(Integer categoryId, Integer pageNum, Integer pageSize);
	
	List<PostDto> getAllPostsByUser(Integer userId, Integer pageNum, Integer pageSize);
	
//	List<PostDto> getAllPostsByUserCategory(Integer userId, Integer categoryId, Integer pageNum, Integer pageSize);
	
	List<PostDto> searchPosts(String searchKey);

}
