package com.app.blog.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.blog.models.BlogApiResponse;
import com.app.blog.models.CommentDto;
import com.app.blog.service.CommentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/comment")
public class CommentController {
	
	@Autowired
	private CommentService commentService;

	@PostMapping("/{postId}/create")
	public ResponseEntity<CommentDto> createComment(@RequestBody @Valid CommentDto commentDto, @PathVariable Integer postId) {
		CommentDto createdComment = commentService.createComment(commentDto, postId);
		return new ResponseEntity<>(createdComment, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{commentId}")
	public ResponseEntity<BlogApiResponse> deleteComment(@PathVariable Integer commentId) {
		commentService.deleteComment(commentId);
		return new ResponseEntity<>(new BlogApiResponse("Comment Deleted Successfully", true), HttpStatus.OK);
	}
	
	@GetMapping("/{postId}")
	public ResponseEntity<Set<CommentDto>> getAllCommentsForPost(@PathVariable Integer postId) {
		Set<CommentDto> comments = commentService.getAllCommentsForPost(postId);
		return new ResponseEntity<>(comments, HttpStatus.OK);
	}
}
