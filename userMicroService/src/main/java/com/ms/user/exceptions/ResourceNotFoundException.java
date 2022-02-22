package com.ms.user.exceptions;

public class ResourceNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = -3311988202108022204L;
	
	public ResourceNotFoundException(String exception) {
		super(exception);
	}

}
