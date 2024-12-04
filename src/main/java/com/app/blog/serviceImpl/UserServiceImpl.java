package com.app.blog.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.blog.entities.Role;
import com.app.blog.entities.UserEntity;
import com.app.blog.exception.ResourceNotFoundException;
import com.app.blog.models.UserDto;
import com.app.blog.repositories.RoleRepo;
import com.app.blog.repositories.UserRepo;
import com.app.blog.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private RoleRepo roleRepo;
	
	@Override
	public UserDto registerUser(UserDto userDto, String userRoleString) {
		
		UserEntity userEntity = modelMapper.map(userDto, UserEntity.class); // dtoToEntity(userDto);
		userEntity.setPassword(passwordEncoder.encode(userDto.getPassword()));
		Role userRole = null;
		if (userRoleString.toLowerCase().equals("admin")) {
			userRole = roleRepo.findById(101).get();
		} else if(userRoleString.toLowerCase().equals("dev")) {
			userRole = roleRepo.findById(102).get();
		} else if(userRoleString.toLowerCase().equals("test")) {
			userRole = roleRepo.findById(103).get();
		}
		userEntity.getRoles().add(userRole);
		UserEntity savedUser = userRepo.save(userEntity);
		
		return modelMapper.map(savedUser, UserDto.class);
	}

	@Override
	public UserDto createUser(UserDto userDto) {
		UserEntity userEntity = modelMapper.map(userDto, UserEntity.class); //dtoToEntity(userDto);
		userEntity.setPassword(passwordEncoder.encode(userDto.getPassword()));
		UserEntity savedUser = userRepo.save(userEntity);
		return  modelMapper.map(savedUser, UserDto.class); //entityToDto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, int userId) {
		UserEntity userEntity = userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("Invalid userId to update"));
		userEntity.setAbout(userDto.getAbout());
		userEntity.setEmail(userDto.getEmail());
		userEntity.setName(userDto.getName());
		userEntity.setPassword(userDto.getPassword());
		UserEntity updatedUser = userRepo.save(userEntity);
		return modelMapper.map(updatedUser, UserDto.class);   //entityToDto(updatedUser);
	}

	@Override
	public void deleteUser(int userId) {
		UserEntity userEntity = userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("Invalid userId to delete"));
		userRepo.delete(userEntity);
	}

	@Override
	public UserDto getUser(int userId) {
		UserEntity userEntity = userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("Invalid userId to fetch"));
		return modelMapper.map(userEntity, UserDto.class); //entityToDto(userEntity);
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<UserEntity> users = userRepo.findAll();
		List<UserDto> returnUsers = users.stream().map(user ->  modelMapper.map(user, UserDto.class)).collect(Collectors.toList());
		return returnUsers;
	}

}
