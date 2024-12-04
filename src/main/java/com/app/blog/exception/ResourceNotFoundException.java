package com.app.blog.exception;

public class ResourceNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1507937673098883089L;
	
	
	String message;


	public ResourceNotFoundException(String message) {
		super(message);
		this.message = message;
	}
}
