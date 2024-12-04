package com.app.blog.serviceImpl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.app.blog.entities.CategoryEntity;
import com.app.blog.entities.PostEntity;
import com.app.blog.entities.UserEntity;
import com.app.blog.exception.ResourceNotFoundException;
import com.app.blog.models.PostDto;
import com.app.blog.repositories.CategoryRepo;
import com.app.blog.repositories.PostRepo;
import com.app.blog.repositories.UserRepo;
import com.app.blog.service.PostService;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	PostRepo postRepo;

	@Autowired
	CategoryRepo categoryRepo;

	@Autowired
	UserRepo userRepo;

	@Autowired
	ModelMapper modelMapper;

	@Override
	public PostDto createPost(PostDto post, Integer categoryId, Integer userId) {

		CategoryEntity category = categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category not found for Id to create Post"));
		UserEntity user = userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User not found for Id to create Post"));
		PostEntity postReq = modelMapper.map(post, PostEntity.class);
		postReq.setAddedDate(new Date());
		postReq.setCategory(category);
		postReq.setUser(user);
		PostEntity createdPost = postRepo.save(postReq);

		return modelMapper.map(createdPost, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto post, Integer postId) {

		PostEntity postEntity = postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("PostId not found to update."));
		postEntity.setContent(post.getContent());
		postEntity.setImageName(post.getImageName());
		postEntity.setTitle(post.getTitle());
		PostEntity updatedPost = postRepo.save(postEntity);

		return modelMapper.map(updatedPost, PostDto.class);
	}

	@Override
	public void deletePost(Integer postId) {

		PostEntity postEntity = postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("PostId not found to delete."));
		postRepo.delete(postEntity);

	}

	@Override
	public PostDto getPost(Integer postId) {

		PostEntity postEntity = postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("PostId not found to fetch."));
		return modelMapper.map(postEntity, PostDto.class);
	}

	@Override
	public List<PostDto> getAllPosts(Integer pageNum, Integer pageSize) {

		Pageable pageable = PageRequest.of(pageNum, pageSize);
		Page<PostEntity> postEntities = postRepo.findAll(pageable);
		List<PostDto> posts = postEntities.stream().map(post -> modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());

		return posts;
	}

	@Override
	public List<PostDto> getAllPostsByCategory(Integer categoryId, Integer pageNum, Integer pageSize) {

		CategoryEntity category = categoryRepo.findById(categoryId).orElseThrow(
				() -> new ResourceNotFoundException("No category found with id for requested category posts"));
		Pageable pageable = PageRequest.of(pageNum, pageSize);
		Page<PostEntity> postEntities = postRepo.findByCategory(pageable, category);
		List<PostDto> categoryPosts = postEntities.stream().map(post -> modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());

		return categoryPosts;
	}

	@Override
	public List<PostDto> getAllPostsByUser(Integer userId, Integer pageNum, Integer pageSize) {

		UserEntity user = userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("No user found with id for requested user posts"));
		Pageable pageable = PageRequest.of(pageNum, pageSize);
		Page<PostEntity> postEntities = postRepo.findByUser(pageable, user);
		List<PostDto> userPosts = postEntities.stream().map(post -> modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());

		return userPosts;
	}
	
//	@Override
//	public List<PostDto> getAllPostsByUserCategory(Integer userId, Integer categoryId, Integer pageNum,
//			Integer pageSize) {
//		
//		CategoryEntity category = categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category not found for Id to create Post"));
//		UserEntity user = userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User not found for Id to create Post"));
//		Pageable pageable = PageRequest.of(pageNum, pageSize);
//		Page<PostEntity> postEntities = postRepo.findByUserCategory(pageable, user, category);
//		List<PostDto> userCategoryPosts = postEntities.stream().map(post -> modelMapper.map(post, PostDto.class))
//				.collect(Collectors.toList());
//		
//		return userCategoryPosts;
//	}

	@Override
	public List<PostDto> searchPosts(String searchKey) {
		// TODO Auto-generated method stub
		Pageable pageable = PageRequest.of(0, 100);
		Page<PostEntity> searchedEntities = postRepo.findByTitleContainingIgnoreCase(pageable, searchKey);  //postRepo.findByTitle(pageable, searchKey);
		List<PostDto> userPosts = searchedEntities.stream().map(post -> modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());
		
		List<PostEntity> resultEntities = postRepo.searchByTitle("%"+searchKey+"%");
		List<PostDto> searchedPosts = resultEntities.stream().map(post -> modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());
		return searchedPosts;
	}

}
