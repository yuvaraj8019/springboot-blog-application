package com.app.blog.handler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.app.blog.exception.ResourceNotFoundException;
import com.app.blog.models.BlogApiResponse;

@RestControllerAdvice
public class BlogExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<BlogApiResponse> handleResourceNotFoundException(ResourceNotFoundException exception) {
		
		BlogApiResponse response = new BlogApiResponse();
		response.setMessage(exception.getMessage());
		response.setStatus(false);
		
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
		
		Map<String,String> response = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach(e-> {
		String name = ((FieldError) e).getField();
		String msg = e.getDefaultMessage();
		response.put(name, msg);
		});
		
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);		
	}
}
