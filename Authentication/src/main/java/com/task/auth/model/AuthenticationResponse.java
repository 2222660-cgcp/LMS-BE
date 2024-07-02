package com.task.auth.model;

public class AuthenticationResponse {

	private String username;
	private boolean isValid;

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public boolean isValid() {
		return isValid;
	}
	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}
	public AuthenticationResponse() {
		super();
	}
	public AuthenticationResponse( String username, boolean isValid) {
		super();
		this.username = username;
		this.isValid = isValid;
	}
	
	@Override
	public String toString() {
		return "AuthenticationResponse [username=" + username + ", isValid=" + isValid + "]";
	}
	
	
}
