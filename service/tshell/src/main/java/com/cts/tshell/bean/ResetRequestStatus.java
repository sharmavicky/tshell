package com.cts.tshell.bean;

public class ResetRequestStatus {
	private boolean userExists;
	private boolean mailSent;
	
	public boolean isUserExists() {
		return userExists;
	}
	public void setUserExists(boolean userExists) {
		this.userExists = userExists;
	}
	public boolean isMailSent() {
		return mailSent;
	}
	public void setMailSent(boolean mailSent) {
		this.mailSent = mailSent;
	}
	public ResetRequestStatus() {
		super();
	}
	@Override
	public String toString() {
		return "ResetRequestStatus [userExists=" + userExists + ", mailSent=" + mailSent + "]";
	}

	

}
