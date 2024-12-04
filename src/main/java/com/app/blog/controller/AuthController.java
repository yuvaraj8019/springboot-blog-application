package com.app.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.blog.models.JwtAuthRequest;
import com.app.blog.models.JwtAuthResponse;
import com.app.blog.models.UserDto;
import com.app.blog.security.JwtTokenHelper;
import com.app.blog.service.UserService;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

	@Autowired
	private JwtTokenHelper jwtTokenHelper;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserService userService;

	@PostMapping("/login")
	public ResponseEntity<JwtAuthResponse> createToken(@RequestBody JwtAuthRequest authRequest) {

		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
				authRequest.getUsername(), authRequest.getPassword());
		authenticationManager.authenticate(authenticationToken);
		UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());
		String token = jwtTokenHelper.generateToken(userDetails);

		return new ResponseEntity<JwtAuthResponse>(new JwtAuthResponse(token), HttpStatus.OK);

	}

	@PostMapping("/register")
	public ResponseEntity<UserDto> registerUser(@RequestBody UserDto userDto,
			@RequestParam(name="role", required = false, defaultValue = "dev") String userRoleString) {

		UserDto registeredUser = userService.registerUser(userDto, userRoleString);

		return new ResponseEntity<UserDto>(registeredUser, HttpStatus.OK);

	}

}
