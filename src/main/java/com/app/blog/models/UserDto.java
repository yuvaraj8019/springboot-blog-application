package com.app.blog.models;

import java.util.HashSet;
import java.util.Set;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {

	private int userId;

	@NotEmpty
	@Size(min=4, message="Username should be min of 4 characters")
	private String name;

	@Email(message = "Email address is not valid !!")
	private String email;

	@NotEmpty
	@Size(min=4, max=10, message="Password should be min of 4 characters & max of 10 characters")
	private String password;

	@NotEmpty(message="About cannot be empty")
	private String about;
	
	private Set<RoleDto> roles = new HashSet<>();

}
