package com.isa.isa.security.exception;

// Custom izuzetak
public class ResourceConflictException extends RuntimeException {
	private static final long serialVersionUID = 1791564636123821405L;

	private String resourceUsername;

	public ResourceConflictException(String resourceUsername, String message) {
		super(message);
		this.setResourceId(resourceUsername);
	}

	public String getResourceId() {
		return resourceUsername;
	}

	public void setResourceId(String resourceUsername) {
		this.resourceUsername = resourceUsername;
	}

}
