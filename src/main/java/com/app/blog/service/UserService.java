package com.app.blog.service;

import java.util.List;

import com.app.blog.models.UserDto;

public interface UserService {
	
	UserDto registerUser(UserDto userDto, String userRoleString);
	
	UserDto createUser(UserDto userDto);
	
	UserDto updateUser(UserDto userDto, int userId);
	
	void deleteUser(int userId);
	
	UserDto getUser(int userId);
	
	List<UserDto> getAllUsers();

}
