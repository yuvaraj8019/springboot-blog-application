package com.app.blog.serviceImpl;

import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.blog.entities.CommentEntity;
import com.app.blog.entities.PostEntity;
import com.app.blog.exception.ResourceNotFoundException;
import com.app.blog.models.CommentDto;
import com.app.blog.repositories.CommentRepo;
import com.app.blog.repositories.PostRepo;
import com.app.blog.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {
	
	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private CommentRepo commentRepo;

	@Override
	public CommentDto createComment(CommentDto commentDto, Integer postId) {
		
		PostEntity post = postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("PostId not found to add Comment."));
		CommentEntity comment = modelMapper.map(commentDto, CommentEntity.class);
		comment.setPost(post);
		CommentEntity savedComment = commentRepo.save(comment);
		
		return modelMapper.map(savedComment, CommentDto.class);
	}

	@Override
	public void deleteComment(Integer commentId) {
		
		CommentEntity comment = commentRepo.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("CommentId not found to delete."));
		commentRepo.delete(comment);

	}

	@Override
	public Set<CommentDto> getAllCommentsForPost(Integer postId) {
		
		PostEntity post = postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("PostId not found to fetch Comments."));
		Set<CommentEntity> comments = commentRepo.findByPost(post);
		Set<CommentDto> commentsSet = comments.stream().map(comment -> modelMapper.map(comment, CommentDto.class)).collect(Collectors.toSet());
		
		return commentsSet;
	}

}
