package com.app.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
import com.app.blog.models.PostDto;
import com.app.blog.service.PostService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/posts")
public class PostController {

	@Autowired
	PostService postService;

	@PostMapping("/user/{userId}/category/{categoryId}/create")
	public ResponseEntity<PostDto> createPost(@RequestBody @Valid PostDto postDto, @PathVariable Integer categoryId,
			@PathVariable Integer userId) {
		PostDto createdPost = postService.createPost(postDto, categoryId, userId);
		return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
	}

	@PutMapping("/update/{postId}")
	public ResponseEntity<PostDto> updatePost(@RequestBody @Valid PostDto postDto, @PathVariable Integer postId) {
		PostDto updatedPost = postService.updatePost(postDto, postId);
		return new ResponseEntity<>(updatedPost, HttpStatus.CREATED);
	}

	@DeleteMapping("/delete/{postId}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<BlogApiResponse> deletePost(@PathVariable Integer postId) {
		postService.deletePost(postId);
		return new ResponseEntity<>(new BlogApiResponse("Post Deleted Successfully", true), HttpStatus.OK);
	}

	@GetMapping("/")
	public ResponseEntity<List<PostDto>> getAllPosts(
			@RequestParam(required = false, defaultValue = "0") Integer pageNum,
			@RequestParam(required = false, defaultValue = "5") Integer pageSize) {
		List<PostDto> allPosts = postService.getAllPosts(pageNum, pageSize);
		return new ResponseEntity<>(allPosts, HttpStatus.CREATED);
	}

	@GetMapping("/{postId}")
	public ResponseEntity<PostDto> getPost(@PathVariable Integer postId,
			@RequestParam(required = false, defaultValue = "0") Integer pageNum,
			@RequestParam(required = false, defaultValue = "5") Integer pageSize) {
		PostDto post = postService.getPost(postId);
		return new ResponseEntity<>(post, HttpStatus.CREATED);
	}

	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<List<PostDto>> getAllPostsByCategory(@PathVariable Integer categoryId,
			@RequestParam(required = false, defaultValue = "0") Integer pageNum,
			@RequestParam(required = false, defaultValue = "5") Integer pageSize) {
		List<PostDto> categoryPosts = postService.getAllPostsByCategory(categoryId, pageNum, pageSize);
		return new ResponseEntity<>(categoryPosts, HttpStatus.CREATED);
	}

	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDto>> getAllPostsByUser(@PathVariable Integer userId,
			@RequestParam(required = false, defaultValue = "0") Integer pageNum,
			@RequestParam(required = false, defaultValue = "5") Integer pageSize) {
		List<PostDto> categoryPosts = postService.getAllPostsByUser(userId, pageNum, pageSize);
		return new ResponseEntity<>(categoryPosts, HttpStatus.CREATED);
	}
	
	@GetMapping("/search/{key}")
	public ResponseEntity<List<PostDto>> searchPosts(@PathVariable String key) {
		List<PostDto> categoryPosts = postService.searchPosts(key);
		return new ResponseEntity<>(categoryPosts, HttpStatus.CREATED);
	}

//	@GetMapping("/user/{userId}/category/{categoryId}/posts")
//	public ResponseEntity<List<PostDto>> getAllPostsByUserCategory(@PathVariable Integer userId,
//			@PathVariable Integer categoryId, @RequestParam Integer pageNum, @RequestParam Integer pageSize) {
//		List<PostDto> userCategoryPosts = postService.getAllPostsByUserCategory(userId, categoryId, pageNum, pageSize);
//		return new ResponseEntity<>(userCategoryPosts, HttpStatus.CREATED);
//	}
}
