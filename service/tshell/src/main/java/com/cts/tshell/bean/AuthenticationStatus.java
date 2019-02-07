package com.cts.tshell.bean;

public class AuthenticationStatus {

	private boolean authenticated;
	private boolean isAdmin;
	private User user; 


	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	private boolean checkEmail;
	private boolean checkUserId;
	private String message;
	
	public AuthenticationStatus() {
		super();
		// TODO Auto-generated constructor stub
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public AuthenticationStatus(boolean authenticated) {
		super();
		this.authenticated = authenticated;
	}

	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isAuthenticated() {
		return authenticated;
	}	
	
	
	
	public void setAuthenticated(boolean authenticated) {
		this.authenticated = authenticated;
	}

	

	public boolean isCheckEmail() {
		return checkEmail;
	}

	public void setCheckEmail(boolean checkEmail) {
		this.checkEmail = checkEmail;
	}

	public boolean isCheckUserId() {
		return checkUserId;
	}

	public void setCheckUserId(boolean checkUserId) {
		this.checkUserId = checkUserId;
	}

	@Override
	public String toString() {
		return "AuthenticationStatus [authenticated=" + authenticated + ", isAdmin=" + isAdmin + ", checkEmail="
				+ checkEmail + ", checkUserId=" + checkUserId + ", message=" + message + "]";
	}


}

